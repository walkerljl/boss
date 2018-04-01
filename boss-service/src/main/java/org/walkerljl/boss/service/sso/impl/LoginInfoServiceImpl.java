package org.walkerljl.boss.service.sso.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.walkerljl.boss.dao.daointerface.sso.LoginInfoDAO;
import org.walkerljl.boss.dao.dataobject.sso.LoginInfoDO;
import org.walkerljl.boss.service.sso.LoginInfoService;
import org.walkerljl.boss.support.dao.daointerface.BaseDAO;
import org.walkerljl.boss.support.service.impl.JqueryDatatableBaseServiceImpl;

/**
 * LoginInfoServiceImpl
 *
 * @author lijunlin
 */
@Service("loginInfoService")
public class LoginInfoServiceImpl extends JqueryDatatableBaseServiceImpl<Long, LoginInfoDO> implements LoginInfoService {

    @Resource
    private LoginInfoDAO loginInfoDAO;

    @Override
    public BaseDAO<Long, LoginInfoDO> getDAO() {
        return loginInfoDAO;
    }
}