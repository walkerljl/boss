package org.walkerljl.boss.service.auth.res.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.walkerljl.boss.dao.daointerface.auth.res.DataTypeDAO;
import org.walkerljl.boss.dao.dataobject.auth.res.DataTypeDO;
import org.walkerljl.boss.service.auth.res.DataTypeService;
import org.walkerljl.boss.support.dao.daointerface.BaseDAO;
import org.walkerljl.boss.support.service.impl.JqueryDatatableBaseServiceImpl;

/**
 * DataTypeServiceImpl
 *
 * @author lijunlin
 */
@Service("dataTypeService")
public class DataTypeServiceImpl extends JqueryDatatableBaseServiceImpl<Long, DataTypeDO> implements DataTypeService {

    @Resource
    private DataTypeDAO dataTypeDAO;

    @Override
    public BaseDAO<Long, DataTypeDO> getDAO() {
        return dataTypeDAO;
    }
}