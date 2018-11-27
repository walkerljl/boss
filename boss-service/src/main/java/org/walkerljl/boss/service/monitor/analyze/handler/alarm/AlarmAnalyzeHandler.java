package org.walkerljl.boss.service.monitor.analyze.handler.alarm;

import org.walkerljl.boss.service.monitor.analyze.AnalyzeContext;
import org.walkerljl.boss.service.monitor.model.MonitorData;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRecord;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRule;
import org.walkerljl.toolkit.standard.resource.Resource;

/**
 * 预警分析处理器
 *
 * @author xingxun
 */
public interface AlarmAnalyzeHandler extends Resource {

    /**
     * 分析处理
     *
     * @param monitorData 监控数据
     * @param alarmRule 预警规则
     * @param context 上下文
     * @return
     */
    AlarmRecord handle(MonitorData monitorData, AlarmRule alarmRule, AnalyzeContext context);
}