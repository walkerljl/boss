package org.walkerljl.boss.service.monitor.analyze.handler.alarm.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.walkerljl.boss.common.util.ArrayUtil;
import org.walkerljl.boss.common.util.CollectionUtil;
import org.walkerljl.boss.common.util.DateUtil;
import org.walkerljl.boss.common.util.StringUtil;
import org.walkerljl.boss.service.monitor.MonitorService;
import org.walkerljl.boss.service.monitor.analyze.AnalyzeContext;
import org.walkerljl.boss.service.monitor.analyze.alarm.enums.MonitorDataChangeTendencyEnum;
import org.walkerljl.boss.service.monitor.analyze.alarm.util.AlarmUtil;
import org.walkerljl.boss.service.monitor.analyze.compare.MonitorDataProductTimeComparator;
import org.walkerljl.boss.service.monitor.analyze.handler.alarm.AlarmAnalyzeHandler;
import org.walkerljl.boss.service.monitor.analyze.handler.alarm.abstracts.AbstractsAlarmAnalyzeHandler;
import org.walkerljl.boss.service.monitor.model.MonitorData;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRecord;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRule;
import org.walkerljl.boss.service.monitor.model.alarm.enums.AlarmLevelEnum;
import org.walkerljl.boss.service.monitor.model.alarm.enums.AlarmRuleTypeEnum;

/**
 * 多日连续变动达到一定百分比预警分析处理器
 *
 * ${days},${changeTendency},${changeValue}
 *
 * @author xingxun
 */
public class MultiDaysChangeExceedPercentAlarmAnalyzeHandler extends AbstractsAlarmAnalyzeHandler implements AlarmAnalyzeHandler {

    private static final int EXPRESSION_LENGTH = 3;

    private MonitorService monitorService;

    public MultiDaysChangeExceedPercentAlarmAnalyzeHandler(
            MonitorService monitorService) {
        this.monitorService = monitorService;
    }

    @Override
    public String getId() {
        return AlarmRuleTypeEnum.MULTI_DAYS_CHANGE_EXCEED_PERCENT.getCode();
    }

    @Override
    protected AlarmRecord handle0(MonitorData monitorData, AlarmRule alarmRule, AnalyzeContext context) {
        String[] expressionItems = StringUtil.split(alarmRule.getExpression(), ",");
        if (ArrayUtil.length(expressionItems) != EXPRESSION_LENGTH) {
            return null;
        }
        MonitorDataChangeTendencyEnum monitorDataChangeTendency = (MonitorDataChangeTendencyEnum) MonitorDataChangeTendencyEnum.values()[0]
                .getEnumObject(expressionItems[1]);
        if (monitorDataChangeTendency == null) {
            return null;
        }
        int changeDays = Integer.parseInt(expressionItems[0]);

        Date queryBeginTime = DateUtil.dateFormat2Date(
                DateUtil.modifyTime(new Date(DateUtil.getTime(monitorData.getTime())), TimeUnit.DAYS, -changeDays),
                DateUtil.FORMAT_YYYY_MM_DD);
        Date queryEndTime = DateUtil.dateFormat2Date(
                DateUtil.modifyTime(new Date(DateUtil.getTime(monitorData.getTime())), TimeUnit.DAYS, -1),
                DateUtil.FORMAT_YYYY_MM_DD);
        List<MonitorData> tempComparedMonitorDatas = monitorService.listMonitorDataByProductTimeScope(monitorData.getBizCode(),
                monitorData.getObjId(),
                queryBeginTime, queryEndTime, 1, changeDays);
        int tempComparedMonitorDataSize = CollectionUtil.size(tempComparedMonitorDatas);
        if (tempComparedMonitorDataSize != changeDays) {
            return null;
        }
        List<MonitorData> comparedMonitorDatas = new ArrayList<>(tempComparedMonitorDataSize);
        for (MonitorData tempComparedMonitorData : tempComparedMonitorDatas) {
            if (tempComparedMonitorData == null) {
                continue;
            }
            comparedMonitorDatas.add(tempComparedMonitorData);
        }
        if (CollectionUtil.size(comparedMonitorDatas) != changeDays) {
            return null;
        }

        //按数据产生的业务日期进行排序
        Collections.sort(comparedMonitorDatas, new MonitorDataProductTimeComparator());

        MonitorData compareMonitorData = monitorData;
        Double changeRuleValue = Double.valueOf(expressionItems[2]);
        AlarmLevelEnum alarmLevel = AlarmLevelEnum.GREEN;
        for (MonitorData comparedMonitorData : comparedMonitorDatas) {
            Double changePercent = null;
            if (monitorDataChangeTendency == MonitorDataChangeTendencyEnum.DECLINE) {
                BigDecimal declineValue = comparedMonitorData.getValue().subtract(compareMonitorData.getValue());
                changePercent = declineValue.divide(comparedMonitorData.getValue(), 4, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
            if (changePercent < changeRuleValue) {
                return null;
            }
            compareMonitorData = comparedMonitorData;
        }

        //命中预警规则
        AlarmRecord alarmRecord = AlarmUtil.buildAlarmRecord(monitorData, alarmRule, alarmLevel, context);

        return alarmRecord;
    }
}