package org.walkerljl.boss.service.monitor.analyze.alarm.notify.abstracts;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.monitor.BaseMonitorUnitTest;
import org.walkerljl.boss.service.monitor.analyze.alarm.notify.AlarmNotifier;
import org.walkerljl.boss.service.monitor.analyze.alarm.notify.MonitorAlarmNotifyException;
import org.walkerljl.boss.service.monitor.analyze.alarm.notify.impl.AlarmNotifierRepositoryFactory;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRecord;

/**
 * AbstractAlarmNotifierTest
 *
 * @author xingxun
 */
public class AbstractAlarmNotifierTest extends BaseMonitorUnitTest {

    @Test
    public void notifyTest() {

        AlarmRecord alarmRecord = null;
        DefaultAlarmNotifier alarmNotifier = new DefaultAlarmNotifier();
        alarmNotifier.notify(alarmRecord);

        alarmRecord = new AlarmRecord();
        alarmNotifier.notify(alarmRecord);
        Assert.assertTrue(alarmNotifier.isNotified());

        boolean flag = false;
        try {
            new AbstractAlarmNotifier() {

                @Override
                protected void notify0(AlarmRecord alarmRecord) {
                    throw new RuntimeException();
                }
            }.notify(alarmRecord);
        } catch (MonitorAlarmNotifyException e) {
            flag = true;
        }
        Assert.assertTrue(flag);
    }

    @Test
    public void initAndDestroy() {

        AlarmNotifier expected = new AbstractAlarmNotifier() {

            @Override
            public String getId() {
                return String.valueOf(System.currentTimeMillis());
            }

            @Override
            public String getGroup() {
                return "AlarmNotifier-xingxun";
            }

            @Override
            protected void notify0(AlarmRecord alarmRecord) {

            }
        };
        expected.init();
        AlarmNotifier actual = AlarmNotifierRepositoryFactory.getDefaultRepository().lookup(expected.getId());
    }
}

class DefaultAlarmNotifier extends AbstractAlarmNotifier implements AlarmNotifier {

    private boolean notified = false;

    @Override
    protected void notify0(AlarmRecord alarmRecord) {
        notified = true;
    }

    /**
     * Getter method for property <tt>notified</tt>.
     *
     * @return property value of notified
     */
    public boolean isNotified() {
        return notified;
    }

    /**
     * Setter method for property <tt>notified</tt>.
     *
     * @param notified  value to be assigned to property notified
     */
    public void setNotified(boolean notified) {
        this.notified = notified;
    }
}