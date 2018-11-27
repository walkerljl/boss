package org.walkerljl.boss.service.task.impl.abstracts;

import org.walkerljl.boss.service.task.TaskHandler;
import org.walkerljl.boss.service.task.impl.TaskExecutionContext;
import org.walkerljl.boss.service.task.impl.handler.impl.TaskHandlerRepositoryFactory;
import org.walkerljl.toolkit.standard.resource.abstracts.AbstractResource;
import org.walkerljl.toolkit.standard.resource.exception.CannotDestroyResourceException;
import org.walkerljl.toolkit.standard.resource.exception.CannotInitResourceException;

/**
 * 抽象的任务处理器
 *
 * @author xingxun
 */
public abstract class AbstractTaskHandler extends AbstractResource implements TaskHandler {

    @Override
    public void handle(TaskExecutionContext context) {
        handle0(context);
    }

    @Override
    public void processInit() throws CannotInitResourceException {
        TaskHandlerRepositoryFactory.getDefaultRepository().register(getId(), this);
    }

    @Override
    public void processDestroy() throws CannotDestroyResourceException {
        TaskHandlerRepositoryFactory.getDefaultRepository().unregister(getId());
    }

    @Override
    public void handleInTransactionAfterRun(TaskExecutionContext context) {

    }

    /**
     * 处理业务逻辑
     *
     * @param context 上下文
     */
    protected abstract void handle0(TaskExecutionContext context);
}