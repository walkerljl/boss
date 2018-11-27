package org.walkerljl.boss.service.task.shell.dispatch.impl;

import java.util.Arrays;
import java.util.List;

import org.walkerljl.boss.service.task.impl.TaskLoggerNames;
import org.walkerljl.boss.service.task.shell.dispatch.SplitedTaskItem;
import org.walkerljl.boss.service.task.shell.dispatch.TaskDispatchLoader;
import org.walkerljl.boss.service.task.shell.dispatch.exception.TaskLoadException;
import org.walkerljl.toolkit.template.log.Logger;
import org.walkerljl.toolkit.template.log.LoggerFactory;
import org.walkerljl.toolkit.template.log.model.InvocationInfo;
import org.walkerljl.toolkit.template.log.util.LoggerDetailUtil;
import org.walkerljl.toolkit.template.log.util.LoggerDigestUtil;

/**
 * DefaultTaskDispatchLoader
 *
 * @author xingxun
 */
public class DefaultTaskDispatchLoader implements TaskDispatchLoader {

    private static final Logger DIGEST_LOGGER = LoggerFactory.getLogger(TaskLoggerNames.TASK_DISPATCH_LOAD_DIGEST);
    private static final Logger DETAIL_LOGGER = LoggerFactory.getLogger(TaskLoggerNames.TASK_DISPATCH_LOAD_DETAIL);

    @Override
    public List<String> load(SplitedTaskItem splitedTaskItem) {
        InvocationInfo<SplitedTaskItem, List<String>> invocationInfo =
                new InvocationInfo<>(getClass(), "load", splitedTaskItem);

        try {
            if (splitedTaskItem == null) {
                return null;
            }
            List<String> taskIds = Arrays.asList(String.valueOf(splitedTaskItem));
            invocationInfo.markSuccess(taskIds);
            return taskIds;
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            throw new TaskLoadException(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }
}