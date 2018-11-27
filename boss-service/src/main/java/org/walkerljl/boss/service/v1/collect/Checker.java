package org.walkerljl.boss.service.v1.collect;

import org.walkerljl.boss.dao.dataobject.monitor.v1.MonitorResultDO;
import org.walkerljl.boss.service.v1.collect.task.MonitorTask;

/**
 * Checker
 *
 * @author lijunlin
 */
public interface Checker {

    MonitorResultDO check(MonitorTask monitorTask);
}
