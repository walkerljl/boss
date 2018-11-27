package org.walkerljl.boss.service.task.impl.abstracts;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.task.BaseTaskUnitTest;
import org.walkerljl.boss.service.task.TaskListener;
import org.walkerljl.boss.service.task.impl.TaskExecutionContext;
import org.walkerljl.boss.service.task.impl.listener.impl.TaskListenerRepositoryFactory;

/**
 * AbstractTaskListenerTest
 *
 * @author xingxun
 */
public class AbstractTaskListenerTest extends BaseTaskUnitTest {

    @Test
    public void processInit() {
        TaskListener expected = new AbstractTaskListenerTestHandler();
        expected.init();

        TaskListener actual = TaskListenerRepositoryFactory.getDefaultRepository().lookup(expected.getId());
        Assert.assertEquals(expected, actual);

        expected.destroy();
    }

    @Test
    public void processDestroy() {
        TaskListener expected = new AbstractTaskListenerTestHandler();
        expected.init();
        expected.destroy();

        TaskListener actual = TaskListenerRepositoryFactory.getDefaultRepository().lookup(expected.getId());
        Assert.assertNull(actual);
    }

}

class AbstractTaskListenerTestHandler extends AbstractTaskListener implements TaskListener {

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
}