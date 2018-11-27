package org.walkerljl.boss.service.monitor.analyze.handler.alarm.impl;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.monitor.BaseMonitorUnitTest;
import org.walkerljl.boss.service.monitor.analyze.handler.alarm.AlarmAnalyzeHandlerRepository;

/**
 * AlarmAnalyzeHandlerRepositoryFactoryTest
 *
 * @author xingxun
 */
public class AlarmAnalyzeHandlerRepositoryFactoryTest extends BaseMonitorUnitTest {

    @Test
    public void getDefaultRepository() {

        AlarmAnalyzeHandlerRepository expected = AlarmAnalyzeHandlerRepositoryFactory.getDefaultRepository();
        AlarmAnalyzeHandlerRepository actual = AlarmAnalyzeHandlerRepositoryFactory.getDefaultRepository();

        Assert.assertTrue(actual instanceof DefaultAlarmAnalyzeHandlerRepository);
        Assert.assertEquals(expected, actual);
    }

}