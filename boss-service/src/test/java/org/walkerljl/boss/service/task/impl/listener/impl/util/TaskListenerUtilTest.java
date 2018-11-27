package org.walkerljl.boss.service.task.impl.listener.impl.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.base.BaseUnitTest;
import org.walkerljl.boss.service.task.TaskListener;
import org.walkerljl.boss.service.task.impl.TaskExecutionContext;
import org.walkerljl.boss.service.task.impl.abstracts.AbstractTaskListener;
import org.walkerljl.boss.service.task.model.Task;

/**
 * TaskListenerUtilTest
 *
 * @author xingxun
 */
public class TaskListenerUtilTest extends BaseUnitTest {

    @Test
    public void invalidParam() {

        List<TaskListener> listeners = new ArrayList<>(1);
        listeners.add(new AbstractTaskListener() {

            @Override
            public void onRunning(TaskExecutionContext context) {

            }

            @Override
            public void onCompleted(TaskExecutionContext context) {

            }

            @Override
            public void onError(TaskExecutionContext context) {

            }

            @Override
            public void onAbort(TaskExecutionContext context) {

            }
        });
        TaskExecutionContext context = new TaskExecutionContext();

        TaskListenerUtil.doOnRunningInterceptors(null, context);
        TaskListenerUtil.doOnRunningInterceptors(listeners, null);
        TaskListenerUtil.doOnRunningInterceptors(null, null);

        TaskListenerUtil.doOnCompletedInterceptors(null, context);
        TaskListenerUtil.doOnCompletedInterceptors(listeners, null);
        TaskListenerUtil.doOnCompletedInterceptors(null, null);

        TaskListenerUtil.doOnErrorInterceptors(null, context);
        TaskListenerUtil.doOnErrorInterceptors(listeners, null);
        TaskListenerUtil.doOnErrorInterceptors(null, null);

        TaskListenerUtil.doOnAbortInterceptors(null, context);
        TaskListenerUtil.doOnAbortInterceptors(listeners, null);
        TaskListenerUtil.doOnAbortInterceptors(null, null);

        Task task = new Task();
        context.setAttribute(TaskExecutionContext.TASK, task);
        TaskListenerUtil.doOnRunningInterceptors(listeners, context);
        TaskListenerUtil.doOnCompletedInterceptors(listeners, context);
        TaskListenerUtil.doOnErrorInterceptors(listeners, context);
        TaskListenerUtil.doOnAbortInterceptors(listeners, context);
    }

    @Test
    public void doOnRunningInterceptors() {
        List<TaskListener> listeners = new ArrayList<>(2);
        DefaultTaskListener taskListener = new DefaultTaskListener();
        listeners.add(taskListener);
        listeners.add(null);
        TaskExecutionContext context = new TaskExecutionContext();
        Task task = new Task();
        context.setAttribute(TaskExecutionContext.TASK, task);

        TaskListenerUtil.doOnRunningInterceptors(listeners, context);
        Assert.assertTrue(taskListener.isOnRunning());
    }

    @Test
    public void doOnCompletedInterceptors() {
        List<TaskListener> listeners = new ArrayList<>(2);
        DefaultTaskListener taskListener = new DefaultTaskListener();
        listeners.add(taskListener);
        listeners.add(null);
        TaskExecutionContext context = new TaskExecutionContext();
        Task task = new Task();
        context.setAttribute(TaskExecutionContext.TASK, task);

        TaskListenerUtil.doOnCompletedInterceptors(listeners, context);
        Assert.assertTrue(taskListener.isOnCompleted());
    }

    @Test
    public void doOnErrorInterceptors() {
        List<TaskListener> listeners = new ArrayList<>(2);
        DefaultTaskListener taskListener = new DefaultTaskListener();
        listeners.add(taskListener);
        listeners.add(null);
        TaskExecutionContext context = new TaskExecutionContext();
        Task task = new Task();
        context.setAttribute(TaskExecutionContext.TASK, task);

        TaskListenerUtil.doOnErrorInterceptors(listeners, context);
        Assert.assertTrue(taskListener.isOnError());
    }

    @Test
    public void doOnAbortInterceptors() {
        List<TaskListener> listeners = new ArrayList<>(2);
        DefaultTaskListener taskListener = new DefaultTaskListener();
        listeners.add(taskListener);
        listeners.add(null);
        TaskExecutionContext context = new TaskExecutionContext();
        Task task = new Task();
        context.setAttribute(TaskExecutionContext.TASK, task);

        TaskListenerUtil.doOnAbortInterceptors(listeners, context);
        Assert.assertTrue(taskListener.isOnAbort());
    }

    @Test
    public void error() {

        List<TaskListener> listeners = new ArrayList<>(1);
        listeners.add(new AbstractTaskListener() {

            @Override
            public void onRunning(TaskExecutionContext context) {
                throw new RuntimeException();
            }

            @Override
            public void onCompleted(TaskExecutionContext context) {
                throw new RuntimeException();
            }

            @Override
            public void onError(TaskExecutionContext context) {
                throw new RuntimeException();
            }

            @Override
            public void onAbort(TaskExecutionContext context) {
                throw new RuntimeException();
            }
        });
        TaskExecutionContext context = new TaskExecutionContext();

        TaskListenerUtil.doOnRunningInterceptors(listeners, context);
        TaskListenerUtil.doOnCompletedInterceptors(listeners, context);
        TaskListenerUtil.doOnErrorInterceptors(listeners, context);
        TaskListenerUtil.doOnAbortInterceptors(listeners, context);
    }
}

class DefaultTaskListener extends AbstractTaskListener implements TaskListener {

    private boolean onRunning = false;
    private boolean onCompleted = false;
    private boolean onError = false;
    private boolean onAbort = false;

    @Override
    public void onRunning(TaskExecutionContext context) {
        onRunning = true;
    }

    @Override
    public void onCompleted(TaskExecutionContext context) {
        onCompleted = true;
    }

    @Override
    public void onError(TaskExecutionContext context) {
        onError = true;
    }

    @Override
    public void onAbort(TaskExecutionContext context) {
        onAbort = true;
    }

    /**
     * Getter method for property <tt>onRunning</tt>.
     *
     * @return property value of onRunning
     */
    public boolean isOnRunning() {
        return onRunning;
    }

    /**
     * Setter method for property <tt>onRunning</tt>.
     *
     * @param onRunning  value to be assigned to property onRunning
     */
    public void setOnRunning(boolean onRunning) {
        this.onRunning = onRunning;
    }

    /**
     * Getter method for property <tt>onCompleted</tt>.
     *
     * @return property value of onCompleted
     */
    public boolean isOnCompleted() {
        return onCompleted;
    }

    /**
     * Setter method for property <tt>onCompleted</tt>.
     *
     * @param onCompleted  value to be assigned to property onCompleted
     */
    public void setOnCompleted(boolean onCompleted) {
        this.onCompleted = onCompleted;
    }

    /**
     * Getter method for property <tt>onError</tt>.
     *
     * @return property value of onError
     */
    public boolean isOnError() {
        return onError;
    }

    /**
     * Setter method for property <tt>onError</tt>.
     *
     * @param onError  value to be assigned to property onError
     */
    public void setOnError(boolean onError) {
        this.onError = onError;
    }

    /**
     * Getter method for property <tt>onAbort</tt>.
     *
     * @return property value of onAbort
     */
    public boolean isOnAbort() {
        return onAbort;
    }

    /**
     * Setter method for property <tt>onAbort</tt>.
     *
     * @param onAbort  value to be assigned to property onAbort
     */
    public void setOnAbort(boolean onAbort) {
        this.onAbort = onAbort;
    }
}