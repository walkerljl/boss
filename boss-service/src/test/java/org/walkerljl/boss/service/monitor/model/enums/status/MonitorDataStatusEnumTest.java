package org.walkerljl.boss.service.monitor.model.enums.status;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.monitor.BaseMonitorUnitTest;

/**
 * MonitorDataStatusEnumTest
 *
 * @author xingxun
 */
public class MonitorDataStatusEnumTest extends BaseMonitorUnitTest {

    @Test
    public void test() {
        Assert.assertEquals("10", MonitorDataStatusEnum.NORMAL.getCode());
        Assert.assertEquals("正常", MonitorDataStatusEnum.NORMAL.getDescription());

        Assert.assertEquals("20", MonitorDataStatusEnum.DELETED.getCode());
        Assert.assertEquals("已删除", MonitorDataStatusEnum.DELETED.getDescription());

        MonitorDataStatusEnum actual = (MonitorDataStatusEnum)MonitorDataStatusEnum.values()[0].getEnumObject("10");
        Assert.assertEquals(MonitorDataStatusEnum.NORMAL, actual);

        actual = (MonitorDataStatusEnum)MonitorDataStatusEnum.values()[0].getEnumObject("20");
        Assert.assertEquals(MonitorDataStatusEnum.DELETED, actual);
    }
}