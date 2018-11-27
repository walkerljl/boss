package org.walkerljl.boss.service.v1.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.walkerljl.boss.dao.daointerface.identity.AppDAO;
import org.walkerljl.boss.dao.daointerface.monitor.v1.MonitorObjectDAO;
import org.walkerljl.boss.dao.daointerface.monitor.v1.MonitorResultDAO;
import org.walkerljl.boss.dao.dataobject.identity.AppDO;
import org.walkerljl.boss.dao.dataobject.monitor.v1.MonitorObjectDO;
import org.walkerljl.boss.dao.dataobject.monitor.v1.MonitorResultDO;
import org.walkerljl.boss.service.v1.MonitorResultService;
import org.walkerljl.boss.support.dao.daointerface.BaseDAO;
import org.walkerljl.boss.support.service.impl.JqueryDatatableBaseServiceImpl;

/**
 * MonitorResultServiceImpl
 *
 * @author lijunlin
 */
@Service("monitorResultService")
public class MonitorResultServiceImpl extends JqueryDatatableBaseServiceImpl<Long, MonitorResultDO> implements MonitorResultService {

    @Resource
    private MonitorResultDAO monitorResultDAO;
    @Resource
    private AppDAO           appDAO;
    @Resource
    private MonitorObjectDAO monitorObjectDAO;

    @Override
    public BaseDAO<Long, MonitorResultDO> getDAO() {
        return monitorResultDAO;
    }

    @Override
    public Map<String, Object> selectJqueryDatatablePage(MonitorResultDO condition) {
        Map<String, Object> resultMap = super.selectJqueryDatatablePage(condition);
        List<MonitorResultDO> monitorResults = (List<MonitorResultDO>) resultMap.get(DATATABLE_DATA_KEY);
        if (CollectionUtils.isEmpty(monitorResults)) {
            return resultMap;
        }
        for (MonitorResultDO ele : monitorResults) {
            AppDO app = appDAO.selectByKey(ele.getAppId());
            if (app != null) {
                ele.setAppName(app.getName());
            }

            MonitorObjectDO monitorObject = monitorObjectDAO.selectByKey(ele.getMonitorObjectId());
            if (monitorObject != null) {
                ele.setMonitorObjectName(monitorObject.getTarget());
            }
        }
        return resultMap;
    }
}
