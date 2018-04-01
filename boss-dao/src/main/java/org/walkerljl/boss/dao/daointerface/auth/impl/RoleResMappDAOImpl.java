package org.walkerljl.boss.dao.daointerface.auth.impl;

import org.springframework.stereotype.Repository;
import org.walkerljl.boss.dao.daointerface.auth.RoleResMappDAO;
import org.walkerljl.boss.dao.dataobject.auth.RoleResMappDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * RoleResMappDaoImpl
 *
 * @author lijunlin
 */
@Repository("roleResMappDAO")
public class RoleResMappDAOImpl extends BaseDAOImpl<Long, RoleResMappDO> implements RoleResMappDAO {

}
