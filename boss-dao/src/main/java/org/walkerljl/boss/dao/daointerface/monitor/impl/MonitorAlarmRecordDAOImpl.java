package org.walkerljl.boss.dao.daointerface.monitor.impl;

import java.util.HashMap;
import java.util.Map;

import org.walkerljl.boss.dao.daointerface.monitor.MonitorAlarmRecordDAO;
import org.walkerljl.boss.dao.dataobject.monitor.AlarmRecordDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * MonitorAlarmRecordDAOImpl
 *
 * @author xingxun
 */
public class MonitorAlarmRecordDAOImpl extends BaseDAOImpl<Long, AlarmRecordDO> implements MonitorAlarmRecordDAO {

    public MonitorAlarmRecordDAOImpl() {
        super.baseNameSpace = "org.walkerljl.boss.dao.daointerface.monitor.MonitorAlarmRecordDAO";
    }

    @Override
    public void save(AlarmRecordDO alarmRecord) {
        insert(getNameSpace("save"), alarmRecord);
    }

    @Override
    public void markAlarmRecordToCompleted(String bizCode, String objId, Long id) {
        Map<String, Object> condition = new HashMap<>(3);
        condition.put("bizCode", bizCode);
        condition.put("objId", objId);
        condition.put("id", id);

        update(getNameSpace("markAlarmRecordToCompleted"), condition);
    }

    @Override
    public AlarmRecordDO getAlarmRecord(String bizCode, String objId, Long id) {
        Map<String, Object> condition = new HashMap<>(3);
        condition.put("bizCode", bizCode);
        condition.put("objId", objId);
        condition.put("id", id);

        return select(getNameSpace("getAlarmRecord"), condition);
    }
}