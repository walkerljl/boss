package org.walkerljl.boss.service.task.impl;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.task.BaseTaskUnitTest;

/**
 * TaskLoggerNamesTest
 *
 * @author xingxun
 */
public class TaskLoggerNamesTest extends BaseTaskUnitTest {

    /**DEFAULT*/
    private static final String DEFAULT = "TASK_DEFAULT";

    /** 服务接入*/
    private static final String SAL_DIGEST = "TASK_SAL_DIGEST";
    private static final String SAL_DETAIL = "TASK_SAL_DETAIL";

    /*** BROKER*/
    private static final String BROKER_DIGEST = "TASK_BROKER_DIGEST";
    private static final String BROKER_DETAIL = "TASK_BROKER_DETAIL";

    /** 任务分发-拆分*/
    private static final String TASK_DISPATCH_SPLIT_DIGEST = "TASK_DISPATCH_SPLIT_DIGEST";
    private static final String TASK_DISPATCH_SPLIT_DETAIL = "TASK_DISPATCH_SPLIT_DETAIL";

    /** 任务分发-加载*/
    private static final String TASK_DISPATCH_LOAD_DIGEST = "TASK_DISPATCH_LOAD_DIGEST";
    private static final String TASK_DISPATCH_LOAD_DETAIL = "TASK_DISPATCH_LOAD_DETAIL";

    /** 任务分发-执行*/
    private static final String TASK_DISPATCH_EXECUTE_DIGEST = "TASK_DISPATCH_EXECUTE_DIGEST";
    private static final String TASK_DISPATCH_EXECUTE_DETAIL = "TASK_DISPATCH_EXECUTE_DETAIL";

    /*** 任务执行*/
    private static final String EXECUTE_DIGEST = "TASK_EXECUTE_DIGEST";
    private static final String EXECUTE_DETAIL = "TASK_EXECUTE_DETAIL";

    /** 预警*/
    private static final String ALARMER_DIGEST = "TASK_ALARMER_DIGEST";
    private static final String ALARMER_DETAIL = "TASK_ALARMER_DETAIL";

    @Test
    public void test() {

        Assert.assertEquals(DEFAULT, TaskLoggerNames.DEFAULT);

        Assert.assertEquals(SAL_DIGEST, TaskLoggerNames.SAL_DIGEST);
        Assert.assertEquals(SAL_DETAIL, TaskLoggerNames.SAL_DETAIL);

        Assert.assertEquals(BROKER_DIGEST, TaskLoggerNames.BROKER_DIGEST);
        Assert.assertEquals(BROKER_DETAIL, TaskLoggerNames.BROKER_DETAIL);

        Assert.assertEquals(TASK_DISPATCH_SPLIT_DIGEST, TaskLoggerNames.TASK_DISPATCH_SPLIT_DIGEST);
        Assert.assertEquals(TASK_DISPATCH_SPLIT_DETAIL, TaskLoggerNames.TASK_DISPATCH_SPLIT_DETAIL);

        Assert.assertEquals(TASK_DISPATCH_LOAD_DIGEST, TaskLoggerNames.TASK_DISPATCH_LOAD_DIGEST);
        Assert.assertEquals(TASK_DISPATCH_LOAD_DETAIL, TaskLoggerNames.TASK_DISPATCH_LOAD_DETAIL);

        Assert.assertEquals(TASK_DISPATCH_EXECUTE_DIGEST, TaskLoggerNames.TASK_DISPATCH_EXECUTE_DIGEST);
        Assert.assertEquals(TASK_DISPATCH_EXECUTE_DETAIL, TaskLoggerNames.TASK_DISPATCH_EXECUTE_DETAIL);

        Assert.assertEquals(EXECUTE_DIGEST, TaskLoggerNames.EXECUTE_DIGEST);
        Assert.assertEquals(EXECUTE_DETAIL, TaskLoggerNames.EXECUTE_DETAIL);

        Assert.assertEquals(ALARMER_DIGEST, TaskLoggerNames.ALARMER_DIGEST);
        Assert.assertEquals(ALARMER_DETAIL, TaskLoggerNames.ALARMER_DETAIL);

    }
}