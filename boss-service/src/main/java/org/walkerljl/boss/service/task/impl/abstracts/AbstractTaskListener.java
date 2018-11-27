package org.walkerljl.boss.service.task.impl.abstracts;

import org.walkerljl.boss.service.task.TaskListener;
import org.walkerljl.boss.service.task.impl.listener.impl.TaskListenerRepositoryFactory;
import org.walkerljl.toolkit.standard.resource.abstracts.AbstractResource;
import org.walkerljl.toolkit.standard.resource.exception.CannotDestroyResourceException;
import org.walkerljl.toolkit.standard.resource.exception.CannotInitResourceException;

/**
 * 抽象的任务监听器
 *
 * @author xingxun
 */
public abstract class AbstractTaskListener extends AbstractResource implements TaskListener {

    @Override
    public void processInit() throws CannotInitResourceException {
        TaskListenerRepositoryFactory.getDefaultRepository().register(getId(), this);
    }

    @Override
    public void processDestroy() throws CannotDestroyResourceException {
        TaskListenerRepositoryFactory.getDefaultRepository().unregister(getId());
    }
}