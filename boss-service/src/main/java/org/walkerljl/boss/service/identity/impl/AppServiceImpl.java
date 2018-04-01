package org.walkerljl.boss.service.identity.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.walkerljl.boss.dao.daointerface.identity.AppDAO;
import org.walkerljl.boss.dao.dataobject.identity.AppDO;
import org.walkerljl.boss.service.identity.AppService;
import org.walkerljl.boss.support.common.log.SysLog;
import org.walkerljl.boss.support.dao.daointerface.BaseDAO;
import org.walkerljl.boss.support.service.impl.JqueryDatatableBaseServiceImpl;
import org.walkerljl.toolkit.standard.model.paging.Paginator;

/**
 * AppServiceImpl
 *
 * @author lijunlin
 */
@Service("appService")
public class AppServiceImpl extends JqueryDatatableBaseServiceImpl<Long, AppDO> implements AppService {

    @Resource
    private AppDAO appDAO;

    @Override
    public BaseDAO<Long, AppDO> getDAO() {
        return appDAO;
    }

    @Override
    public Long insert(AppDO app) {
        app.setToken(generateAppToken());
        return super.insert(app);
    }

    @Override
    public Long insert(AppDO app, SysLog sysLog) {
        app.setToken(generateAppToken());
        return super.insert(app);
    }

    /**
     * Generate app token
     * @return
     */
    private String generateAppToken() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    @Override
    public List<AppDO> selectAccessibleApps(String userId) {
        AppDO condition = new AppDO();
        condition.setManagerId(userId);
        return selectList(condition);
    }

    @Override
    public List<AppDO> select100Apps() {
        AppDO condition = new AppDO();
        //condition.setCurrentPage(0);
        //condition.setPageSize(100);
        //condition.setQueryTotalCount(false);
        Paginator<AppDO> page = selectPage(condition);
        return page == null ? null : page.getDataList();
    }
}