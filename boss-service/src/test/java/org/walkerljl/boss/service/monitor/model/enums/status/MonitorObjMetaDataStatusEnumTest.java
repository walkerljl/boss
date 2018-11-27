package org.walkerljl.boss.service.monitor.model.enums.status;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.monitor.BaseMonitorUnitTest;

/**
 * MonitorObjMetaDataStatusEnumTest
 *
 * @author xingxun
 */
public class MonitorObjMetaDataStatusEnumTest extends BaseMonitorUnitTest {

    @Test
    public void test() {

        Assert.assertEquals("10", MonitorObjMetaDataStatusEnum.NORMAL.getCode());
        Assert.assertEquals("正常", MonitorObjMetaDataStatusEnum.NORMAL.getDescription());

        Assert.assertEquals("20", MonitorObjMetaDataStatusEnum.DELETED.getCode());
        Assert.assertEquals("已删除", MonitorObjMetaDataStatusEnum.DELETED.getDescription());

        Assert.assertEquals("30", MonitorObjMetaDataStatusEnum.PAUSE_ALARM.getCode());
        Assert.assertEquals("暂定预警", MonitorObjMetaDataStatusEnum.PAUSE_ALARM.getDescription());

        MonitorObjMetaDataStatusEnum actual = (MonitorObjMetaDataStatusEnum)MonitorObjMetaDataStatusEnum.values()[0].getEnumObject("10");
        Assert.assertEquals(MonitorObjMetaDataStatusEnum.NORMAL, actual);

        actual = (MonitorObjMetaDataStatusEnum)MonitorObjMetaDataStatusEnum.values()[0].getEnumObject("20");
        Assert.assertEquals(MonitorObjMetaDataStatusEnum.DELETED, actual);

        actual = (MonitorObjMetaDataStatusEnum)MonitorObjMetaDataStatusEnum.values()[0].getEnumObject("30");
        Assert.assertEquals(MonitorObjMetaDataStatusEnum.PAUSE_ALARM, actual);
    }
}