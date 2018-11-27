package org.walkerljl.boss.service.task.impl.handler;

import org.walkerljl.boss.service.task.TaskHandler;
import org.walkerljl.boss.service.task.impl.TaskLoggerNames;
import org.walkerljl.boss.service.task.impl.TaskExecutionContext;
import org.walkerljl.boss.service.task.impl.abstracts.AbstractTaskHandler;
import org.walkerljl.boss.service.task.model.Task;
import org.walkerljl.toolkit.template.log.Logger;
import org.walkerljl.toolkit.template.log.LoggerFactory;
import org.walkerljl.toolkit.template.log.util.LoggerUtil;

/**
 * DummyTaskHandler
 *
 * @author xingxun
 */
public class DummyTaskHandler extends AbstractTaskHandler implements TaskHandler {

    private static final  Logger LOGGER = LoggerFactory.getLogger(TaskLoggerNames.DEFAULT);

    @Override
    protected void handle0(TaskExecutionContext context) {
        Task task = (Task)context.getAttribute(TaskExecutionContext.TASK);
        if (LOGGER.isInfoEnabled()) {
            LoggerUtil.info(LOGGER, task);
        }
    }

    @Override
    public void handleInTransactionAfterRun(TaskExecutionContext context) {

    }
}