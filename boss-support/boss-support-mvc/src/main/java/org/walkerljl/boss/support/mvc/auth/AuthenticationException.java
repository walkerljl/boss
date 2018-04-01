package org.walkerljl.boss.support.mvc.auth;

import org.walkerljl.toolkit.standard.exception.AppException;
import org.walkerljl.toolkit.standard.exception.code.ErrorCode;

/**
 * 鉴权异常类
 *
 * @author xingxun
 */
public class AuthenticationException extends AppException {

    private static final long serialVersionUID = -6786549876849535944L;

    /**
     * 异常码
     */
    protected ErrorCode code;

    /**
     * 默认构造函数
     */
    public AuthenticationException() {
        super();
    }

    /**
     * 构造函数
     *
     * @param message 消息
     */
    public AuthenticationException(String message) {
        super(message);
    }

    /**
     * 构造函数
     *
     * @param e 异常对象
     */
    public AuthenticationException(Throwable e) {
        super(e);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     */
    public AuthenticationException(ErrorCode code) {
        super(code.getDescription());
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param code    异常码
     * @param message 消息
     */
    public AuthenticationException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param message 异常消息
     * @param e       异常对象
     */
    public AuthenticationException(String message, Throwable e) {
        super(message, e);
    }

    /**
     * 构造函数
     *
     * @param code    异常码
     * @param message 异常消息
     * @param e       异常对象
     */
    public AuthenticationException(ErrorCode code, String message, Throwable e) {
        super(message, e);
        this.code = code;
    }

    /**
     * 获取异常码
     *
     * @return
     */
    public ErrorCode getCode() {
        return code;
    }
}