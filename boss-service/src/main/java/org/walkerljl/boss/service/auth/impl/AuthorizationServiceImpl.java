package org.walkerljl.boss.service.auth.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.walkerljl.boss.dao.daointerface.auth.AuthorizationDAO;
import org.walkerljl.boss.dao.dataobject.auth.AuthorizationDO;
import org.walkerljl.boss.model.enums.auth.AuthType;
import org.walkerljl.boss.sdk.auth.enums.AuthObjectType;
import org.walkerljl.boss.service.auth.AuthorizationService;
import org.walkerljl.boss.support.dao.daointerface.BaseDAO;
import org.walkerljl.boss.support.enums.StatusEnum;
import org.walkerljl.boss.support.service.impl.BaseServiceImpl;

/**
 * AuthorizationServiceImpl
 *
 * @author lijunlin
 */
@Service("authorizationService")
public class AuthorizationServiceImpl extends BaseServiceImpl<Long, AuthorizationDO> implements AuthorizationService {

    @Resource
    private AuthorizationDAO authorizationDAO;

    @Override
    public BaseDAO<Long, AuthorizationDO> getDAO() {
        return authorizationDAO;
    }

    @Override
    public List<AuthorizationDO> queryPostAuthsByUserId(String userId) {
        AuthorizationDO condition = new AuthorizationDO();
        condition.setObjectType(Integer.valueOf(AuthObjectType.USER.getCode()));
        condition.setObjectId(userId);
        condition.setAuthType(AuthType.POST.getValue());
        condition.setStatus(StatusEnum.ENABLED.getCode());
        return selectList(condition);
    }

    @Override
    public List<AuthorizationDO> queryRoleAuthsByUserId(String userId) {
        AuthorizationDO condition = new AuthorizationDO();
        condition.setObjectType(Integer.valueOf(AuthObjectType.USER.getCode()));
        condition.setObjectId(userId);
        condition.setAuthType(AuthType.ROLE.getValue());
        condition.setStatus(StatusEnum.ENABLED.getCode());
        return selectList(condition);
    }
}