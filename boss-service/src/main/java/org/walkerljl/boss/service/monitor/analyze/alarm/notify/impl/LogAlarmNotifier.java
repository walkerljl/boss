package org.walkerljl.boss.service.monitor.analyze.alarm.notify.impl;

import org.walkerljl.boss.service.monitor.analyze.alarm.notify.AlarmNotifier;
import org.walkerljl.boss.service.monitor.analyze.alarm.notify.abstracts.AbstractAlarmNotifier;
import org.walkerljl.boss.service.monitor.impl.MonitorLoggerNames;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRecord;
import org.walkerljl.boss.service.monitor.model.alarm.enums.AlarmChannelEnum;
import org.walkerljl.toolkit.template.log.Logger;
import org.walkerljl.toolkit.template.log.LoggerFactory;
import org.walkerljl.toolkit.template.log.util.LoggerUtil;

/**
 * 日志预警通知器
 *
 * @author xingxun
 */
public class LogAlarmNotifier extends AbstractAlarmNotifier implements AlarmNotifier {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitorLoggerNames.DEFAULT);

    @Override
    public String getId() {
        return AlarmChannelEnum.LOG.getCode();
    }

    @Override
    protected void notify0(AlarmRecord alarmRecord) {
        if (LOGGER.isInfoEnabled()) {
            LoggerUtil.info(LOGGER, String.valueOf(alarmRecord));
        }
    }
}