package org.walkerljl.boss.dao.daointerface.auth.res.impl;

import org.springframework.stereotype.Repository;
import org.walkerljl.boss.dao.daointerface.auth.res.ApiDAO;
import org.walkerljl.boss.dao.dataobject.auth.res.ApiDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * ApiDaoImpl
 *
 * @author lijunlin
 */
@Repository("apiDAO")
public class ApiDAOImpl extends BaseDAOImpl<Long, ApiDO> implements ApiDAO {

}
