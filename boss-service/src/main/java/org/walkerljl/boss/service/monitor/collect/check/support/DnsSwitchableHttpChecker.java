package org.walkerljl.boss.service.monitor.collect.check.support;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.walkerljl.boss.dao.daointerface.identity.AppDAO;
import org.walkerljl.boss.dao.daointerface.monitor.MonitorResultDAO;
import org.walkerljl.boss.dao.dataobject.identity.AppDO;
import org.walkerljl.boss.dao.dataobject.monitor.MonitorResultDO;
import org.walkerljl.boss.dao.dataobject.sys.AppConfigDO;
import org.walkerljl.boss.model.enums.monitor.MonitorObjectType;
import org.walkerljl.boss.service.monitor.collect.task.MonitorTask;
import org.walkerljl.boss.service.sys.AppConfigService;
import org.walkerljl.boss.support.enums.StatusEnum;
import org.walkerljl.toolkit.lang.StringUtils;

/**
 * HttpMonitor
 *
 * @author lijunlin
 */
@Component("dnsSwitchableHttpChecker")
public class DnsSwitchableHttpChecker extends AbstractChecker {

    @Resource
    private AppDAO           appDAO;
    @Resource
    private AppConfigService appConfigService;
    @Resource
    private MonitorResultDAO monitorResultDAO;

    @Override
    public MonitorResultDO check0(MonitorTask monitorTask) {
        AppDO app = appDAO.selectByKey(monitorTask.getAppId());
        if (app == null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(String.format("Invalid app id:%s.", monitorTask.getAppId()));
            }
            return null;
        }

        int index = 0;
        boolean isContinue = true;
        while (isContinue) {
            String appConfigKey = String.format("%s.host.ip.%s", app.getCode(), index);
            AppConfigDO appConfig = appConfigService.getAppConfigByKey(monitorTask.getAppId(), appConfigKey);
            String hostIp = (appConfig == null ? null : appConfig.getValue());
            if (StringUtils.isNotBlank(hostIp)) {
                //DnsCacheManipulator.setDnsCache(app.getDomain(), hostIp);
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(String.format("Set host:%s,ip:%s.", app.getDomain(), hostIp));
                }

                int code = MonitorResultDO.CODE_SUCCESS;
                String responseString = null;
                try {
                    responseString = null;//new HttpUtils(monitorTask.getTarget()).execute();
                } catch (Throwable e) {
                    code = MonitorResultDO.CODE_FAILURE;
                    responseString = e.getMessage();
                }

                MonitorResultDO partResult = buildResult(monitorTask, responseString, code);
                partResult.setRemark(hostIp);
                monitorResultDAO.insert(partResult);

                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(String.format("Invocated url:%s,return:%s.",
                            monitorTask.getTarget(), responseString));
                }
            } else {
                isContinue = false;
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(String.format("No more host ip for app:%s,index:%s.",
                            app.getCode(), index));
                }
            }

            index++;
        }
        return buildResult(monitorTask, "成功", MonitorResultDO.CODE_SUCCESS);
    }

    private MonitorResultDO buildResult(MonitorTask monitorTask, String content, Integer code) {
        MonitorResultDO result = new MonitorResultDO();
        result.setAppId(monitorTask.getAppId());
        result.setMonitorObjectId(monitorTask.getMonitorObjectId());
        result.setMonitorType(monitorTask.getType().getValue());
        result.setContent(content);
        result.setCode(code);
        result.setStatus(StatusEnum.ENABLED.getCode());
        result.setCreatedTime(new Date());
        result.setCreator("");
        result.setModifiedTime(result.getCreatedTime());
        result.setModifier(result.getCreator());
        return result;
    }

    @Override
    public MonitorObjectType getType() {
        return MonitorObjectType.DNS_SWITCHABLE_HTTP;
    }
}
