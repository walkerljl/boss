package org.walkerljl.boss.service.projectbuilder.impl;

import org.jboss.netty.bootstrap.Bootstrap;
import org.walkerljl.boss.service.projectbuilder.Builder;
import org.walkerljl.boss.service.projectbuilder.Constants;
import org.walkerljl.boss.service.projectbuilder.ReplaceData;
import org.walkerljl.boss.service.projectbuilder.entity.AppInfo;
import org.walkerljl.boss.service.projectbuilder.entity.Table;

import java.io.*;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author lijunlin
 */
public class BuilderImpl implements Builder {

    @Override
    public void build(URL url, AppInfo appInfo) throws Exception {
        if ("jar".equals(url.getProtocol())) {
            buildFromJar(appInfo, url);
        } else if ("file".equals(url.getProtocol())) {
            buildFromDir(appInfo, new File(URLDecoder.decode(url.getFile(), Constants.CHARTSET)));
        } else {
            System.out.println("错误,找不到模板项目.");
        }
    }

    private String replace(AppInfo appInfo, String content, boolean filePath, Table table, boolean isDomain) {
        if (filePath) {
            return ReplaceData.replacePath(appInfo, content, table);
        }
        if (isDomain) {
            return ReplaceData.replaceDomain(appInfo, content, table);
        }
        return ReplaceData.replace(appInfo, content, table);
    }

    private void write(String content, OutputStream os) {
        try {
            os.write(content.getBytes(Constants.CHARTSET));
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String read(InputStream is) {
        ByteArrayOutputStream os = null;
        try {
            os = new ByteArrayOutputStream();
            byte[] bs = new byte[1024];
            int readed = -1;
            while ((readed = is.read(bs)) != -1) {
                os.write(bs, 0, readed);
            }
            is.close();
            os.flush();
            os.close();
            return (new String(os.toByteArray(), Constants.CHARTSET));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private void buildFromDir(AppInfo appInfo, File dir) throws Exception {
        if (!dir.exists()) {
            return;
        }
        if (dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                buildFromDir(appInfo, file);
            }
        }
        String filePath = dir.getAbsolutePath().replace("\\", "/");
        if (filePath.indexOf(Constants.SEARCH_FLAG) < 0) {
            return;
        }
        int index = filePath.indexOf(Constants.SEARCH_FLAG) + 9;

        InputStream in = null;
        if (!dir.isDirectory() && filePath.contains("${table.className}")) {
            for (Table table : appInfo.getDatabase().getTables()) {
                if (!dir.isDirectory()) {
                    in = new FileInputStream(dir);
                }
                File outFile = new File(appInfo.getOutputPath(), replace(appInfo, filePath.substring(index), true, table, false));
                build(appInfo, outFile, in, dir.isDirectory(), table, filePath.endsWith("${table.className}.java.vm"));
            }
        } else {
            if (!dir.isDirectory()) {
                in = new FileInputStream(dir);
            }
            File outFile = new File(appInfo.getOutputPath(), replace(appInfo, filePath.substring(index), true, null, false));
            build(appInfo, outFile, in, dir.isDirectory(), null, false);
        }
    }

    private void buildFromJar(AppInfo appInfo, URL url) throws Exception {
        JarURLConnection connection = (JarURLConnection) url.openConnection();
        JarFile jar = connection.getJarFile();
        try {
            for (Enumeration<JarEntry> e = jar.entries(); e.hasMoreElements(); ) {
                JarEntry entry = e.nextElement();
                String name = entry.getName();
                if (!name.startsWith(Constants.SEARCH_FLAG)) {
                    continue;
                }

                InputStream in = null;
                if (!entry.isDirectory() && name.contains("${table.className}")) {
                    for (Table table : appInfo.getDatabase().getTables()) {
                        if (!entry.isDirectory()) {
                            in = Bootstrap.class.getResourceAsStream(name);
                        }
                        File outFile = new File(appInfo.getOutputPath(), replace(appInfo, name.substring(9), true, table, false));
                        build(appInfo, outFile, in, entry.isDirectory(), table, name.endsWith("${table.className}.java.vm"));
                    }
                } else {
                    if (!entry.isDirectory()) {
                        in = Bootstrap.class.getResourceAsStream(name);
                    }
                    build(appInfo, new File(appInfo.getOutputPath(), replace(appInfo, name.substring(9), true, null, false)), in, entry.isDirectory(), null, false);
                }

            }
        } finally {
            jar.close();// 解除文件占用
        }
    }

    private void build(AppInfo appInfo, File file, InputStream in, boolean isDir, Table table, boolean domain) throws Exception {
        if (isDir) {
            if (!file.exists()) {
                file.mkdirs();
                System.out.println(String.format("创建目录:%s", file.getAbsolutePath()));
            }
        } else {
            File parent = file.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
                System.out.println(String.format("创建目录:%s", parent.getAbsolutePath()));
            }
            boolean exists = file.exists();
            String fileContent = replace(appInfo, read(in), false, table, domain);
            write(fileContent, new FileOutputStream(file));
            System.out.println(String.format("%s文件:%s", (exists ? "更新" : "创建"), file.getAbsolutePath()));
        }
    }
}
