package org.walkerljl.boss.service.monitor.analyze.handler.alarm.abstracts;

import org.walkerljl.boss.service.monitor.analyze.AnalyzeContext;
import org.walkerljl.boss.service.monitor.analyze.MonitorAnalyzeException;
import org.walkerljl.boss.service.monitor.analyze.handler.alarm.AlarmAnalyzeHandler;
import org.walkerljl.boss.service.monitor.analyze.handler.alarm.impl.AlarmAnalyzeHandlerRepositoryFactory;
import org.walkerljl.boss.service.monitor.impl.MonitorLoggerNames;
import org.walkerljl.boss.service.monitor.model.MonitorData;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRecord;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRule;
import org.walkerljl.toolkit.standard.resource.abstracts.AbstractResource;
import org.walkerljl.toolkit.standard.resource.exception.CannotDestroyResourceException;
import org.walkerljl.toolkit.standard.resource.exception.CannotInitResourceException;
import org.walkerljl.toolkit.template.log.Logger;
import org.walkerljl.toolkit.template.log.LoggerFactory;
import org.walkerljl.toolkit.template.log.model.InvocationInfo;
import org.walkerljl.toolkit.template.log.util.LoggerDetailUtil;
import org.walkerljl.toolkit.template.log.util.LoggerDigestUtil;

/**
 * 抽象的预警分析处理器
 *
 * @author xingxun
 */
public abstract class AbstractsAlarmAnalyzeHandler extends AbstractResource implements AlarmAnalyzeHandler {

    private static final Logger DIGEST_LOGGER = LoggerFactory.getLogger(MonitorLoggerNames.ANALYZE_DIGEST);
    private static final Logger DETAIL_LOGGER = LoggerFactory.getLogger(MonitorLoggerNames.ANALYZE_DETAIL);

    @Override
    public void processInit() throws CannotInitResourceException {
        AlarmAnalyzeHandlerRepositoryFactory.getDefaultRepository().register(getId(), this);
    }

    @Override
    public void processDestroy() throws CannotDestroyResourceException {
        AlarmAnalyzeHandlerRepositoryFactory.getDefaultRepository().unregister(getId());
    }

    @Override
    public AlarmRecord handle(MonitorData monitorData, AlarmRule alarmRule, AnalyzeContext analyzeContext) {
        InvocationInfo<Object[], AlarmRecord> invocationInfo =
                new InvocationInfo<>(getClass(), "handle",
                        new Object[] {monitorData, alarmRule, analyzeContext});
        try {
            if (monitorData == null) {
                return null;
            }
            if (alarmRule == null) {
                return null;
            }
            if (analyzeContext == null) {
                return null;
            }
            AlarmRecord alarmRecord = handle0(monitorData, alarmRule, analyzeContext);
            invocationInfo.markSuccess(alarmRecord);
            return alarmRecord;
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            throw new MonitorAnalyzeException(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }

    /**
     * 具体的分析处理
     *
     * @param monitorData 监控数据
     * @param alarmRule 预警规则
     * @param analyzeContext 分析上下文
     * @return
     */
    protected abstract AlarmRecord handle0(MonitorData monitorData, AlarmRule alarmRule, AnalyzeContext analyzeContext);

}