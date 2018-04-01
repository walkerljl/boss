package org.walkerljl.boss.common;

import org.walkerljl.configuration.client.Configurator;
import org.walkerljl.configuration.client.ConfiguratorFactory;

/**
 * Constants
 *
 * @author xingxun
 */
public class Constants {

    public static Configurator getConfigurator() {
        return ConfiguratorFactory.getStdConfigurator();
    }
}
