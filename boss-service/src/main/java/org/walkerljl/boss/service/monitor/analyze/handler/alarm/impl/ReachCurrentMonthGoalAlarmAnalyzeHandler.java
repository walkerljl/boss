package org.walkerljl.boss.service.monitor.analyze.handler.alarm.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.walkerljl.boss.service.monitor.analyze.AnalyzeContext;
import org.walkerljl.boss.service.monitor.analyze.alarm.util.AlarmUtil;
import org.walkerljl.boss.service.monitor.analyze.handler.alarm.AlarmAnalyzeHandler;
import org.walkerljl.boss.service.monitor.analyze.handler.alarm.abstracts.AbstractsAlarmAnalyzeHandler;
import org.walkerljl.boss.common.util.ArrayUtil;
import org.walkerljl.boss.common.util.DateUtil;
import org.walkerljl.boss.common.util.StringUtil;
import org.walkerljl.boss.service.monitor.model.MonitorData;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRecord;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRule;
import org.walkerljl.boss.service.monitor.model.alarm.enums.AlarmLevelEnum;
import org.walkerljl.boss.service.monitor.model.alarm.enums.AlarmRuleTypeEnum;

/**
 * 达到当月目标预警分析处理器
 * 预警规则表达式：${month1},${goal1};${month2},${goal2};......
 * @author xingxun
 */
public class ReachCurrentMonthGoalAlarmAnalyzeHandler extends AbstractsAlarmAnalyzeHandler implements AlarmAnalyzeHandler {

    private static final int EXPRESSION_LENGTH = 2;

    @Override
    public String getId() {
        return AlarmRuleTypeEnum.REACH_CURRENT_MONTH_GOAL.getCode();
    }

    @Override
    protected AlarmRecord handle0(MonitorData monitorData, AlarmRule alarmRule, AnalyzeContext analyzeContext) {
        if (monitorData.getValue() == null) {
            return null;
        }
        String[] expressionPairs = StringUtil.split(alarmRule.getExpression(), ";");
        if (ArrayUtil.isEmpty(expressionPairs)) {
            return null;
        }
        String currentGoalDateStr = DateUtil.dateFormat2String(new Date(), DateUtil.FORMAT_YYYYMM);
        BigDecimal goalValue = null;

        for (String expressionPair : expressionPairs) {
            String[] expressionPairItems = StringUtil.split(expressionPair, ",");
            if (ArrayUtil.length(expressionPairItems) != EXPRESSION_LENGTH) {
                continue;
            }
            if (currentGoalDateStr.equalsIgnoreCase(expressionPairItems[0])) {
                goalValue = new BigDecimal(expressionPairItems[1]);
                break;
            }
        }

        if (goalValue == null) {
            return null;
        }

        boolean isHit = monitorData.getValue().compareTo(goalValue) >= 0;

        if (!isHit) {
            return null;
        }

        AlarmRecord alarmRecord = AlarmUtil.buildAlarmRecord(monitorData, alarmRule, AlarmLevelEnum.GREEN, analyzeContext);

        return alarmRecord;
    }
}