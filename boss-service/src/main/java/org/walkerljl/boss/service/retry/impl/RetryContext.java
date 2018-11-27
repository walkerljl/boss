package org.walkerljl.boss.service.retry.impl;

import org.walkerljl.boss.support.common.attribute.AttributeAccessor;
import org.walkerljl.boss.support.common.attribute.impl.DefaultAttributeAccessor;

/**
 * 重试上下文
 *
 * @author xingxun
 */
public class RetryContext extends DefaultAttributeAccessor implements AttributeAccessor {

    public static final String KEY_TASK = "context.task";
}