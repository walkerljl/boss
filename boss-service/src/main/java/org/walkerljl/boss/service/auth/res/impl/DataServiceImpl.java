package org.walkerljl.boss.service.auth.res.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.walkerljl.boss.dao.daointerface.auth.res.DataDAO;
import org.walkerljl.boss.dao.dataobject.auth.res.DataDO;
import org.walkerljl.boss.service.auth.res.DataService;
import org.walkerljl.boss.support.dao.daointerface.BaseDAO;
import org.walkerljl.boss.support.service.impl.JqueryDatatableBaseServiceImpl;

/**
 * DataServiceImpl
 *
 * @author lijunlin
 */
@Service("dataService")
public class DataServiceImpl extends JqueryDatatableBaseServiceImpl<Long, DataDO> implements DataService {

    @Resource
    private DataDAO dataDAO;

    @Override
    public BaseDAO<Long, DataDO> getDAO() {
        return dataDAO;
    }
}