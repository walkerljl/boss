package org.walkerljl.boss.service.monitor.analyze.handler.alarm.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.common.util.DateUtil;
import org.walkerljl.boss.service.monitor.BaseMonitorUnitTest;
import org.walkerljl.boss.service.monitor.analyze.AnalyzeContext;
import org.walkerljl.boss.service.monitor.analyze.handler.alarm.AlarmAnalyzeHandler;
import org.walkerljl.boss.service.monitor.model.MonitorData;
import org.walkerljl.boss.service.monitor.model.MonitorObjMetaData;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRecord;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRule;
import org.walkerljl.boss.service.monitor.model.alarm.enums.AlarmRuleTypeEnum;

/**
 * ReachCurrentMonthGoalAlarmAnalyzeHandlerTest
 *
 * @author xingxun
 */
public class ReachCurrentMonthGoalAlarmAnalyzeHandlerTest extends BaseMonitorUnitTest {

    @Test
    public void getId() {
        AlarmAnalyzeHandler alarmAnalyzeHandler = new ReachCurrentMonthGoalAlarmAnalyzeHandler(

        );
        Assert.assertEquals(AlarmRuleTypeEnum.REACH_CURRENT_MONTH_GOAL.getCode(), alarmAnalyzeHandler.getId());
    }

    @Test
    public void handle0() {

        MonitorData monitorData = new MonitorData();
        monitorData.setMonitorObjMetaData(new MonitorObjMetaData());
        monitorData.setValue(new BigDecimal(8));
        AlarmRule alarmRule = new AlarmRule();
        String expression = "";
        alarmRule.setExpression(expression);
        AnalyzeContext context = new AnalyzeContext();

        AlarmAnalyzeHandler alarmAnalyzeHandler = new ReachCurrentMonthGoalAlarmAnalyzeHandler(

        );
        AlarmRecord actual = alarmAnalyzeHandler.handle(monitorData, alarmRule, context);
        Assert.assertNull(actual);

        monitorData.setValue(new BigDecimal(1000));
        expression = ";";
        alarmRule.setExpression(expression);
        actual = alarmAnalyzeHandler.handle(monitorData, alarmRule, context);
        Assert.assertNull(actual);

        expression = "201810,100";
        alarmRule.setExpression(expression);
        actual = alarmAnalyzeHandler.handle(monitorData, alarmRule, context);
        Assert.assertNull(actual);

        expression = String.format("%s,", DateUtil.dateFormat2String(new Date(), DateUtil.FORMAT_YYYYMM));
        alarmRule.setExpression(expression);
        actual = alarmAnalyzeHandler.handle(monitorData, alarmRule, context);
        Assert.assertNull(actual);

        expression = String.format("%s,100", DateUtil.dateFormat2String(new Date(), DateUtil.FORMAT_YYYYMM));
        alarmRule.setExpression(expression);
        actual = alarmAnalyzeHandler.handle(monitorData, alarmRule, context);
        Assert.assertNotNull(actual);

        expression = String.format("%s,10000", DateUtil.dateFormat2String(new Date(), DateUtil.FORMAT_YYYYMM));
        alarmRule.setExpression(expression);
        actual = alarmAnalyzeHandler.handle(monitorData, alarmRule, context);
        Assert.assertNull(actual);
    }
}