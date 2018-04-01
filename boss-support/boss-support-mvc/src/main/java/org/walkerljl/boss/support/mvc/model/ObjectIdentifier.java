package org.walkerljl.boss.support.mvc.model;

import java.util.List;

import org.walkerljl.boss.sdk.auth.model.Menu;
import org.walkerljl.boss.support.mvc.auth.Button;
import org.walkerljl.toolkit.lang.StringPool;
import org.walkerljl.toolkit.lang.StringUtils;
import org.walkerljl.toolkit.standard.model.BaseEntity;

/**
 * 对象标识符
 *
 * @author xingxun
 */
public class ObjectIdentifier extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 按钮列表
     */
    private List<Button> buttons;
    /**
     * 名称
     */
    private String name;
    /**
     * 编码
     */
    private String code;
    /**
     * URL地址
     */
    private String url;
    /**
     * 模板路径
     */
    private String templatePath;
    /**
     * 是否加载菜单条
     */
    private boolean isLoadMenuBar = true;
    /**
     * 按钮激活状态
     */
    private int[] buttonActives;
    /**
     * 父菜单
     */
    private Menu[] parentMenus;
    /**
     * 布局模板
     */
    private String layout = "layout/default";

    /**
     * 默认构造函数
     */
    public ObjectIdentifier() {}

    /**
     * 构造函数
     *
     * @param name         名称
     * @param templatePath 模板路径
     */
    public ObjectIdentifier(String name, String templatePath) {
        this.name = name;
        this.templatePath = templatePath;
        intelligentParse();//智能解析
    }

    /**
     * 构造函数
     *
     * @param name         名称
     * @param code         编码
     * @param templatePath 模板路径
     */
    public ObjectIdentifier(String name, String code, String templatePath) {
        this.name = name;
        this.code = code;
        this.templatePath = templatePath;
        intelligentParse();//智能解析
    }

    /**
     * 构造函数
     *
     * @param name         名称
     * @param code         编码
     * @param templatePath 模板路径
     * @param url          URL
     */
    public ObjectIdentifier(String name, String code, String templatePath, String url) {
        this.name = name;
        this.code = code;
        this.templatePath = templatePath;
        this.url = url;
        intelligentParse();//智能解析
    }

    /**
     * 智能解析
     */
    public void intelligentParse() {
        if (StringUtils.isEmpty(templatePath)) {
            return;
        }
        if (StringUtils.isEmpty(url)) {
            url = templatePath;
        }
        if (StringUtils.isNotEmpty(code)) {
            return;
        }
        if (templatePath.indexOf(StringPool.SLASH) == -1) {
            code = templatePath.toLowerCase();
        } else {
            String[] items = templatePath.split(StringPool.SLASH);
            if (items == null || items.length == 0) {
                return;
            }
            StringBuilder sb = new StringBuilder(items[0].trim());
            for (int i = 1; i < items.length; i++) {
                sb.append("_").append(items[i].toLowerCase().trim());
            }
            code = sb.toString();
        }
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
     * 获取编码
     *
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置编码
     *
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
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
     * 获取模板路径
     *
     * @return
     */
    public String getTemplatePath() {
        return templatePath;
    }

    /**
     * 设置模板路径
     *
     * @param templatePath
     */
    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    /**
     * 是否加载菜单条
     *
     * @return
     */
    public boolean isLoadMenuBar() {
        return isLoadMenuBar;
    }

    /**
     * 设置是否加载菜单条
     *
     * @param isLoadMenuBar
     */
    public void setLoadMenuBar(boolean isLoadMenuBar) {
        this.isLoadMenuBar = isLoadMenuBar;
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
     * @param buttons
     */
    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }

    /**
     * 设置按钮激活状态
     *
     * @return
     */
    public int[] getButtonActives() {
        return buttonActives;
    }

    /**
     * 按钮激活状态
     *
     * @param buttonActives
     */
    public void setButtonActives(int[] buttonActives) {
        this.buttonActives = buttonActives;
    }

    /**
     * 获取父菜单
     *
     * @return
     */
    public Menu[] getParentMenus() {
        return parentMenus;
    }

    /**
     * 设置父菜单
     *
     * @param parentMenus
     */
    public void setParentMenus(Menu[] parentMenus) {
        this.parentMenus = parentMenus;
    }

    /**
     * 获取布局模板
     */
    public String getLayout() {
        return layout;
    }

    /**
     * 设置布局模板
     *
     * @param layout
     */
    public void setLayout(String layout) {
        this.layout = layout;
    }

    @Override
    public String toString() {
        return "ObjectIdentifier [name=" + name + ", code=" + code + ", url=" + url + ", templatePath=" + templatePath
                + ", isLoadMenuBar=" + isLoadMenuBar + ", layout=" + layout + "]";
    }
}