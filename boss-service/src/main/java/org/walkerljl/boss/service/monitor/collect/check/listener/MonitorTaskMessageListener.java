package org.walkerljl.boss.service.monitor.collect.check.listener;

import org.walkerljl.boss.service.monitor.collect.task.MonitorTask;

/**
 * MonitorTaskMessageListener
 *
 * @author lijunlin
 */
public interface MonitorTaskMessageListener {

    /**
     * Response to message
     *
     * @param message
     */
    void onMessage(MonitorTask message);
}
