package org.walkerljl.boss.service.v1.collect;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.walkerljl.boss.service.v1.collect.check.MonitorTaskMessageFetcher;
import org.walkerljl.boss.service.v1.collect.task.MonitorTaskDispatcher;
import org.walkerljl.toolkit.logging.Logger;
import org.walkerljl.toolkit.logging.LoggerFactory;
import org.walkerljl.toolkit.standard.machine.Machine;
import org.walkerljl.toolkit.standard.machine.abstracts.AbstractMachine;
import org.walkerljl.toolkit.standard.machine.exception.CannotStartMachineException;
import org.walkerljl.toolkit.standard.machine.exception.CannotStopMachineException;

/**
 * MonitorMachine
 *
 * @author lijunlin
 */
@Component("monitorMachine")
public class MonitorMachine extends AbstractMachine implements Machine {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitorMachine.class);

    @Resource
    private MonitorTaskDispatcher     monitorTaskDispatcher;
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
