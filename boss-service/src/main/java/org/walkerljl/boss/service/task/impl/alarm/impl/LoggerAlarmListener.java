package org.walkerljl.boss.service.task.impl.alarm.impl;

import org.walkerljl.boss.service.task.TaskListener;
import org.walkerljl.boss.service.task.impl.TaskExecutionContext;
import org.walkerljl.boss.service.task.impl.abstracts.AbstractTaskListener;
import org.walkerljl.boss.service.task.impl.alarm.Alarm;
import org.walkerljl.boss.service.task.impl.alarm.AlarmInfo;
import org.walkerljl.boss.service.task.impl.alarm.impl.util.AlarmParamBuilder;

/**
 * 日志报警监听器
 *
 * @author xingxun
 */
public class LoggerAlarmListener extends AbstractTaskListener implements TaskListener {

    private Alarm alarm = new LoggerAlarm();

    @Override
    public void onRunning(TaskExecutionContext context) {
        //do nothing.
    }

    @Override
    public void onCompleted(TaskExecutionContext context) {
        //do nothing.
    }

    @Override
    public void onError(TaskExecutionContext context) {
        alarm(context);
    }

    @Override
    public void onAbort(TaskExecutionContext context) {
        alarm(context);
    }

    /**
     * alarm
     *
     * @param context
     */
    private void alarm(TaskExecutionContext context) {
        AlarmInfo alarmInfo = AlarmParamBuilder.buildAlarmInfo(context);
        alarm.alarm(alarmInfo);
    }
}