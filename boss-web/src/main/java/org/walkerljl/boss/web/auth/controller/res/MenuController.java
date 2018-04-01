package org.walkerljl.boss.web.auth.controller.res;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.walkerljl.boss.dao.dataobject.auth.res.MenuDO;
import org.walkerljl.boss.dao.dataobject.identity.AppDO;
import org.walkerljl.boss.sdk.auth.annotation.Authentication;
import org.walkerljl.boss.sdk.auth.model.tree.TreeNode;
import org.walkerljl.boss.service.auth.res.MenuService;
import org.walkerljl.boss.service.identity.AppService;
import org.walkerljl.boss.support.mvc.controller.template.CurdTemplate;
import org.walkerljl.boss.support.mvc.model.ObjectIdentifier;
import org.walkerljl.boss.support.service.BaseService;

/**
 * MenuController
 *
 * @author lijunlin
 */
@Controller
@Authentication
@RequestMapping(value = "/identity/auth/res/menu", method = {RequestMethod.POST, RequestMethod.GET})
public class MenuController extends CurdTemplate<MenuDO> {

    @Resource
    private MenuService menuService;
    @Resource
    private AppService appService;

    public MenuController() {
        setObjectIdentifier(new ObjectIdentifier("应用菜单管理", "/identity/auth/res/menu"));
    }

    @RequestMapping(value = "/loadMenuTree")
    public ModelAndView loadMenuTree(Long appId, Long id) {
        if (id == null) {
            AppDO app = appService.selectByKey(appId);
            TreeNode node = null;
            if (app != null) {
                node = new TreeNode();
                node.setId("0");
                node.setParentId("-1");
                node.setName(app.getName());
                node.setParent(true);
            }
            return toSimpleJSON(node, TreeNode.getZtreeNodeFormat());
        } else {
            MenuDO condition = new MenuDO();
            condition.setParentId(id);
            List<TreeNode> nodes = menuService.queryChildNodesByAppIdAndParentId(appId, id);
            return toSimpleJSON(nodes);
        }
    }

    @Override
    public BaseService<Long, MenuDO> getService() {
        return menuService;
    }
}