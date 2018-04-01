package org.walkerljl.boss.web.identity;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.walkerljl.boss.dao.dataobject.identity.AppDO;
import org.walkerljl.boss.sdk.auth.annotation.Authentication;
import org.walkerljl.boss.service.identity.AppService;
import org.walkerljl.boss.support.mvc.controller.template.JqueryDatatableCurdTemplate;
import org.walkerljl.boss.support.mvc.model.ObjectIdentifier;
import org.walkerljl.boss.support.service.JqueryDatatableBaseService;


/**
 * 应用
 *
 * @author lijunlin
 */
@Controller
@Authentication
@RequestMapping(value = "/identity/app", method = {RequestMethod.POST, RequestMethod.GET})
public class AppController extends JqueryDatatableCurdTemplate<AppDO> {

    @Resource
    private AppService appService;

    public AppController() {
        ObjectIdentifier objectIdentifier = new ObjectIdentifier("应用管理", "/identity/app");
        //TODO
        //objectIdentifier.setParentMenus(new MenuDO[]{new MenuDO("权限管理", null)});
        setObjectIdentifier(objectIdentifier);
    }

    @Override
    public JqueryDatatableBaseService<Long, AppDO> getJqueryDatatableBaseService() {
        return appService;
    }
}