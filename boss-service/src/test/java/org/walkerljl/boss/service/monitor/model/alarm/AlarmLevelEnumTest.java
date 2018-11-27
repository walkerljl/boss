package org.walkerljl.boss.service.monitor.model.alarm;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.monitor.BaseMonitorUnitTest;
import org.walkerljl.boss.service.monitor.model.alarm.enums.AlarmLevelEnum;

/**
 * AlarmLevelEnumTest
 *
 * @author xingxun
 */
public class AlarmLevelEnumTest extends BaseMonitorUnitTest {

    @Test
    public void test() {
        Assert.assertEquals("10", AlarmLevelEnum.GREEN.getCode());
        Assert.assertEquals("绿", AlarmLevelEnum.GREEN.getDescription());

        Assert.assertEquals("20", AlarmLevelEnum.YELLOW.getCode());
        Assert.assertEquals("黄", AlarmLevelEnum.YELLOW.getDescription());

        Assert.assertEquals("30", AlarmLevelEnum.ORGANE.getCode());
        Assert.assertEquals("橙", AlarmLevelEnum.ORGANE.getDescription());

        Assert.assertEquals("40", AlarmLevelEnum.RED.getCode());
        Assert.assertEquals("红", AlarmLevelEnum.RED.getDescription());

        AlarmLevelEnum actual = (AlarmLevelEnum) AlarmLevelEnum.values()[0].getEnumObject("10");
        Assert.assertEquals(AlarmLevelEnum.GREEN, actual);

        actual = (AlarmLevelEnum) AlarmLevelEnum.values()[0].getEnumObject("20");
        Assert.assertEquals(AlarmLevelEnum.YELLOW, actual);

        actual = (AlarmLevelEnum) AlarmLevelEnum.values()[0].getEnumObject("30");
        Assert.assertEquals(AlarmLevelEnum.ORGANE, actual);

        actual = (AlarmLevelEnum) AlarmLevelEnum.values()[0].getEnumObject("40");
        Assert.assertEquals(AlarmLevelEnum.RED, actual);

    }
}