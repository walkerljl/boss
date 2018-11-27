package org.walkerljl.boss.service.monitor.analyze.alarm;

import org.walkerljl.boss.service.monitor.exception.MonitorException;
import org.walkerljl.toolkit.standard.exception.code.ErrorCode;

/**
 * 监控预警异常
 *
 * @author xingxun
 */
public class MonitorAlarmException extends MonitorException {

    private static final long serialVersionUID = -3408332888117835649L;

    /**
     * 默认构造函数
     */
    public MonitorAlarmException() {
        super();
    }

    /**
     * 构造函数
     *
     * @param message 异常消息
     */
    public MonitorAlarmException(String message) {
        super(message);
    }

    /**
     * 构造函数
     *
     * @param e 异常对象
     */
    public MonitorAlarmException(Throwable e) {
        super(e);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     */
    public MonitorAlarmException(ErrorCode code) {
        super(code.getDescription());
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param message 异常消息
     */
    public MonitorAlarmException(ErrorCode code, String message) {
        super(code, message);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param e 异常对象
     */
    public MonitorAlarmException(ErrorCode code, Throwable e) {
        super(code.getDescription(), e);
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param message 异常消息
     * @param e 异常对象
     */
    public MonitorAlarmException(String message, Throwable e) {
        super(message, e);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param message 异常消息
     * @param e 异常对象
     */
    public MonitorAlarmException(ErrorCode code, String message, Throwable e) {
        super(code, message, e);
    }
}