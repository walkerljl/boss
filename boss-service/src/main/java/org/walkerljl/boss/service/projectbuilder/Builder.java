package org.walkerljl.boss.service.projectbuilder;

import org.walkerljl.boss.service.projectbuilder.entity.AppInfo;

import java.net.URL;

/**
 * @author lijunlin
 */
public interface Builder {

    void build(URL url, AppInfo appInfo) throws Exception;
}
