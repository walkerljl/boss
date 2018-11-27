package org.walkerljl.boss.service.retry.listener;

import org.walkerljl.boss.service.retry.RetryListener;
import org.walkerljl.toolkit.standard.repository.ObjectRepository;

/**
 * 重试监听器仓储
 *
 * @author xingxun
 */
public interface RetryListenerRepository extends ObjectRepository<String, RetryListener> {

}