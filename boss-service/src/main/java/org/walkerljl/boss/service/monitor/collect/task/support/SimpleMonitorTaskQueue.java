package org.walkerljl.boss.service.monitor.collect.task.support;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Component;
import org.walkerljl.boss.common.Constants;
import org.walkerljl.boss.service.monitor.collect.task.MonitorTask;
import org.walkerljl.boss.service.monitor.collect.task.MonitorTaskQueue;
import org.walkerljl.toolkit.logging.Logger;
import org.walkerljl.toolkit.logging.LoggerFactory;

/**
 * SimpleMonitorTaskQueue
 *
 * @author lijunlin
 */
@Component("monitorTaskQueue")
public class SimpleMonitorTaskQueue implements MonitorTaskQueue {

    private static final Logger                     LOGGER    = LoggerFactory.getLogger(SimpleMonitorTaskQueue.class);
    private              int                        queueSize = Constants.getConfigurator().getAsInteger("monitor.taskqueue.size", 1000);
    private              BlockingQueue<MonitorTask> queue     = new LinkedBlockingQueue<MonitorTask>(queueSize);

    @Override
    public void add(MonitorTask task) {
        if (task == null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Monitor task is null.");
            }
            return;
        }
        queue.add(task);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(String.format("Add one monitor task to queue:%s", task));
        }
    }

    @Override
    public MonitorTask pull() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            LOGGER.error(e);
            return null;
        }
    }
}
