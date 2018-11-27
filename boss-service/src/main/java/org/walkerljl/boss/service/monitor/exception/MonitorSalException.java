package org.walkerljl.boss.service.monitor.exception;

import org.walkerljl.toolkit.standard.exception.code.ErrorCode;

/**
 * 监控服务接入异常
 *
 * @author xingxun
 */
public class MonitorSalException extends MonitorException {

    private static final long serialVersionUID = -3408332888117835649L;

    /**
     * 默认构造函数
     */
    public MonitorSalException() {
        super();
    }

    /**
     * 构造函数
     *
     * @param message 异常消息
     */
    public MonitorSalException(String message) {
        super(message);
    }

    /**
     * 构造函数
     *
     * @param e 异常对象
     */
    public MonitorSalException(Throwable e) {
        super(e);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     */
    public MonitorSalException(ErrorCode code) {
        super(code.getDescription());
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param message 异常消息
     */
    public MonitorSalException(ErrorCode code, String message) {
        super(code, message);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param e 异常对象
     */
    public MonitorSalException(ErrorCode code, Throwable e) {
        super(code.getDescription(), e);
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param message 异常消息
     * @param e 异常对象
     */
    public MonitorSalException(String message, Throwable e) {
        super(message, e);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param message 异常消息
     * @param e 异常对象
     */
    public MonitorSalException(ErrorCode code, String message, Throwable e) {
        super(code, message, e);
    }
}