package org.walkerljl.boss.dao.daointerface.monitor.impl;

import java.util.HashMap;
import java.util.Map;

import org.walkerljl.boss.dao.daointerface.monitor.MonitorObjMetaDataDAO;
import org.walkerljl.boss.dao.dataobject.monitor.MonitorObjMetaDataDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * MonitorObjMetaDataDAOImpl
 *
 * @author xingxun
 */
public class MonitorObjMetaDataDAOImpl extends BaseDAOImpl<Long, MonitorObjMetaDataDO> implements MonitorObjMetaDataDAO {

    public MonitorObjMetaDataDAOImpl() {
        super.baseNameSpace = "org.walkerljl.boss.dao.daointerface.monitor.MonitorObjMetaDataDAO";
    }

    @Override
    public MonitorObjMetaDataDO getByBizCodeAndObjId(String bizCode, String objId) {
        Map<String, String> condition = new HashMap<>(2);
        condition.put("bizCode", bizCode);
        condition.put("objId", objId);

        return select(getNameSpace("getByBizCodeAndObjId"), condition);
    }
}