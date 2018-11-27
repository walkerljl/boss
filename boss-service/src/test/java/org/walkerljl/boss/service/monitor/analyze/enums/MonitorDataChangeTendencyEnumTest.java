package org.walkerljl.boss.service.monitor.analyze.enums;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.monitor.BaseMonitorUnitTest;
import org.walkerljl.boss.service.monitor.analyze.alarm.enums.MonitorDataChangeTendencyEnum;

/**
 * MonitorDataChangeTendencyEnumTest
 *
 * @author xingxun
 */
public class MonitorDataChangeTendencyEnumTest extends BaseMonitorUnitTest {

    @Test
    public void test() {
        Assert.assertEquals("decline", MonitorDataChangeTendencyEnum.DECLINE.getCode());

        MonitorDataChangeTendencyEnum actual = (MonitorDataChangeTendencyEnum)MonitorDataChangeTendencyEnum.values()[0].getEnumObject("decline");
        Assert.assertEquals(MonitorDataChangeTendencyEnum.DECLINE, actual);
    }
}