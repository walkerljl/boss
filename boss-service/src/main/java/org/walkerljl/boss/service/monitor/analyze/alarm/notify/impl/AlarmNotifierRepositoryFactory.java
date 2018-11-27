package org.walkerljl.boss.service.monitor.analyze.alarm.notify.impl;

import org.walkerljl.boss.service.monitor.analyze.alarm.notify.AlarmNotifierRepository;

/**
 * AlarmNotifierRepositoryFactory
 *
 * @author xingxun
 */
public class AlarmNotifierRepositoryFactory {

    public static AlarmNotifierRepository getDefaultRepository() {
        return Holder.defaultRepository;
    }

    private static class Holder {
        private static AlarmNotifierRepository defaultRepository = new DefaultAlarmNotifierRepository();
    }
}