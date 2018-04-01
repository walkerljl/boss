package org.walkerljl.boss.service.monitor.collect;

import org.walkerljl.boss.dao.dataobject.monitor.MonitorResultDO;
import org.walkerljl.boss.service.monitor.collect.task.MonitorTask;

/**
 * Checker
 *
 * @author lijunlin
 */
public interface Checker {

    MonitorResultDO check(MonitorTask monitorTask);
}
