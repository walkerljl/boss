package org.walkerljl.boss.service.retry.impl;

import org.walkerljl.boss.service.retry.RetryHandler;
import org.walkerljl.toolkit.standard.repository.ObjectRepository;

/**
 * 重试处理器仓储
 *
 * @author xingxun
 */
public interface RetryHandlerRepository extends ObjectRepository<String, RetryHandler> {

}