package org.walkerljl.boss.service.monitor.collect.check.storer;

import org.walkerljl.boss.dao.dataobject.monitor.MonitorResultDO;
import org.walkerljl.toolkit.standard.Machine;

/**
 * MonitorResultStorer
 *
 * @author lijunlin
 */
public interface MonitorResultStorer extends Machine, Runnable {

    void store(MonitorResultDO monitorResult);
}
