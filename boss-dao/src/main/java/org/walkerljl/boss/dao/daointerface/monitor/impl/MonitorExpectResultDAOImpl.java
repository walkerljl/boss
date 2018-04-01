package org.walkerljl.boss.dao.daointerface.monitor.impl;

import org.springframework.stereotype.Component;
import org.walkerljl.boss.dao.daointerface.monitor.MonitorExpectResultDAO;
import org.walkerljl.boss.dao.dataobject.monitor.MonitorExpectResultDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * MonitorExpectResultDaoImpl
 *
 * @author lijunlin
 */
@Component("monitorExpectResultDAO")
public class MonitorExpectResultDAOImpl extends BaseDAOImpl<Long, MonitorExpectResultDO> implements MonitorExpectResultDAO {

}
