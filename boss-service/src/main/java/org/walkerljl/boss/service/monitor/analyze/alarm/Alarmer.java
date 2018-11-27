package org.walkerljl.boss.service.monitor.analyze.alarm;

import org.walkerljl.boss.service.monitor.model.alarm.AlarmRecord;
import org.walkerljl.boss.service.task.TaskHandler;

/**
 * 预警器
 *
 * @author xingxun
 */
public interface Alarmer extends TaskHandler {

    /**
     * 预警
     *
     * @param alarmRecord 预警记录
     */
    void alarm(AlarmRecord alarmRecord);
}