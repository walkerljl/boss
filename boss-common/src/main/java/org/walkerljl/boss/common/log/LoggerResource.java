package org.walkerljl.boss.common.log;

import org.walkerljl.boss.common.log.defaults.DefaultLoggerRepository;
import org.walkerljl.toolkit.standard.resource.Resource;
import org.walkerljl.toolkit.standard.resource.exception.CannotDestroyResourceException;
import org.walkerljl.toolkit.standard.resource.exception.CannotInitResourceException;
import org.walkerljl.toolkit.template.log.LoggerFactory;

/**
 * LoggerResource
 *
 * @author xingxun
 */
public class LoggerResource implements Resource {

    @Override
    public String getInstanceId() {
        return null;
    }

    @Override
    public void init() throws CannotInitResourceException {
        //绑定日志实现
        LoggerFactory.bindLoggerRepository(new DefaultLoggerRepository());
    }

    @Override
    public void destroy() throws CannotDestroyResourceException {

    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getGroup() {
        return null;
    }
}