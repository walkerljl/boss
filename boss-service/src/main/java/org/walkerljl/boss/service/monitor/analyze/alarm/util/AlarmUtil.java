package org.walkerljl.boss.service.monitor.analyze.alarm.util;

import java.util.Date;

import org.walkerljl.boss.service.monitor.analyze.AnalyzeConfig;
import org.walkerljl.boss.service.monitor.analyze.AnalyzeContext;
import org.walkerljl.boss.service.monitor.model.MonitorData;
import org.walkerljl.boss.service.monitor.model.MonitorObjMetaData;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRecord;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRule;
import org.walkerljl.boss.service.monitor.model.alarm.enums.AlarmLevelEnum;
import org.walkerljl.boss.service.monitor.model.alarm.enums.status.AlarmRecordStatusEnum;
import org.walkerljl.boss.service.monitor.model.alarm.enums.AlarmRuleTypeEnum;

/**
 * AlarmUtil
 *
 * @author xingxun
 */
public class AlarmUtil {

    /**
     * 构建预警记录
     *
     * @param monitorData
     * @param alarmRule
     * @param context
     * @param alarmLevel
     * @return
     */
    public static AlarmRecord buildAlarmRecord(MonitorData monitorData, AlarmRule alarmRule,
                                               AlarmLevelEnum alarmLevel, AnalyzeContext context) {
        if (monitorData == null) {
            return null;
        }
        if (alarmRule == null) {
            return null;
        }
        if (context == null) {
            return null;
        }
        if (alarmLevel == null) {
            return null;
        }
        if (monitorData.getMonitorObjMetaData() == null) {
            return null;
        }
        //命中预警规则
        AlarmRecord alarmRecord = new AlarmRecord();
        MonitorObjMetaData monitorObjMetaData = monitorData.getMonitorObjMetaData();
        alarmRecord.setBizCode(monitorObjMetaData.getBizCode());
        alarmRecord.setBizName(monitorObjMetaData.getBizName());
        alarmRecord.setObjId(monitorObjMetaData.getObjId());
        alarmRecord.setObjName(monitorObjMetaData.getObjName());
        alarmRecord.setData(monitorData.getValue());
        alarmRecord.setDataTime(monitorData.getTime());
        alarmRecord.setLevel(alarmLevel);
        AlarmRuleTypeEnum alarmRuleType = alarmRule.getType();
        alarmRecord.setContent(
                String.format("%s:%s", alarmRuleType == null ? null : alarmRuleType.getDescription(), alarmRule.getExpression()));
        Date date = new Date();
        alarmRecord.setTime(date);
        alarmRecord.setChannels(alarmRule.getChannels());
        alarmRecord.setReceivers(monitorObjMetaData.getAlarmReceivers());
        alarmRecord.setStatus(AlarmRecordStatusEnum.UNALARM);
        AnalyzeConfig analyzeConfig = (AnalyzeConfig) context.getAttribute(AnalyzeContext.CONFIG);
        alarmRecord.setCreator(analyzeConfig == null ? null : analyzeConfig.getOperatorId());
        alarmRecord.setCreatedTime(date);
        alarmRecord.setModifier(alarmRecord.getCreator());
        alarmRecord.setModifiedTime(alarmRecord.getCreatedTime());

        return alarmRecord;
    }
}