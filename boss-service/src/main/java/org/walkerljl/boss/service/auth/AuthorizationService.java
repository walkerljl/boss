package org.walkerljl.boss.service.auth;


import java.util.List;

import org.walkerljl.boss.dao.dataobject.auth.AuthorizationDO;
import org.walkerljl.boss.support.service.BaseService;

/**
 * 授权业务接口
 *
 * @author lijunlin
 */
public interface AuthorizationService extends BaseService<Long, AuthorizationDO> {

    /**
     * 根据用户ID查询岗位授权
     *
     * @param userId
     * @return
     */
    List<AuthorizationDO> queryPostAuthsByUserId(String userId);

    /**
     * 根据用户ID查询角色授权
     *
     * @param userId
     * @return
     */
    List<AuthorizationDO> queryRoleAuthsByUserId(String userId);
}
