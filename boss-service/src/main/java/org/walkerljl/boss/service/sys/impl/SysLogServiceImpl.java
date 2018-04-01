package org.walkerljl.boss.service.sys.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.walkerljl.boss.dao.daointerface.sys.SysLogDAO;
import org.walkerljl.boss.dao.dataobject.sys.SysLogDO;
import org.walkerljl.boss.service.sys.SysLogService;
import org.walkerljl.boss.support.dao.daointerface.BaseDAO;
import org.walkerljl.boss.support.service.impl.JqueryDatatableBaseServiceImpl;

/**
 * SysLogServiceImpl
 *
 * @author lijunlin
 */
@Service("sysLogService")
public class SysLogServiceImpl extends JqueryDatatableBaseServiceImpl<Long, SysLogDO> implements SysLogService {

    @Resource
    private SysLogDAO sysLogDAO;

    @Override
    public BaseDAO<Long, SysLogDO> getDAO() {
        return sysLogDAO;
    }

}