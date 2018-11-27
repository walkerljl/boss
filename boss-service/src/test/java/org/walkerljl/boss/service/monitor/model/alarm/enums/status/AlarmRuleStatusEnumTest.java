package org.walkerljl.boss.service.monitor.model.alarm.enums.status;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.monitor.BaseMonitorUnitTest;

/**
 * AlarmRuleStatusEnumTest
 *
 * @author xingxun
 */
public class AlarmRuleStatusEnumTest extends BaseMonitorUnitTest {

    @Test
    public void test() {
        Assert.assertEquals("10", AlarmRuleStatusEnum.NORMAL.getCode());
        Assert.assertEquals("正常", AlarmRuleStatusEnum.NORMAL.getDescription());

        Assert.assertEquals("20", AlarmRuleStatusEnum.DELETED.getCode());
        Assert.assertEquals("已删除", AlarmRuleStatusEnum.DELETED.getDescription());

        AlarmRuleStatusEnum actual = (AlarmRuleStatusEnum)AlarmRuleStatusEnum.values()[0].getEnumObject("10");
        Assert.assertEquals(AlarmRuleStatusEnum.NORMAL, actual);

        actual = (AlarmRuleStatusEnum)AlarmRuleStatusEnum.values()[0].getEnumObject("20");
        Assert.assertEquals(AlarmRuleStatusEnum.DELETED, actual);
    }
}