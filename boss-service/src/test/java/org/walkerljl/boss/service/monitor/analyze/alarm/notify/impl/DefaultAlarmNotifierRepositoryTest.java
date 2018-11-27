package org.walkerljl.boss.service.monitor.analyze.alarm.notify.impl;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.monitor.BaseMonitorUnitTest;
import org.walkerljl.boss.service.monitor.analyze.alarm.notify.AlarmNotifier;
import org.walkerljl.boss.service.monitor.analyze.alarm.notify.AlarmNotifierRepository;
import org.walkerljl.boss.service.monitor.analyze.alarm.notify.abstracts.AbstractAlarmNotifier;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRecord;
import org.walkerljl.toolkit.standard.repository.exception.ObjectRepositoryException;

/**
 * DefaultAlarmNotifierRepositoryTest
 *
 * @author xingxun
 */
public class DefaultAlarmNotifierRepositoryTest extends BaseMonitorUnitTest {

    @Test
    public void test() {

        String expectedObjectId = "objectId";

        AlarmNotifierRepository objectRepository = new DefaultAlarmNotifierRepository();

        objectRepository.lookup("");
        objectRepository.lookup(null);

        AlarmNotifier expected = new AbstractAlarmNotifier() {

            @Override
            protected void notify0(AlarmRecord alarmRecord) {

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

        AlarmNotifier actual = objectRepository.lookup(expectedObjectId);
        Assert.assertNull(actual);

        objectRepository.register(expectedObjectId, expected);
        actual = objectRepository.lookup(expectedObjectId);
        Assert.assertEquals(actual, expected);

        Collection<AlarmNotifier> actuals = (Collection<AlarmNotifier>)objectRepository.lookupAll();
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