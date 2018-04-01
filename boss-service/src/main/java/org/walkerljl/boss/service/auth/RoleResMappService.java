package org.walkerljl.boss.service.auth;

import java.util.List;

import org.walkerljl.boss.dao.dataobject.auth.RoleResMappDO;
import org.walkerljl.boss.support.service.BaseService;

/**
 * 角色、资源映射业务接口
 *
 * @author lijunlin
 */
public interface RoleResMappService extends BaseService<Long, RoleResMappDO> {

    /**
     * 根据角色ID列表获取角色、资源授权
     */
    List<RoleResMappDO> queryByRoleIds(List<Long> roleIds);

    /**
     * 根据角色ID列表获取授权的资源码ID列表
     *
     * @param roleIds
     * @return
     */
    List<Long> queryAuthResCodeIdsByRoleIds(List<Long> roleIds);
}
