package org.walkerljl.boss.service.monitor.analyze.alarm.impl;

import java.util.List;

import org.walkerljl.boss.common.util.CollectionUtil;
import org.walkerljl.boss.service.monitor.MonitorService;
import org.walkerljl.boss.service.monitor.analyze.alarm.Alarmer;
import org.walkerljl.boss.service.monitor.analyze.alarm.MonitorAlarmException;
import org.walkerljl.boss.service.monitor.analyze.alarm.notify.AlarmNotifier;
import org.walkerljl.boss.service.monitor.analyze.alarm.notify.impl.AlarmNotifierRepositoryFactory;
import org.walkerljl.boss.service.monitor.impl.MonitorLoggerNames;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRecord;
import org.walkerljl.boss.service.monitor.model.alarm.enums.AlarmChannelEnum;
import org.walkerljl.boss.service.task.impl.TaskExecutionContext;
import org.walkerljl.boss.service.task.impl.abstracts.AbstractTaskHandler;
import org.walkerljl.toolkit.standard.resource.exception.CannotDestroyResourceException;
import org.walkerljl.toolkit.standard.resource.exception.CannotInitResourceException;
import org.walkerljl.toolkit.template.log.Logger;
import org.walkerljl.toolkit.template.log.LoggerFactory;
import org.walkerljl.toolkit.template.log.model.InvocationInfo;
import org.walkerljl.toolkit.template.log.util.LoggerDetailUtil;
import org.walkerljl.toolkit.template.log.util.LoggerDigestUtil;

/**
 * 默认的预警器
 *
 * @author xingxun
 */
public class DefaultAlarmer extends AbstractTaskHandler implements Alarmer {

    private static final Logger DIGEST_LOGGER = LoggerFactory.getLogger(MonitorLoggerNames.ALARMER_DIGEST);
    private static final Logger DETAIL_LOGGER = LoggerFactory.getLogger(MonitorLoggerNames.ALARMER_DETAIL);

    private static final String TASK_CONTEXT_KEY_ALARM_RECORD = "task.monitor.alarm.record";

    private MonitorService monitorService;

    public DefaultAlarmer(MonitorService monitorService) {
        this.monitorService = monitorService;
    }

    @Override
    public void alarm(AlarmRecord alarmRecord) {
        InvocationInfo<AlarmRecord, Void> invocationInfo =
                new InvocationInfo<>(getClass(), "alarm", alarmRecord);
        try {
            if (alarmRecord == null) {
                return;
            }
            //发送预警通知
            List<AlarmChannelEnum> channels = null;
            if (CollectionUtil.isEmpty(channels = alarmRecord.getChannels())) {
                return;
            }
            for (AlarmChannelEnum channel : channels) {
                if (channel == null) {
                    continue;
                }
                AlarmNotifier notifier = AlarmNotifierRepositoryFactory.getDefaultRepository().lookup(channel.getCode());
                if (notifier != null) {
                    notifier.notify(alarmRecord);
                }
            }
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            throw new MonitorAlarmException(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }

    @Override
    public void processInit() throws CannotInitResourceException {

    }

    @Override
    public void processDestroy() throws CannotDestroyResourceException {

    }

    @Override
    protected void handle0(TaskExecutionContext context) {
        String taskBizId = String.valueOf(context.getAttribute(TaskExecutionContext.TASK_BIZ_ID));
        AlarmRecord identityAlarmRecord = AlarmRecord.parseIdentityId(taskBizId);

        AlarmRecord alarmRecord = monitorService.getAlarmRecord(identityAlarmRecord.getBizCode(),
                identityAlarmRecord.getObjId(),
                identityAlarmRecord.getId());
        context.setAttribute(TASK_CONTEXT_KEY_ALARM_RECORD, alarmRecord);
    }

    @Override
    public void handleInTransactionAfterRun(TaskExecutionContext context) {
        AlarmRecord alarmRecord = (AlarmRecord)context.getAttribute(TASK_CONTEXT_KEY_ALARM_RECORD);
        if (alarmRecord == null) {
            return;
        }
        //预警
        alarm(alarmRecord);
        //标注预警成功
        monitorService.markAlarmRecordToCompleted(alarmRecord.getBizCode(),
                alarmRecord.getObjId(),
                alarmRecord.getId());

    }
}