package org.walkerljl.boss.service.task.impl;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.task.BaseTaskUnitTest;

/**
 * TaskExecutionContextTest
 *
 * @author xingxun
 */
public class TaskExecutionContextTest extends BaseTaskUnitTest {

    public static final String EXECUTION_CONFIG                = "context.execution.config";

    private static final String TASK                = "context.task";
    private static final String TASK_BIZ_CODE       = "context.task.bizcode";
    private static final String TASK_BIZ_ID         = "context.task.bizid";
    private static final String TASK_ID             = "context.task.id";
    private static final String TASK_STATUS         = "context.task.status";
    private static final String TASK_TIME_OUT = "context.timeout";

    private static final String EXECUTION_RESULT_THROABLE = "context.execution.result.throable";

    @Test
    public void test() {

        Assert.assertEquals(EXECUTION_CONFIG, TaskExecutionContext.EXECUTION_CONFIG);

        Assert.assertEquals(TASK, TaskExecutionContext.TASK);
        Assert.assertEquals(TASK_BIZ_CODE, TaskExecutionContext.TASK_BIZ_CODE);
        Assert.assertEquals(TASK_BIZ_ID, TaskExecutionContext.TASK_BIZ_ID);
        Assert.assertEquals(TASK_ID, TaskExecutionContext.TASK_ID);
        Assert.assertEquals(TASK_STATUS, TaskExecutionContext.TASK_STATUS);
        Assert.assertEquals(TASK_TIME_OUT, TaskExecutionContext.TASK_TIME_OUT);

        Assert.assertEquals(EXECUTION_RESULT_THROABLE, TaskExecutionContext.EXECUTION_RESULT_THROABLE);

    }
}