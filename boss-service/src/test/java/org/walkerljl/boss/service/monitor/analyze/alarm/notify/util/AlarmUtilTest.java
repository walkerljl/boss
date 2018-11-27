package org.walkerljl.boss.service.monitor.analyze.alarm.notify.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.monitor.BaseMonitorUnitTest;
import org.walkerljl.boss.service.monitor.analyze.AnalyzeConfig;
import org.walkerljl.boss.service.monitor.analyze.AnalyzeContext;
import org.walkerljl.boss.service.monitor.analyze.alarm.util.AlarmUtil;
import org.walkerljl.boss.service.monitor.model.MonitorData;
import org.walkerljl.boss.service.monitor.model.MonitorObjMetaData;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRecord;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRule;
import org.walkerljl.boss.service.monitor.model.alarm.enums.AlarmChannelEnum;
import org.walkerljl.boss.service.monitor.model.alarm.enums.AlarmLevelEnum;
import org.walkerljl.boss.service.monitor.model.alarm.enums.status.AlarmRecordStatusEnum;
import org.walkerljl.boss.service.monitor.model.alarm.enums.AlarmRuleTypeEnum;

/**
 * AlarmUtilTest
 *
 * @author xingxun
 */
public class AlarmUtilTest extends BaseMonitorUnitTest {

    @Test
    public void buildAlarmRecordForInvalidParam() {

        MonitorData monitorData = new MonitorData();
        AlarmRule alarmRule = new AlarmRule();
        AlarmLevelEnum alarmLevel = AlarmLevelEnum.GREEN;
        AnalyzeContext context = new AnalyzeContext();

        AlarmRecord actual = AlarmUtil.buildAlarmRecord(null, alarmRule, alarmLevel, context);
        Assert.assertNull(actual);

        actual = AlarmUtil.buildAlarmRecord(monitorData, null, alarmLevel, context);
        Assert.assertNull(actual);

        actual = AlarmUtil.buildAlarmRecord(monitorData, alarmRule, null, context);
        Assert.assertNull(actual);

        actual = AlarmUtil.buildAlarmRecord(monitorData, alarmRule, alarmLevel, null);
        Assert.assertNull(actual);

        actual = AlarmUtil.buildAlarmRecord(null, null, alarmLevel, context);
        Assert.assertNull(actual);

        actual = AlarmUtil.buildAlarmRecord(null, alarmRule, null, context);
        Assert.assertNull(actual);

        actual = AlarmUtil.buildAlarmRecord(null, alarmRule, alarmLevel, null);
        Assert.assertNull(actual);

        actual = AlarmUtil.buildAlarmRecord(monitorData, null, null, context);
        Assert.assertNull(actual);

        actual = AlarmUtil.buildAlarmRecord(monitorData, null, alarmLevel, null);
        Assert.assertNull(actual);

        actual = AlarmUtil.buildAlarmRecord(monitorData, alarmRule, null, null);
        Assert.assertNull(actual);

        actual = AlarmUtil.buildAlarmRecord(null, null, null, context);
        Assert.assertNull(actual);

        actual = AlarmUtil.buildAlarmRecord(monitorData, null, null, null);
        Assert.assertNull(actual);

        actual = AlarmUtil.buildAlarmRecord(null, null, null, null);
        Assert.assertNull(actual);

        actual = AlarmUtil.buildAlarmRecord(monitorData, alarmRule, alarmLevel, context);
        Assert.assertNull(actual);
    }

    @Test
    public void buildAlarmRecord() {

        MonitorData monitorData = new MonitorData();
        monitorData.setTime(new Date());
        monitorData.setValue(new BigDecimal(10));
        AlarmRule alarmRule = new AlarmRule();
        alarmRule.setType(AlarmRuleTypeEnum.REACH_CURRENT_MONTH_GOAL);
        List<AlarmChannelEnum> alarmChannels = Arrays.asList(AlarmChannelEnum.LOG);
        alarmRule.setChannels(alarmChannels);
        AlarmLevelEnum alarmLevel = AlarmLevelEnum.GREEN;
        AnalyzeContext context = new AnalyzeContext();
        context.setAttribute(AnalyzeContext.CONFIG, new AnalyzeConfig());
        MonitorObjMetaData monitorObjMetaData = new MonitorObjMetaData();
        monitorData.setMonitorObjMetaData(monitorObjMetaData);
        monitorObjMetaData.setBizCode("bizCode");
        monitorObjMetaData.setBizName("bizName");
        monitorObjMetaData.setObjId("objId");
        monitorObjMetaData.setObjName("objName");
        monitorObjMetaData.setAlarmReceivers(Arrays.asList("xingxun,jarvis"));

        AlarmRecord actual = AlarmUtil.buildAlarmRecord(monitorData, alarmRule, alarmLevel, context);

        Assert.assertEquals(monitorObjMetaData.getBizCode(), actual.getBizCode());
        Assert.assertEquals(monitorObjMetaData.getBizName(), actual.getBizName());
        Assert.assertEquals(monitorObjMetaData.getObjId(), actual.getObjId());
        Assert.assertEquals(monitorObjMetaData.getObjName(), actual.getObjName());
        Assert.assertEquals(monitorData.getTime(), actual.getDataTime());
        Assert.assertEquals(monitorData.getValue(), actual.getData());
        Assert.assertEquals(alarmLevel.getCode(), actual.getLevel().getCode());
        Assert.assertEquals(String.format("%s:%s", alarmRule.getType() == null ? null : alarmRule.getType().getDescription(), alarmRule.getExpression()),
                actual.getContent());
        Assert.assertNotNull(actual.getTime());
        Assert.assertEquals(alarmRule.getChannels(), actual.getChannels());
        Assert.assertEquals(monitorObjMetaData.getAlarmReceivers(), actual.getReceivers());
        Assert.assertEquals(AlarmRecordStatusEnum.UNALARM, actual.getStatus());
        Assert.assertEquals(new AnalyzeConfig().getOperatorId(), actual.getCreator());
        Assert.assertEquals(new AnalyzeConfig().getOperatorId(), actual.getModifier());
        Assert.assertEquals(actual.getCreatedTime(), actual.getModifiedTime());
    }
}