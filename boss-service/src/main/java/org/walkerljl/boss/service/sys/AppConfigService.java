package org.walkerljl.boss.service.sys;

import org.walkerljl.boss.dao.dataobject.sys.AppConfigDO;
import org.walkerljl.boss.support.service.JqueryDatatableBaseService;

/**
 * 应用配置业务接口
 *
 * @author lijunlin
 */
public interface AppConfigService extends JqueryDatatableBaseService<Long, AppConfigDO> {

    void testTransaction();

    AppConfigDO getAppConfigByKey(Long appId, String appConfigKey);
}
