package org.walkerljl.boss.service.retry.impl;

/**
 * RetryHandlerRepositoryFactory
 *
 * @author xingxun
 */
public class RetryHandlerRepositoryFactory {

    public static RetryHandlerRepository getDefaultRepository() {
        return RetryHandlerRepositoryFactory.FactoryHolder.defaultRepository;
    }

    private static class FactoryHolder {
        private static RetryHandlerRepository defaultRepository = new DefaultRetryHandlerRepository();
    }
}