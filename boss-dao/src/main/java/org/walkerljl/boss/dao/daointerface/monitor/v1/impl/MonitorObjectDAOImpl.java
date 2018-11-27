package org.walkerljl.boss.dao.daointerface.monitor.v1.impl;

import org.springframework.stereotype.Component;
import org.walkerljl.boss.dao.daointerface.monitor.v1.MonitorObjectDAO;
import org.walkerljl.boss.dao.dataobject.monitor.v1.MonitorObjectDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * MonitorObjectDaoImpl
 *
 * @author lijunlin
 */
@Component("monitorObjectDAO")
public class MonitorObjectDAOImpl extends BaseDAOImpl<Long, MonitorObjectDO> implements MonitorObjectDAO {

}
