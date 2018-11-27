package org.walkerljl.boss.service.task.impl.executor;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.task.BaseTaskUnitTest;

/**
 * TaskExecutorConfigTest
 *
 * @author xingxun
 */
public class TaskExecutorConfigTest extends BaseTaskUnitTest {

    /** ThreadPoolExecutor config:workQueueSize*/
    private       int    workQueueSize = 1000;
    /**  ThreadPoolExecutor config:keepAliveTime */
    private       int    keepAliveTime = 60;
    /** ThreadPoolExecutor config:corePoolSize */
    private final int    corePoolSize  = 5;
    /** ThreadPoolExecutor config:maxPoolSize */
    private       int    maxPoolSize   = 10;

    @Test
    public void test() {

        TaskExecutorConfig actual = new TaskExecutorConfig();
        Assert.assertEquals(TaskExecutorConfig.DEFAULT_EXECUTOR_ID, actual.getId());
        Assert.assertEquals(TaskExecutorConfig.DEFAULT_EXECUTOR_NAME, actual.getName());
        Assert.assertEquals(corePoolSize, actual.getCorePoolSize());
        Assert.assertEquals(maxPoolSize, actual.getMaxPoolSize());
        Assert.assertEquals(workQueueSize, actual.getWorkQueueSize());
        Assert.assertEquals(keepAliveTime, actual.getKeepAliveTime());
    }
}