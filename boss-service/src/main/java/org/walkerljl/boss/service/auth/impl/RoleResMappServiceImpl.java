package org.walkerljl.boss.service.auth.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.walkerljl.boss.dao.daointerface.auth.RoleResMappDAO;
import org.walkerljl.boss.dao.dataobject.auth.RoleResMappDO;
import org.walkerljl.boss.service.auth.RoleResMappService;
import org.walkerljl.boss.support.dao.daointerface.BaseDAO;
import org.walkerljl.boss.support.service.impl.BaseServiceImpl;
import org.walkerljl.toolkit.lang.CollectionUtils;
import org.walkerljl.toolkit.lang.ListUtils;

/**
 * RoleResMappServiceImpl
 *
 * @author lijunlin
 */
@Service("roleResMappService")
public class RoleResMappServiceImpl extends BaseServiceImpl<Long, RoleResMappDO> implements RoleResMappService {

    @Resource
    private RoleResMappDAO roleResMappDAO;

    @Override
    public BaseDAO<Long, RoleResMappDO> getDAO() {
        return roleResMappDAO;
    }

    @Override
    public List<RoleResMappDO> queryByRoleIds(List<Long> roleIds) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Long> queryAuthResCodeIdsByRoleIds(List<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return null;
        }
        List<RoleResMappDO> roleResMapps = queryByRoleIds(roleIds);
        if (CollectionUtils.isEmpty(roleResMapps)) {
            return null;
        }
        List<Long> resCodeIds = ListUtils.newArrayList();
        //for (RoleResMappDO roleResMapp : roleResMapps) {
        //    if (roleResMapp.isEnabled()) {
        //        resCodeIds.add(roleResMapp.getResCodeId());
        //    }
        //}
        return resCodeIds;
    }
}