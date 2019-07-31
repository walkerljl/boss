package org.walkerljl.boss.service.task.impl.executor.impl;

import org.junit.Test;
import org.walkerljl.boss.service.task.BaseTaskUnitTest;
import org.walkerljl.boss.service.task.impl.RunnableTask;
import org.walkerljl.boss.service.task.impl.executor.TaskExecutor;
import org.walkerljl.boss.service.task.impl.executor.TaskExecutorConfig;
import org.walkerljl.boss.service.task.impl.impl.DefaultRunnableTask;

/**
 * DefaultTaskExecutorTest
 *
 * @author xingxun
 */
public class DefaultTaskExecutorTest extends BaseTaskUnitTest {

    @Test
    public void execute() {

        TaskExecutorConfig taskExecutorConfig = new TaskExecutorConfig();
        TaskExecutor taskExecutor = new DefaultTaskExecutor(taskExecutorConfig);

        taskExecutor.execute(null);

        RunnableTask task = new DefaultRunnableTask(null, null);
        taskExecutor.execute(task);

        taskExecutor.start();
        taskExecutor.execute(task);

        taskExecutor.stop();
    }
}