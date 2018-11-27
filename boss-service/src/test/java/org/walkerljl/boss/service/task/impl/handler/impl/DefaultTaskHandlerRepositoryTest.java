package org.walkerljl.boss.service.task.impl.handler.impl;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.task.BaseTaskUnitTest;
import org.walkerljl.boss.service.task.TaskHandler;
import org.walkerljl.boss.service.task.impl.TaskExecutionContext;
import org.walkerljl.boss.service.task.impl.abstracts.AbstractTaskHandler;
import org.walkerljl.boss.service.task.impl.handler.TaskHandlerRepository;
import org.walkerljl.toolkit.standard.repository.exception.ObjectRepositoryException;

/**
 * DefaultTaskHandlerRepositoryTest
 *
 * @author xingxun
 */
public class DefaultTaskHandlerRepositoryTest extends BaseTaskUnitTest {

    @Test
    public void test() {

        String expectedObjectId = "objectId";

        TaskHandlerRepository objectRepository = new DefaultTaskHandlerRepository();

        objectRepository.lookup("");
        objectRepository.lookup(null);

        TaskHandler expected = new AbstractTaskHandler() {

            @Override
            protected void handle0(TaskExecutionContext context) {

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

        TaskHandler actual = objectRepository.lookup(expectedObjectId);
        Assert.assertNull(actual);

        objectRepository.register(expectedObjectId, expected);
        actual = objectRepository.lookup(expectedObjectId);
        Assert.assertEquals(actual, expected);

        Collection<TaskHandler> actuals = (Collection<TaskHandler>)objectRepository.lookupAll();
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