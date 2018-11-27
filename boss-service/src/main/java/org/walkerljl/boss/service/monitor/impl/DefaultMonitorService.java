package org.walkerljl.boss.service.monitor.impl;

import java.util.Date;
import java.util.List;

import org.walkerljl.boss.dao.daointerface.monitor.MonitorAlarmRecordDAO;
import org.walkerljl.boss.dao.daointerface.monitor.MonitorAlarmRuleDAO;
import org.walkerljl.boss.dao.daointerface.monitor.MonitorDataDAO;
import org.walkerljl.boss.dao.daointerface.monitor.MonitorObjMetaDataDAO;
import org.walkerljl.boss.dao.dataobject.monitor.AlarmRecordDO;
import org.walkerljl.boss.service.monitor.MonitorService;
import org.walkerljl.boss.service.monitor.exception.MonitorSalException;
import org.walkerljl.boss.service.monitor.model.MonitorData;
import org.walkerljl.boss.service.monitor.model.MonitorObjMetaData;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRecord;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRule;
import org.walkerljl.toolkit.template.log.Logger;
import org.walkerljl.toolkit.template.log.LoggerFactory;
import org.walkerljl.toolkit.template.log.model.InvocationInfo;
import org.walkerljl.toolkit.template.log.util.LoggerDetailUtil;
import org.walkerljl.toolkit.template.log.util.LoggerDigestUtil;

/**
 * 默认的监控服务
 *
 * @author xingxun
 */
public class DefaultMonitorService implements MonitorService {

    private static final Logger DIGEST_LOGGER = LoggerFactory.getLogger(MonitorLoggerNames.SAL_DIGEST);
    private static final Logger DETAIL_LOGGER = LoggerFactory.getLogger(MonitorLoggerNames.SAL_DETAIL);

    private MonitorObjMetaDataDAO monitorObjMetaDataDAO;
    private MonitorDataDAO        monitorDataDAO;
    private MonitorAlarmRuleDAO   monitorAlarmRuleDAO;
    private MonitorAlarmRecordDAO monitorAlarmRecordDAO;

    @Override
    public void saveAlarmRecord(AlarmRecord alarmRecord) {
        InvocationInfo<AlarmRecord, Void> invocationInfo =
                new InvocationInfo<>(getClass(), "saveAlarmRecord", alarmRecord);
        try {
            AlarmRecordDO alarmRecordDO = ModelConverter.toAlarmRecordDO(alarmRecord);
            if (alarmRecordDO == null) {
                return;
            }
            monitorAlarmRecordDAO.save(alarmRecordDO);

            String id = String.valueOf(alarmRecordDO.getId());
            alarmRecord.setId(id);
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            throw new MonitorSalException(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }

    @Override
    public void markAlarmRecordToCompleted(String bizCode, String objId, String id) {
        InvocationInfo<Object[], Void> invocationInfo =
                new InvocationInfo<>(getClass(), "markAlarmRecordToCompleted", new Object[]{bizCode, objId, id});
        try {
            monitorAlarmRecordDAO.markAlarmRecordToCompleted(bizCode, objId, Long.parseLong(id));
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            throw new MonitorSalException(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }

    @Override
    public MonitorData getMonitorData(String bizCode, String monitorObjId, Date productTime) {
        InvocationInfo<Object[], MonitorData> invocationInfo =
                new InvocationInfo<>(getClass(), "getMonitorData", new Object[] {bizCode, monitorObjId, productTime});

        try {
            MonitorData resultData = ModelConverter.toMonitorData(monitorDataDAO.getMonitorData(bizCode, monitorObjId, productTime));
            invocationInfo.markSuccess(resultData);
            return resultData;
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            throw new MonitorSalException(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }

    @Override
    public AlarmRecord getAlarmRecord(String bizCode, String objId, String id) {
        InvocationInfo<Object[], AlarmRecord> invocationInfo =
                new InvocationInfo<>(getClass(), "getAlarmRecord", new Object[] {bizCode, objId, id});

        try {
            AlarmRecord resultData = ModelConverter.toAlarmRecord(monitorAlarmRecordDAO.getAlarmRecord(bizCode, objId, Long.parseLong(id)));
            invocationInfo.markSuccess(resultData);
            return resultData;
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            throw new MonitorSalException(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }

    @Override
    public MonitorObjMetaData getMonitorObjMetaData(String bizCode, String monitorObjId) {
        InvocationInfo<Object[], MonitorObjMetaData> invocationInfo =
                new InvocationInfo<>(getClass(), "getMonitorObjMetaData", new Object[] {bizCode, monitorObjId});

        try {
            MonitorObjMetaData resultData = ModelConverter.toMonitorObjMetaData(
                    monitorObjMetaDataDAO.getByBizCodeAndObjId(bizCode, monitorObjId));
            invocationInfo.markSuccess(resultData);
            return resultData;
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            throw new MonitorSalException(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }

    @Override
    public List<AlarmRule> listAlarmRules(String bizCode, String monitorObjId) {
        InvocationInfo<Object[], List<AlarmRule>> invocationInfo =
                new InvocationInfo<>(getClass(), "listAlarmRules", new Object[] {bizCode, monitorObjId});

        try {
            List<AlarmRule> resultDataList = ModelConverter.toAlarmRules(monitorAlarmRuleDAO.listByBizCodeAndObjId(bizCode, monitorObjId));
            invocationInfo.markSuccess(resultDataList);
            return resultDataList;
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            throw new MonitorSalException(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }

    @Override
    public List<MonitorData> listMonitorDataByProductTimeScope(String bizCode, String monitorObjId, Date beginTime, Date endTime,
                                                               Integer currentPage, Integer pageSize) {
        InvocationInfo<Object[], List<MonitorData>> invocationInfo =
                new InvocationInfo<>(getClass(), "listMonitorDataByProductTimeScope", new Object[] {bizCode, monitorObjId,
                        beginTime, endTime, currentPage, pageSize});

        try {
            List<MonitorData> resultDataList = ModelConverter.toMonitorDatas(
                    monitorDataDAO.listMonitorDataByProductTimeScope(bizCode, monitorObjId,
                            beginTime, endTime, currentPage, pageSize));
            invocationInfo.markSuccess(resultDataList);
            return resultDataList;
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            throw new MonitorSalException(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }

    /**
     * Setter method for property <tt>monitorObjMetaDataDAO</tt>.
     *
     * @param monitorObjMetaDataDAO  value to be assigned to property monitorObjMetaDataDAO
     */
    public void setMonitorObjMetaDataDAO(MonitorObjMetaDataDAO monitorObjMetaDataDAO) {
        this.monitorObjMetaDataDAO = monitorObjMetaDataDAO;
    }

    /**
     * Setter method for property <tt>monitorDataDAO</tt>.
     *
     * @param monitorDataDAO  value to be assigned to property monitorDataDAO
     */
    public void setMonitorDataDAO(MonitorDataDAO monitorDataDAO) {
        this.monitorDataDAO = monitorDataDAO;
    }

    /**
     * Setter method for property <tt>monitorAlarmRuleDAO</tt>.
     *
     * @param monitorAlarmRuleDAO  value to be assigned to property monitorAlarmRuleDAO
     */
    public void setMonitorAlarmRuleDAO(MonitorAlarmRuleDAO monitorAlarmRuleDAO) {
        this.monitorAlarmRuleDAO = monitorAlarmRuleDAO;
    }

    /**
     * Setter method for property <tt>monitorAlarmRecordDAO</tt>.
     *
     * @param monitorAlarmRecordDAO  value to be assigned to property monitorAlarmRecordDAO
     */
    public void setMonitorAlarmRecordDAO(MonitorAlarmRecordDAO monitorAlarmRecordDAO) {
        this.monitorAlarmRecordDAO = monitorAlarmRecordDAO;
    }
}