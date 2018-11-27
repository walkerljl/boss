package org.walkerljl.boss.dao.daointerface.monitor.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.walkerljl.boss.dao.daointerface.monitor.MonitorDataDAO;
import org.walkerljl.boss.dao.dataobject.monitor.MonitorDataDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;
import org.walkerljl.toolkit.standard.model.paging.Paginator;

/**
 * MonitorDataDAOImpl
 *
 * @author xingxun
 */
public class MonitorDataDAOImpl extends BaseDAOImpl<Long, MonitorDataDO> implements MonitorDataDAO {

    public MonitorDataDAOImpl() {
        super.baseNameSpace = "org.walkerljl.boss.dao.daointerface.monitor.MonitorDataDAO";
    }

    @Override
    public MonitorDataDO getMonitorData(String bizCode, String objId, Date time) {
        Map<String, Object> condition = new HashMap<>(3);
        condition.put("bizCode", bizCode);
        condition.put("objId", objId);
        condition.put("time", time);

        return select("getMonitorData", condition);
    }

    @Override
    public List<MonitorDataDO> listMonitorDataByProductTimeScope(String bizCode, String objId, Date beginTime, Date endTime,
                                                                 int currentPage, int pageSize) {
        Map<String, Object> condition = new HashMap<>(6);
        condition.put("bizCode", bizCode);
        condition.put("objId", objId);
        condition.put("beginTime", beginTime);
        condition.put("endTime", endTime);
        Paginator<MonitorDataDO> paginator = new Paginator<>(currentPage, pageSize);
        condition.put("beginIndex", paginator.getBeginIndex());
        condition.put("pageSize", paginator.getPageSize());

        return selectList("listMonitorDataByProductTimeScope", condition);
    }
}