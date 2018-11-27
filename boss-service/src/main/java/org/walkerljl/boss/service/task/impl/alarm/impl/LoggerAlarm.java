package org.walkerljl.boss.service.task.impl.alarm.impl;

import org.walkerljl.boss.service.task.impl.TaskLoggerNames;
import org.walkerljl.boss.service.task.impl.alarm.Alarm;
import org.walkerljl.boss.service.task.impl.alarm.AlarmInfo;
import org.walkerljl.toolkit.template.log.Logger;
import org.walkerljl.toolkit.template.log.LoggerFactory;
import org.walkerljl.toolkit.template.log.util.LoggerUtil;

/**
 * 日志报警器
 *
 * @author xingxun
 */
public class LoggerAlarm implements Alarm {

    private static final Logger LOGGER_DIGEST = LoggerFactory.getLogger(TaskLoggerNames.ALARMER_DIGEST);
    private static final Logger LOGGER_DETAIL = LoggerFactory.getLogger(TaskLoggerNames.ALARMER_DETAIL);

    @Override
    public void alarm(AlarmInfo alarmInfo) {
        if (alarmInfo == null) {
            return;
        }
        if (LOGGER_DIGEST.isInfoEnabled()) {
            LoggerUtil.info(LOGGER_DIGEST, String.format("%s", alarmInfo.getKey()));
        }
        if (LOGGER_DETAIL.isInfoEnabled()) {
            LoggerUtil.info(LOGGER_DETAIL, String.format("key:%s,description:%s.", alarmInfo.getKey(), alarmInfo.getDescription()));
        }
    }
}