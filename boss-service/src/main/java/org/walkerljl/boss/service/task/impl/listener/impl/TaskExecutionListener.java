package org.walkerljl.boss.service.task.impl.listener.impl;

import org.walkerljl.boss.service.task.TaskListener;
import org.walkerljl.boss.service.task.impl.TaskLoggerNames;
import org.walkerljl.boss.service.task.impl.TaskExecutionContext;
import org.walkerljl.boss.service.task.impl.abstracts.AbstractTaskListener;
import org.walkerljl.boss.service.task.model.Task;
import org.walkerljl.toolkit.template.log.Logger;
import org.walkerljl.toolkit.template.log.LoggerFactory;
import org.walkerljl.toolkit.template.log.model.InvocationInfo;
import org.walkerljl.toolkit.template.log.util.LoggerDetailUtil;
import org.walkerljl.toolkit.template.log.util.LoggerDigestUtil;

/**
 * 任务执行监听器
 *
 * @author xingxun
 */
public class TaskExecutionListener extends AbstractTaskListener implements TaskListener {

    private static final Logger DIGEST_LOGGER = LoggerFactory.getLogger(TaskLoggerNames.EXECUTE_DIGEST);
    private static final Logger DETAIL_LOGGER = LoggerFactory.getLogger(TaskLoggerNames.EXECUTE_DETAIL);

    private static final String OPERATION_KEY_RUNNING = "RUNNING";
    private static final String OPERATION_KEY_COMPLETED = "COMPLETED";
    private static final String OPERATION_KEY_ERROR = "ERROR";
    private static final String OPERATION_KEY_ABORT = "ABORT";


    @Override
    public void onRunning(TaskExecutionContext context) {
        doLog(OPERATION_KEY_RUNNING, context);
    }

    @Override
    public void onCompleted(TaskExecutionContext context) {
        doLog(OPERATION_KEY_COMPLETED, context);
    }

    @Override
    public void onError(TaskExecutionContext context) {
        doLog(OPERATION_KEY_ERROR, context);
    }

    @Override
    public void onAbort(TaskExecutionContext context) {
        doLog(OPERATION_KEY_ABORT, context);
    }

    /**
     * doLog
     *
     * @param keyWord
     * @param context
     */
    private void doLog(String keyWord, TaskExecutionContext context) {
        if (context == null) {
            return;
        }
        Task task = (Task) context.getAttribute(TaskExecutionContext.TASK);
        InvocationInfo<Task, TaskExecutionContext> invocationInfo = new InvocationInfo<>(getClass(), keyWord, task);
        invocationInfo.markSuccess(context);
        LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
        LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
    }
}