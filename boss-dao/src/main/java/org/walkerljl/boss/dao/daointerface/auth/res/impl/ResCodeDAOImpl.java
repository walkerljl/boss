package org.walkerljl.boss.dao.daointerface.auth.res.impl;

import org.springframework.stereotype.Repository;
import org.walkerljl.boss.dao.daointerface.auth.res.ResCodeDAO;
import org.walkerljl.boss.dao.dataobject.auth.res.ResCodeDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * ResCodeDaoImpl
 *
 * @author lijunlin
 */
@Repository("resCodeDAO")
public class ResCodeDAOImpl extends BaseDAOImpl<Long, ResCodeDO> implements ResCodeDAO {

}
