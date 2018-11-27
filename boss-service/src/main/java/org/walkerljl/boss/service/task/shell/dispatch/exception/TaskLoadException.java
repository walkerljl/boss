package org.walkerljl.boss.service.task.shell.dispatch.exception;

import org.walkerljl.boss.service.task.exception.TaskException;
import org.walkerljl.toolkit.standard.exception.code.ErrorCode;

/**
 * 任务加载异常
 *
 * @author xingxun
 */
public class TaskLoadException extends TaskException {

    private static final long serialVersionUID = -3408332888117835649L;

    /**
     * 默认构造函数
     */
    public TaskLoadException() {
        super();
    }

    /**
     * 构造函数
     *
     * @param message 异常消息
     */
    public TaskLoadException(String message) {
        super(message);
    }

    /**
     * 构造函数
     *
     * @param e 异常对象
     */
    public TaskLoadException(Throwable e) {
        super(e);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     */
    public TaskLoadException(ErrorCode code) {
        super(code.getDescription());
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param message 异常消息
     */
    public TaskLoadException(ErrorCode code, String message) {
        super(code, message);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param e 异常对象
     */
    public TaskLoadException(ErrorCode code, Throwable e) {
        super(code.getDescription(), e);
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param message 异常消息
     * @param e 异常对象
     */
    public TaskLoadException(String message, Throwable e) {
        super(message, e);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param message 异常消息
     * @param e 异常对象
     */
    public TaskLoadException(ErrorCode code, String message, Throwable e) {
        super(code, message, e);
    }
}