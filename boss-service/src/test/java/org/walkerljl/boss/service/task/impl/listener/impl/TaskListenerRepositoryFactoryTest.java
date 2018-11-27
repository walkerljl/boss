package org.walkerljl.boss.service.task.impl.listener.impl;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.task.BaseTaskUnitTest;
import org.walkerljl.boss.service.task.impl.listener.TaskListenerRepository;

/**
 * TaskListenerRepositoryFactoryTest
 *
 * @author xingxun
 */
public class TaskListenerRepositoryFactoryTest extends BaseTaskUnitTest {

    @Test
    public void getDefaultRepository() {

        TaskListenerRepository expected = TaskListenerRepositoryFactory.getDefaultRepository();
        TaskListenerRepository actual = TaskListenerRepositoryFactory.getDefaultRepository();

        Assert.assertTrue(actual instanceof DefaultTaskListenerRepository);
        Assert.assertEquals(expected, actual);
    }

}