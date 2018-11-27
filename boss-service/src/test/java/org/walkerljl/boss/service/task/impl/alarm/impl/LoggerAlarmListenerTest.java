package org.walkerljl.boss.service.task.impl.alarm.impl;

import org.junit.Test;
import org.walkerljl.boss.service.task.BaseTaskUnitTest;
import org.walkerljl.boss.service.task.TaskListener;
import org.walkerljl.boss.service.task.impl.TaskExecutionContext;

/**
 * LoggerAlarmListenerTest
 *
 * @author xingxun
 */
public class LoggerAlarmListenerTest extends BaseTaskUnitTest {

    @Test
    public void onRunning() {
        TaskExecutionContext context = new TaskExecutionContext();
        TaskListener taskListener = new LoggerAlarmListener();
        taskListener.onRunning(context);
    }

    @Test
    public void onCompleted() {
        TaskExecutionContext context = new TaskExecutionContext();
        TaskListener taskListener = new LoggerAlarmListener();
        taskListener.onCompleted(context);
    }

    @Test
    public void onError() {
        TaskExecutionContext context = new TaskExecutionContext();
        TaskListener taskListener = new LoggerAlarmListener();
        taskListener.onError(context);
    }

    @Test
    public void onAbort() {
        TaskExecutionContext context = new TaskExecutionContext();
        TaskListener taskListener = new LoggerAlarmListener();
        taskListener.onAbort(context);
    }
}