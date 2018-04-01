package org.walkerljl.boss.dao.dataobject.blog;

import org.walkerljl.boss.support.dao.dataobject.BaseDO;
import org.walkerljl.toolkit.db.api.annotation.Column;
import org.walkerljl.toolkit.db.api.annotation.Entity;

/**
 * 评论
 *
 * @author lijunlin
 */
@Entity("blog_article_comment")
public class CommentDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 评论文章Id
     */
    @Column("article_id")
    private Long articleId;
    /**
     * 父评论Id
     */
    @Column("parent_id")
    private Long parentId;
    /**
     * 评论内容
     */
    @Column("content")
    private String content;

    public CommentDO() {}

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}