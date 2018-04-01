package org.walkerljl.boss.dao.daointerface.auth.impl;

import org.springframework.stereotype.Repository;
import org.walkerljl.boss.dao.daointerface.auth.AuthorizationDAO;
import org.walkerljl.boss.dao.dataobject.auth.AuthorizationDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * AuthorizationDaoImpl
 *
 * @author lijunlin
 */
@Repository("authorizationDAO")
public class AuthorizationDAOImpl extends BaseDAOImpl<Long, AuthorizationDO> implements AuthorizationDAO {

}
