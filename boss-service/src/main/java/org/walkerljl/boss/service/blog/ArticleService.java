package org.walkerljl.boss.service.blog;

import org.walkerljl.boss.dao.dataobject.blog.ArticleDO;
import org.walkerljl.boss.dao.dataobject.blog.CollectionDO;
import org.walkerljl.boss.dao.dataobject.blog.CommentDO;
import org.walkerljl.boss.dao.dataobject.blog.FocusDO;
import org.walkerljl.boss.support.service.BaseService;

/**
 * ArticleService
 *
 * @author lijunlin
 */
public interface ArticleService extends BaseService<Long, ArticleDO> {

    /**
     * 阅读
     *
     * @param id
     * @param readerId 阅读者Id
     * @return
     */
    ArticleDO read(Long id, String readerId);

    /**
     * 评论
     *
     * @param comment
     * @return
     */
    boolean comment(CommentDO comment);

    /**
     * 点赞
     *
     * @param id
     * @return
     */
    boolean praise(Long id);

    /**
     * 收藏
     *
     * @param collection
     * @return
     */
    boolean collect(CollectionDO collection);

    /**
     * 关注
     *
     * @param focus
     * @return
     */
    boolean focus(FocusDO focus);
}