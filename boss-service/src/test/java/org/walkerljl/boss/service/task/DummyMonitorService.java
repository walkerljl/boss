package org.walkerljl.boss.service.task;

import java.util.Date;
import java.util.List;

import org.walkerljl.boss.service.monitor.MonitorService;
import org.walkerljl.boss.service.monitor.model.MonitorData;
import org.walkerljl.boss.service.monitor.model.MonitorObjMetaData;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRecord;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRule;

/**
 * DummyMonitorService
 *
 * @author xingxun
 */
public class DummyMonitorService implements MonitorService  {
    @Override
    public void saveAlarmRecord(AlarmRecord alarmRecord) {
    }

    @Override
    public void markAlarmRecordToCompleted(String bizCode, String objId, String id) {

    }

    @Override
    public MonitorData getMonitorData(String bizCode, String monitorObjId, Date prodcutTime) {
        return null;
    }

    @Override
    public AlarmRecord getAlarmRecord(String bizCode, String objId, String id) {
        return null;
    }

    @Override
    public MonitorObjMetaData getMonitorObjMetaData(String bizCode, String monitorObjId) {
        return null;
    }

    @Override
    public List<AlarmRule> listAlarmRules(String bizCode, String monitorObjId) {
        return null;
    }

    @Override
    public List<MonitorData> listMonitorDataByProductTimeScope(String bizCode, String monitorObjId, Date beginTime, Date endTime,
                                                               Integer currentPage, Integer pageSize) {
        return null;
    }
}