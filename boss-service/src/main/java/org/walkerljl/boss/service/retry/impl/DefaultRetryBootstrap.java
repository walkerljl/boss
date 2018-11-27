package org.walkerljl.boss.service.retry.impl;

import org.walkerljl.boss.service.retry.RetryBootstrap;
import org.walkerljl.boss.service.task.TaskBootstrap;
import org.walkerljl.toolkit.standard.machine.abstracts.AbstractMachine;
import org.walkerljl.toolkit.standard.machine.exception.CannotStartMachineException;
import org.walkerljl.toolkit.standard.machine.exception.CannotStopMachineException;

/**
 * 默认的重试引导程序
 *
 * @author xingxun
 */
public class DefaultRetryBootstrap extends AbstractMachine implements RetryBootstrap {

    private TaskBootstrap taskBootstrap;

    public DefaultRetryBootstrap(TaskBootstrap taskBootstrap) {
        this.taskBootstrap = taskBootstrap;
    }

    @Override
    public void processStart() throws CannotStartMachineException {
        taskBootstrap.start();
    }

    @Override
    public void processStop() throws CannotStopMachineException {
        taskBootstrap.stop();
    }
}