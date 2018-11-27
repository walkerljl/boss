package org.walkerljl.boss.service.task.shell.dispatch;

/**
 * 监控任务Executor
 *
 * @author xingxun
 */
public interface TaskDispatchExecutor {

    /**
     * 执行任务
     *
     * @param taskId 任务ID
     */
    void execute(String taskId);
}