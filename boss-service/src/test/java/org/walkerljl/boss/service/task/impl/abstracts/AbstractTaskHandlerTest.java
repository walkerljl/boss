package org.walkerljl.boss.service.task.impl.abstracts;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.task.BaseTaskUnitTest;
import org.walkerljl.boss.service.task.TaskHandler;
import org.walkerljl.boss.service.task.impl.TaskExecutionContext;
import org.walkerljl.boss.service.task.impl.handler.impl.TaskHandlerRepositoryFactory;

/**
 * AbstractTaskHandlerTest
 *
 * @author xingxun
 */
public class AbstractTaskHandlerTest extends BaseTaskUnitTest {

    @Test
    public void processInit() {
        TaskHandler expected = new AbstractTaskHandlerTestHandler();
        expected.init();

        TaskHandler actual = TaskHandlerRepositoryFactory.getDefaultRepository().lookup(expected.getId());
        Assert.assertEquals(expected, actual);

        expected.destroy();
    }

    @Test
    public void processDestroy() {
        TaskHandler expected = new AbstractTaskHandlerTestHandler();
        expected.init();
        expected.destroy();

        TaskHandler actual = TaskHandlerRepositoryFactory.getDefaultRepository().lookup(expected.getId());
        Assert.assertNull(actual);
    }
}

class AbstractTaskHandlerTestHandler extends AbstractTaskHandler implements TaskHandler {

    @Override
    protected void handle0(TaskExecutionContext context) {

    }
}
