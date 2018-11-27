package org.walkerljl.boss.service.monitor.analyze.alarm.notify;

import org.walkerljl.boss.service.monitor.model.alarm.AlarmRecord;
import org.walkerljl.toolkit.standard.resource.Resource;

/**
 * 预警通知器
 *
 * @author xingxun
 */
public interface AlarmNotifier extends Resource {

    /**
     * 通知
     *
     * @param alarmRecord 预警记录
     */
    void notify(AlarmRecord alarmRecord);
}