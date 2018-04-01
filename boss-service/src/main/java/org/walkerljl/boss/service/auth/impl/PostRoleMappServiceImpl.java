package org.walkerljl.boss.service.auth.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.walkerljl.boss.dao.daointerface.auth.PostRoleMappDAO;
import org.walkerljl.boss.dao.dataobject.auth.PostRoleMappDO;
import org.walkerljl.boss.service.auth.PostRoleMappService;
import org.walkerljl.boss.support.dao.daointerface.BaseDAO;
import org.walkerljl.boss.support.service.impl.BaseServiceImpl;

/**
 * PostRoleMappServiceImpl
 *
 * @author lijunlin
 */
@Service("postRoleMappService")
public class PostRoleMappServiceImpl extends BaseServiceImpl<Long, PostRoleMappDO> implements PostRoleMappService {

    @Resource
    private PostRoleMappDAO postRoleMappDAO;

    @Override
    public BaseDAO<Long, PostRoleMappDO> getDAO() {
        return postRoleMappDAO;
    }

    @Override
    public List<PostRoleMappDO> selectByPostIds(List<Long> postIds) {
        // TODO Auto-generated method stub
        return null;
    }
}