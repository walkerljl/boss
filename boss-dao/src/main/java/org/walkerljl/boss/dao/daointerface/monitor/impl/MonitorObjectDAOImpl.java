package org.walkerljl.boss.dao.daointerface.monitor.impl;

import org.springframework.stereotype.Component;
import org.walkerljl.boss.dao.daointerface.monitor.MonitorObjectDAO;
import org.walkerljl.boss.dao.dataobject.monitor.MonitorObjectDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * MonitorObjectDaoImpl
 *
 * @author lijunlin
 */
@Component("monitorObjectDAO")
public class MonitorObjectDAOImpl extends BaseDAOImpl<Long, MonitorObjectDO> implements MonitorObjectDAO {

}
