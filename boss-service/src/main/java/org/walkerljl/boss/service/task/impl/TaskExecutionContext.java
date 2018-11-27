package org.walkerljl.boss.service.task.impl;

import org.walkerljl.boss.support.common.attribute.AttributeAccessor;
import org.walkerljl.boss.support.common.attribute.impl.DefaultAttributeAccessor;

/**
 * 任务执行上下文
 *
 * @author xingxun
 */
public class TaskExecutionContext extends DefaultAttributeAccessor implements AttributeAccessor {

    public static final String EXECUTION_CONFIG                = "context.execution.config";

    public static final String TASK                = "context.task";
    public static final String TASK_BIZ_CODE       = "context.task.bizcode";
    public static final String TASK_BIZ_ID         = "context.task.bizid";
    public static final String TASK_ID             = "context.task.id";
    public static final String TASK_STATUS         = "context.task.status";
    public static final String TASK_HANDLER = "context.task.handler";
    public static final String TASK_TIME_OUT = "context.timeout";

    public static final String EXECUTION_RESULT_THROABLE = "context.execution.result.throable";
}