package org.walkerljl.boss.service.auth.res.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.walkerljl.boss.dao.daointerface.auth.res.ResCodeDAO;
import org.walkerljl.boss.dao.dataobject.auth.res.ResCodeDO;
import org.walkerljl.boss.service.auth.res.ResCodeService;
import org.walkerljl.boss.support.dao.daointerface.BaseDAO;
import org.walkerljl.boss.support.service.impl.JqueryDatatableBaseServiceImpl;

/**
 * ResCodeServiceImpl
 *
 * @author lijunlin
 */
@Service("resCodeService")
public class ResCodeServiceImpl extends JqueryDatatableBaseServiceImpl<Long, ResCodeDO> implements ResCodeService {

    @Resource
    private ResCodeDAO resCodeDAO;

    @Override
    public BaseDAO<Long, ResCodeDO> getDAO() {
        return resCodeDAO;
    }
}