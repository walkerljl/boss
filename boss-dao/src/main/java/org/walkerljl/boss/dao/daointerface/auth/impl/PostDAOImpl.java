package org.walkerljl.boss.dao.daointerface.auth.impl;

import org.springframework.stereotype.Repository;
import org.walkerljl.boss.dao.daointerface.auth.PostDAO;
import org.walkerljl.boss.dao.dataobject.auth.PostDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * PostDaoImpl
 *
 * @author lijunlin
 */
@Repository("postDAO")
public class PostDAOImpl extends BaseDAOImpl<Long, PostDO> implements PostDAO {

}
