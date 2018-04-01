package org.walkerljl.boss.web.sys.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.walkerljl.boss.dao.dataobject.identity.AppDO;
import org.walkerljl.boss.dao.dataobject.sys.AppConfigDO;
import org.walkerljl.boss.sdk.auth.annotation.Authentication;
import org.walkerljl.boss.service.identity.AppService;
import org.walkerljl.boss.service.sys.AppConfigService;
import org.walkerljl.boss.support.mvc.MvcSupporter;
import org.walkerljl.boss.support.mvc.controller.template.JqueryDatatableCurdTemplate;
import org.walkerljl.boss.support.mvc.model.ObjectIdentifier;
import org.walkerljl.boss.support.mvc.model.ViewResult;
import org.walkerljl.boss.support.service.JqueryDatatableBaseService;

/**
 * 应用配置
 *
 * @author lijunlin
 */
@Controller
@Authentication
@RequestMapping(value = "/sys/config", method = {RequestMethod.POST, RequestMethod.GET})
public class AppConfigController extends JqueryDatatableCurdTemplate<AppConfigDO> {

    @Resource
    private AppConfigService appConfigService;
    @Resource
    private AppService appService;
    @Resource
    private MvcSupporter mvcSupporter;

    public AppConfigController() {
        ObjectIdentifier objectIdentifier = new ObjectIdentifier("配置信息管理", "/sys/config");
        //TODO
        //objectIdentifier.setParentMenus(new Menu[]{new Menu("系统设置", null)});
        setObjectIdentifier(objectIdentifier);
    }

    @Override
    public JqueryDatatableBaseService<Long, AppConfigDO> getJqueryDatatableBaseService() {
        return appConfigService;
    }

    @RequestMapping(value = "testTransaction")
    public ModelAndView testTransaction() {
        appConfigService.testTransaction();
        return toJSON("testTransaction");
    }

    @Override
    protected ViewResult getIndexModel() {
        ViewResult viewResult = new ViewResult();
        viewResult.addModel("apps", getAccessibleApps());
        return viewResult;
    }

    @Override
    protected AppConfigDO processView(Long id) {
        AppConfigDO record = super.processView(id);
        if (record != null) {
            AppDO app = appService.selectByKey(record.getAppId());
            if (app != null) {
                record.setAppName(app.getName());
            }
        }
        return record;
    }

    @Override
    protected ViewResult getEditModel(Long id) {
        ViewResult viewResult = super.getEditModel(id);
        viewResult.addModel("apps", getAccessibleApps());
        return viewResult;
    }

    /**
     * 获取用户可访问的应用列表
     *
     * @return
     */
    private List<AppDO> getAccessibleApps() {
        List<AppDO> apps = null;
        if (mvcSupporter.currentUserIsAdmin()) {
            apps = appService.select100Apps();
        } else {
            apps = appService.selectAccessibleApps(getCurrentUserId());
        }
        return apps;
    }
}