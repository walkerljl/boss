package org.walkerljl.boss.service.task.impl.handler.impl;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.task.BaseTaskUnitTest;
import org.walkerljl.boss.service.task.impl.handler.TaskHandlerRepository;

/**
 * TaskHandlerRepositoryFactoryTest
 *
 * @author xingxun
 */
public class TaskHandlerRepositoryFactoryTest extends BaseTaskUnitTest {

    @Test
    public void getDefaultRepository() {

        TaskHandlerRepository expected = TaskHandlerRepositoryFactory.getDefaultRepository();
        TaskHandlerRepository actual = TaskHandlerRepositoryFactory.getDefaultRepository();

        Assert.assertTrue(actual instanceof DefaultTaskHandlerRepository);
        Assert.assertEquals(expected, actual);
    }

}