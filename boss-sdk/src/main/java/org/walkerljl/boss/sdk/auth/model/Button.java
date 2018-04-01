package org.walkerljl.boss.sdk.auth.model;

import org.walkerljl.boss.sdk.BaseEntity;

/**
 * 按钮
 *
 * @author xingxun
 */
public class Button extends BaseEntity implements Comparable<Object> {

    private static final long serialVersionUID = 1752101362593979343L;

    /**
     * ID
     */
    private Long    id;
    /**
     * 编号
     */
    private String  code;
    /**
     * Name
     */
    private String  name;
    /**
     * 按钮显示名称
     */
    private String  text;
    /**
     * URL
     */
    private String  url;
    /**
     * ICON
     */
    private String  icon;
    /**
     * CSS 样式
     */
    private String  css;
    /**
     * 权限码
     */
    private String  authCode;
    /**
     * 单击事件
     */
    private String  onclickEvent;
    /**
     * 是否可用
     */
    private Boolean enabled;
    /**
     * 排序值
     */
    private Integer     sortSequence;

    /**
     * 构造函数
     */
    public Button() {}

    /**
     * 构造函数
     *
     * @param authCode 权限码
     */
    public Button(String authCode) {
        this(null, null, null, null, null, null, authCode);
    }

    /**
     * 构造函数
     *
     * @param id       ID
     * @param authCode 权限码
     */
    public Button(Long id, String authCode) {
        this(id, null, null, null, null, null, authCode);
    }

    /**
     * 构造函数
     *
     * @param id       ID
     * @param code 编号
     * @param name     名称
     */
    public Button(Long id, String code, String name) {
        this(id, code, name, null, null, null, null);
    }

    /**
     * 构造函数
     *
     * @param id       ID
     * @param code 编号
     * @param name     名称
     * @param authCode 权限码
     */
    public Button(Long id, String code, String name, String authCode) {
        this(id, code, name, null, null, null, authCode);
    }

    /**
     * 构造函数
     *
     * @param id       ID
     * @param code 编号
     * @param name     名称
     * @param url      URL
     * @param authCode 权限码
     */
    public Button(Long id, String code, String name, String url, String authCode) {
        this(id, code, name, url, null, null, authCode);
    }

    /**
     * 构造函数
     *
     * @param id       ID
     * @param code     编号
     * @param name     名称
     * @param url      URL
     * @param icon     ICON图标
     * @param css      CSS样式
     * @param authCode 权限码
     */
    public Button(Long id, String code, String name, String url, String icon, String css, String authCode) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.url = url;
        this.icon = icon;
        this.css = css;
        this.authCode = authCode;
    }

    @Override
    public int compareTo(Object o) {
        Button button = (Button) o;
        if ((button.sortSequence == null ? 0 : button.sortSequence) <
                (this.sortSequence == null ? 0 : this.sortSequence)) {
            return -1;
        } else if ((button.sortSequence == null ? 0 : button.sortSequence) >
                (this.sortSequence == null ? 0 : this.sortSequence)) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 获取ID
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取编号
     *
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置编号
     *
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取名称
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取文本
     *
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     * 设置文本
     *
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * 获取URL
     *
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置URL
     *
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取ICON图标
     *
     * @return
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置ICON图标
     *
     * @param icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取CSS样式
     *
     * @return
     */
    public String getCss() {
        return css;
    }

    /**
     * 设置CSS样式
     *
     * @param css
     */
    public void setCss(String css) {
        this.css = css;
    }

    /**
     * 获取权限码
     *
     * @return
     */
    public String getAuthCode() {
        return authCode;
    }

    /**
     * 设置权限码
     *
     * @param authCode
     */
    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    /**
     * 获取单击事件
     *
     * @return
     */
    public String getOnclickEvent() {
        return onclickEvent;
    }

    /**
     * 设置单击事件
     *
     * @param onclickEvent
     */
    public void setOnclickEvent(String onclickEvent) {
        this.onclickEvent = onclickEvent;
    }

    /**
     * 获取是否可用
     *
     * @return
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置是否可用
     *
     * @param enabled 是否可用
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取排序值
     *
     * @return
     */
    public Integer getSortSequence() {
        return sortSequence;
    }

    /**
     * 设置排序值
     *
     * @param sortSequence
     */
    public void setSortSequence(Integer sortSequence) {
        this.sortSequence = sortSequence;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }
        Button other = (Button) obj;
        if (id == null) {
            if (other.id != null) { return false; }
        } else if (!id.equals(other.id)) { return false; }
        return true;
    }

    public String toHtmlString() {
        String body = "";
        if (enabled) {
            body = String.format("<button type='button' id='%s' name='%s' class='%s' onclick='%s'><i class='%s'></i>%s</button>",
                    new Object[] {getId(), getName(), getCss(), getOnclickEvent(), getIcon(), getText()});
        }
        return body;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}