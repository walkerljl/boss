package org.walkerljl.boss.dao.daointerface.blog.impl;

import org.springframework.stereotype.Repository;
import org.walkerljl.boss.dao.daointerface.blog.CollectionDAO;
import org.walkerljl.boss.dao.dataobject.blog.CollectionDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * CollectionDaoImpl
 *
 * @author lijunlin
 */
@Repository("collectionDAO")
public class CollectionDAOImpl extends BaseDAOImpl<Long, CollectionDO> implements CollectionDAO {

}
