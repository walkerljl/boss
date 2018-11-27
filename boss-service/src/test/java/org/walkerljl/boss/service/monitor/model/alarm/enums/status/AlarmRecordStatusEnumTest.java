package org.walkerljl.boss.service.monitor.model.alarm.enums.status;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.monitor.BaseMonitorUnitTest;

/**
 * AlarmRecordStatusEnumTest
 *
 * @author xingxun
 */
public class AlarmRecordStatusEnumTest extends BaseMonitorUnitTest {

    @Test
    public void test() {

        Assert.assertEquals("20", AlarmRecordStatusEnum.DELETED.getCode());
        Assert.assertEquals("已删除", AlarmRecordStatusEnum.DELETED.getDescription());

        Assert.assertEquals("30", AlarmRecordStatusEnum.UNALARM.getCode());
        Assert.assertEquals("未预警", AlarmRecordStatusEnum.UNALARM.getDescription());

        Assert.assertEquals("31", AlarmRecordStatusEnum.ALARMED.getCode());
        Assert.assertEquals("已预警", AlarmRecordStatusEnum.ALARMED.getDescription());

        AlarmRecordStatusEnum actual = (AlarmRecordStatusEnum)AlarmRecordStatusEnum.values()[0].getEnumObject("20");
        Assert.assertEquals(AlarmRecordStatusEnum.DELETED, actual);

        actual = (AlarmRecordStatusEnum)AlarmRecordStatusEnum.values()[0].getEnumObject("30");
        Assert.assertEquals(AlarmRecordStatusEnum.UNALARM, actual);

        actual = (AlarmRecordStatusEnum)AlarmRecordStatusEnum.values()[0].getEnumObject("31");
        Assert.assertEquals(AlarmRecordStatusEnum.ALARMED, actual);
    }
}