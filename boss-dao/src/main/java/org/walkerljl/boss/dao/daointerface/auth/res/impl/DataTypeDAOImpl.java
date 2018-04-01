package org.walkerljl.boss.dao.daointerface.auth.res.impl;

import org.springframework.stereotype.Repository;
import org.walkerljl.boss.dao.daointerface.auth.res.DataTypeDAO;
import org.walkerljl.boss.dao.dataobject.auth.res.DataTypeDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * DataTypeDaoImpl
 *
 * @author lijunlin
 */
@Repository("dataTypeDAO")
public class DataTypeDAOImpl extends BaseDAOImpl<Long, DataTypeDO> implements DataTypeDAO {

}
