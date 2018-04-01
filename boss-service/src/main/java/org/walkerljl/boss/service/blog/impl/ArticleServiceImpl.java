package org.walkerljl.boss.service.blog.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.walkerljl.boss.dao.daointerface.blog.ArticleDAO;
import org.walkerljl.boss.dao.daointerface.blog.CommentDAO;
import org.walkerljl.boss.dao.dataobject.blog.ArticleDO;
import org.walkerljl.boss.dao.dataobject.blog.CollectionDO;
import org.walkerljl.boss.dao.dataobject.blog.CommentDO;
import org.walkerljl.boss.dao.dataobject.blog.FocusDO;
import org.walkerljl.boss.service.blog.ArticleService;
import org.walkerljl.boss.support.dao.daointerface.BaseDAO;
import org.walkerljl.boss.support.service.impl.BaseServiceImpl;
import org.walkerljl.toolkit.lang.StringUtils;
import org.walkerljl.toolkit.standard.exception.AppServiceException;

/**
 * ArticleServiceImpl
 *
 * @author lijunlin
 */
@Service("articleService")
public class ArticleServiceImpl extends BaseServiceImpl<Long, ArticleDO> implements ArticleService {

    @Resource
    private ArticleDAO articleDAO;
    @Resource
    private CommentDAO commentDAO;

    @Override
    public BaseDAO<Long, ArticleDO> getDAO() {
        return articleDAO;
    }

    @Override
    public Long insert(ArticleDO article) {
        article.setCommentCount(0L);
        article.setCollectCount(0L);
        article.setReadCount(0L);
        article.setPraiseCount(0L);
        return super.insert(article);
    }

    @Override
    public ArticleDO read(Long id, String readerId) {
        ArticleDO article = selectByKey(id);
        if (article == null) {
            throw new AppServiceException("获取博客信息失败,无效的博客Id:" + id);
        }
        if (StringUtils.notEqauls(readerId, article.getCreator())) {
            article.setReadCount(article.getReadCount() + 1);
            if (updateByKey(article, article.getId()) <= 0) {
                throw new AppServiceException("增加博客阅读次数失败,博客Id:" + id);
            }
        }
        return article;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean comment(CommentDO comment) {
        ArticleDO article = selectByKey(comment.getArticleId());
        if (article == null) {
            throw new AppServiceException("评论失败,无效的博客Id:" + comment.getArticleId());
        }
        if (StringUtils.notEqauls(comment.getCreator(), article.getCreator())) {
            article.setCommentCount(article.getCommentCount() + 1);
            if (updateByKey(article, article.getId()) <= 0) {
                throw new AppServiceException("评论失败,博客Id:" + comment.getArticleId());
            }
        } else {
            throw new AppServiceException("不能评论自己的帖子");
        }
        return true;
    }

    @Override
    public boolean praise(Long id) {
        ArticleDO article = selectByKey(id);
        if (article != null) {
            article.setPraiseCount(article.getPraiseCount() + 1);
            if (updateByKey(article, article.getId()) <= 0) {
                throw new AppServiceException("点赞失败,博客Id:" + id);
            }
        } else {
            throw new AppServiceException("点赞失败,无效的博客Id:" + id);
        }
        return true;
    }

    @Override
    public boolean collect(CollectionDO collection) {
        ArticleDO article = selectByKey(collection.getArticleId());
        if (article != null) {
            article.setCollectCount(article.getCollectCount() + 1);
            if (updateByKey(article, article.getId()) <= 0) {
                throw new AppServiceException("收藏失败,博客Id:" + collection.getArticleId());
            }
        } else {
            throw new AppServiceException("收藏失败,无效的博客Id:" + collection.getArticleId());
        }
        return true;
    }

    @Override
    public boolean focus(FocusDO focus) {
        // TODO Auto-generated method stub
        return false;
    }
}