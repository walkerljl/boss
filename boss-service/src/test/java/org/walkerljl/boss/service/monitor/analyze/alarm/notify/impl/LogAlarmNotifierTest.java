package org.walkerljl.boss.service.monitor.analyze.alarm.notify.impl;

import org.junit.Test;
import org.walkerljl.boss.service.monitor.BaseMonitorUnitTest;
import org.walkerljl.boss.service.monitor.analyze.alarm.notify.AlarmNotifier;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRecord;

/**
 * LogAlarmNotifierTest
 *
 * @author xingxun
 */
public class LogAlarmNotifierTest extends BaseMonitorUnitTest {

    @Test
    public void notify0() {

        AlarmRecord alarmRecord = null;
        AlarmNotifier alarmNotifier = new LogAlarmNotifier();
        alarmNotifier.notify(alarmRecord);

        alarmRecord = new AlarmRecord();
        alarmNotifier.notify(alarmRecord);
    }
}