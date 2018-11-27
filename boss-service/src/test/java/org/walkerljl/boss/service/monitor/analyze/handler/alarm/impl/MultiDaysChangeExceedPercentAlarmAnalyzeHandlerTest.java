package org.walkerljl.boss.service.monitor.analyze.handler.alarm.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.monitor.BaseMonitorUnitTest;
import org.walkerljl.boss.service.monitor.MonitorService;
import org.walkerljl.boss.service.monitor.analyze.AnalyzeContext;
import org.walkerljl.boss.service.monitor.analyze.handler.alarm.AlarmAnalyzeHandler;
import org.walkerljl.boss.service.monitor.model.MonitorData;
import org.walkerljl.boss.service.monitor.model.MonitorObjMetaData;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRecord;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRule;
import org.walkerljl.boss.service.monitor.model.alarm.enums.AlarmRuleTypeEnum;

/**
 * MultiDaysChangeExceedPercentAlarmAnalyzeHandlerTest
 *
 * @author xingxun
 */
public class MultiDaysChangeExceedPercentAlarmAnalyzeHandlerTest extends BaseMonitorUnitTest {

    @Test
    public void getId() {
        AlarmAnalyzeHandler alarmAnalyzeHandler = new MultiDaysChangeExceedPercentAlarmAnalyzeHandler(
                new DummyMonitorService()
        );
        Assert.assertEquals(AlarmRuleTypeEnum.MULTI_DAYS_CHANGE_EXCEED_PERCENT.getCode(), alarmAnalyzeHandler.getId());
    }

    @Test
    public void handle0ForNull() {

        AlarmAnalyzeHandler alarmAnalyzeHandler = new MultiDaysChangeExceedPercentAlarmAnalyzeHandler(
                new DummyMonitorService()
        );
        MonitorData monitorData = new MonitorData();
        AlarmRule alarmRule = new AlarmRule();
        AnalyzeContext context = new AnalyzeContext();
        AlarmRecord actual = alarmAnalyzeHandler.handle(monitorData, alarmRule, context);
        Assert.assertNull(actual);

        String expression = "1,d,10";
        alarmRule.setExpression(expression);
        actual = alarmAnalyzeHandler.handle(monitorData, alarmRule, context);
        Assert.assertNull(actual);

        expression = "1,decline,10";
        alarmRule.setExpression(expression);
        actual = alarmAnalyzeHandler.handle(monitorData, alarmRule, context);
        Assert.assertNull(actual);

        alarmAnalyzeHandler = new MultiDaysChangeExceedPercentAlarmAnalyzeHandler(new DefaultMonitorService());
        actual = alarmAnalyzeHandler.handle(monitorData, alarmRule, context);
        Assert.assertNull(actual);
    }

    @Test
    public void handle0() {

        AlarmAnalyzeHandler alarmAnalyzeHandler = new MultiDaysChangeExceedPercentAlarmAnalyzeHandler(
                new org.walkerljl.boss.service.monitor.impl.DefaultMonitorService() {
                    @Override
                    public List<MonitorData> listMonitorDataByProductTimeScope(String bizCode, String monitorObjId, Date beginTime, Date endTime,
                                                                               Integer currentPage, Integer pageSize) {

                        MonitorData monitorData = new MonitorData();
                        monitorData.setValue(new BigDecimal(10));
                        return Arrays.asList(monitorData);
                    }
                }
        );
        MonitorData monitorData = new MonitorData();
        monitorData.setMonitorObjMetaData(new MonitorObjMetaData());
        monitorData.setValue(new BigDecimal(8));
        AlarmRule alarmRule = new AlarmRule();
        String expression = "1,decline,0.1";
        alarmRule.setExpression(expression);
        AnalyzeContext context = new AnalyzeContext();
        AlarmRecord actual = alarmAnalyzeHandler.handle(monitorData, alarmRule, context);
        Assert.assertNotNull(actual);
    }
}

class DummyMonitorService implements MonitorService {

    @Override
    public void saveAlarmRecord(AlarmRecord alarmRecord) {
    }

    @Override
    public void markAlarmRecordToCompleted(String bizCode, String objId, String id) {

    }

    @Override
    public MonitorData getMonitorData(String bizCode, String monitorObjId, Date prodcutTime) {
        return null;
    }

    @Override
    public AlarmRecord getAlarmRecord(String bizCode, String objId, String id) {
        return null;
    }

    @Override
    public MonitorObjMetaData getMonitorObjMetaData(String bizCode, String monitorObjId) {
        return null;
    }

    @Override
    public List<AlarmRule> listAlarmRules(String bizCode, String monitorObjId) {
        return null;
    }

    @Override
    public List<MonitorData> listMonitorDataByProductTimeScope(String bizCode, String monitorObjId, Date beginTime, Date endTime,
                                                               Integer currentPage, Integer pageSize) {
        return null;
    }
}

class DefaultMonitorService implements MonitorService {

    @Override
    public void saveAlarmRecord(AlarmRecord alarmRecord) {
    }

    @Override
    public void markAlarmRecordToCompleted(String bizCode, String objId, String id) {

    }

    @Override
    public MonitorData getMonitorData(String bizCode, String monitorObjId, Date prodcutTime) {
        return null;
    }

    @Override
    public AlarmRecord getAlarmRecord(String bizCode, String objId, String id) {
        return null;
    }

    @Override
    public MonitorObjMetaData getMonitorObjMetaData(String bizCode, String monitorObjId) {
        return null;
    }

    @Override
    public List<AlarmRule> listAlarmRules(String bizCode, String monitorObjId) {
        return null;
    }

    @Override
    public List<MonitorData> listMonitorDataByProductTimeScope(String bizCode, String monitorObjId, Date beginTime, Date endTime,
                                                               Integer currentPage, Integer pageSize) {
        List<MonitorData> monitorDatas = new ArrayList<>(1);
        monitorDatas.add(null);

        return monitorDatas;
    }
}