package org.walkerljl.boss.dao.dataobject.blog;

import org.walkerljl.boss.support.dao.dataobject.BaseDO;

/**
 * 点赞
 *
 * @author lijunlin
 */
public class PraiseDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 文章Id
     */
    private Long articleId;

    public PraiseDO() {}

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }
}
