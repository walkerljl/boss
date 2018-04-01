package org.walkerljl.boss.dao.daointerface.monitor.impl;

import org.springframework.stereotype.Component;
import org.walkerljl.boss.dao.daointerface.monitor.MonitorParamDAO;
import org.walkerljl.boss.dao.dataobject.monitor.MonitorParamDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * MonitorParamDaoImpl
 *
 * @author lijunlin
 */
@Component("monitorParamDAO")
public class MonitorParamDAOImpl extends BaseDAOImpl<Long, MonitorParamDO> implements MonitorParamDAO {

}
