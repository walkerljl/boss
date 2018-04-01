package org.walkerljl.boss.dao.daointerface.sso.impl;

import org.springframework.stereotype.Repository;
import org.walkerljl.boss.dao.daointerface.sso.LoginInfoDAO;
import org.walkerljl.boss.dao.dataobject.sso.LoginInfoDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * LoginInfoDaoImpl
 *
 * @author lijunlin
 */
@Repository("loginInfoDAO")
public class LoginInfoDAOImpl extends BaseDAOImpl<Long, LoginInfoDO> implements LoginInfoDAO {

}
