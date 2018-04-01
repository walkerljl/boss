package org.walkerljl.boss.dao.daointerface.auth.impl;

import org.springframework.stereotype.Repository;
import org.walkerljl.boss.dao.daointerface.auth.RoleDAO;
import org.walkerljl.boss.dao.dataobject.auth.RoleDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * RoleDaoImpl
 *
 * @author lijunlin
 */
@Repository("roleDAO")
public class RoleDAOImpl extends BaseDAOImpl<Long, RoleDO> implements RoleDAO {

}
