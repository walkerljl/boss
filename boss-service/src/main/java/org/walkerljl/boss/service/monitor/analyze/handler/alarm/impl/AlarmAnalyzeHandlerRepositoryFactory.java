package org.walkerljl.boss.service.monitor.analyze.handler.alarm.impl;

import org.walkerljl.boss.service.monitor.analyze.handler.alarm.AlarmAnalyzeHandlerRepository;

/**
 * AlarmAnalyzeHandlerRepositoryFactory
 *
 * @author xingxun
 */
public class AlarmAnalyzeHandlerRepositoryFactory {

    public static AlarmAnalyzeHandlerRepository getDefaultRepository() {
        return Holder.defaultRepository;
    }

    private static class Holder {
        private static AlarmAnalyzeHandlerRepository defaultRepository = new DefaultAlarmAnalyzeHandlerRepository();
    }
}