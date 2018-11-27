package org.walkerljl.boss.service.monitor.model.alarm;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.monitor.BaseMonitorUnitTest;
import org.walkerljl.boss.service.monitor.model.alarm.enums.AlarmChannelEnum;

/**
 * AlarmChannelEnumTest
 *
 * @author xingxun
 */
public class AlarmChanneEnumTest extends BaseMonitorUnitTest {

    @Test
    public void test() {
        Assert.assertEquals("log", AlarmChannelEnum.LOG.getCode());
        Assert.assertEquals("日志预警渠道", AlarmChannelEnum.LOG.getDescription());

        AlarmChannelEnum actual = (AlarmChannelEnum) AlarmChannelEnum.values()[0].getEnumObject("log");
        Assert.assertEquals(AlarmChannelEnum.LOG, actual);
    }
}