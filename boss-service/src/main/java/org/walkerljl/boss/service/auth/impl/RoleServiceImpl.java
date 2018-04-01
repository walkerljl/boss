package org.walkerljl.boss.service.auth.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.walkerljl.boss.dao.daointerface.auth.RoleDAO;
import org.walkerljl.boss.dao.dataobject.auth.RoleDO;
import org.walkerljl.boss.service.auth.RoleService;
import org.walkerljl.boss.support.dao.daointerface.BaseDAO;
import org.walkerljl.boss.support.service.impl.BaseServiceImpl;

/**
 * RoleServiceImpl
 *
 * @author lijunlin
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Long, RoleDO> implements RoleService {

    @Resource
    private RoleDAO roleDAO;

    @Override
    public BaseDAO<Long, RoleDO> getDAO() {
        return roleDAO;
    }
}