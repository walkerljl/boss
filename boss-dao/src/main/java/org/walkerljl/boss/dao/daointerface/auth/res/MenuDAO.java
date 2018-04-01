/*
 * Copyright (c) 2010-2015 www.walkerljl.org All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.boss.dao.daointerface.auth.res;

import org.walkerljl.boss.dao.dataobject.auth.res.MenuDO;
import org.walkerljl.boss.support.dao.daointerface.BaseDAO;

/**
 * 菜单数据访问接口
 *
 * @author lijunlin
 */
public interface MenuDAO extends BaseDAO<Long, MenuDO> {

    /**
     * 根据应用ID和父ID查询孩子节点
     *
     * @param appId    应用ID
     * @param parentId 父ID
     * @return
     */
    //List<TreeNode> selectChildNodesByAppIdAndParentId(Long appId, Long parentId);
}
