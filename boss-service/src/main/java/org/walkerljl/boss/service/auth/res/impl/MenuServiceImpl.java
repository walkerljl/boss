package org.walkerljl.boss.service.auth.res.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.walkerljl.boss.dao.daointerface.auth.res.MenuDAO;
import org.walkerljl.boss.dao.dataobject.auth.res.MenuDO;
import org.walkerljl.boss.sdk.auth.model.tree.TreeNode;
import org.walkerljl.boss.service.auth.res.MenuService;
import org.walkerljl.boss.support.dao.daointerface.BaseDAO;
import org.walkerljl.boss.support.service.impl.BaseServiceImpl;

/**
 * MenuServiceImpl
 *
 * @author lijunlin
 */
@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl<Long, MenuDO> implements MenuService {

    @Resource
    private MenuDAO menuDAO;

    @Override
    public BaseDAO<Long, MenuDO> getDAO() {
        return menuDAO;
    }

    @Override
    public List<TreeNode> queryChildNodesByAppIdAndParentId(Long appId, Long parentId) {
        //return menuDAO.selectChildNodesByAppIdAndParentId(appId, parentId);
        return null;
    }

    @Override
    public List<MenuDO> queryAuthMenusByResCodeIds(List<Long> resCodeIds) {
        return null;
    }
}