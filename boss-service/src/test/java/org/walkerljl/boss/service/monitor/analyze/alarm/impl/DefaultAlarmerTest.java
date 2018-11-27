package org.walkerljl.boss.service.monitor.analyze.alarm.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.monitor.BaseMonitorUnitTest;
import org.walkerljl.boss.service.monitor.analyze.alarm.Alarmer;
import org.walkerljl.boss.service.monitor.analyze.alarm.MonitorAlarmException;
import org.walkerljl.boss.service.monitor.analyze.alarm.notify.abstracts.AbstractAlarmNotifier;
import org.walkerljl.boss.service.monitor.analyze.alarm.notify.impl.AlarmNotifierRepositoryFactory;
import org.walkerljl.boss.service.monitor.analyze.alarm.notify.impl.LogAlarmNotifier;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRecord;
import org.walkerljl.boss.service.monitor.model.alarm.enums.AlarmChannelEnum;
import org.walkerljl.boss.service.task.DummyMonitorService;

/**
 * DefaultAlarmerTest
 *
 * @author xingxun
 */
public class DefaultAlarmerTest extends BaseMonitorUnitTest {

    @Test
    public void alarm() {

        AlarmRecord alarmRecord = null;
        Alarmer alarmer = new DefaultAlarmer(new DummyMonitorService());
        alarmer.alarm(alarmRecord);

        alarmRecord = new AlarmRecord();
        alarmer.alarm(alarmRecord);

        List<AlarmChannelEnum> alarmChannels = new ArrayList<>(3);
        alarmRecord.setChannels(alarmChannels);
        alarmChannels.add(null);
        alarmer.alarm(alarmRecord);

        alarmChannels.add(AlarmChannelEnum.LOG);
        alarmer.alarm(alarmRecord);

        new LogAlarmNotifier().init();
        alarmer.alarm(alarmRecord);

        AlarmNotifierRepositoryFactory.getDefaultRepository().register(AlarmChannelEnum.LOG.getCode(), new AbstractAlarmNotifier() {

            @Override
            public String getId() {
                return AlarmChannelEnum.LOG.getCode();
            }

            @Override
            protected void notify0(AlarmRecord alarmRecord) {
                throw new MonitorAlarmException();
            }
        });

        boolean flag = false;
        try {
            alarmer.alarm(alarmRecord);
        } catch (MonitorAlarmException e) {
            flag = true;
        }
        Assert.assertTrue(flag);
    }
}