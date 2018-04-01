package org.walkerljl.boss.support.service;

import java.io.Serializable;
import java.util.Map;

/**
 * 基于Jquery DataTable的基础业务接口
 *
 * @author lijunlin
 */
public interface JqueryDatatableBaseService<KEY extends Serializable, T> extends BaseService<KEY, T> {

    /**
     * 查询适用于Jquery DataTable的分页数据
     *
     * @param condition
     * @return
     */
    Map<String, Object> selectJqueryDatatablePage(T condition);
}
