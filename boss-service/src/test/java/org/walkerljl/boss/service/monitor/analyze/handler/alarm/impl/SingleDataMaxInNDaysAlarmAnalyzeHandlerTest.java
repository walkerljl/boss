package org.walkerljl.boss.service.monitor.analyze.handler.alarm.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.monitor.BaseMonitorUnitTest;
import org.walkerljl.boss.service.monitor.analyze.AnalyzeContext;
import org.walkerljl.boss.service.monitor.analyze.handler.alarm.AlarmAnalyzeHandler;
import org.walkerljl.boss.service.monitor.model.MonitorData;
import org.walkerljl.boss.service.monitor.model.MonitorObjMetaData;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRecord;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRule;
import org.walkerljl.boss.service.monitor.model.alarm.enums.AlarmRuleTypeEnum;

/**
 * SingleDataMaxInNDaysAlarmAnalyzeHandlerTest
 *
 * @author xingxun
 */
public class SingleDataMaxInNDaysAlarmAnalyzeHandlerTest extends BaseMonitorUnitTest {

    @Test
    public void getId() {
        AlarmAnalyzeHandler alarmAnalyzeHandler = new SingleDataMaxInNDaysAlarmAnalyzeHandler(
                new DefaultMonitorService()
        );
        Assert.assertEquals(AlarmRuleTypeEnum.SINGLE_DAY_MAX_IN_N_DAYS.getCode(), alarmAnalyzeHandler.getId());
    }

    @Test
    public void handle0() {

        AlarmAnalyzeHandler alarmAnalyzeHandler = new SingleDataMaxInNDaysAlarmAnalyzeHandler(
                new DefaultMonitorService()
        );

        MonitorData monitorData = new MonitorData();
        monitorData.setMonitorObjMetaData(new MonitorObjMetaData());
        AlarmRule alarmRule = new AlarmRule();
        AnalyzeContext context = new AnalyzeContext();

        AlarmRecord actual = alarmAnalyzeHandler.handle(monitorData, alarmRule, context);
        Assert.assertNull(actual);

        alarmRule.setExpression("1");
        monitorData.setValue(new BigDecimal(100));
        //actual = alarmAnalyzeHandler.handle(monitorData, alarmRule, context);
        //Assert.assertNull(actual);

        alarmAnalyzeHandler = new SingleDataMaxInNDaysAlarmAnalyzeHandler(
                new org.walkerljl.boss.service.monitor.impl.DefaultMonitorService() {
                    @Override
                    public List<MonitorData> listMonitorDataByProductTimeScope(String bizCode, String monitorObjId, Date beginTime, Date endTime,
                                                                               Integer currentPage, Integer pageSize) {
                        List<MonitorData> monitorDatas = new ArrayList<>(1);
                        MonitorData tempMonitorData = new MonitorData();
                        monitorDatas.add(tempMonitorData);
                        tempMonitorData.setValue(new BigDecimal(80));
                        return monitorDatas;
                    }
                }
        );
        actual = alarmAnalyzeHandler.handle(monitorData, alarmRule, context);
        Assert.assertNotNull(actual);
    }
}