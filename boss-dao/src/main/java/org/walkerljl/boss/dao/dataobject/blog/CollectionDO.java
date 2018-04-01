package org.walkerljl.boss.dao.dataobject.blog;

import org.walkerljl.boss.support.dao.dataobject.BaseDO;

/**
 * 收藏
 *
 * @author lijunlin
 */
public class CollectionDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 帖子Id
     */
    private Long articleId;

    public CollectionDO() {}

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }
}