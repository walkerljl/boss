package org.walkerljl.boss.service.v1.collect.task;

/**
 * 监控任务队列
 *
 * @author lijunlin
 */
public interface MonitorTaskQueue {

    void add(MonitorTask task);

    MonitorTask pull();
}
