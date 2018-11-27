package org.walkerljl.boss.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.walkerljl.toolkit.standard.machine.Machine;
import org.walkerljl.toolkit.standard.machine.abstracts.AbstractMachine;
import org.walkerljl.toolkit.standard.machine.exception.CannotStartMachineException;
import org.walkerljl.toolkit.standard.machine.exception.CannotStopMachineException;
import org.walkerljl.toolkit.standard.resource.exception.CannotInitResourceException;

/**
 * ServiceBootstrap
 *
 * @author xingxun
 */
public class ServiceBootstrap extends AbstractMachine implements Machine {

    @Resource(name = "monitorMachine")
    private Machine monitorMachine;

    @Override
    public void processInit() throws CannotInitResourceException {
    }

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
