package org.walkerljl.boss.service.v1.collect.check.support;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.walkerljl.boss.model.enums.monitor.MonitorObjectType;
import org.walkerljl.boss.service.v1.collect.Checker;
import org.walkerljl.toolkit.logging.Logger;
import org.walkerljl.toolkit.logging.LoggerFactory;

/**
 * Checker库
 *
 * @author lijunlin
 */
public class CheckerRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckerRepository.class);

    private static final Map<MonitorObjectType, Checker> REPOSITORY =
            new HashMap<MonitorObjectType, Checker>();

    /**
     * 绑定资源
     *
     * @param key
     * @param resource
     */
    public static void bind(MonitorObjectType key, Checker resource) {
        REPOSITORY.put(key, resource);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("Bind one resource,key:%s,resource:%s", key.toString(), resource.toString()));
        }
    }

    /**
     * 查找资源
     *
     * @param key
     * @return
     */
    public static Checker lookup(MonitorObjectType key) {
        if (key == null) {
            return null;
        }
        Checker resource = REPOSITORY.get(key);
        LOGGER.debug(String.format("Lookup one resource,key:%s,resource:%s", key.toString(), resource.toString()));
        return resource;
    }

    /**
     * 列出所有资源
     *
     * @return
     */
    public static Map<MonitorObjectType, Checker> list() {
        return Collections.unmodifiableMap(REPOSITORY);
    }
}
