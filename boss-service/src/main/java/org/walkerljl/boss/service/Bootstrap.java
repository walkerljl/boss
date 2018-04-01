package org.walkerljl.boss.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.walkerljl.toolkit.standard.Machine;
import org.walkerljl.toolkit.standard.abstracts.AbstractMachine;
import org.walkerljl.toolkit.standard.exception.machine.CannotStartMachineException;
import org.walkerljl.toolkit.standard.exception.machine.CannotStopMachineException;

/**
 * Bootstrap
 *
 * @author lijunlin
 */
@Component("bootstrap")
public class Bootstrap extends AbstractMachine implements Machine {

    @Resource(name = "monitorMachine")
    private Machine monitorMachine;

    @Override
    public void processStart() throws CannotStartMachineException {

    }

    @Override
    public void processStop() throws CannotStopMachineException {

    }

    @PostConstruct
    @Override
    public void start() throws CannotStartMachineException {
        monitorMachine.start();
    }

    @PreDestroy
    @Override
    public void stop() throws CannotStopMachineException {
        monitorMachine.stop();
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
