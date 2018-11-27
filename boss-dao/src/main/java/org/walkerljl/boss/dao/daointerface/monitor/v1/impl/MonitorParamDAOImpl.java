package org.walkerljl.boss.dao.daointerface.monitor.v1.impl;

import org.springframework.stereotype.Component;
import org.walkerljl.boss.dao.daointerface.monitor.v1.MonitorParamDAO;
import org.walkerljl.boss.dao.dataobject.monitor.v1.MonitorParamDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * MonitorParamDaoImpl
 *
 * @author lijunlin
 */
@Component("monitorParamDAO")
public class MonitorParamDAOImpl extends BaseDAOImpl<Long, MonitorParamDO> implements MonitorParamDAO {

}
