package org.walkerljl.boss.service.task.impl.listener.impl;

import org.walkerljl.boss.service.task.impl.listener.TaskListenerRepository;

/**
 * TaskListenerRepositoryFactory
 *
 * @author xingxun
 */
public class TaskListenerRepositoryFactory {

    public static TaskListenerRepository getDefaultRepository() {
        return TaskListenerRepositoryFactory.FactoryHolder.defaultRepository;
    }

    private static class FactoryHolder {
        private static TaskListenerRepository defaultRepository = new DefaultTaskListenerRepository();
    }
}