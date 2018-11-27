package org.walkerljl.boss.dao.daointerface.monitor;

import org.walkerljl.boss.dao.dataobject.monitor.MonitorObjMetaDataDO;

/**
 * 监控对象元数据数据访问接口
 *
 * @author xingxun
 */
public interface MonitorObjMetaDataDAO {

    /**
     * 根据业务编码和监控对象ID查询监控对象元数据
     *
     * @param bizCode 接入业务编码
     * @param objId 监控对象ID
     * @return
     */
    MonitorObjMetaDataDO getByBizCodeAndObjId(String bizCode, String objId);
}