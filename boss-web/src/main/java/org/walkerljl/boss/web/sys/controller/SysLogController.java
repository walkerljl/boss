package org.walkerljl.boss.web.sys.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.walkerljl.boss.dao.dataobject.sys.SysLogDO;
import org.walkerljl.boss.sdk.auth.annotation.Authentication;
import org.walkerljl.boss.service.sys.SysLogService;
import org.walkerljl.boss.support.mvc.controller.template.JqueryDatatableCurdTemplate;
import org.walkerljl.boss.support.mvc.model.ObjectIdentifier;
import org.walkerljl.boss.support.service.JqueryDatatableBaseService;

/**
 * @author lijunlin
 */
@Controller
@Authentication
@RequestMapping(value = "/sys/log", method = {RequestMethod.POST, RequestMethod.GET})
public class SysLogController extends JqueryDatatableCurdTemplate<SysLogDO> {

    @Resource
    private SysLogService sysLogService;

    public SysLogController() {
        ObjectIdentifier objectIdentifier = new ObjectIdentifier("日志管理", "/sys/log");
        //TODO
        //objectIdentifier.setParentMenus(new Menu[]{new Menu("系统管理", null)});
        objectIdentifier.setButtonActives(new int[]{0, 0, 0, 1, 0, 0, 0});
        setObjectIdentifier(objectIdentifier);
    }

    @Override
    public JqueryDatatableBaseService<Long, SysLogDO> getJqueryDatatableBaseService() {
        return sysLogService;
    }
}
