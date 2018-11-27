package org.walkerljl.boss.web.monitor.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.walkerljl.boss.dao.dataobject.identity.AppDO;
import org.walkerljl.boss.dao.dataobject.monitor.v1.MonitorResultDO;
import org.walkerljl.boss.sdk.auth.annotation.Authentication;
import org.walkerljl.boss.service.identity.AppService;
import org.walkerljl.boss.service.v1.MonitorResultService;
import org.walkerljl.boss.support.mvc.controller.template.JqueryDatatableCurdTemplate;
import org.walkerljl.boss.support.mvc.model.ObjectIdentifier;
import org.walkerljl.boss.support.service.JqueryDatatableBaseService;

/**
 * 监控结果
 *
 * @author lijunlin
 */
@Controller
@Authentication
@RequestMapping(value = "/monitor/result", method = {RequestMethod.POST, RequestMethod.GET})
public class MonitorResultController extends JqueryDatatableCurdTemplate<MonitorResultDO> {

    @Resource
    private MonitorResultService monitorResultService;
    @Resource
    private AppService appService;

    public MonitorResultController() {
        ObjectIdentifier objectIdentifier = new ObjectIdentifier("监控管理/监控结果", "/monitor/result");
        //TODO
        //objectIdentifier.setParentMenus(new Menu[]{new Menu("监控管理", null)});
        setObjectIdentifier(objectIdentifier);
        objectIdentifier.setButtonActives(new int[]{0, 0, 1, 1, 0, 0, 0});
    }

    @Override
    public JqueryDatatableBaseService<Long, MonitorResultDO> getJqueryDatatableBaseService() {
        return monitorResultService;
    }

    @Override
    protected MonitorResultDO processView(Long id) {
        MonitorResultDO record = super.processView(id);
        if (record != null) {
            AppDO app = appService.selectByKey(record.getAppId());
            if (app != null) {
                record.setAppName(app.getName());
            }
        }
        return record;
    }
}