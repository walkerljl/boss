package org.walkerljl.boss.service.monitor.analyze.handler.alarm.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.walkerljl.boss.common.util.CollectionUtil;
import org.walkerljl.boss.service.monitor.MonitorService;
import org.walkerljl.boss.service.monitor.analyze.AnalyzeContext;
import org.walkerljl.boss.service.monitor.analyze.alarm.util.AlarmUtil;
import org.walkerljl.boss.service.monitor.analyze.handler.alarm.AlarmAnalyzeHandler;
import org.walkerljl.boss.service.monitor.analyze.handler.alarm.abstracts.AbstractsAlarmAnalyzeHandler;
import org.walkerljl.boss.common.util.DateUtil;
import org.walkerljl.boss.service.monitor.model.MonitorData;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRecord;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRule;
import org.walkerljl.boss.service.monitor.model.alarm.enums.AlarmLevelEnum;
import org.walkerljl.boss.service.monitor.model.alarm.enums.AlarmRuleTypeEnum;

/**
 * 单日数据达到最近N天数据最大值
 * 预警规则表达式：${days}
 * @author xingxun
 */
public class SingleDataMaxInNDaysAlarmAnalyzeHandler extends AbstractsAlarmAnalyzeHandler implements AlarmAnalyzeHandler {

    private MonitorService monitorServiceRepository;

    public SingleDataMaxInNDaysAlarmAnalyzeHandler(
            MonitorService monitorServiceRepository) {
        this.monitorServiceRepository = monitorServiceRepository;
    }

    @Override
    public String getId() {
        return AlarmRuleTypeEnum.SINGLE_DAY_MAX_IN_N_DAYS.getCode();
    }

    @Override
    protected AlarmRecord handle0(MonitorData monitorData, AlarmRule alarmRule, AnalyzeContext analyzeContext) {
        if (monitorData.getValue() == null) {
            return null;
        }
        int days = Integer.valueOf(alarmRule.getExpression());
        Date queryBeginTime = DateUtil.dateFormat2Date(
                DateUtil.modifyTime(new Date(DateUtil.getTime(monitorData.getTime())), TimeUnit.DAYS, -days),
                DateUtil.FORMAT_YYYY_MM_DD);
        Date queryEndTime = DateUtil.dateFormat2Date(
                DateUtil.modifyTime(new Date(DateUtil.getTime(monitorData.getTime())), TimeUnit.DAYS, -1),
                DateUtil.FORMAT_YYYY_MM_DD);
        List<MonitorData> comparedMonitorDatas = monitorServiceRepository.listMonitorDataByProductTimeScope(monitorData.getBizCode(),
                monitorData.getObjId(),
                queryBeginTime, queryEndTime, 1, days);

        if (days != CollectionUtil.size(comparedMonitorDatas)) {
            return null;
        }
        boolean isMax = true;
        for (MonitorData comparedMonitorData : comparedMonitorDatas) {
            if (monitorData.getValue().compareTo(comparedMonitorData.getValue()) == -1) {
                isMax = false;
                break;
            }
        }

        if (!isMax) {
            return null;
        }

        AlarmRecord alarmRecord = AlarmUtil.buildAlarmRecord(monitorData, alarmRule, AlarmLevelEnum.GREEN, analyzeContext);
        return alarmRecord;
    }
}