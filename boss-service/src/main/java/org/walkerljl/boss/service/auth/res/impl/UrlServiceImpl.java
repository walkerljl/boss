package org.walkerljl.boss.service.auth.res.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.walkerljl.boss.dao.daointerface.auth.res.UrlDAO;
import org.walkerljl.boss.dao.dataobject.auth.res.UrlDO;
import org.walkerljl.boss.service.auth.res.UrlService;
import org.walkerljl.boss.support.dao.daointerface.BaseDAO;
import org.walkerljl.boss.support.service.impl.JqueryDatatableBaseServiceImpl;

/**
 * UrlServiceImpl
 *
 * @author lijunlin
 */
@Service("urlService")
public class UrlServiceImpl extends JqueryDatatableBaseServiceImpl<Long, UrlDO> implements UrlService {

    @Resource
    private UrlDAO urlDAO;

    @Override
    public BaseDAO<Long, UrlDO> getDAO() {
        return urlDAO;
    }
}