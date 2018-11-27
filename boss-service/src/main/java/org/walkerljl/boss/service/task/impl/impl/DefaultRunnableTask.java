package org.walkerljl.boss.service.task.impl.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.walkerljl.boss.common.util.ThrowableUtil;
import org.walkerljl.boss.service.task.TaskHandler;
import org.walkerljl.boss.service.task.TaskListener;
import org.walkerljl.boss.service.task.TaskService;
import org.walkerljl.boss.service.task.exception.TaskException;
import org.walkerljl.boss.service.task.impl.RunnableTask;
import org.walkerljl.boss.service.task.impl.TaskExecutionContext;
import org.walkerljl.boss.service.task.impl.TaskLoggerNames;
import org.walkerljl.boss.service.task.impl.handler.impl.TaskHandlerRepositoryFactory;
import org.walkerljl.boss.service.task.impl.listener.impl.TaskListenerRepositoryFactory;
import org.walkerljl.boss.service.task.impl.listener.impl.util.TaskListenerUtil;
import org.walkerljl.boss.service.task.impl.util.TaskExecutionIntervalCalculator;
import org.walkerljl.boss.service.task.impl.util.TaskLogBuilder;
import org.walkerljl.boss.service.task.model.Task;
import org.walkerljl.boss.service.task.model.TaskLog;
import org.walkerljl.boss.service.task.model.TaskParam;
import org.walkerljl.boss.service.task.shell.dispatch.exception.TaskExecutionException;
import org.walkerljl.toolkit.template.handle.service.ServiceAssertUtil;
import org.walkerljl.toolkit.template.log.Logger;
import org.walkerljl.toolkit.template.log.LoggerFactory;
import org.walkerljl.toolkit.template.log.model.InvocationInfo;
import org.walkerljl.toolkit.template.log.util.LoggerDetailUtil;
import org.walkerljl.toolkit.template.log.util.LoggerDigestUtil;

/**
 *  默认可执行的任务
 *
 * @author xingxun
 */
public class DefaultRunnableTask implements RunnableTask {

    private static final Logger DIGEST_LOGGER = LoggerFactory.getLogger(TaskLoggerNames.EXECUTE_DIGEST);
    private static final Logger DETAIL_LOGGER = LoggerFactory.getLogger(TaskLoggerNames.EXECUTE_DETAIL);

    /** 上下文*/
    private TaskExecutionContext context;
    /** 任务业务接口*/
    private TaskService          taskService;
    /** 数据源*/
    private DataSource dataSource;

    public DefaultRunnableTask(TaskExecutionContext context, TaskService taskService, DataSource dataSource) {
        this.context = context;
        this.taskService = taskService;
        this.dataSource = dataSource;
    }

    @Override
    public void run() {
        List<TaskListener> listeners = null;
        Task task = null;
        InvocationInfo<Task, TaskExecutionContext> invocationInfo =
                new InvocationInfo<>(getClass(), "run", task);

        try {
            String bizCode = String.valueOf(context.getAttribute(TaskExecutionContext.TASK_BIZ_CODE));
            String bizId = String.valueOf(context.getAttribute(TaskExecutionContext.TASK_BIZ_ID));
            String taskId = String.valueOf(context.getAttribute(TaskExecutionContext.TASK_ID));

            //加锁
            Long timeout = Long.valueOf(String.valueOf(context.getAttribute(TaskExecutionContext.TASK_TIME_OUT)));
            boolean isLocked = taskService.lockTask(bizCode, bizId, taskId, timeout);
            if (!isLocked) {
                throw new TaskException("Failure to lock the monitor data.");
            }

            //Check 状态
            task = taskService.getTask(bizCode, bizId, taskId);
            //boolean isCanExecute = TaskUtil.isCanExecute(task,
            //        (TaskExecutionConfig) context.getAttribute(TaskExecutionContext.EXECUTION_CONFIG));
            //if (!isCanExecute) {
            //    TaskListenerUtil.doOnAbortInterceptors(listeners, context);
            //    throw new TaskException("It is not time to execute.");
            //}

            //填充执行参数
            List<TaskParam> taskParams = taskService.listTaskParams(bizCode, bizId, taskId);
            task.setParams(taskParams);
            context.setAttribute(TaskExecutionContext.TASK, task);

            //获取所有监听器
            listeners = new ArrayList<>(TaskListenerRepositoryFactory.getDefaultRepository().lookupAll());

            //执行拦截器-执行中
            TaskListenerUtil.doOnRunningInterceptors(listeners, context);

            //执行任务
            handleTask(context, task.getHandlerId());

            //执行拦截器-成功执行
            TaskListenerUtil.doOnCompletedInterceptors(listeners, context);

            invocationInfo.markSuccess(context);
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            context.setAttribute(TaskExecutionContext.EXECUTION_RESULT_THROABLE, e);
            //执行拦截器-执行错误
            TaskListenerUtil.doOnErrorInterceptors(listeners, context);
            throw new TaskException(e);
        } finally {
            //解锁任务并记录日志
            try {
                unlockAndRecordLog(context, task, invocationInfo);
            } catch (Throwable e) {
                invocationInfo.markFailure(e);
            }
            //打印日志
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }

    private void handleTask(TaskExecutionContext context, String handlerId) {
        TaskHandler handler = TaskHandlerRepositoryFactory.getDefaultRepository()
                .lookup(handlerId);
        if (handler == null) {
            return;
        }
        context.setAttribute(TaskExecutionContext.TASK_HANDLER, handler);
        handler.handle(context);
    }

    private void unlockAndRecordLog(TaskExecutionContext context, Task task, InvocationInfo invocationInfo) throws SQLException {
        Date nextRetryTime = TaskExecutionIntervalCalculator.calculateNextRetryTime(task.getRetryRule(), task.getAttempts() + 1);
        TaskLog taskLog = null;
        String errorMsg = null;
        if (invocationInfo.isSuccess()) {
            taskLog = TaskLogBuilder.buildSuccessTaskLog(task);
        } else {
            errorMsg = ThrowableUtil.getMessage(invocationInfo.getThrowable());
            taskLog = TaskLogBuilder.buildFailureTaskLog(task, errorMsg);
        }

        Connection connection = dataSource.getConnection();
        ServiceAssertUtil.assertParam(connection != null, "connection");
        try {
            //开启事务
            connection.setAutoCommit(false);

            //业务回调
            TaskHandler taskHandler = (TaskHandler) context.getAttribute(TaskExecutionContext.TASK_HANDLER);
            if (taskHandler != null) {
                taskHandler.handleInTransactionAfterRun(context);
            }

            //解锁监控数据
            if (invocationInfo.isSuccess()) {
                taskService.markTaskExecutedSuccess(task.getBizCode(), task.getBizId(), task.getId());
            } else {
                taskService.markTaskExecutedFailure(task.getBizCode(), task.getBizId(),
                        task.getId(), nextRetryTime, errorMsg);
            }
            //记录任务执行日志
            taskService.saveTaskLog(taskLog);
        } catch (Throwable e) {
            //回滚事务
            connection.rollback();
            throw new TaskExecutionException(e);
        }
        //提交事务
        connection.commit();
    }
}