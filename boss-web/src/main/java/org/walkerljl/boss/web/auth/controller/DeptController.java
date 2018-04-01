package org.walkerljl.boss.web.auth.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.walkerljl.boss.dao.dataobject.auth.DeptDO;
import org.walkerljl.boss.sdk.auth.annotation.Authentication;
import org.walkerljl.boss.sdk.auth.enums.AuthType;
import org.walkerljl.boss.service.auth.DeptService;
import org.walkerljl.boss.support.mvc.controller.template.JqueryDatatableCurdTemplate;
import org.walkerljl.boss.support.service.JqueryDatatableBaseService;

/**
 * 权限部门
 *
 * @author lijunlin
 */
@Controller
@Authentication(type = AuthType.CODE)
@RequestMapping(value = "/identity/auth/dept", method = {RequestMethod.POST, RequestMethod.GET})
public class DeptController extends JqueryDatatableCurdTemplate<DeptDO> {

    @Resource
    DeptService deptService;

    @Override
    public JqueryDatatableBaseService<Long, DeptDO> getJqueryDatatableBaseService() {
        return deptService;
    }
}