package org.walkerljl.boss.service.task.impl.alarm.impl;

import org.junit.Test;
import org.walkerljl.boss.service.task.BaseTaskUnitTest;
import org.walkerljl.boss.service.task.impl.alarm.Alarm;
import org.walkerljl.boss.service.task.impl.alarm.AlarmInfo;

/**
 * LoggerAlarmTest
 *
 * @author xingxun
 */
public class LoggerAlarmTest extends BaseTaskUnitTest {

    @Test
    public void alarm() {
        Alarm alarm = new LoggerAlarm();
        alarm.alarm(null);

        AlarmInfo alarmInfo = new AlarmInfo("key", "desc");
        alarm.alarm(alarmInfo);
    }
}