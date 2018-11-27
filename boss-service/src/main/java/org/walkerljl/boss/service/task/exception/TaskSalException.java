package org.walkerljl.boss.service.task.exception;

import org.walkerljl.toolkit.standard.exception.code.ErrorCode;

/**
 * TaskSalException
 *
 * @author xingxun
 */
public class TaskSalException extends TaskException {

    private static final long serialVersionUID = -3408332888117835649L;

    /**
     * 默认构造函数
     */
    public TaskSalException() {
        super();
    }

    /**
     * 构造函数
     *
     * @param message 异常消息
     */
    public TaskSalException(String message) {
        super(message);
    }

    /**
     * 构造函数
     *
     * @param e 异常对象
     */
    public TaskSalException(Throwable e) {
        super(e);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     */
    public TaskSalException(ErrorCode code) {
        super(code.getDescription());
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param message 异常消息
     */
    public TaskSalException(ErrorCode code, String message) {
        super(code, message);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param e 异常对象
     */
    public TaskSalException(ErrorCode code, Throwable e) {
        super(code.getDescription(), e);
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param message 异常消息
     * @param e 异常对象
     */
    public TaskSalException(String message, Throwable e) {
        super(message, e);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param message 异常消息
     * @param e 异常对象
     */
    public TaskSalException(ErrorCode code, String message, Throwable e) {
        super(code, message, e);
    }
}