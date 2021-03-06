package org.walkerljl.boss.common.log.defaults;

import org.walkerljl.toolkit.logging.LoggerFactory;
import org.walkerljl.toolkit.template.log.Logger;
import org.walkerljl.toolkit.template.log.LoggerRepository;

/**
 * DefaultLoggerRepository
 *
 * @author xingxun
 */
public class DefaultLoggerRepository implements LoggerRepository {

    @Override
    public Logger getLogger(Class<?> clazz) {
        return new DefaultLogger(LoggerFactory.getLogger(clazz));
    }

    @Override
    public Logger getLogger(String name) {
        return new DefaultLogger(LoggerFactory.getLogger(name));
    }
}