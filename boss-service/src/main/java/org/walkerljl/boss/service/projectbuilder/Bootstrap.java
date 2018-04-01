//package org.walkerljl.boss.service.projectbuilder;
//
//import org.walkerljl.boss.service.projectbuilder.entity.AppInfo;
//import org.walkerljl.boss.service.projectbuilder.impl.BuilderImpl;
//import org.walkerljl.boss.service.projectbuilder.impl.InputImpl;
//import org.walkerljl.commons.format.Scanf;
//import java.net.URL;
//
///**
// * 启动程序
// *
// * @author lijunlin
// */
//public class Bootstrap {
//
//
//    public static void main(String[] args) throws Exception {
//        Input input = new InputImpl();
//        AppInfo appInfo = input.read();
//        if (appInfo.isDbSupport()) {
//            MetaData.buildAppInfo(appInfo);
//        } else {
//            appInfo.getDatabase().addTable(MetaData.getTestTable());
//        }
//
//        URL url = Bootstrap.class.getResource(Constants.SEARCH_FLAG);
//        if (url == null) {
//            System.out.println("错误,找不到模板项目.");
//            System.exit(1);
//        }
//        System.out.println("Load [" + url.toString() + "].");
//        Builder builder = new BuilderImpl();
//        builder.build(url, appInfo);
//        System.out.println("\r\n\r\n项目已为您构建成功,请导入到您习惯的IDE使用.\r\n\r\n");
//
//        Scanf.readLine("按任意键退出");
//    }
//}
