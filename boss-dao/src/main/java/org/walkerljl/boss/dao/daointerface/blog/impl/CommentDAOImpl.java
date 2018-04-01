package org.walkerljl.boss.dao.daointerface.blog.impl;

import org.springframework.stereotype.Repository;
import org.walkerljl.boss.dao.daointerface.blog.CommentDAO;
import org.walkerljl.boss.dao.dataobject.blog.CommentDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * CommentDaoImpl
 *
 * @author lijunlin
 */
@Repository("commentDAO")
public class CommentDAOImpl extends BaseDAOImpl<Long, CommentDO> implements CommentDAO {

}
