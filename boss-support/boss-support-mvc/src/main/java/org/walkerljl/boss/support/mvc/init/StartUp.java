/*
 * Copyright (c) 2010-2015 www.walkerljl.org All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.boss.support.mvc.init;

import org.walkerljl.configuration.client.ConfiguratorFactory;
import org.walkerljl.configuration.client.impl.readonly.PropertiesConfiguratorProvider;
import org.walkerljl.toolkit.logging.Logger;
import org.walkerljl.toolkit.logging.LoggerFactory;

/**
 * StartUp
 *
 * @author xingxun
 */
public abstract class StartUp {

    public static String vTime = "";
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    //@Resource private AppConfigDao appConfigDao;

    public void start() {
        LOGGER.info("Initializing system data");
        try {
            if ("".equals(vTime)) {
                vTime = System.currentTimeMillis() + "";
            }
            ConfiguratorFactory.bind(new PropertiesConfiguratorProvider(new String[] {"/conf/config.properties"}));
            //ConfiguratorFactory.init(new DefaultAppConfigLoader());
            subProcess();
        } catch (Exception e) {
            LOGGER.info("Fail to initialize system data:" + e.getMessage(), e);
        }
        LOGGER.info("Finished to initialize system data");
    }

    //	private class DefaultAppConfigLoader implements AppConfigLoader {
    //		@Override
    //		public List<AppConfig> loadAll() {
    //			return appConfigDao.selectList(null);
    //		}
    //	}

    /**
     * 子过程
     */
    public abstract void subProcess();
}