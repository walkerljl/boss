package org.walkerljl.boss.sdk.auth.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.walkerljl.boss.sdk.auth.model.Button;
import org.walkerljl.boss.sdk.auth.model.Menu;

/**
 *
 * @author xingxun
 */
public class AuthenticationUtil {

    public static List<Button> buildButtons(List<String> authCodes) {
        if (isEmpty(authCodes)) {
            return null;
        }
        List<Button> buttons = new ArrayList<Button>(authCodes.size());
        for (String authCode : authCodes) {
            buttons.add(new Button(authCode));
        }

        return buttons;
    }

    public static List<Menu> filterAuthedMenus(List<Menu> menus, Set<String> authCodes) {
        if (isEmpty(authCodes)) {
            return null;
        }
        if (isEmpty(menus)) {
            return null;
        }

        List<Menu> authedMenus = new ArrayList<Menu>(menus.size());
        for (Menu menu : menus) {
            boolean isAuthedMenu = isAuthedMenu(menu, authCodes);
            if (!isAuthedMenu) {
                continue;
            }
            Menu newMenu = Menu.create(menu);
            authedMenus.add(newMenu);
            if (menu.hasChild()) {
                List<Menu> authedChildren = filterAuthedMenus(menu.getChildren(), authCodes);
                if (authedChildren != null) {
                    newMenu.setChildren(authedChildren);
                }
            }
        }
        return authedMenus;
    }

    private static boolean isAuthedMenu(Menu menu, Set<String> authCodes) {
        if (menu == null) {
            return false;
        }
        boolean isAuthedMenu = (isValidAuthCode(menu.getAuthCode()) && authCodes.contains(menu.getAuthCode()));
        if (isAuthedMenu) {
            return true;
        }
        boolean hasAuthedButton = hasAuthedButton(menu.getButtons(), authCodes);
        if (hasAuthedButton) {
            return true;
        }
        boolean hasAuthedChildMenu = hasAuthedChildMenu(menu.getChildren(), authCodes);
        return hasAuthedChildMenu;
    }

    private static boolean hasAuthedChildMenu(List<Menu> children, Set<String> authCodes) {
        if (isEmpty(children)) {
            return false;
        }

        for (Menu child : children) {
            if (child == null) {
                continue;
            }
            boolean isAuthed = isAuthedMenu(child, authCodes);
            if (isAuthed) {
                return true;
            }
            if (child.hasChild()) {
                boolean hasAuthedChildMenu = hasAuthedChildMenu(child.getChildren(), authCodes);
                if (hasAuthedChildMenu) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean hasAuthedButton(List<Button> buttons, Set<String> authCodes) {
        if (isEmpty(buttons)) {
            return false;
        }

        for (Button button : buttons) {
            if (button == null) {
                continue;
            }
            boolean hasAuthed = authCodes.contains(button.getAuthCode());
            if (hasAuthed) {
                return true;
            }
        }

        return false;
    }

    private static boolean isValidAuthCode(String authCode) {
        return authCode != null && !authCode.equals("");
    }

    private static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
}