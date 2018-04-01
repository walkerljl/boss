package org.walkerljl.boss.dao.daointerface.blog.impl;

import org.springframework.stereotype.Repository;
import org.walkerljl.boss.dao.daointerface.blog.ArticleDAO;
import org.walkerljl.boss.dao.dataobject.blog.ArticleDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * ArticleDaoImpl
 *
 * @author lijunlin
 */
@Repository("articleDAO")
public class ArticleDAOImpl extends BaseDAOImpl<Long, ArticleDO> implements ArticleDAO {


}
