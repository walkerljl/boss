package org.walkerljl.boss.service.monitor.impl;

import org.walkerljl.boss.service.monitor.MonitorBootstrap;
import org.walkerljl.boss.service.task.TaskBootstrap;
import org.walkerljl.toolkit.standard.machine.abstracts.AbstractMachine;
import org.walkerljl.toolkit.standard.machine.exception.CannotStartMachineException;
import org.walkerljl.toolkit.standard.machine.exception.CannotStopMachineException;

/**
 * 默认的监控引导程序
 *
 * @author xingxun
 */
public class DefaultMonitorBootstrap extends AbstractMachine implements MonitorBootstrap {

    private TaskBootstrap taskBootstrap;

    public DefaultMonitorBootstrap(TaskBootstrap taskBootstrap) {
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