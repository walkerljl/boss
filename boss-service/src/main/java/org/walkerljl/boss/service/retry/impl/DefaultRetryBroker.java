package org.walkerljl.boss.service.retry.impl;

import org.walkerljl.boss.service.retry.RetryBroker;
import org.walkerljl.boss.service.retry.model.ModelConverter;
import org.walkerljl.boss.service.retry.model.RetryTask;
import org.walkerljl.boss.service.task.TaskBroker;
import org.walkerljl.toolkit.standard.machine.abstracts.AbstractMachine;
import org.walkerljl.toolkit.standard.machine.exception.CannotStartMachineException;
import org.walkerljl.toolkit.standard.machine.exception.CannotStopMachineException;

/**
 * DefaultRetryBroker
 *
 * @author xingxun
 */
public class DefaultRetryBroker extends AbstractMachine implements RetryBroker {

    private TaskBroker taskBroker;

    public DefaultRetryBroker(TaskBroker taskBroker) {
        this.taskBroker = taskBroker;
    }

    @Override
    public String submit(RetryTask retryJob) {
        return taskBroker.submit(ModelConverter.toTask(retryJob));
    }

    @Override
    public void markRetryJobToCompleted(String bizCode, String bizId, String taskId) {
        taskBroker.markTaskToExecuted(bizCode, bizId, taskId);
    }

    @Override
    public void processStart() throws CannotStartMachineException {
        taskBroker.start();
    }

    @Override
    public void processStop() throws CannotStopMachineException {
        taskBroker.stop();
    }
}