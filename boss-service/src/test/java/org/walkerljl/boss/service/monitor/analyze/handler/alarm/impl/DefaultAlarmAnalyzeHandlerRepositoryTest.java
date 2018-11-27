package org.walkerljl.boss.service.monitor.analyze.handler.alarm.impl;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.monitor.BaseMonitorUnitTest;
import org.walkerljl.boss.service.monitor.analyze.AnalyzeContext;
import org.walkerljl.boss.service.monitor.analyze.handler.alarm.AlarmAnalyzeHandler;
import org.walkerljl.boss.service.monitor.analyze.handler.alarm.AlarmAnalyzeHandlerRepository;
import org.walkerljl.boss.service.monitor.analyze.handler.alarm.abstracts.AbstractsAlarmAnalyzeHandler;
import org.walkerljl.boss.service.monitor.model.MonitorData;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRecord;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRule;
import org.walkerljl.toolkit.standard.repository.exception.ObjectRepositoryException;

/**
 * DefaultAlarmAnalyzeHandlerRepositoryTest
 *
 * @author xingxun
 */
public class DefaultAlarmAnalyzeHandlerRepositoryTest extends BaseMonitorUnitTest {

    @Test
    public void test() {

        String expectedObjectId = "objectId";

        AlarmAnalyzeHandlerRepository objectRepository = new DefaultAlarmAnalyzeHandlerRepository();

        objectRepository.lookup("");
        objectRepository.lookup(null);

        AlarmAnalyzeHandler expected = new AbstractsAlarmAnalyzeHandler() {

            @Override
            protected AlarmRecord handle0(MonitorData monitorData, AlarmRule alarmRule, AnalyzeContext analyzeContext) {
                return null;
            }
        };
        boolean actualFlag = false;

        //register
        try {
            objectRepository.register("", null);
        } catch (ObjectRepositoryException e) {
            actualFlag = true;
        }
        Assert.assertTrue(actualFlag);
        actualFlag = false;
        try {
            objectRepository.register(null, null);
        } catch (ObjectRepositoryException e) {
            actualFlag = true;
        }
        Assert.assertTrue(actualFlag);
        actualFlag = false;
        try {
            objectRepository.register(expectedObjectId, null);
        } catch (ObjectRepositoryException e) {
            actualFlag = true;
        }
        Assert.assertTrue(actualFlag);

        //unregister
        objectRepository.unregister("");
        objectRepository.unregister(null);

        AlarmAnalyzeHandler actual = objectRepository.lookup(expectedObjectId);
        Assert.assertNull(actual);

        objectRepository.register(expectedObjectId, expected);
        actual = objectRepository.lookup(expectedObjectId);
        Assert.assertEquals(actual, expected);

        Collection<AlarmAnalyzeHandler> actuals = (Collection<AlarmAnalyzeHandler>)objectRepository.lookupAll();
        Assert.assertEquals(actuals.size(), 1);
        Assert.assertEquals(actuals.iterator().next(), expected);
        try {
            actuals.add(expected);
        } catch (UnsupportedOperationException e) {
            actualFlag = true;
        }
        Assert.assertTrue(actualFlag);

        objectRepository.unregister(expectedObjectId);
        actual = objectRepository.lookup(expectedObjectId);
        Assert.assertNull(actual);
    }
}