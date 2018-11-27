package org.walkerljl.boss.service.retry.listener.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.walkerljl.boss.service.retry.RetryListener;
import org.walkerljl.boss.service.retry.listener.RetryListenerRepository;
import org.walkerljl.boss.service.task.TaskListener;
import org.walkerljl.boss.service.task.impl.listener.impl.TaskListenerRepositoryFactory;

/**
 * DefaultRetryListenerRepository
 *
 * @author xingxun
 */
public class DefaultRetryListenerRepository implements RetryListenerRepository {

    @Override
    public void register(String id, RetryListener retryListener) {
        TaskListenerRepositoryFactory.getDefaultRepository().register(id, retryListener);
    }

    @Override
    public void unregister(String id) {
        TaskListenerRepositoryFactory.getDefaultRepository().unregister(id);
    }

    @Override
    public RetryListener lookup(String id) {
        return (RetryListener) TaskListenerRepositoryFactory.getDefaultRepository().lookup(id);
    }

    @Override
    public Collection<? extends RetryListener> lookupAll() {
        Collection<TaskListener> taskListeners = (Collection<TaskListener>) TaskListenerRepositoryFactory.getDefaultRepository()
                .lookupAll();
        if (taskListeners == null) {
            return null;
        }
        Collection<RetryListener> retryListeners = new ArrayList<>(taskListeners.size());
        for (TaskListener taskListener : taskListeners) {
            retryListeners.add((RetryListener) taskListener);
        }
        return retryListeners;
    }
}