package org.walkerljl.boss.service.blog.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.walkerljl.boss.dao.daointerface.blog.ArticleTypeDAO;
import org.walkerljl.boss.dao.dataobject.blog.ArticleTypeDO;
import org.walkerljl.boss.service.blog.ArticleTypeService;
import org.walkerljl.boss.support.dao.daointerface.BaseDAO;
import org.walkerljl.boss.support.service.impl.BaseServiceImpl;

/**
 * ArticleTypeServiceImpl
 *
 * @author lijunlin
 */
@Service("articleTypeService")
public class ArticleTypeServiceImpl extends BaseServiceImpl<Long, ArticleTypeDO> implements ArticleTypeService {

    @Resource
    private ArticleTypeDAO articleTypeDAO;

    @Override
    public BaseDAO<Long, ArticleTypeDO> getDAO() {
        return articleTypeDAO;
    }
}
