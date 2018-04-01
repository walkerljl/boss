package org.walkerljl.boss.support.mvc.controller.template;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.walkerljl.boss.support.service.BaseService;
import org.walkerljl.boss.support.service.JqueryDatatableBaseService;

/**
 * JqueryDatatableCurdTemplate
 *
 * @author xingxun
 */
public abstract class JqueryDatatableCurdTemplate<T> extends DataTableTemplate<T> {

    /**
     * 实现父类的getService
     */
    public BaseService<Long, T> getService() {
        return getJqueryDatatableBaseService();
    }

    @Override
    @RequestMapping(value = "selectJSONPage")
    public ModelAndView selectJSONPage(T t) {
        return toJSON(processJqueryDatatableSelectPage(t));
    }

    /**
     * 处理Jquery Datatable查询分页数据
     *
     * @param t
     * @return
     */
    protected Map<String, Object> processJqueryDatatableSelectPage(T t) {
        return getJqueryDatatableBaseService().selectJqueryDatatablePage(t);
    }

    /**
     * 获取业务接口对象
     *
     * @return
     */
    public abstract JqueryDatatableBaseService<Long, T> getJqueryDatatableBaseService();
}