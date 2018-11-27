package org.walkerljl.boss.service.task.impl.alarm;

/**
 * 报警器
 *
 * @author xingxun
 */
public interface Alarm {

    /**
     * 报警
     *
     * @param alarmInfo 报警信息
     */
    void alarm(AlarmInfo alarmInfo);
}