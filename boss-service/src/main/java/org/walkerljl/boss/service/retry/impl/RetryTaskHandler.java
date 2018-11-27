package org.walkerljl.boss.service.retry.impl;

import org.walkerljl.boss.service.retry.RetryHandler;
import org.walkerljl.boss.service.retry.model.ModelConverter;
import org.walkerljl.boss.service.retry.model.RetryTask;
import org.walkerljl.boss.service.task.TaskHandler;
import org.walkerljl.boss.service.task.impl.TaskExecutionContext;
import org.walkerljl.boss.service.task.impl.abstracts.AbstractTaskHandler;
import org.walkerljl.boss.service.task.model.Task;

/**
 * 重试任务处理器
 *
 * @author xingxun
 */
public class RetryTaskHandler extends AbstractTaskHandler implements TaskHandler {

    @Override
    public void handle0(TaskExecutionContext context) {
        Task task = (Task) context.getAttribute(TaskExecutionContext.TASK);
        if (task == null) {
            return;
        }
        RetryTask retryTask = ModelConverter.toRetryTask(task);
        if (retryTask == null) {
            return;
        }
        RetryHandler retryHandler = RetryHandlerRepositoryFactory.getDefaultRepository().lookup(retryTask.getHandlerId());
        if (retryHandler == null) {
            return;
        }

        RetryContext retryContext = new RetryContext();
        retryHandler.handle(retryContext);
    }
}