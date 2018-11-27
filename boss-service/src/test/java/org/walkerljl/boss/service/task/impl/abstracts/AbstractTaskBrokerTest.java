package org.walkerljl.boss.service.task.impl.abstracts;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.task.BaseTaskUnitTest;
import org.walkerljl.boss.service.task.TaskBroker;
import org.walkerljl.boss.service.task.exception.TaskBrokerException;
import org.walkerljl.boss.service.task.model.Task;

/**
 * AbstractTaskBrokerTest
 *
 * @author xingxun
 */
public class AbstractTaskBrokerTest extends BaseTaskUnitTest {

    @Test
    public void submit() {
        DefaultTaskBroker taskBroker = new DefaultTaskBroker();
        taskBroker.start();
        taskBroker.submit(null);
        Assert.assertFalse(taskBroker.isSubmited());

        Task task = new Task();
        String expected = taskBroker.submit(task);
        Assert.assertTrue(taskBroker.isSubmited());
        taskBroker.setSubmited(false);
        Assert.assertEquals(expected, "taskId");

        taskBroker.stop();
        taskBroker.submit(null);
        Assert.assertFalse(taskBroker.isSubmited());
        taskBroker.setSubmited(false);

        taskBroker.submit(task);
        Assert.assertFalse(taskBroker.isSubmited());
        taskBroker.setSubmited(false);

        TaskBroker taskBroker2 = new AbstractTaskBroker() {

            @Override
            public String submit0(Task task) {
                throw new RuntimeException();
            }

            @Override
            public void markTaskToExecuted0(String bizCode, String bizId, String taskId) {

            }
        };
        taskBroker2.start();
        boolean flag = false;
        try {
            taskBroker2.submit(task);
        } catch (TaskBrokerException e) {
            flag = true;
        }
        Assert.assertTrue(flag);
    }

    @Test
    public void markTaskToExecuted() {
        String bizCode = "bizCode";
        String bizId = "bizId";
        String taskId = "taskId";
        DefaultTaskBroker taskBroker = new DefaultTaskBroker();
        taskBroker.start();
        taskBroker.markTaskToExecuted(bizCode, bizId, taskId);
        Assert.assertTrue(taskBroker.isMarkTaskToExecuted());
        taskBroker.setMarkTaskToExecuted(false);

        taskBroker.markTaskToExecuted(null, bizId, taskId);
        Assert.assertFalse(taskBroker.isMarkTaskToExecuted());
        taskBroker.setMarkTaskToExecuted(false);

        taskBroker.markTaskToExecuted(bizCode, null, taskId);
        Assert.assertFalse(taskBroker.isMarkTaskToExecuted());
        taskBroker.setMarkTaskToExecuted(false);

        taskBroker.markTaskToExecuted(bizCode, bizId,  null);
        Assert.assertFalse(taskBroker.isMarkTaskToExecuted());
        taskBroker.setMarkTaskToExecuted(false);

        taskBroker.markTaskToExecuted(null, null,  taskId);
        Assert.assertFalse(taskBroker.isMarkTaskToExecuted());
        taskBroker.setMarkTaskToExecuted(false);

        taskBroker.markTaskToExecuted(null, bizId,  null);
        Assert.assertFalse(taskBroker.isMarkTaskToExecuted());
        taskBroker.setMarkTaskToExecuted(false);

        taskBroker.markTaskToExecuted(bizCode, null,  null);
        Assert.assertFalse(taskBroker.isMarkTaskToExecuted());
        taskBroker.setMarkTaskToExecuted(false);

        taskBroker.markTaskToExecuted(null, null,  null);
        Assert.assertFalse(taskBroker.isMarkTaskToExecuted());
        taskBroker.setMarkTaskToExecuted(false);

        taskBroker.stop();
        taskBroker.markTaskToExecuted(bizCode, bizId, taskId);
        Assert.assertFalse(taskBroker.isMarkTaskToExecuted());
        taskBroker.setMarkTaskToExecuted(false);

        TaskBroker taskBroker2 = new AbstractTaskBroker() {

            @Override
            public String submit0(Task task) {
                return null;
            }

            @Override
            public void markTaskToExecuted0(String bizCode, String bizId, String taskId) {
                throw new RuntimeException();
            }
        };
        taskBroker2.start();
        boolean flag = false;
        try {
            taskBroker2.markTaskToExecuted(bizCode, bizCode, taskId);
        } catch (TaskBrokerException e) {
            flag = true;
        }
        Assert.assertTrue(flag);
    }
}

class DefaultTaskBroker extends AbstractTaskBroker implements TaskBroker {

    private boolean submited = false;
    private boolean markTaskToExecuted = false;

    @Override
    public String submit0(Task task) {
        submited = true;
        return "taskId";
    }

    @Override
    public void markTaskToExecuted0(String bizCode, String bizId, String taskId) {
        markTaskToExecuted = true;
    }

    /**
     * Getter method for property <tt>submited</tt>.
     *
     * @return property value of submited
     */
    public boolean isSubmited() {
        return submited;
    }

    /**
     * Setter method for property <tt>submited</tt>.
     *
     * @param submited  value to be assigned to property submited
     */
    public void setSubmited(boolean submited) {
        this.submited = submited;
    }

    /**
     * Getter method for property <tt>markTaskToExecuted</tt>.
     *
     * @return property value of markTaskToExecuted
     */
    public boolean isMarkTaskToExecuted() {
        return markTaskToExecuted;
    }

    /**
     * Setter method for property <tt>markTaskToExecuted</tt>.
     *
     * @param markTaskToExecuted  value to be assigned to property markTaskToExecuted
     */
    public void setMarkTaskToExecuted(boolean markTaskToExecuted) {
        this.markTaskToExecuted = markTaskToExecuted;
    }
}