package org.walkerljl.boss.service.sys.impl.integration;

import org.junit.Test;
import org.walkerljl.boss.service.base.BaseIntegrationTest;
import org.walkerljl.boss.service.sys.AppConfigService;

import javax.annotation.Resource;
import java.io.FileNotFoundException;

/**
 * AppConfigServiceImplTest
 *
 * @author lijunlin
 */
public class AppConfigServiceImplTest extends BaseIntegrationTest {

    @Resource
    private AppConfigService appConfigService;

    @Test
    public void test() throws FileNotFoundException {
//        String dataFilePath = "C:\\Users\\lijunlin\\Desktop\\checker服务器列表.xlsx";
//        List<List<Object>> dataList =
//                ExcelUtils.read(new FileInputStream(dataFilePath));
//        List<AppConfig> appConfigs = new ArrayList<AppConfig>();
//        int index = 0;
//        for (List<Object> row : dataList) {
//            String ip = String.valueOf(row.get(0));
//            LOGGER.debug(ip);
//
//            AppConfig appConfig = new AppConfig();
//            appConfigs.add(appConfig);
//            appConfig.initBaseFieldValues("admin");
//            appConfig.setAppId(2L);
//            appConfig.setKey("checker.host.ip." + index);
//            appConfig.setName("checker主机列表");
//            appConfig.setValue(ip.trim());
//
//            index ++;
//        }
//
//        appConfigService.insert(appConfigs);
    }

}
