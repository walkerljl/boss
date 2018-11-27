package org.walkerljl.boss.service.task.impl.abstracts;

import org.walkerljl.boss.common.util.StringUtil;
import org.walkerljl.boss.service.task.TaskBroker;
import org.walkerljl.boss.service.task.exception.TaskBrokerException;
import org.walkerljl.boss.service.task.impl.TaskLoggerNames;
import org.walkerljl.boss.service.task.model.Task;
import org.walkerljl.toolkit.standard.machine.abstracts.AbstractMachine;
import org.walkerljl.toolkit.standard.machine.exception.CannotStartMachineException;
import org.walkerljl.toolkit.standard.machine.exception.CannotStopMachineException;
import org.walkerljl.toolkit.template.log.Logger;
import org.walkerljl.toolkit.template.log.LoggerFactory;
import org.walkerljl.toolkit.template.log.model.InvocationInfo;
import org.walkerljl.toolkit.template.log.util.LoggerDetailUtil;
import org.walkerljl.toolkit.template.log.util.LoggerDigestUtil;

/**
 * AbstractTaskBroker
 *
 * @author xingxun
 */
public abstract class AbstractTaskBroker extends AbstractMachine implements TaskBroker {

    private static final Logger DIGEST_LOGGER = LoggerFactory.getLogger(TaskLoggerNames.BROKER_DIGEST);
    private static final Logger DETAIL_LOGGER = LoggerFactory.getLogger(TaskLoggerNames.BROKER_DETAIL);

    @Override
    public void processStart() throws CannotStartMachineException {

    }

    @Override
    public void processStop() throws CannotStopMachineException {

    }

    @Override
    public String submit(Task task) {
        InvocationInfo<Object[], String> invocationInfo = new InvocationInfo<>(getClass(),
                "submit", new Object[] {task, isRunning()});
        String taskId = null;
        try {
            if (task == null) {
                return null;
            }
            if (!isRunning()) {
                return null;
            }
            taskId = submit0(task);
            invocationInfo.markSuccess(taskId);
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            throw new TaskBrokerException(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
        return taskId;
    }

    @Override
    public void markTaskToExecuted(String bizCode, String bizId, String taskId) {
        InvocationInfo<Object[], String> invocationInfo = new InvocationInfo<>(getClass(),
                "markTaskToExecuted", new Object[] {bizCode, bizId, taskId, isRunning()});
        try {
            if (StringUtil.isEmpty(bizCode)) {
                return;
            }
            if (StringUtil.isEmpty(bizId)) {
                return;
            }
            if (StringUtil.isEmpty(taskId)) {
                return;
            }
            if (!isRunning()) {
                return;
            }
            markTaskToExecuted0(bizCode, bizId, taskId);
            invocationInfo.markSuccess();
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            throw new TaskBrokerException(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }

    /**
     * 处理提交任务
     *
     * @param task 重试任务
     * @return
     */
    public abstract String submit0(Task task);

    /**
     * 处理标注任务已经完成
     *
     * @param bizCode 业务编码
     * @param bizId 业务ID
     * @param taskId 任务ID
     */
    public abstract void markTaskToExecuted0(String bizCode, String bizId, String taskId);
}
