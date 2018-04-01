package org.walkerljl.boss.service.auth.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.walkerljl.boss.dao.daointerface.auth.DeptDAO;
import org.walkerljl.boss.dao.dataobject.auth.DeptDO;
import org.walkerljl.boss.service.auth.DeptService;
import org.walkerljl.boss.support.dao.daointerface.BaseDAO;
import org.walkerljl.boss.support.service.impl.JqueryDatatableBaseServiceImpl;

/**
 * DeptServiceImpl
 *
 * @author lijunlin
 */
@Service("deptService")
public class DeptServiceImpl extends JqueryDatatableBaseServiceImpl<Long, DeptDO> implements DeptService {

    @Resource
    private DeptDAO deptDAO;

    @Override
    public BaseDAO<Long, DeptDO> getDAO() {
        return deptDAO;
    }
}