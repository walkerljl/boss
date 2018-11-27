package org.walkerljl.boss.service.task.impl.handler.impl;

import org.walkerljl.boss.service.task.impl.handler.TaskHandlerRepository;

/**
 * RetryHandlerRepositoryFactory
 *
 * @author xingxun
 */
public class TaskHandlerRepositoryFactory {

    public static TaskHandlerRepository getDefaultRepository() {
        return TaskHandlerRepositoryFactory.FactoryHolder.defaultRepository;
    }

    private static class FactoryHolder {
        private static TaskHandlerRepository defaultRepository = new DefaultTaskHandlerRepository();
    }
}