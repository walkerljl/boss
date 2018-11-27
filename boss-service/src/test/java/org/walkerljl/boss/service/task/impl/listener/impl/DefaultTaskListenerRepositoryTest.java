package org.walkerljl.boss.service.task.impl.listener.impl;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.task.BaseTaskUnitTest;
import org.walkerljl.boss.service.task.TaskListener;
import org.walkerljl.boss.service.task.impl.TaskExecutionContext;
import org.walkerljl.boss.service.task.impl.abstracts.AbstractTaskListener;
import org.walkerljl.boss.service.task.impl.listener.TaskListenerRepository;
import org.walkerljl.toolkit.standard.repository.exception.ObjectRepositoryException;

/**
 * DefaultTaskListenerRepositoryTest
 *
 * @author xingxun
 */
public class DefaultTaskListenerRepositoryTest extends BaseTaskUnitTest {

    @Test
    public void test() {

        String expectedObjectId = "objectId";

        TaskListenerRepository objectRepository = new DefaultTaskListenerRepository();

        objectRepository.lookup("");
        objectRepository.lookup(null);

        TaskListener expected = new AbstractTaskListener() {
            @Override
            public void onRunning(TaskExecutionContext context) {

            }

            @Override
            public void onCompleted(TaskExecutionContext context) {

            }

            @Override
            public void onError(TaskExecutionContext context) {

            }

            @Override
            public void onAbort(TaskExecutionContext context) {

            }
        };
        boolean actualFlag = false;

        //register
        try {
            objectRepository.register("", null);
        } catch (ObjectRepositoryException e) {
            actualFlag = true;
        }
        Assert.assertTrue(actualFlag);
        actualFlag = false;
        try {
            objectRepository.register(null, null);
        } catch (ObjectRepositoryException e) {
            actualFlag = true;
        }
        Assert.assertTrue(actualFlag);
        actualFlag = false;
        try {
            objectRepository.register(expectedObjectId, null);
        } catch (ObjectRepositoryException e) {
            actualFlag = true;
        }
        Assert.assertTrue(actualFlag);

        //unregister
        objectRepository.unregister("");
        objectRepository.unregister(null);

        TaskListener actual = objectRepository.lookup(expectedObjectId);
        Assert.assertNull(actual);

        objectRepository.register(expectedObjectId, expected);
        actual = objectRepository.lookup(expectedObjectId);
        Assert.assertEquals(actual, expected);

        Collection<TaskListener> actuals = (Collection<TaskListener>)objectRepository.lookupAll();
        Assert.assertEquals(actuals.size(), 1);
        Assert.assertEquals(actuals.iterator().next(), expected);
        try {
            actuals.add(expected);
        } catch (UnsupportedOperationException e) {
            actualFlag = true;
        }
        Assert.assertTrue(actualFlag);

        objectRepository.unregister(expectedObjectId);
        actual = objectRepository.lookup(expectedObjectId);
        Assert.assertNull(actual);
    }
}