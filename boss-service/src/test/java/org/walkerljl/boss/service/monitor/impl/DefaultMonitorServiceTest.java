package org.walkerljl.boss.service.monitor.impl;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.walkerljl.boss.dao.daointerface.monitor.MonitorAlarmRecordDAO;
import org.walkerljl.boss.dao.daointerface.monitor.MonitorAlarmRuleDAO;
import org.walkerljl.boss.dao.daointerface.monitor.MonitorDataDAO;
import org.walkerljl.boss.dao.daointerface.monitor.MonitorObjMetaDataDAO;
import org.walkerljl.boss.dao.dataobject.monitor.AlarmRecordDO;
import org.walkerljl.boss.dao.dataobject.monitor.AlarmRuleDO;
import org.walkerljl.boss.dao.dataobject.monitor.MonitorDataDO;
import org.walkerljl.boss.dao.dataobject.monitor.MonitorObjMetaDataDO;
import org.walkerljl.boss.service.monitor.BaseMonitorUnitTest;
import org.walkerljl.boss.service.monitor.exception.MonitorSalException;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRecord;

/**
 * DefaultMonitorServiceTest
 *
 * @author xingxun
 */
public class DefaultMonitorServiceTest extends BaseMonitorUnitTest {

    @Test(expected = MonitorSalException.class)
    public void saveAlarmRecord() {
        DefaultMonitorService monitorService = new DefaultMonitorService();
        monitorService.setMonitorAlarmRecordDAO(new MonitorAlarmRecordDAO() {
            @Override
            public void save(AlarmRecordDO alarmRecord) {

            }

            @Override
            public void markAlarmRecordToCompleted(String bizCode, String objId, Long id) {

            }

            @Override
            public AlarmRecordDO getAlarmRecord(String bizCode, String objId, Long id) {
                return null;
            }
        });
        monitorService.saveAlarmRecord(new AlarmRecord());

        monitorService = new DefaultMonitorService();
        monitorService.setMonitorAlarmRecordDAO(new MonitorAlarmRecordDAO() {
            @Override
            public void save(AlarmRecordDO alarmRecord) {
                throw new RuntimeException();
            }

            @Override
            public void markAlarmRecordToCompleted(String bizCode, String objId, Long id) {

            }

            @Override
            public AlarmRecordDO getAlarmRecord(String bizCode, String objId, Long id) {
                return null;
            }
        });
        monitorService.saveAlarmRecord(new AlarmRecord());
    }

    @Test(expected = MonitorSalException.class)
    public void getMonitorData() {
        DefaultMonitorService monitorService = new DefaultMonitorService();
        monitorService.setMonitorDataDAO(new MonitorDataDAO() {

            @Override
            public MonitorDataDO getMonitorData(String bizCode, String monitorObjId, Date productTime) {
                return null;
            }

            @Override
            public List<MonitorDataDO> listMonitorDataByProductTimeScope(String bizCode, String monitorObjId, Date beginTime, Date endTime,
                                                                         int currentPage, int pageSize) {
                return null;
            }
        });
        monitorService.getMonitorData(null, null, null);

        monitorService = new DefaultMonitorService();
        monitorService.setMonitorDataDAO(new MonitorDataDAO() {

            @Override
            public MonitorDataDO getMonitorData(String bizCode, String monitorObjId, Date productTime) {
                throw new RuntimeException();
            }

            @Override
            public List<MonitorDataDO> listMonitorDataByProductTimeScope(String bizCode, String monitorObjId, Date beginTime, Date endTime,
                                                                         int currentPage, int pageSize) {
                return null;
            }
        });
        monitorService.getMonitorData(null, null, null);
    }

    @Test(expected = MonitorSalException.class)
    public void getMonitorObjMetaData() {

        DefaultMonitorService monitorService = new DefaultMonitorService();
        monitorService.setMonitorObjMetaDataDAO(new MonitorObjMetaDataDAO() {
            @Override
            public MonitorObjMetaDataDO getByBizCodeAndObjId(String bizCode, String monitorObjId) {
                return null;
            }
        });
        monitorService.getMonitorObjMetaData(null, null);

        monitorService.setMonitorObjMetaDataDAO(new MonitorObjMetaDataDAO() {
            @Override
            public MonitorObjMetaDataDO getByBizCodeAndObjId(String bizCode, String monitorObjId) {
                throw new RuntimeException();
            }
        });
        monitorService.getMonitorObjMetaData(null, null);
    }

    @Test(expected = MonitorSalException.class)
    public void listAlarmRules() {
        DefaultMonitorService monitorService = new DefaultMonitorService();
        monitorService.setMonitorAlarmRuleDAO(new MonitorAlarmRuleDAO() {
            @Override
            public List<AlarmRuleDO> listByBizCodeAndObjId(String bizCode, String monitorObjId) {
                return null;
            }
        });
        monitorService.listAlarmRules(null, null);

        monitorService.setMonitorAlarmRuleDAO(new MonitorAlarmRuleDAO() {
            @Override
            public List<AlarmRuleDO> listByBizCodeAndObjId(String bizCode, String monitorObjId) {
                throw new RuntimeException();
            }
        });
        monitorService.listAlarmRules(null, null);
    }

    @Test(expected = MonitorSalException.class)
    public void listMonitorDataByProductTimeScope() {

        DefaultMonitorService monitorService = new DefaultMonitorService();
        monitorService.setMonitorDataDAO(new MonitorDataDAO() {

            @Override
            public MonitorDataDO getMonitorData(String bizCode, String monitorObjId, Date productTime) {
                return null;
            }

            @Override
            public List<MonitorDataDO> listMonitorDataByProductTimeScope(String bizCode, String monitorObjId, Date beginTime, Date endTime,
                                                                         int currentPage, int pageSize) {
                return null;
            }
        });
        monitorService.listMonitorDataByProductTimeScope(null, null,
                null, null,
                null, null);

        monitorService = new DefaultMonitorService();
        monitorService.setMonitorDataDAO(new MonitorDataDAO() {

            @Override
            public MonitorDataDO getMonitorData(String bizCode, String monitorObjId, Date productTime) {
                return null;
            }

            @Override
            public List<MonitorDataDO> listMonitorDataByProductTimeScope(String bizCode, String monitorObjId, Date beginTime, Date endTime,
                                                                         int currentPage, int pageSize) {
                throw new RuntimeException();
            }
        });
        monitorService.listMonitorDataByProductTimeScope(null, null,
                null, null,
                null, null);
    }
}