package org.walkerljl.boss.service.auth.res;

import java.util.List;

import org.walkerljl.boss.dao.dataobject.auth.res.MenuDO;
import org.walkerljl.boss.sdk.auth.model.tree.TreeNode;
import org.walkerljl.boss.support.service.BaseService;

/**
 * 菜单业务逻辑接口
 *
 * @author lijunlin
 */
public interface MenuService extends BaseService<Long, MenuDO> {

    /**
     * 根据应用ID和父ID查询孩子节点
     *
     * @param appId    应用ID
     * @param parentId 父ID
     * @return
     */
    List<TreeNode> queryChildNodesByAppIdAndParentId(Long appId, Long parentId);

    List<MenuDO> queryAuthMenusByResCodeIds(List<Long> resCodeIds);
}
