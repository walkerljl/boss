package org.walkerljl.boss.dao.daointerface.auth.res.impl;

import org.springframework.stereotype.Repository;
import org.walkerljl.boss.dao.daointerface.auth.res.MenuDAO;
import org.walkerljl.boss.dao.dataobject.auth.res.MenuDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * MenuDao
 *
 * @author lijunlin
 */
@Repository("menuDAO")
public class MenuDAOImpl extends BaseDAOImpl<Long, MenuDO> implements MenuDAO {

    public MenuDAOImpl() {
        super.baseNameSpace = "org.walkerljl.identity.dao.auth.res.MenuDAO";
    }

    //@Override
    //public List<TreeNode> selectChildNodesByAppIdAndParentId(Long appId, Long parentId) {
    //    if (appId == null || parentId == null) {
    //        return null;
    //    }
    //    Map<String, Object> params = MapUtils.newHashMap();
    //    params.put("appId", appId);
    //    params.put("parentId", parentId);
    //    return selectList(getNameSpace("selectChildNodesByAppIdAndParentId"), params);
    //}
}