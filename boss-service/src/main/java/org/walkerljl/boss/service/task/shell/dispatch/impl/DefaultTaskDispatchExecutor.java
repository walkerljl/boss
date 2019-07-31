package org.walkerljl.boss.service.task.shell.dispatch.impl;

import org.walkerljl.boss.service.task.TaskService;
import org.walkerljl.boss.service.task.impl.RunnableTask;
import org.walkerljl.boss.service.task.impl.TaskExecutionConfig;
import org.walkerljl.boss.service.task.impl.TaskExecutionContext;
import org.walkerljl.boss.service.task.impl.TaskLoggerNames;
import org.walkerljl.boss.service.task.impl.executor.TaskExecutor;
import org.walkerljl.boss.service.task.impl.executor.TaskExecutorConfig;
import org.walkerljl.boss.service.task.impl.executor.TaskExecutorRepository;
import org.walkerljl.boss.service.task.impl.impl.DefaultRunnableTask;
import org.walkerljl.boss.service.task.impl.util.TaskUtil;
import org.walkerljl.boss.service.task.model.enums.status.TaskStatusEnum;
import org.walkerljl.boss.service.task.shell.dispatch.TaskDispatchExecutor;
import org.walkerljl.boss.service.task.shell.dispatch.exception.TaskExecutionException;
import org.walkerljl.toolkit.template.log.Logger;
import org.walkerljl.toolkit.template.log.LoggerFactory;
import org.walkerljl.toolkit.template.log.model.InvocationInfo;
import org.walkerljl.toolkit.template.log.util.LoggerDetailUtil;
import org.walkerljl.toolkit.template.log.util.LoggerDigestUtil;

/**
 * DefaultTaskDispatchExecutor
 *
 * @author xingxun
 */
public class DefaultTaskDispatchExecutor implements TaskDispatchExecutor {

    private static final Logger DIGEST_LOGGER = LoggerFactory.getLogger(TaskLoggerNames.TASK_DISPATCH_EXECUTE_DIGEST);
    private static final Logger DETAIL_LOGGER = LoggerFactory.getLogger(TaskLoggerNames.TASK_DISPATCH_EXECUTE_DETAIL);

    private TaskService         taskService;
    private TaskExecutionConfig executionConfig;

    public DefaultTaskDispatchExecutor(TaskService taskService, TaskExecutionConfig executionConfig) {
        this.taskService = taskService;
        this.executionConfig = executionConfig;
    }

    @Override
    public void execute(String taskId) {
        InvocationInfo<String, Void> invocationInfo =
                new InvocationInfo<>(getClass(), "execute", taskId);

        try {
            //任务信息解析
            DefaultSplitedTaskItem splitedTaskItem = DefaultSplitedTaskItem.parse(taskId);
            if (splitedTaskItem == null) {
                return;
            }
            TaskStatusEnum taskStatus = (TaskStatusEnum) TaskStatusEnum.values()[0].getEnumObject(splitedTaskItem.getTaskStatus());

            //构建执行器配置
            TaskExecutorConfig executorConfig = executionConfig.getExecutorConfig(taskStatus);

            //查询任务执行器
            TaskExecutor executor = TaskExecutorRepository.getInstance().lookup(executorConfig);
            if (executor == null) {
                return;
            }

            //构建任务执行上下文
            TaskExecutionContext context = TaskUtil.buildExecutionContext(executionConfig, splitedTaskItem);

            //构建可执行的任务
            RunnableTask runnableTask = new DefaultRunnableTask(context, taskService);

            //任务执行
            executor.execute(runnableTask);
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            throw new TaskExecutionException(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }
}