package org.walkerljl.boss.web.monitor.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.walkerljl.boss.dao.dataobject.monitor.v1.MonitorObjectDO;
import org.walkerljl.boss.sdk.auth.annotation.Authentication;
import org.walkerljl.boss.service.v1.MonitorObjectService;
import org.walkerljl.boss.support.mvc.controller.template.JqueryDatatableCurdTemplate;
import org.walkerljl.boss.support.mvc.model.ObjectIdentifier;
import org.walkerljl.boss.support.service.JqueryDatatableBaseService;

/**
 * 监控对象
 *
 * @author lijunlin
 */
@Controller
@Authentication
@RequestMapping(value = "/monitor/object", method = {RequestMethod.POST, RequestMethod.GET})
public class MonitorObjectController extends JqueryDatatableCurdTemplate<MonitorObjectDO> {

    @Resource
    private MonitorObjectService monitorObjectService;

    public MonitorObjectController() {
        ObjectIdentifier objectIdentifier = new ObjectIdentifier("监控管理/监控对象", "/monitor/object");
        //objectIdentifier.setParentMenus(new Menu[]{new Menu("监控对象", null)});
        setObjectIdentifier(objectIdentifier);
    }

    @Override
    public JqueryDatatableBaseService<Long, MonitorObjectDO> getJqueryDatatableBaseService() {
        return monitorObjectService;
    }
}