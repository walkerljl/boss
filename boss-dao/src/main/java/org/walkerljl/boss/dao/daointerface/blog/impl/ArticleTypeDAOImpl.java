package org.walkerljl.boss.dao.daointerface.blog.impl;

import org.springframework.stereotype.Repository;
import org.walkerljl.boss.dao.daointerface.blog.ArticleTypeDAO;
import org.walkerljl.boss.dao.dataobject.blog.ArticleTypeDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * ArticleTypeDaoImpl
 *
 * @author lijunlin
 */
@Repository("articleTypeDAO")
public class ArticleTypeDAOImpl extends BaseDAOImpl<Long, ArticleTypeDO> implements ArticleTypeDAO {

}
