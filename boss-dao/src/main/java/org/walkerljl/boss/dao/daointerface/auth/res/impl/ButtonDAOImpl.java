package org.walkerljl.boss.dao.daointerface.auth.res.impl;

import org.springframework.stereotype.Repository;
import org.walkerljl.boss.dao.daointerface.auth.res.ButtonDAO;
import org.walkerljl.boss.dao.dataobject.auth.res.ButtonDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * ButtonDaoImpl
 *
 * @author lijunlin
 */
@Repository("buttonDAO")
public class ButtonDAOImpl extends BaseDAOImpl<Long, ButtonDO> implements ButtonDAO {

}
