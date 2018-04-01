package org.walkerljl.boss.dao.daointerface.identity.impl;

import org.springframework.stereotype.Repository;
import org.walkerljl.boss.dao.daointerface.identity.AppDAO;
import org.walkerljl.boss.dao.dataobject.identity.AppDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * AppDao
 *
 * @author lijunlin
 */
@Repository("appDAO")
public class AppDAOImpl extends BaseDAOImpl<Long, AppDO> implements AppDAO {

}
