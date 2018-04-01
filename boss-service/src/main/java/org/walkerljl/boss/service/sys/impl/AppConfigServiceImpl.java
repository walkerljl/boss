package org.walkerljl.boss.service.sys.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.walkerljl.boss.dao.daointerface.sys.AppConfigDAO;
import org.walkerljl.boss.dao.dataobject.sys.AppConfigDO;
import org.walkerljl.boss.service.sys.AppConfigService;
import org.walkerljl.boss.support.dao.daointerface.BaseDAO;
import org.walkerljl.boss.support.enums.StatusEnum;
import org.walkerljl.boss.support.service.impl.JqueryDatatableBaseServiceImpl;
import org.walkerljl.toolkit.lang.StringUtils;

/**
 * AppConfigServiceImpl
 *
 * @author lijunlin
 */
@Service("appConfigService")
public class AppConfigServiceImpl extends JqueryDatatableBaseServiceImpl<Long, AppConfigDO> implements AppConfigService {

    @Resource
    private AppConfigDAO appConfigDAO;

    @Override
    public BaseDAO<Long, AppConfigDO> getDAO() {
        return appConfigDAO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testTransaction() {
        appConfigDAO.updateStatusByKey2("system.upgrading");

        for (int i = 1; i <= 2; i++) {
            AppConfigDO sysConfig = new AppConfigDO();
            sysConfig.setName(i + "");
            sysConfig.setKey(i + "");
            sysConfig.setValue(i + "");
            sysConfig.setRemark(i + "");
            sysConfig.setStatus("1");
            sysConfig.setCreator("lijunlin");
            sysConfig.setCreatedTime(new Date());
            sysConfig.setModifier(sysConfig.getModifier());
            sysConfig.setModifiedTime(sysConfig.getCreatedTime());
            if (i == 2) {
                throw new RuntimeException("testTransaction");
            }
        }
    }

    @Override
    public AppConfigDO getAppConfigByKey(Long appId, String appConfigKey) {
        if (StringUtils.isBlank(appConfigKey)) {
            return null;
        }
        AppConfigDO condition = new AppConfigDO();
        condition.setAppId(appId);
        condition.setKey(appConfigKey.trim().toLowerCase());
        condition.setStatus(StatusEnum.ENABLED.getCode());
        return selectOne(condition);
    }
}
