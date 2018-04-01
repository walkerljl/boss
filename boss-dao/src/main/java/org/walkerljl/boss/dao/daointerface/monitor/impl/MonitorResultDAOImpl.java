package org.walkerljl.boss.dao.daointerface.monitor.impl;

import org.springframework.stereotype.Repository;
import org.walkerljl.boss.dao.daointerface.monitor.MonitorResultDAO;
import org.walkerljl.boss.dao.dataobject.monitor.MonitorResultDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * MonitorResultDaoImpl
 *
 * @author lijunlin
 */
@Repository("monitorResultDAO")
public class MonitorResultDAOImpl extends BaseDAOImpl<Long, MonitorResultDO> implements MonitorResultDAO {
}
