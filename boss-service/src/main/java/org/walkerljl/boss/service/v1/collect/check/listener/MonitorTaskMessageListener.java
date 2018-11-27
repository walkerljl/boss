package org.walkerljl.boss.service.v1.collect.check.listener;

import org.walkerljl.boss.service.v1.collect.task.MonitorTask;

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
