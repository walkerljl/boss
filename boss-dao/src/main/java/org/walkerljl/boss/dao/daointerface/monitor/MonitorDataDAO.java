package org.walkerljl.boss.dao.daointerface.monitor;

import java.util.Date;
import java.util.List;

import org.walkerljl.boss.dao.dataobject.monitor.MonitorDataDO;

/**
 * 监控数据数据访问接口
 *
 * @author xingxun
 */
public interface MonitorDataDAO {

    /**
     * 根据业务编码、监控对象ID、监控数据ID查询监控数据
     *
     * @param bizCode 接入业务编码
     * @param objId 监控对象ID
     * @param time 监控数据产出时间
     * @return
     */
    MonitorDataDO getMonitorData(String bizCode, String objId, Date time);

    /**
     * 根据生产时间间隔查询监控数据列表
     *
     * @param bizCode 接入业务编码
     * @param objId 监控对象ID
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param currentPage 当前页码
     * @param pageSize 分页大小
     * @return
     */
    List<MonitorDataDO> listMonitorDataByProductTimeScope(String bizCode, String objId,
                                                          Date beginTime, Date endTime,
                                                          int currentPage, int pageSize);
}