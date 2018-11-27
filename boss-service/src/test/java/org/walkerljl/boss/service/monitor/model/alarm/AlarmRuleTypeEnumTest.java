package org.walkerljl.boss.service.monitor.model.alarm;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.monitor.BaseMonitorUnitTest;
import org.walkerljl.boss.service.monitor.model.alarm.enums.AlarmRuleTypeEnum;

/**
 * AlarmChannelEnumTest
 *
 * @author xingxun
 */
public class AlarmRuleTypeEnumTest extends BaseMonitorUnitTest {

    @Test
    public void test() {
        Assert.assertEquals("1000", AlarmRuleTypeEnum.MULTI_DAYS_CHANGE_EXCEED_PERCENT.getCode());
        Assert.assertEquals("多日连续变动达到一定百分比", AlarmRuleTypeEnum.MULTI_DAYS_CHANGE_EXCEED_PERCENT.getDescription());

        Assert.assertEquals("1001", AlarmRuleTypeEnum.SINGLE_DAY_MAX_IN_N_DAYS.getCode());
        Assert.assertEquals("单日值达到最近N天峰值", AlarmRuleTypeEnum.SINGLE_DAY_MAX_IN_N_DAYS.getDescription());

        Assert.assertEquals("1002", AlarmRuleTypeEnum.REACH_CURRENT_MONTH_GOAL.getCode());
        Assert.assertEquals("达成当月KPI目标", AlarmRuleTypeEnum.REACH_CURRENT_MONTH_GOAL.getDescription());

        AlarmRuleTypeEnum actual = (AlarmRuleTypeEnum) AlarmRuleTypeEnum.values()[0].getEnumObject("1000");
        Assert.assertEquals(AlarmRuleTypeEnum.MULTI_DAYS_CHANGE_EXCEED_PERCENT, actual);

        actual = (AlarmRuleTypeEnum) AlarmRuleTypeEnum.values()[0].getEnumObject("1001");
        Assert.assertEquals(AlarmRuleTypeEnum.SINGLE_DAY_MAX_IN_N_DAYS, actual);

        actual = (AlarmRuleTypeEnum) AlarmRuleTypeEnum.values()[0].getEnumObject("1002");
        Assert.assertEquals(AlarmRuleTypeEnum.REACH_CURRENT_MONTH_GOAL, actual);
    }
}