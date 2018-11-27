package org.walkerljl.boss.service.task.impl.util;

import java.util.Date;

import org.walkerljl.boss.service.task.impl.TaskExecutionConfig;
import org.walkerljl.boss.service.task.impl.TaskExecutionContext;
import org.walkerljl.boss.service.task.model.Task;
import org.walkerljl.boss.service.task.model.enums.status.TaskStatusEnum;
import org.walkerljl.boss.service.task.shell.dispatch.impl.DefaultSplitedTaskItem;
import org.walkerljl.boss.support.common.util.NumUtil;

/**
 * Retry util
 *
 * @author xingxun
 */
public class TaskUtil {

    /**
     * 构建任务执行上下文
     *
     * @param executionConfig
     * @param splitedTaskItem
     * @return
     */
    public static TaskExecutionContext buildExecutionContext(TaskExecutionConfig executionConfig, DefaultSplitedTaskItem splitedTaskItem) {
        if (executionConfig == null) {
            return null;
        }
        if (splitedTaskItem == null) {
            return null;
        }
        TaskExecutionContext context = new TaskExecutionContext();

        context.setAttribute(TaskExecutionContext.EXECUTION_CONFIG, executionConfig);
        context.setAttribute(TaskExecutionContext.TASK_BIZ_CODE, splitedTaskItem.getBizCode());
        context.setAttribute(TaskExecutionContext.TASK_BIZ_ID, splitedTaskItem.getBizId());
        context.setAttribute(TaskExecutionContext.TASK_ID, splitedTaskItem.getTaskId());
        context.setAttribute(TaskExecutionContext.TASK_STATUS, splitedTaskItem.getTaskStatus());
        context.setAttribute(TaskExecutionContext.TASK_TIME_OUT, executionConfig.getTaskExecutionTimeout());

        return context;
    }

    /**
     * 是否可以执行
     *
     * @param task
     * @param config
     * @return
     */
    public static boolean isCanExecute(Task task, TaskExecutionConfig config) {
        if (task == null) {
            return false;
        }
        TaskStatusEnum status = (TaskStatusEnum) task.getStatus();
        if (status == null) {
            return false;
        }
        //已经被执行的任务，不执行
        if (status == TaskStatusEnum.PROCESSED) {
            return false;
        }

        //被删除的任务，不执行
        if (status == TaskStatusEnum.DELETED) {
            return false;
        }
        //超过最大重试次数，不执行
        boolean isMaxAttemptsLimit = NumUtil.intValue(task.getAttempts()) >= NumUtil.intValue(task.getMaxAttempts());
        if (isMaxAttemptsLimit) {
            return false;
        }

        //没执行的任务，执行
        if (status == TaskStatusEnum.UNPROCESS) {
            return true;
        }
        //上次执行失败的任务，执行
        if (status == TaskStatusEnum.FAILURE) {
            if (task.getNextRetryTime() == null || System.currentTimeMillis() >= task.getNextRetryTime().getTime()) {
                return true;
            }
        }
        //任务正在运行中且超时
        if (status == TaskStatusEnum.PROCESSING) {
            Date lastRetryTime = task.getLastRetryTime();
            if (lastRetryTime == null) {
                return true;
            }
            //使用默认配置
            if (config == null) {
                config = new TaskExecutionConfig();
            }
            boolean isTimeout = System.currentTimeMillis() >= (lastRetryTime.getTime() + NumUtil.longValue(config.getTaskExecutionTimeout()) * 1000);
            boolean isCanExecute = isTimeout;
            return isCanExecute;
        }
        return false;
    }
}