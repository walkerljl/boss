package org.walkerljl.boss.service.monitor.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.walkerljl.boss.dao.daointerface.identity.AppDAO;
import org.walkerljl.boss.dao.daointerface.monitor.MonitorObjectDAO;
import org.walkerljl.boss.dao.dataobject.identity.AppDO;
import org.walkerljl.boss.dao.dataobject.monitor.MonitorObjectDO;
import org.walkerljl.boss.service.monitor.MonitorObjectService;
import org.walkerljl.boss.support.dao.daointerface.BaseDAO;
import org.walkerljl.boss.support.service.impl.JqueryDatatableBaseServiceImpl;

/**
 * MonitorObjectServiceImpl
 *
 * @author lijunlin
 */
@Service("monitorObjectService")
public class MonitorObjectServiceImpl extends JqueryDatatableBaseServiceImpl<Long, MonitorObjectDO> implements MonitorObjectService {

    @Resource
    private MonitorObjectDAO monitorObjectDAO;
    @Resource
    private AppDAO           appDAO;

    @Override
    public BaseDAO<Long, MonitorObjectDO> getDAO() {
        return monitorObjectDAO;
    }

    @Override
    public Map<String, Object> selectJqueryDatatablePage(MonitorObjectDO condition) {
        Map<String, Object> resultMap = super.selectJqueryDatatablePage(condition);
        List<MonitorObjectDO> monitorObjects = (List<MonitorObjectDO>) resultMap.get(DATATABLE_DATA_KEY);
        if (CollectionUtils.isEmpty(monitorObjects)) {
            return resultMap;
        }
        for (MonitorObjectDO monitorObject : monitorObjects) {
            AppDO app = appDAO.selectByKey(monitorObject.getAppId());
            if (app != null) {
                monitorObject.setAppName(app.getName());
            }
        }
        return resultMap;
    }
}
