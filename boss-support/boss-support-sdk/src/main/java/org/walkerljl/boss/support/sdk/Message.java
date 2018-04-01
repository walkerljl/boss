package org.walkerljl.boss.support.sdk;

/**
 * 消息对象
 * <p>
 * 使用说明：<br/>
 * Message message = ....<br/>
 * if (message.isSuccess()) {//判断成功<br/>
 * T data = message.getData();//成功之后获取数据<br/>
 * <p>
 * } else {//失败<br/>
 * <br/>
 * }<br/>
 * message.getBody();//获取消息体,如：操作成功，操作失败<br/>
 * message.getCode();//获取消息响应码,如：200,404,500等，可能为空<br/>
 *
 * @author lijunlin
 */
public class Message<T> extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private static final int DEFAULT_SUCCESS_CODE = BossCode.SUCCESS.getCode();
    private static final int DEFAULT_FAILURE_CODE = BossCode.FAILURE.getCode();
    private static final String DEFAULT_SUCCESS_MSG = "操作成功";
    private static final String DEFAULT_FAILURE_MSG = "操作失败";

    /**
     * 消息码
     */
    private int code;
    /**
     * 消息体
     */
    private String body;
    /**
     * 成功/失败消息
     */
    private boolean result;
    /**
     * 数据
     */
    private T data;

    /**
     * 默认构造函数
     */
    public Message() {
    }

    /**
     * 私有构造函数
     *
     * @param result 消息结果,true:成功,false:失败
     * @param code   消息码
     * @param body   消息体
     * @param data   消息数据/模型
     */
    private Message(boolean result, int code, String body, T data) {
        this.result = result;
        this.code = code;
        this.body = body;
        this.data = data;
    }

    //自定义方法

    /**
     * 创建消息
     *
     * @param result
     * @return
     */
    public static <T> Message<T> create(boolean result) {
        return Message.create(result,
                (result ? DEFAULT_SUCCESS_CODE : DEFAULT_FAILURE_CODE),
                (result ? DEFAULT_SUCCESS_MSG : DEFAULT_FAILURE_MSG), null);
    }

    /**
     * 创建消息
     *
     * @param result
     * @param code
     * @param body
     * @param data
     * @return
     */
    public static <T> Message<T> create(boolean result, int code, String body, T data) {
        return result ? Message.success(code, body, data) : Message.failure(code, body, data);
    }

    /**
     * 创建成功消息
     *
     * @return
     */
    public static <T> Message<T> success() {
        return Message.success(DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MSG, null);
    }

    /**
     * 创建成功消息
     *
     * @param body
     * @return
     */
    public static <T> Message<T> success(String body) {
        return Message.success(DEFAULT_SUCCESS_CODE, body, null);
    }

    /**
     * 创建成功消息
     *
     * @param data
     * @return
     */
    public static <T> Message<T> success(T data) {
        return Message.success(DEFAULT_SUCCESS_CODE, null, data);
    }

    /**
     * 创建成功消息
     *
     * @param body
     * @param data
     * @return
     */
    public static <T> Message<T> success(String body, T data) {
        return new Message<T>(true, DEFAULT_SUCCESS_CODE, body, data);
    }

    /**
     * 创建成功消息
     *
     * @param code
     * @param body
     * @param data
     * @return
     */
    public static <T> Message<T> success(int code, String body, T data) {
        return new Message<T>(true, code, body, data);
    }

    /**
     * 创建成功消息
     *
     * @param code
     * @param body
     * @return
     */
    public static <T> Message<T> success(int code, String body) {
        return new Message<T>(true, code, body, null);
    }

    /**
     * 创建失败消息
     *
     * @return
     */
    public static <T> Message<T> failure() {
        return Message.failure(DEFAULT_FAILURE_CODE, DEFAULT_FAILURE_MSG, null);
    }

    /**
     * 创建失败消息
     *
     * @param body
     * @return
     */
    public static <T> Message<T> failure(String body) {
        return Message.failure(DEFAULT_FAILURE_CODE, body, null);
    }

    /**
     * 创建失败消息
     *
     * @param body
     * @param data
     * @return
     */
    public static <T> Message<T> failure(String body, T data) {
        return new Message<T>(false, DEFAULT_FAILURE_CODE, body, data);
    }

    /**
     * 创建失败消息
     *
     * @param data
     * @return
     */
    public static <T> Message<T> failure(T data) {
        return new Message<T>(false, DEFAULT_FAILURE_CODE, DEFAULT_FAILURE_MSG, data);
    }

    /**
     * 创建失败消息
     *
     * @param code
     * @param body
     * @param data
     * @return
     */
    public static <T> Message<T> failure(int code, String body, T data) {
        return new Message<T>(false, code, body, data);
    }
    /**
     * 创建失败消息
     *
     * @param code
     * @param body
     * @return
     */
    public static <T> Message<T> failure(int code, String body) {
        return new Message<T>(false, code, body, null);
    }

    /**
     * 是否成功
     *
     * @return
     */
    public boolean isSuccess() {
        return result;
    }

    /**
     * 是否失败
     *
     * @return
     */
    public boolean isFailure() {
        return !isSuccess();
    }

    //setters and getters

    /**
     * 获取消息编码
     *
     * @return
     */
    public int getCode() {
        return code;
    }

    /**
     * 设置消息编码
     *
     * @param code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 获取消息体
     *
     * @return
     */
    public String getBody() {
        return body;
    }

    /**
     * 设置消息体
     *
     * @param body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * 获取数据
     *
     * @return
     */
    public T getData() {
        return data;
    }

    /**
     * 设置数据
     *
     * @param data
     */
    public void setData(T data) {
        this.data = data;
    }
}