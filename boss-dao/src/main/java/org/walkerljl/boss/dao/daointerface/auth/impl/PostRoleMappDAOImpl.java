package org.walkerljl.boss.dao.daointerface.auth.impl;

import org.springframework.stereotype.Repository;
import org.walkerljl.boss.dao.daointerface.auth.PostRoleMappDAO;
import org.walkerljl.boss.dao.dataobject.auth.PostRoleMappDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * PostRoleMappDaoImpl
 *
 * @author lijunlin
 */
@Repository("postRoleMappDAO")
public class PostRoleMappDAOImpl extends BaseDAOImpl<Long, PostRoleMappDO> implements PostRoleMappDAO {

}
