package org.walkerljl.boss.service.task.impl.listener.impl;

import org.junit.Test;
import org.walkerljl.boss.service.task.BaseTaskUnitTest;
import org.walkerljl.boss.service.task.TaskListener;
import org.walkerljl.boss.service.task.impl.TaskExecutionContext;

/**
 * TaskExecutionListenerTest
 *
 * @author xingxun
 */
public class TaskExecutionListenerTest extends BaseTaskUnitTest {

    @Test
    public void onRunning() {
        TaskListener taskListener = new TaskExecutionListener();
        taskListener.onRunning(null);

        TaskExecutionContext context = new TaskExecutionContext();
        taskListener.onRunning(context);
    }

    @Test
    public void onCompleted() {
        TaskListener taskListener = new TaskExecutionListener();
        taskListener.onCompleted(null);

        TaskExecutionContext context = new TaskExecutionContext();
        taskListener.onCompleted(context);
    }

    @Test
    public void onError() {
        TaskListener taskListener = new TaskExecutionListener();
        taskListener.onError(null);

        TaskExecutionContext context = new TaskExecutionContext();
        taskListener.onError(context);
    }

    @Test
    public void onAbort() {
        TaskListener taskListener = new TaskExecutionListener();
        taskListener.onAbort(null);

        TaskExecutionContext context = new TaskExecutionContext();
        taskListener.onAbort(context);
    }
}