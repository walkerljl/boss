package org.walkerljl.boss.service.v1.collect.check.storer;

import org.walkerljl.boss.dao.dataobject.monitor.v1.MonitorResultDO;
import org.walkerljl.toolkit.standard.machine.Machine;

/**
 * MonitorResultStorer
 *
 * @author lijunlin
 */
public interface MonitorResultStorer extends Machine, Runnable {

    void store(MonitorResultDO monitorResult);
}
