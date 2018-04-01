package org.walkerljl.boss.service.auth;

import java.util.List;

import org.walkerljl.boss.dao.dataobject.auth.PostRoleMappDO;
import org.walkerljl.boss.support.service.BaseService;

/**
 * 岗位、角色映射业务接口
 *
 * @author lijunlin
 */
public interface PostRoleMappService extends BaseService<Long, PostRoleMappDO> {

    /**
     * 通过岗位ID列表查询岗位、角色映射列表
     *
     * @param postIds
     * @return
     */
    List<PostRoleMappDO> selectByPostIds(List<Long> postIds);
}
