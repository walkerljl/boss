package org.walkerljl.boss.dao.daointerface.monitor;

import org.walkerljl.boss.dao.dataobject.monitor.AlarmRecordDO;

/**
 * 预警记录数据访问接口
 *
 * @author xingxun
 */
public interface MonitorAlarmRecordDAO {

    /**
     * 添加
     *
     * @param alarmRecord 预警记录
     */
    void save(AlarmRecordDO alarmRecord);

    /**
     * 标注预警记录已经处理完成
     *
     * @param bizCode 业务编码
     * @param objId 对象ID
     * @param id ID
     */
    void markAlarmRecordToCompleted(String bizCode, String objId, Long id);

    /**
     * 查询预警记录
     *
     * @param bizCode 业务编码
     * @param objId 对象ID
     * @param id ID
     * @return
     */
    AlarmRecordDO getAlarmRecord(String bizCode, String objId, Long id);
}