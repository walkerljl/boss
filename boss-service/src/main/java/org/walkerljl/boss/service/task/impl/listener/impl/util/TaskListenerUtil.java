package org.walkerljl.boss.service.task.impl.listener.impl.util;

import java.util.List;

import org.walkerljl.boss.common.util.CollectionUtil;
import org.walkerljl.boss.service.task.TaskListener;
import org.walkerljl.boss.service.task.impl.TaskLoggerNames;
import org.walkerljl.boss.service.task.impl.TaskExecutionContext;
import org.walkerljl.boss.support.common.util.JSONUtil;
import org.walkerljl.boss.support.common.util.ThrowableUtil;
import org.walkerljl.toolkit.template.log.Logger;
import org.walkerljl.toolkit.template.log.LoggerFactory;
import org.walkerljl.toolkit.template.log.util.LoggerUtil;

/**
 * TaskListenerUtil
 *
 * @author xingxun
 */
public class TaskListenerUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskLoggerNames.DEFAULT);

    public static void doOnRunningInterceptors(List<TaskListener> listeners, TaskExecutionContext context) {
        intercept0(listeners, context, ListenerInterceptType.ON_RUNNING);
    }

    public static void doOnCompletedInterceptors(List<TaskListener> listeners, TaskExecutionContext context) {
        intercept0(listeners, context, ListenerInterceptType.ON_COMPLETED);
    }

    public static void doOnErrorInterceptors(List<TaskListener> listeners, TaskExecutionContext context) {
        intercept0(listeners, context, ListenerInterceptType.ON_ERROR);
    }

    public static void doOnAbortInterceptors(List<TaskListener> listeners, TaskExecutionContext context) {
        intercept0(listeners, context, ListenerInterceptType.ON_ABORT);
    }

    private static void intercept0(List<TaskListener> listeners, TaskExecutionContext context, ListenerInterceptType type) {
        if (CollectionUtil.isEmpty(listeners)) {
            return;
        }
        if (context == null) {
            return;
        }
        for (TaskListener listener : listeners) {
            if (listener == null) {
                continue;
            }
            try {
                if (type == ListenerInterceptType.ON_RUNNING) {
                    listener.onRunning(context);
                } else if (type == ListenerInterceptType.ON_COMPLETED) {
                    listener.onCompleted(context);
                } else if (type == ListenerInterceptType.ON_ERROR) {
                    listener.onError(context);
                } else if (type == ListenerInterceptType.ON_ABORT) {
                    listener.onAbort(context);
                }
            } catch (Throwable e) {
                LoggerUtil.error(LOGGER, e, buildLogContent(listener, context, e));
            }
        }
    }

    private static String buildLogContent(TaskListener listener,
                                          TaskExecutionContext context,
                                          Throwable e) {
        return String.format("(%s)(%s)(%s)(%s)",
                ThrowableUtil.getMessage(e),
                JSONUtil.toJSONString(listener),
                JSONUtil.toJSONString(context),
                JSONUtil.toJSONString(context.getAttribute(TaskExecutionContext.TASK))
        );
    }
}

enum ListenerInterceptType {
    ON_RUNNING,
    ON_COMPLETED,
    ON_ERROR,
    ON_ABORT,
}