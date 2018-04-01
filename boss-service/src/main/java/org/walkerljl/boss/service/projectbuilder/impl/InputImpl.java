//package org.walkerljl.boss.service.projectbuilder.impl;
//
//import org.walkerljl.boss.service.projectbuilder.Input;
//import org.walkerljl.boss.service.projectbuilder.entity.AppInfo;
//import org.walkerljl.boss.service.projectbuilder.entity.Database;
//import org.walkerljl.commons.format.Scanf;
//import org.walkerljl.commons.util.StringUtils;
//
//import java.io.File;
//
///**
// * @author lijunlin
// */
//public class InputImpl implements Input {
//
//    @Override
//    public AppInfo read() throws Exception {
//        boolean confirm = false;
//        boolean dbSupport = false;
//
//        AppInfo appInfo = new AppInfo();
//        while (!confirm) {
//            String groupId = StringUtils.isNotBlank(appInfo.getGroupId()) ? appInfo.getGroupId() : Scanf.readLine("请输入groupId(如:com.jd.simple)\t");
//            appInfo.setGroupId(groupId);
//            if (StringUtils.isBlank(groupId)) {
//                continue;
//            }
//
//            String artifactId = StringUtils.isNotBlank(appInfo.getArtifactId()) ? appInfo.getArtifactId() : Scanf.readLine("请输入artifactId(如:jd-simple)\t");
//            appInfo.setArtifactId(artifactId);
//            if (!StringUtils.isNotBlank(artifactId)) {
//                continue;
//            }
//
//            String author = StringUtils.isNotBlank(appInfo.getAuthor()) ? appInfo.getAuthor() : Scanf.readLine("请输入代码作者(如:zhangsan)\t");
//            appInfo.setAuthor(author);
//            if (!StringUtils.isNotBlank(author)) {
//                continue;
//            }
//
//            dbSupport = "Y".equalsIgnoreCase(Scanf.readLine("是否连接数据库生成更多代码?(Y/N)\t"));
//            if (dbSupport) {
//                Database db = appInfo.getDatabase();
//                String dbAddr = StringUtils.isNotBlank(db.getAddress()) ? db.getAddress() : Scanf.readLine("请输入数据库地址(如:ip:port)\t\t");
//                db.setAddress(dbAddr);
//                if (StringUtils.isBlank(dbAddr)) {
//                    continue;
//                }
//
//                String dbName = StringUtils.isNotBlank(db.getName()) ? db.getName() : Scanf.readLine("请输入数据库库名称\t\t");
//                db.setName(dbName);
//                if (StringUtils.isBlank(dbName)) {
//                    continue;
//                }
//
//                String user = StringUtils.isNotBlank(db.getUser()) ? db.getUser() : Scanf.readLine("请输入数据库库用户名\t\t");
//                db.setUser(user);
//                if (StringUtils.isBlank(user)) {
//                    continue;
//                }
//
//                String password = StringUtils.isNotBlank(db.getPassword()) ? db.getPassword() : Scanf.readLine("请输入数据库密码\t\t");
//                db.setPassword(password);
//                if (StringUtils.isNotBlank(password)) {
//                    continue;
//                }
//
//                String tablePattern = StringUtils.isNotBlank(db.getTablePattern()) ? db.getTablePattern() : Scanf.readLine("请输入表匹配关键字(可为空)\t\t");
//                if (StringUtils.isBlank(tablePattern)) {
//                    tablePattern = null;
//                }
//                db.setTablePattern(tablePattern);
//            }
//
//            String output = StringUtils.isBlank(appInfo.getOutputPath()) ? appInfo.getOutputPath() : Scanf.readLine("请输入项目工程保存路径(如:./)\t");
//            appInfo.setOutputPath(output);
//            if (StringUtils.isBlank(output)) {
//                continue;
//            }
//
//            //初始化输出目录
//            File outDir = new File(output);
//            String msg = String.format("\r\n您输入的信息如下:\r\ngroupId\t\t:%s\r\nartifactId\t:%s\r\nauthor\t\t:%s\r\nsavePath\t:%s",
//                    groupId, artifactId, author, outDir.getAbsolutePath());
//            if (dbSupport) {
//                Database db = appInfo.getDatabase();
//                msg += String.format("\r\n数据库地址\t\t:%s\r\n数据库名称\t\t:%s\r\n数据库用户\t\t:%s\r\n数据库密码\t\t:%s",
//                        appInfo.getDatabase(), db.getName(), db.getUser(), db.getPassword());
//            }
//            confirm = "Y".equalsIgnoreCase(Scanf.readLine(msg + "\r\n\r\n以上信息是否正确,请确认?(Y/N)"));
//            if (!confirm) {
//                appInfo = new AppInfo();
//            }
//        }
//        return appInfo;
//    }
//}
