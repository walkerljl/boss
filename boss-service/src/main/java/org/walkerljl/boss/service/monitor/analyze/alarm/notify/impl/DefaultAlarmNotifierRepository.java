package org.walkerljl.boss.service.monitor.analyze.alarm.notify.impl;

import org.walkerljl.boss.service.monitor.analyze.alarm.notify.AlarmNotifier;
import org.walkerljl.boss.service.monitor.analyze.alarm.notify.AlarmNotifierRepository;
import org.walkerljl.toolkit.standard.repository.abstracts.AbstractObjectRepository;

/**
 * 默认的预警通知器仓储
 *
 * @author xingxun
 */
public class DefaultAlarmNotifierRepository extends AbstractObjectRepository<String, AlarmNotifier> implements AlarmNotifierRepository {

}