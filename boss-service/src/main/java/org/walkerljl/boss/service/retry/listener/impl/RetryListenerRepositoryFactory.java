package org.walkerljl.boss.service.retry.listener.impl;

import org.walkerljl.boss.service.retry.listener.RetryListenerRepository;

/**
 * RetryListenerRepositoryFactory
 *
 * @author xingxun
 */
public class RetryListenerRepositoryFactory {

    public static RetryListenerRepository getDefaultRepository() {
        return RetryListenerRepositoryFactory.FactoryHolder.defaultRepository;
    }

    private static class FactoryHolder {
        private static RetryListenerRepository defaultRepository = new DefaultRetryListenerRepository();
    }
}