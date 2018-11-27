package org.walkerljl.boss.service.retry;

import org.walkerljl.boss.service.retry.impl.RetryContext;
import org.walkerljl.toolkit.standard.resource.Resource;

/**
 * 重试处理器
 *
 * @author xingxun
 */
public interface RetryHandler extends Resource {

    /**
     * 重试
     *
     * @param context 重试上下文
     */
    void handle(RetryContext context);
}