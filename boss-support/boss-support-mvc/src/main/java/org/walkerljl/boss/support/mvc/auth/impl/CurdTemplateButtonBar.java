package org.walkerljl.boss.support.mvc.auth.impl;

import org.walkerljl.boss.support.mvc.MvcSupporter;
import org.walkerljl.boss.support.mvc.auth.Button;
import org.walkerljl.boss.support.mvc.auth.ButtonBar;
import org.walkerljl.toolkit.lang.IntegerUtils;
import org.walkerljl.toolkit.lang.ListUtils;
import org.walkerljl.toolkit.lang.StringUtils;

import java.util.Collections;
import java.util.List;

/**
 * CurdTemplateButtonBar
 *
 * @author lijunlin
 */
public class CurdTemplateButtonBar implements ButtonBar {

    private static final long serialVersionUID = 1L;
    /**
     * 按钮状态激活值
     */
    private static final Integer BUTTON_ACTIVE_VALUE = 1;

    private final Long[] buttonIds = new Long[]{1L, 2L, 3L, 4L, 5L, 6L, 7L};
    private final String[] buttonNames = new String[]{"add", "edit", "view", "search", "enable", "disable", "del"};
    private final String[] buttonTexts = new String[]{"新增", "修改", "详细", "检索", "启用", "停用", "删除"};
    private final String[] buttonCsses = new String[]{"btn btn-xs btn-success", "btn btn-xs btn-success",
            "btn btn-xs btn-success", "btn btn-xs btn-success", "btn btn-xs btn-success", "btn btn-xs btn-warning",
            "btn btn-xs btn-danger"};
    private final String[] buttonIcons = new String[]{"icon-plus-sign-alt", "icon-edit", "icon-view", "icon-search",
            "icon-plus-sign-alt", "icon-trash", "icon-trash"};
    private final String[] buttonAuthCodes = new String[]{"edit", "edit", "", "", "modifystatus", "modifystatus", "delete"};
    private final String buttonOnClickEventPrefix = "JARVIS.MVC.CURD.";
    private int[] actives = new int[]{1, 1, 1, 1, 1, 1, 1};
    private MvcSupporter mvcSupporter;

    public CurdTemplateButtonBar(MvcSupporter mvcSupporter, int[] actives) {
        this.mvcSupporter = mvcSupporter;
        if (actives != null && actives.length == buttonIds.length) {
            this.actives = actives;
        }
    }

    /**
     * 获取权限码
     *
     * @param authCodePrefix
     * @param key
     * @return
     */
    private String getAuthCode(String authCodePrefix, String key) {
        key = StringUtils.isBlank(key) ? "" : key;
        return StringUtils.isBlank(authCodePrefix) ? key : authCodePrefix + "-" + key;
    }

    /**
     * 获取单击事件
     *
     * @param key
     * @return
     */
    private String getOnClickEvent(String key) {
        return buttonOnClickEventPrefix + key + "();";
    }

    @Override
    public List<Button> getButtons(String userId, boolean isAdmin, String authCodePrefix) {
        List<Button> buttons = ListUtils.newArrayList();
        int activeLen = actives.length;
        for (int i = 0; i < buttonIds.length; i++) {
            if (activeLen > i && IntegerUtils.notEquals(actives[i], BUTTON_ACTIVE_VALUE)) {
                continue;
            }
            Long buttonId = buttonIds[i];
            String authCode = getAuthCode(authCodePrefix, buttonAuthCodes[i]);
            boolean isEnable = true;
            if (!isAdmin) {
                isEnable = mvcSupporter.validateUserAuth(authCode);
            }
            if (isEnable) {
                Button button = new Button();
                buttons.add(button);

                button.setId(buttonId);
                button.setName(buttonNames[i]);
                button.setText(buttonTexts[i]);
                button.setIcon(buttonIcons[i]);
                button.setCss(buttonCsses[i]);
                button.setOnclickEvent(getOnClickEvent(buttonNames[i]));
                button.setAuthCode(authCode);
            }
        }
        Collections.sort(buttons);
        return buttons;
    }
}