package org.walkerljl.boss.service.monitor.analyze;

import org.walkerljl.boss.service.monitor.exception.MonitorException;
import org.walkerljl.toolkit.standard.exception.code.ErrorCode;

/**
 * 监控分析异常
 *
 * @author xingxun
 */
public class MonitorAnalyzeException extends MonitorException {

    private static final long serialVersionUID = -3408332888117835649L;

    /**
     * 默认构造函数
     */
    public MonitorAnalyzeException() {
        super();
    }

    /**
     * 构造函数
     *
     * @param message 异常消息
     */
    public MonitorAnalyzeException(String message) {
        super(message);
    }

    /**
     * 构造函数
     *
     * @param e 异常对象
     */
    public MonitorAnalyzeException(Throwable e) {
        super(e);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     */
    public MonitorAnalyzeException(ErrorCode code) {
        super(code.getDescription());
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param message 异常消息
     */
    public MonitorAnalyzeException(ErrorCode code, String message) {
        super(code, message);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param e 异常对象
     */
    public MonitorAnalyzeException(ErrorCode code, Throwable e) {
        super(code.getDescription(), e);
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param message 异常消息
     * @param e 异常对象
     */
    public MonitorAnalyzeException(String message, Throwable e) {
        super(message, e);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param message 异常消息
     * @param e 异常对象
     */
    public MonitorAnalyzeException(ErrorCode code, String message, Throwable e) {
        super(code, message, e);
    }
}