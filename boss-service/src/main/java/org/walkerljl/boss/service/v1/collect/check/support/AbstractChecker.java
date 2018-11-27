package org.walkerljl.boss.service.v1.collect.check.support;

import javax.annotation.Resource;

import org.walkerljl.boss.dao.daointerface.monitor.v1.MonitorResultDAO;
import org.walkerljl.boss.dao.dataobject.monitor.v1.MonitorResultDO;
import org.walkerljl.boss.model.enums.monitor.MonitorObjectType;
import org.walkerljl.boss.service.v1.collect.Checker;
import org.walkerljl.boss.service.v1.collect.task.MonitorTask;
import org.walkerljl.toolkit.logging.Logger;
import org.walkerljl.toolkit.logging.LoggerFactory;

/**
 * AbstractChecker
 *
 * @author lijunlin
 */
public abstract class AbstractChecker implements Checker {

    protected final Logger LOGGER     = LoggerFactory.getLogger(getClass());
    private final   String objectName = getClass().getSimpleName();
    @Resource
    private MonitorResultDAO monitorResultDAO;

    public AbstractChecker() {
        CheckerRepository.bind(getType(), this);
    }

    @Override
    public MonitorResultDO check(MonitorTask monitorTask) {
        MonitorResultDO result = null;
        try {
            result = check0(monitorTask);
            monitorResultDAO.insert(result);
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(String.format("%s has executed one monitor task:%s,result:%s",
                        objectName, monitorTask.toString(), (result == null ? "NULL" : result.toString())));
            }
            if (result.getCode() == MonitorResultDO.CODE_FAILURE) {
                sendErrorNotice(result);
            }
            return result;
        } catch (Throwable e) {
            LOGGER.error(String.format("%s occurs one error when do check.", objectName), e);
        }
        return null;
    }

    private void sendErrorNotice(MonitorResultDO monitorResult) {
        //SmtpServer smtpServer = SmtpServer.create("smtp.163.com", 25)
        //        .authenticateWith(new SimpleAuthenticator("walkerljls@163.com", "xxxx"));
        //SendMailSession sendMailSession = smtpServer.createSession();
        //sendMailSession.open();
        //Email mail = Email.create();
        //mail.setFrom(new MailAddress("walkerljl", "walkerljls@163.com"));
        //mail.setTo(new MailAddress("xxxx"));
        //mail.setSubject("walkerljl监控");
        //mail.addText(monitorResult.toString());
        //sendMailSession.sendMail(mail);
    }

    /**
     * 获取类型
     *
     * @return
     */
    public abstract MonitorObjectType getType();

    /**
     * Check
     *
     * @param monitorTask
     * @return
     */
    public abstract MonitorResultDO check0(MonitorTask monitorTask);
}
