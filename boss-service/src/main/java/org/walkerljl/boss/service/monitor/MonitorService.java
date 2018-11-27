package org.walkerljl.boss.service.monitor;

import java.util.Date;
import java.util.List;

import org.walkerljl.boss.service.monitor.model.MonitorData;
import org.walkerljl.boss.service.monitor.model.MonitorObjMetaData;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRecord;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRule;

/**
 * 监控服务
 *
 * @author xingxun
 */
public interface MonitorService {

    /**
     * 添加预警记录
     *
     * @param alarmRecord 预警记录
     */
    void saveAlarmRecord(AlarmRecord alarmRecord);

    /**
     * 标注预警记录已经处理完成
     *
     * @param bizCode 业务编码
     * @param objId 对象ID
     * @param id ID
     */
    void markAlarmRecordToCompleted(String bizCode, String objId, String id);

    /**
     * 根据业务编码、监控对象ID、监控数据ID查询监控数据
     *
     * @param bizCode 接入业务编码
     * @param monitorObjId 监控对象ID
     * @param prodcutTime 监控数据产出时间
     * @return
     */
    MonitorData getMonitorData(String bizCode, String monitorObjId, Date prodcutTime);

    /**
     * 查询预警记录
     *
     * @param bizCode 业务编码
     * @param objId 对象ID
     * @param id ID
     * @return
     */
    AlarmRecord getAlarmRecord(String bizCode, String objId, String id);

    /**
     * 根据业务编码和监控对象ID查询监控对象元数据
     *
     * @param bizCode 接入业务编码
     * @param monitorObjId 监控对象ID
     * @return
     */
    MonitorObjMetaData getMonitorObjMetaData(String bizCode, String monitorObjId);

    /**
     * 根据业务编码和监控对象ID查询预警规则列表
     *
     * @param bizCode 接入业务编码
     * @param monitorObjId 监控对象ID
     * @return
     */
    List<AlarmRule> listAlarmRules(String bizCode, String monitorObjId);

    /**
     * 根据生产时间间隔查询监控数据列表
     *
     * @param bizCode 接入业务编码
     * @param monitorObjId 监控对象ID
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param currentPage 当前页码
     * @param pageSize 分页大小
     * @return
     */
    List<MonitorData> listMonitorDataByProductTimeScope(String bizCode, String monitorObjId,
                                                        Date beginTime, Date endTime,
                                                        Integer currentPage, Integer pageSize);
}