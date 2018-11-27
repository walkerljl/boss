package org.walkerljl.boss.service.monitor.analyze.alarm.notify.abstracts;

import org.walkerljl.boss.service.monitor.analyze.alarm.notify.AlarmNotifier;
import org.walkerljl.boss.service.monitor.analyze.alarm.notify.MonitorAlarmNotifyException;
import org.walkerljl.boss.service.monitor.analyze.alarm.notify.impl.AlarmNotifierRepositoryFactory;
import org.walkerljl.boss.service.monitor.impl.MonitorLoggerNames;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRecord;
import org.walkerljl.toolkit.standard.resource.abstracts.AbstractResource;
import org.walkerljl.toolkit.standard.resource.exception.CannotDestroyResourceException;
import org.walkerljl.toolkit.standard.resource.exception.CannotInitResourceException;
import org.walkerljl.toolkit.template.log.Logger;
import org.walkerljl.toolkit.template.log.LoggerFactory;
import org.walkerljl.toolkit.template.log.model.InvocationInfo;
import org.walkerljl.toolkit.template.log.util.LoggerDetailUtil;
import org.walkerljl.toolkit.template.log.util.LoggerDigestUtil;

/**
 * 抽象的预警通知器
 *
 * @author xinguxn
 */
public abstract class AbstractAlarmNotifier extends AbstractResource implements AlarmNotifier {

    private static final Logger DIGEST_LOGGER = LoggerFactory.getLogger(MonitorLoggerNames.NOTIFIER_DIGEST);
    private static final Logger DETAIL_LOGGER = LoggerFactory.getLogger(MonitorLoggerNames.NOTIFIER_DETAIL);

    @Override
    public void processInit() throws CannotInitResourceException {
        AlarmNotifierRepositoryFactory.getDefaultRepository().register(getId(), this);
    }

    @Override
    public void processDestroy() throws CannotDestroyResourceException {
        AlarmNotifierRepositoryFactory.getDefaultRepository().unregister(getId());
    }

    @Override
    public void notify(AlarmRecord alarmRecord) {
        InvocationInfo<AlarmRecord, Void> invocationInfo =
                new InvocationInfo<>(getClass(), "notify", alarmRecord);
        try {
            if (alarmRecord == null) {
                return;
            }
            notify0(alarmRecord);
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            throw new MonitorAlarmNotifyException(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }

    /**
     * 处理通知业务逻辑
     *
     * @param alarmRecord
     */
    protected abstract void notify0(AlarmRecord alarmRecord);
}