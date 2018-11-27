package org.walkerljl.boss.service.monitor.impl;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.base.BaseUnitTest;
import org.walkerljl.boss.service.monitor.MonitorBootstrap;
import org.walkerljl.boss.service.task.TaskBootstrap;
import org.walkerljl.toolkit.standard.machine.abstracts.AbstractMachine;
import org.walkerljl.toolkit.standard.machine.exception.CannotStartMachineException;
import org.walkerljl.toolkit.standard.machine.exception.CannotStopMachineException;

/**
 * DefaultMonitorBootstrapTest
 *
 * @author xingxun
 */
public class DefaultMonitorBootstrapTest extends BaseUnitTest {

    @Test
    public void startAndStop() {

        DefaultTaskBootstrap taskBootstrap = new DefaultTaskBootstrap();
        MonitorBootstrap monitorBootstrap = new DefaultMonitorBootstrap(taskBootstrap);
        monitorBootstrap.start();
        Assert.assertTrue(taskBootstrap.isStarted());

        monitorBootstrap.stop();
        Assert.assertFalse(taskBootstrap.isStarted());
    }
}

class DefaultTaskBootstrap extends AbstractMachine implements TaskBootstrap {

    private boolean started = false;

    @Override
    public void processStart() throws CannotStartMachineException {
        started = true;
    }

    @Override
    public void processStop() throws CannotStopMachineException {
        started = false;
    }

    /**
     * Getter method for property <tt>started</tt>.
     *
     * @return property value of started
     */
    public boolean isStarted() {
        return started;
    }

    /**
     * Setter method for property <tt>started</tt>.
     *
     * @param started  value to be assigned to property started
     */
    public void setStarted(boolean started) {
        this.started = started;
    }
}