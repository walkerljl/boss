package org.walkerljl.boss.service.auth.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.walkerljl.boss.dao.daointerface.auth.PostDAO;
import org.walkerljl.boss.dao.dataobject.auth.PostDO;
import org.walkerljl.boss.service.auth.PostService;
import org.walkerljl.boss.support.dao.daointerface.BaseDAO;
import org.walkerljl.boss.support.service.impl.BaseServiceImpl;

/**
 * PostServiceImpl
 *
 * @author lijunlin
 */
@Service("postService")
public class PostServiceImpl extends BaseServiceImpl<Long, PostDO> implements PostService {

    @Resource
    private PostDAO postDAO;

    @Override
    public BaseDAO<Long, PostDO> getDAO() {
        return postDAO;
    }
}