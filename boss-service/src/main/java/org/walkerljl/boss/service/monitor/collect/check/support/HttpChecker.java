package org.walkerljl.boss.service.monitor.collect.check.support;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.walkerljl.boss.dao.dataobject.monitor.MonitorResultDO;
import org.walkerljl.boss.model.enums.monitor.MonitorObjectType;
import org.walkerljl.boss.service.monitor.collect.task.MonitorTask;
import org.walkerljl.boss.support.enums.StatusEnum;

/**
 * HttpMonitor
 *
 * @author lijunlin
 */
@Component("httpChecker")
public class HttpChecker extends AbstractChecker {

    @Override
    public MonitorResultDO check0(MonitorTask monitorTask) {
        String responseString = null;
        int code = MonitorResultDO.CODE_SUCCESS;
        try {
            responseString = null;//new HttpUtils(monitorTask.getTarget()).execute();
        } catch (Throwable e) {
            code = MonitorResultDO.CODE_FAILURE;
            responseString = e.getMessage();
        }
        MonitorResultDO result = new MonitorResultDO();
        result.setAppId(monitorTask.getAppId());
        result.setMonitorObjectId(monitorTask.getMonitorObjectId());
        result.setMonitorType(monitorTask.getType().getValue());
        result.setCode(code);
        result.setContent(responseString);
        result.setStatus(StatusEnum.ENABLED.getCode());
        result.setCreatedTime(new Date());
        result.setCreator("");
        result.setModifiedTime(result.getCreatedTime());
        result.setModifier(result.getCreator());
        return result;
    }

    @Override
    public MonitorObjectType getType() {
        return MonitorObjectType.HTTP;
    }
}
