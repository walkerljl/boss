package org.walkerljl.boss.service.task;

import org.walkerljl.boss.service.task.impl.TaskExecutionContext;
import org.walkerljl.toolkit.standard.resource.Resource;

/**
 * 任务处理器
 *
 * @author xingxun
 */
public interface TaskHandler extends Resource {

    /**
     * 任务处理
     *
     * @param context 任务执行上下文
     */
    void handle(TaskExecutionContext context);

    /**
     * 任务执行完成之后需要处理的业务逻辑
     *
     * @param context 任务执行上下文
     */
    void handleInTransactionAfterRun(TaskExecutionContext context);
}