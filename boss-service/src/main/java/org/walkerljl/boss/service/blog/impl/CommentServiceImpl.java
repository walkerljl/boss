package org.walkerljl.boss.service.blog.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.walkerljl.boss.dao.daointerface.blog.CommentDAO;
import org.walkerljl.boss.dao.dataobject.blog.CommentDO;
import org.walkerljl.boss.service.blog.CommentService;
import org.walkerljl.boss.support.dao.daointerface.BaseDAO;
import org.walkerljl.boss.support.service.impl.BaseServiceImpl;


/**
 * CommentServiceImpl
 *
 * @author lijunlin
 */
@Service("commentService")
public class CommentServiceImpl extends BaseServiceImpl<Long, CommentDO> implements CommentService {

    @Resource
    private CommentDAO commentDAO;

    @Override
    public BaseDAO<Long, CommentDO> getDAO() {
        return commentDAO;
    }
}