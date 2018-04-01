package org.walkerljl.boss.service.auth.res.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.walkerljl.boss.dao.daointerface.auth.res.ApiDAO;
import org.walkerljl.boss.dao.dataobject.auth.res.ApiDO;
import org.walkerljl.boss.service.auth.res.ApiService;
import org.walkerljl.boss.support.dao.daointerface.BaseDAO;
import org.walkerljl.boss.support.service.impl.JqueryDatatableBaseServiceImpl;


/**
 * ApiServiceImpl
 *
 * @author lijunlin
 */
@Service("apiService")
public class ApiServiceImpl extends JqueryDatatableBaseServiceImpl<Long, ApiDO> implements ApiService {

    @Resource
    private ApiDAO apiDAO;

    @Override
    public BaseDAO<Long, ApiDO> getDAO() {
        return apiDAO;
    }
}