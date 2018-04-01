package org.walkerljl.boss.sdk.auth.model;

import java.util.ArrayList;
import java.util.List;

import org.walkerljl.boss.sdk.BaseEntity;

/**
 * 菜单
 *
 * @author xingxun
 */
public class Menu extends BaseEntity {

    private static final long serialVersionUID = -5954135791206135700L;

    /**
     * ID
     */
    private String       id;
    /**编号*/
    private String       code;
    /**
     * 名称
     */
    private String       name;
    /**
     * 父ID
     */
    private String       parentId;
    /**
     * 顺序值
     */
    private Integer      sortSequence;
    /**
     * URL
     */
    private String       url;
    /**
     * ICON
     */
    private String       icon;
    /**
     * CSS 样式
     */
    private String       css;
    /**
     * 权限码
     */
    private String       authCode;
    /**
     * 是否激活
     */
    private Boolean      active;
    /**
     * 子菜单列表
     */
    private List<Menu>   children;
    /**
     * 按表列表
     */
    private List<Button> buttons;

    /**
     * 默认构造函数
     */
    public Menu() {}

    /**
     * 构造函数
     *
     * @param id   ID
     * @param code 编号
     * @param name     名称
     */
    public Menu(String id, String code, String name) {
        this(id, code, name, null, null, null, null, null);
    }

    /**
     * 构造函数
     *
     * @param id   ID
     * @param code 编号
     * @param name     名称
     * @param parentId 父ID
     */
    public Menu(String id, String code, String name, String parentId) {
        this(id, code, name, parentId, null, null, null, null);
    }

    /**
     * 构造函数
     *
     * @param id   ID
     * @param code 编号
     * @param name     名称
     * @param parentId 父ID
     * @param sortSequence    顺序值
     */
    public Menu(String id, String code, String name, String parentId, Integer sortSequence) {
        this(id, code, name, parentId, sortSequence, null, null, null);
    }

    /**
     * 构造函数
     *
     * @param id   ID
     * @param code 编号
     * @param name     名称
     * @param parentId 父ID
     * @param sortSequence    顺序值
     * @param url      URL
     */
    public Menu(String id, String code, String name, String parentId, Integer sortSequence, String url) {
        this(id, code, name, parentId, sortSequence, url, null, null);
    }

    /**
     * 构造函数
     *
     * @param id   ID
     * @param code 编号
     * @param name     名称
     * @param parentId 父ID
     * @param sortSequence    顺序值
     * @param url      URL
     * @param icon     ICON图标
     */
    public Menu(String id, String code, String name, String parentId, Integer sortSequence, String url, String icon) {
        this(id, code, name, parentId, sortSequence, url, icon, null);
    }

    /**
     * 构造函数
     *
     * @param id       ID
     * @param code 编号
     * @param name     名称
     * @param parentId 父ID
     * @param sortSequence    顺序值
     * @param url      URL
     * @param icon     ICON图标
     * @param authCode 权限码
     */
    public Menu(String id, String code, String name, String parentId, Integer sortSequence, String url, String icon, String authCode) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.parentId = parentId;
        this.sortSequence = sortSequence;
        this.url = url;
        this.icon = icon;
        this.authCode = authCode;
    }

    public static Menu create(Menu menu) {
        if (menu == null) {
            return null;
        }
        Menu newMenu = new Menu();
        newMenu.setId(menu.getId());
        newMenu.setCode(menu.getCode());
        newMenu.setName(menu.getName());
        newMenu.setParentId(menu.getParentId());
        newMenu.setSortSequence(menu.getSortSequence());
        newMenu.setUrl(menu.getUrl());
        newMenu.setIcon(menu.getIcon());
        newMenu.setCss(menu.getCss());
        newMenu.setChildren(menu.getChildren());

        return newMenu;
    }

    /**
     * 是否拥有孩子
     *
     * @return
     */
    public boolean hasChild() {
        return children != null && !children.isEmpty();
    }

    /**
     * 添加子菜单
     *
     * @param child 子菜单
     */
    public void addChild(Menu child) {
        if (children == null) {
            children = new ArrayList<Menu>(10);
        }
        children.add(child);
    }

    /**
     * 添加按钮
     *
     * @param button 按钮
     */
    public void addButton(Button button) {
        if (buttons == null) {
            buttons = new ArrayList<Button>(10);
        }
        buttons.add(button);
    }

    /**
     * 添加按钮列表
     *
     * @param buttons 按钮列表
     */
    public void addButtons(List<Button> buttons) {
        if (buttons == null || buttons.isEmpty()) {
            return;
        }
        if (this.buttons == null) {
            this.buttons = new ArrayList<Button>(buttons.size());
        }
        this.buttons.addAll(buttons);
    }

    /**
     * 获取ID
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id
     */
    public void setId(String id) {
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
     * 获取父ID
     *
     * @return
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置父ID
     *
     * @param parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取顺序值
     *
     * @return
     */
    public Integer getSortSequence() {
        return sortSequence;
    }

    /**
     * 设置顺序值
     *
     * @param sortSequence
     */
    public void setSortSequence(Integer sortSequence) {
        this.sortSequence = sortSequence;
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
     * 获取是否激活
     *
     * @return
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * 设置是否激活
     *
     * @param active 是否激活
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * 获取子菜单列表
     *
     * @return
     */
    public List<Menu> getChildren() {
        return children;
    }

    /**
     * 设置子菜单列表
     *
     * @param children
     */
    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    /**
     * 获取按钮列表
     *
     * @return
     */
    public List<Button> getButtons() {
        return buttons;
    }

    /**
     * 设置按钮列表
     *
     * @param buttons 按钮列表
     */
    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}