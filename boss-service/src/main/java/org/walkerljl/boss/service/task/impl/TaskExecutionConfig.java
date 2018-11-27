package org.walkerljl.boss.service.task.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.walkerljl.boss.service.task.impl.executor.TaskExecutorConfig;
import org.walkerljl.boss.service.task.model.base.BaseTaskModel;
import org.walkerljl.boss.service.task.model.enums.status.TaskStatusEnum;

/**
 * 任务执行配置
 *
 * @author xingxun
 */
public class TaskExecutionConfig extends BaseTaskModel {

    private static final long serialVersionUID = -4088342977048677947L;

    /** 任务加载间隔，单位：秒*/
    private long taskLoadInterval       = 30;
    /** 每次加载的最大任务数*/
    private int  maxTaskQuantityPerLoad = 1000;
    /** 每次分页查询的任务条数*/
    private int  taskLoadPageSize       = 200;
    /** 任务执行超时时间，单位：秒*/
    private long taskExecutionTimeout   = 300;

    /** 任务执行器配置Map*/
    private ConcurrentMap<String, TaskExecutorConfig> taskExecutorConfigMap = new ConcurrentHashMap<>(3);

    /**
     * 获取执行器配置
     *
     * @param taskStatus
     * @return
     */
    public TaskExecutorConfig getExecutorConfig(TaskStatusEnum taskStatus) {
        if (taskStatus == null) {
            return null;
        }
        TaskExecutorConfig taskExecutorConfig = taskExecutorConfigMap.get(taskStatus.getCode());
        if (taskExecutorConfig != null) {
            return taskExecutorConfig;
        }
        synchronized (this) {
            if (taskExecutorConfig != null) {
                return taskExecutorConfig;
            }
            taskExecutorConfig = new TaskExecutorConfig();
        }
        taskExecutorConfigMap.putIfAbsent(taskStatus.getCode(), taskExecutorConfig);
        return taskExecutorConfig;
    }

    /**
     * Getter method for property <tt>taskLoadInterval</tt>.
     *
     * @return property value of taskLoadInterval
     */
    public long getTaskLoadInterval() {
        return taskLoadInterval;
    }

    /**
     * Getter method for property <tt>maxTaskQuantityPerLoad</tt>.
     *
     * @return property value of maxTaskQuantityPerLoad
     */
    public int getMaxTaskQuantityPerLoad() {
        return maxTaskQuantityPerLoad;
    }

    /**
     * Getter method for property <tt>taskLoadPageSize</tt>.
     *
     * @return property value of taskLoadPageSize
     */
    public int getTaskLoadPageSize() {
        return taskLoadPageSize;
    }

    /**
     * Getter method for property <tt>taskExecutionTimeout</tt>.
     *
     * @return property value of taskExecutionTimeout
     */
    public long getTaskExecutionTimeout() {
        return taskExecutionTimeout;
    }

    /**
     * Getter method for property <tt>taskExecutorConfigMap</tt>.
     *
     * @return property value of taskExecutorConfigMap
     */
    public ConcurrentMap<String, TaskExecutorConfig> getTaskExecutorConfigMap() {
        return taskExecutorConfigMap;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}