package org.walkerljl.boss.service.v1.collect.check.listener.support;

import org.springframework.stereotype.Component;
import org.walkerljl.boss.model.enums.monitor.MonitorObjectType;
import org.walkerljl.boss.service.v1.collect.Checker;
import org.walkerljl.boss.service.v1.collect.check.listener.MonitorTaskMessageListener;
import org.walkerljl.boss.service.v1.collect.check.support.CheckerRepository;
import org.walkerljl.boss.service.v1.collect.task.MonitorTask;
import org.walkerljl.toolkit.logging.Logger;
import org.walkerljl.toolkit.logging.LoggerFactory;

/**
 * DefaultMonitorTaskMessageListener
 *
 * @author lijunlin
 */
@Component("monitorTaskMessageListener")
public class DefaultMonitorTaskMessageListener implements MonitorTaskMessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultMonitorTaskMessageListener.class);

    @Override
    public void onMessage(MonitorTask message) {
        if (message == null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Received messge is null.");
            }
            return;
        }
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(String.format("Received one message:%s", message.toString()));
        }
        onMessage0(message);
    }

    /**
     * Process on message
     *
     * @param message
     */
    public void onMessage0(MonitorTask message) {
        MonitorObjectType monitorTaskType = message.getType();
        Checker checker = CheckerRepository.lookup(monitorTaskType);
        if (checker == null) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("Lookup monitor task checker is null");
            }
            return;
        }
        checker.check(message);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(String.format("%s check one monitor task:%s", checker.toString(), message.toString()));
        }
    }
}
