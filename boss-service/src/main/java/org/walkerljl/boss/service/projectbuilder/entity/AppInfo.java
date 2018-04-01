package org.walkerljl.boss.service.projectbuilder.entity;

import java.io.Serializable;

import org.walkerljl.toolkit.lang.datetime.DateUtils;

/**
 * @author lijunlin
 */
public class AppInfo implements Serializable {

    private static final long serialVersionUID = 1315279473925732075L;

    private String groupId;
    private String artifactId;
    private String author;
    private Database database;
    private String outputPath;
    private boolean dbSupport;

    /**
     * 默认构造函数
     */
    public AppInfo() {}

    public String getDate() {
        return DateUtils.getCurrentDate();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public boolean isDbSupport() {
        return dbSupport;
    }

    public void setDbSupport(boolean dbSupport) {
        this.dbSupport = dbSupport;
    }
}
