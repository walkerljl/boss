package org.walkerljl.boss.dao.daointerface.auth.res.impl;

import org.springframework.stereotype.Repository;
import org.walkerljl.boss.dao.daointerface.auth.res.UrlDAO;
import org.walkerljl.boss.dao.dataobject.auth.res.UrlDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * UrlDaoImpl
 *
 * @author lijunlin
 */
@Repository("urlDAO")
public class UrlDAOImpl extends BaseDAOImpl<Long, UrlDO> implements UrlDAO {

}
