package org.walkerljl.boss.service.monitor.analyze.alarm.notify.impl;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.monitor.BaseMonitorUnitTest;
import org.walkerljl.boss.service.monitor.analyze.alarm.notify.AlarmNotifierRepository;

/**
 * AlarmNotifierRepositoryFactoryTest
 *
 * @author xingxun
 */
public class AlarmNotifierRepositoryFactoryTest extends BaseMonitorUnitTest {

    @Test
    public void getDefaultRepository() {

        AlarmNotifierRepository expected = AlarmNotifierRepositoryFactory.getDefaultRepository();
        AlarmNotifierRepository actual = AlarmNotifierRepositoryFactory.getDefaultRepository();

        Assert.assertTrue(actual instanceof DefaultAlarmNotifierRepository);
        Assert.assertEquals(expected, actual);
    }

}