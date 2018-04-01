package org.walkerljl.boss.support.service.impl;


import org.walkerljl.boss.support.dao.dataobject.JqueryDatatableBaseDO;
import org.walkerljl.boss.support.dao.dataobject.JqueryDatatableBaseDONoRule;
import org.walkerljl.boss.support.service.JqueryDatatableBaseService;
import org.walkerljl.toolkit.lang.MapUtils;
import org.walkerljl.toolkit.standard.model.paging.Paginator;

import java.io.Serializable;
import java.util.Map;

/**
 * JqueryDatatableBaseServiceImpl
 *
 * @author lijunlin
 */
public abstract class JqueryDatatableBaseServiceImpl<KEY extends Serializable, T> extends BaseServiceImpl<KEY, T> implements JqueryDatatableBaseService<KEY, T> {

    /**
     * dataTable分页插件,响应次数Key
     */
    protected static final String DATATABLE_SECHO_KEY = "sEcho";
    /**
     * dataTable分页插件,记录总数Key
     */
    protected static final String DATATABLE_TOTAL_RECORDS_KEY = "iTotalRecords";
    /**
     * dataTable分页插件,显示的记录总数Key
     */
    protected static final String DATATABLE_TOTAL_DISPLAY_RECORDS_KEY = "iTotalDisplayRecords";
    /**
     * dataTable分页插件,分页数据Key
     */
    protected static final String DATATABLE_DATA_KEY = "data";

    @Override
    public Map<String, Object> selectJqueryDatatablePage(T condition) {
        if (condition == null) {
            return null;
        }
        Map<String, Object> resultMap = MapUtils.newHashMap();
        if (condition instanceof JqueryDatatableBaseDO) {
            resultMap.put(DATATABLE_SECHO_KEY, ((JqueryDatatableBaseDO) condition).getsEcho());
        } else if (condition instanceof JqueryDatatableBaseDONoRule) {
            resultMap.put(DATATABLE_SECHO_KEY, ((JqueryDatatableBaseDONoRule) condition).getsEcho());
        } else {
            return null;
        }
        Paginator<T> page = selectPage(condition);
        resultMap.put(DATATABLE_TOTAL_RECORDS_KEY, page.getTotalCount());
        resultMap.put(DATATABLE_TOTAL_DISPLAY_RECORDS_KEY, page.getTotalCount());
        resultMap.put(DATATABLE_DATA_KEY, page.getDataList());
        return resultMap;
    }
}