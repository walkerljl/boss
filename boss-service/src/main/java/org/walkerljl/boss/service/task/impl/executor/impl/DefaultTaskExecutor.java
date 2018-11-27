package org.walkerljl.boss.service.task.impl.executor.impl;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.walkerljl.boss.service.task.impl.RunnableTask;
import org.walkerljl.boss.service.task.impl.executor.TaskExecutor;
import org.walkerljl.boss.service.task.impl.executor.TaskExecutorConfig;
import org.walkerljl.boss.support.common.util.NamedThreadFactory;
import org.walkerljl.toolkit.standard.machine.abstracts.AbstractMachine;
import org.walkerljl.toolkit.standard.machine.exception.CannotStartMachineException;
import org.walkerljl.toolkit.standard.machine.exception.CannotStopMachineException;

/**
 * 默认的任务执行器
 *
 * @author xingxun
 */
public class DefaultTaskExecutor extends AbstractMachine implements TaskExecutor {

    private TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    private ThreadPoolExecutor threadPoolExecutor;
    private TaskExecutorConfig taskExecutorConfig;

    /**
     * 构造函数
     *
     * @param taskExecutorConfig 任务执行器配置
     */
    public DefaultTaskExecutor(TaskExecutorConfig taskExecutorConfig) {
        this.taskExecutorConfig = taskExecutorConfig;
        threadPoolExecutor = new ThreadPoolExecutor(taskExecutorConfig.getCorePoolSize(), taskExecutorConfig.getMaxPoolSize(),
                taskExecutorConfig.getKeepAliveTime(), KEEP_ALIVE_TIME_UNIT,
                new ArrayBlockingQueue<>(taskExecutorConfig.getWorkQueueSize()),
                new NamedThreadFactory(taskExecutorConfig.getId()));
    }

    @Override
    public void execute(RunnableTask task) {
        if (task == null) {
            return;
        }
        if (!isRunning()) {
            return;
        }
        threadPoolExecutor.execute(task);
    }

    @Override
    public String getId() {
        return taskExecutorConfig.getId();
    }

    @Override
    public void processStart() throws CannotStartMachineException {

    }

    @Override
    public void processStop() throws CannotStopMachineException {

    }
}