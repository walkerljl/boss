package org.walkerljl.boss.service.monitor.collect;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.walkerljl.boss.service.monitor.collect.check.MonitorTaskMessageFetcher;
import org.walkerljl.boss.service.monitor.collect.task.MonitorTaskDispatcher;
import org.walkerljl.toolkit.logging.Logger;
import org.walkerljl.toolkit.logging.LoggerFactory;
import org.walkerljl.toolkit.standard.Machine;
import org.walkerljl.toolkit.standard.abstracts.AbstractMachine;
import org.walkerljl.toolkit.standard.exception.machine.CannotStartMachineException;
import org.walkerljl.toolkit.standard.exception.machine.CannotStopMachineException;

/**
 * MonitorMachine
 *
 * @author lijunlin
 */
@Component("monitorMachine")
public class MonitorMachine extends AbstractMachine implements Machine {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitorMachine.class);

    @Resource
    private MonitorTaskDispatcher monitorTaskDispatcher;
    @Resource
    private MonitorTaskMessageFetcher monitorTaskMessageFetcher;

    @Override
    public void processStart() throws CannotStartMachineException {

    }

    @Override
    public void processStop() throws CannotStopMachineException {

    }

    @Override
    public void start() throws CannotStartMachineException {
        monitorTaskMessageFetcher.start();
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Monitor machine has started.");
        }
    }

    @Override
    public void stop() throws CannotStopMachineException {
        monitorTaskMessageFetcher.stop();
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Monitor machine has stopped.");
        }
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getGroup() {
        return null;
    }
}
