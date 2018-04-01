package org.walkerljl.boss.service.monitor.collect.task;

/**
 * 监控任务队列
 *
 * @author lijunlin
 */
public interface MonitorTaskQueue {

    void add(MonitorTask task);

    MonitorTask pull();
}
