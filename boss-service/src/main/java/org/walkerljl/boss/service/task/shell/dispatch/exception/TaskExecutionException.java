package org.walkerljl.boss.service.task.shell.dispatch.exception;

import org.walkerljl.boss.service.task.exception.TaskException;
import org.walkerljl.toolkit.standard.exception.code.ErrorCode;

/**
 * 任务执行异常
 *
 * @author xingxun
 */
public class TaskExecutionException extends TaskException {

    private static final long serialVersionUID = -3408332888117835649L;

    /**
     * 默认构造函数
     */
    public TaskExecutionException() {
        super();
    }

    /**
     * 构造函数
     *
     * @param message 异常消息
     */
    public TaskExecutionException(String message) {
        super(message);
    }

    /**
     * 构造函数
     *
     * @param e 异常对象
     */
    public TaskExecutionException(Throwable e) {
        super(e);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     */
    public TaskExecutionException(ErrorCode code) {
        super(code.getDescription());
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param message 异常消息
     */
    public TaskExecutionException(ErrorCode code, String message) {
        super(code, message);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param e 异常对象
     */
    public TaskExecutionException(ErrorCode code, Throwable e) {
        super(code.getDescription(), e);
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param message 异常消息
     * @param e 异常对象
     */
    public TaskExecutionException(String message, Throwable e) {
        super(message, e);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param message 异常消息
     * @param e 异常对象
     */
    public TaskExecutionException(ErrorCode code, String message, Throwable e) {
        super(code, message, e);
    }
}