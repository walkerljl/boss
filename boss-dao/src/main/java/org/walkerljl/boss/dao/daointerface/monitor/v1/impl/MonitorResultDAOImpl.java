package org.walkerljl.boss.dao.daointerface.monitor.v1.impl;

import org.springframework.stereotype.Repository;
import org.walkerljl.boss.dao.daointerface.monitor.v1.MonitorResultDAO;
import org.walkerljl.boss.dao.dataobject.monitor.v1.MonitorResultDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * MonitorResultDaoImpl
 *
 * @author lijunlin
 */
@Repository("monitorResultDAO")
public class MonitorResultDAOImpl extends BaseDAOImpl<Long, MonitorResultDO> implements MonitorResultDAO {
}
