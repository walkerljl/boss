package org.walkerljl.boss.dao.dataobject.blog;

import org.walkerljl.boss.support.dao.dataobject.BaseDO;
import org.walkerljl.toolkit.db.api.annotation.Column;
import org.walkerljl.toolkit.db.api.annotation.Entity;

/**
 * Article
 *
 * @author lijunlin
 */
@Entity("blog_article")
public class ArticleDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    @Column("title")
    private String title;
    /**
     * 类型Id
     */
    @Column("type_id")
    private Long typeId;
    /**
     * 顺序值
     */
    @Column("order")
    private Integer order;
    /**
     * 内容
     */
    @Column("content")
    private String content;
    /**
     * 评论次数
     */
    @Column("comment_count")
    private Long commentCount;
    /**
     * 阅读次数
     */
    @Column("read_count")
    private Long readCount;
    /**
     * 受赞次数
     */
    @Column("praise_count")
    private Long praiseCount;
    /**
     * 收藏次数
     */
    @Column("collect_count")
    private Long collectCount;

    //扩展字段
    /**
     * 分类名称
     */
    private String typeName;
    /**
     * 搜索文本
     */
    private String searchText;

    public ArticleDO() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public Long getReadCount() {
        return readCount;
    }

    public void setReadCount(Long readCount) {
        this.readCount = readCount;
    }

    public Long getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(Long praiseCount) {
        this.praiseCount = praiseCount;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public Long getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Long collectCount) {
        this.collectCount = collectCount;
    }
}