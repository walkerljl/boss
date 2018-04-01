package org.walkerljl.boss.dao.daointerface.auth.res.impl;

import org.springframework.stereotype.Repository;
import org.walkerljl.boss.dao.daointerface.auth.res.DataDAO;
import org.walkerljl.boss.dao.dataobject.auth.res.DataDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;


/**
 * DataDaoImpl
 *
 * @author lijunlin
 */
@Repository("dataDAO")
public class DataDAOImpl extends BaseDAOImpl<Long, DataDO> implements DataDAO {

}
