package org.walkerljl.boss.support.mvc.controller.template;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.walkerljl.boss.sdk.auth.annotation.Authentication;
import org.walkerljl.boss.support.BaseModel;
import org.walkerljl.boss.support.UserInfo;
import org.walkerljl.boss.support.common.log.SysLog;
import org.walkerljl.boss.support.enums.StatusEnum;
import org.walkerljl.boss.support.enums.SysLogTypeEnum;
import org.walkerljl.boss.support.mvc.MvcSupporter;
import org.walkerljl.boss.support.mvc.auth.Button;
import org.walkerljl.boss.support.mvc.auth.ButtonBar;
import org.walkerljl.boss.support.mvc.auth.impl.CurdTemplateButtonBar;
import org.walkerljl.boss.support.mvc.controller.BaseController;
import org.walkerljl.boss.support.mvc.model.ViewResult;
import org.walkerljl.boss.support.mvc.model.context.ServletContext;
import org.walkerljl.boss.support.mvc.model.enums.EditTypeEnum;
import org.walkerljl.boss.support.service.BaseService;
import org.walkerljl.configuration.client.ConfiguratorFactory;
import org.walkerljl.toolkit.lang.CollectionUtils;
import org.walkerljl.toolkit.lang.ListUtils;
import org.walkerljl.toolkit.lang.LongUtils;
import org.walkerljl.toolkit.lang.MapUtils;
import org.walkerljl.toolkit.lang.ReflectUtils;
import org.walkerljl.toolkit.lang.StringPool;
import org.walkerljl.toolkit.lang.StringUtils;
import org.walkerljl.toolkit.standard.Result;
import org.walkerljl.toolkit.standard.exception.AppException;
import org.walkerljl.toolkit.standard.model.paging.Paginator;

/**
 * 基于简单增、删、改、查的Template
 *
 * @author xingxun
 */
public abstract class CurdTemplate<T> extends BaseController {

    /**
     * 编辑类型Key
     */
    protected static final String EDIT_TYPE_KEY = "editType";
    /**
     * 数据模型Key
     */
    protected static final String DATA_MODEL_KEY = "model";
    /**
     * 状态选项Key
     */
    private static final String STATUS_OPTIONS_KEY = "statusOptions";
    @SuppressWarnings("unchecked")
    private Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
            .getActualTypeArguments()[0];
    /**
     * 首页模板
     */
    private String indexTemplate = "index";
    private boolean indexSkipLayout = false;
    /**
     * 添加操作模板
     */
    private String addTemplate = "edit";
    /**
     * 编辑操作模板
     */
    private String editTemplate = "edit";
    /**
     * 详情模板
     */
    private String viewTemplate = "edit";

    @Resource
    private MvcSupporter mvcSupporter;

    public CurdTemplate() {}

    @Override
    public Map<String, Object> getDefaultContext() {
        Map<String, Object> context = super.getDefaultContext();
        if (context == null) {
            context = MapUtils.newHashMap();
        }
        context.put("indexAddress", buildAddress(""));
        context.put("selectJSONPageAddress", buildAddress("selectJSONPage"));
        context.put("selectPageAddress", buildAddress("selectPage"));
        context.put("searchAddress", buildAddress("search"));
        context.put("addAddress", buildAddress("add"));
        context.put("editAddress", buildAddress("edit-"));
        context.put("saveAddress", buildAddress("save"));
        context.put("modifyStatusAddress", buildAddress("modifyStatus"));
        context.put("deleteAddress", buildAddress("delete"));
        context.put("viewAddress", buildAddress("view-"));
        context.put("isExistsAddress", buildAddress("isExists"));
        context.put("parentMenus", getObjectIdentifier().getParentMenus());
        return context;
    }

    /**
     * 构建地址
     *
     * @param key
     * @return
     */
    private String buildAddress(String key) {
        StringBuilder adddess = new StringBuilder();
        adddess.append(ServletContext.getContextPath()).append(StringPool.SLASH);
        adddess.append(getObjectIdentifier().getCode());
        if (StringUtils.isNotBlank(key)) {
            adddess.append(StringPool.SLASH).append(key);
        }
        return adddess.toString();
    }

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView index(T condition) {
        ViewResult viewResult = getIndexModel();
        if (viewResult == null) {
            viewResult = new ViewResult();
        }
        viewResult.addModel(STATUS_OPTIONS_KEY, StatusEnum.values());
        if (ConfiguratorFactory.getStdConfigurator().getAsBoolean("indexPageIsLoadData", false)) {
            viewResult.addModel(DATA_MODEL_KEY, processSelectPage(condition));
        }
        if (getObjectIdentifier().isLoadMenuBar()) {
            //设置按钮条
            String currentUserId = getCurrentUserId();
            List<Button> customizedButtons = getObjectIdentifier().getButtons();
            if (ListUtils.isEmpty(customizedButtons)) {
                ButtonBar curdTemplateDefaultButtonBar = new CurdTemplateButtonBar(mvcSupporter,
                        getObjectIdentifier().getButtonActives());
                List<Button> buttonBar = curdTemplateDefaultButtonBar.getButtons(currentUserId,
                        mvcSupporter.currentUserIsAdmin(), getObjectIdentifier().getCode());
                viewResult.addModel("buttons", buttonBar);
            } else {
                viewResult.addModel("buttons", customizedButtons);
            }
        }
        return toCustomizedViewResult(indexTemplate, viewResult, indexSkipLayout);
    }

    /**
     * 获取后去首页需要加载的数据模型
     * 默认的数据模型为空
     *
     * @return
     */
    protected ViewResult getIndexModel() {
        return null;
    }

    /**
     * 查询JSON分页数据
     *
     * @param t
     * @return
     */
    @RequestMapping(value = "selectJSONPage")
    public ModelAndView selectJSONPage(T t) {
        return toJSON(processSelectPage(t));
    }

    /**
     * 查询分页数据
     *
     * @param t
     * @return
     */
    @RequestMapping(value = "selectPage")
    public ModelAndView selectPage(T t) {
        ViewResult viewResult = new ViewResult();
        viewResult.addModel(DATA_MODEL_KEY, processSelectPage(t));
        if (indexSkipLayout) {
            return toViewResultSkipLayout(getTemplate(indexTemplate), viewResult);
        } else {
            return toViewResult(getTemplate(indexTemplate), viewResult);
        }
    }

    /**
     * 处理分页查询
     *
     * @param t
     * @return
     */
    protected Paginator<T> processSelectPage(T t) {
        return getService().selectPage(t);
    }

    /**
     * 条件查询,领域字段值不为NULL则为条件
     *
     * @param t
     * @return
     */
    @RequestMapping(value = "search", method = {RequestMethod.POST})
    public ModelAndView search(T t) {
        return toJSON(processSearch(t));
    }

    /**
     * 处理条件查询,领域字段值不为NULL则为条件
     *
     * @param t
     * @return
     */
    protected List<T> processSearch(T t) {
        return getService().selectList(t);
    }

    /**
     * 新增
     *
     * @return
     */
    @Authentication(codes = {"edit"})
    @RequestMapping(value = "/add", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView add() {
        ViewResult viewResult = getAddModel();
        viewResult.addModel(EDIT_TYPE_KEY, EditTypeEnum.ADD.getCode());
        return toCustomizedViewResult(addTemplate, viewResult, true);
    }

    /**
     * 获取新增页面需要加载的数据模型
     * 默认的数据模型为空
     *
     * @return
     */
    protected ViewResult getAddModel() {
        return getEditModel(null);
    }

    /**
     * 编辑
     *
     * @param id
     * @return
     */
    @Authentication(codes = {"edit"})
    @RequestMapping(value = "/edit-{id}", method = {RequestMethod.GET})
    public ModelAndView edit(@PathVariable Long id) {
        ViewResult viewResult = getEditModel(id);
        viewResult.addModel(EDIT_TYPE_KEY, EditTypeEnum.EDIT.getCode());
        viewResult.addModel(DATA_MODEL_KEY, processView(id));
        return toCustomizedViewResult(editTemplate, viewResult, true);
    }

    /**
     * 获取编辑页面需要加载的数据模型
     * 默认的数据模型为空
     *
     * @param id
     * @return
     */
    protected ViewResult getEditModel(Long id) {
        ViewResult viewResult = new ViewResult();
        viewResult.addModel(STATUS_OPTIONS_KEY, StatusEnum.values());
        return viewResult;
    }

    /**
     * 处理保存操作
     *
     * @param object
     * @return
     */
    @Authentication(codes = {"edit"})
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public ModelAndView save(T object) {
        Result<Object> result = null;
        if (object != null && object instanceof BaseModel) {
            UserInfo currentUser = getCurrentUser();
            BaseModel model = (BaseModel) object;
            if (LongUtils.greatThanZero(model.getId())) {
                model.setModifiedTime(new Date());
                if (currentUser != null) {
                    model.setModifier(currentUser.getId());
                }
                Long key = model.getId();
                model.setId(null);
                SysLog sysLog = buildSysLog(model.toString(), null, SysLogTypeEnum.UPDATE);
                result = Result.create(getService().updateByKey(object, key, sysLog) > 0);
            } else {//新增
                model.setStatusType(StatusEnum.ENABLED);
                model.setCreatedTime(new Date());
                model.setModifiedTime(model.getCreatedTime());
                if (currentUser != null) {
                    model.setCreator(currentUser.getId());
                    model.setModifier(model.getCreator());
                }
                SysLog sysLog = buildSysLog(model.toString(), SysLogTypeEnum.ADD);
                result = Result.create(getService().insert(object, sysLog) > 0);
            }
        }
        return toJSON(null, result.isSuccess(), result.getMessage());
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @Authentication(codes = {"delete"})
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public ModelAndView delete(String ids) {
        Result<Object> message = processDelete(StringUtils.splitToLongList(ids, ","));
        return toJSON(null, message.isSuccess(), message.getMessage());
    }

    /**
     * 处理删除业务
     *
     * @param ids
     * @return
     */
    protected Result<Object> processDelete(List<Long> ids) {
        SysLog sysLog = buildSysLog(CollectionUtils.wrap(ids, ","), SysLogTypeEnum.PHYSICS_DELETE);
        return Result.create(getService().deleteByKeys(ids, sysLog) > 0);
    }

    /**
     * 更新状态
     *
     * @param ids
     * @return
     */
    @Authentication(codes = {"modifystatus"})
    @RequestMapping(value = "/modifyStatus", method = {RequestMethod.POST})
    public ModelAndView modifyStatus(String ids, String status) {
        T entity = null;
        Assert.isTrue(StringUtils.isNotBlank(ids), "ids为空");
        Assert.isTrue(StatusEnum.getByCode(status) != null, "status无效");
        try {
            entity = entityClass.newInstance();
            ReflectUtils.invoke(entity, "setStatus", status);
        } catch (Exception e) {
            throw new AppException(e);
        }
        SysLog sysLog = buildSysLog(ids, SysLogTypeEnum.getByCode(status));
        boolean isSuccess = getService().updateByKeys(entity, StringUtils.splitToLongList(ids, ","), sysLog) > 0;
        Result<Object> result = Result.create(isSuccess);
        return toJSON(null, result.isSuccess(), result.getMessage());
    }

    /**
     * 详情页面
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/view-{id}", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView view(@PathVariable Long id) {
        ViewResult viewResult = getViewModel(id);
        viewResult.addModel(EDIT_TYPE_KEY, EditTypeEnum.VIEW.getCode());
        viewResult.addModel(DATA_MODEL_KEY, processView(id));
        return toCustomizedViewResult(viewTemplate, viewResult, true);
    }

    /**
     * 处理查看详情页面
     *
     * @param id
     * @return
     */
    protected T processView(Long id) {
        T t = getService().selectByKey(id);
        return t;
    }

    /**
     * 记录是否存在
     *
     * @param t
     */
    @RequestMapping(value = "/isExists", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView isExists(T t) {
        boolean isExists = ListUtils.size(getService().selectList(t)) > 0;
        ViewResult viewResult = new ViewResult();
        viewResult.addModel("isExists", isExists);
        return toJSON(viewResult);
    }

    /**
     * 获取详情页面需要加载的数据模型
     *
     * @param id
     * @return
     */
    protected ViewResult getViewModel(Long id) {
        return new ViewResult();
    }

    /**
     * 视图输出
     *
     * @param view
     * @param viewResult
     * @param skipLayout
     * @return
     */
    private ModelAndView toCustomizedViewResult(String view, ViewResult viewResult, boolean skipLayout) {
        if (skipLayout) {
            return toViewResultSkipLayout(getTemplate(view), viewResult);
        } else {
            return toViewResult(getObjectIdentifier().getLayout(), getTemplate(view), viewResult);
        }
    }

    /**
     * 设置首页模板
     *
     * @param indexTemplate
     */
    protected void setIndexTemplate(String indexTemplate) {
        this.indexTemplate = indexTemplate;
    }

    /**
     * 设置新增操作模板
     *
     * @param addTemplate
     */
    protected void setAddTemplate(String addTemplate) {
        this.addTemplate = addTemplate;
    }

    /**
     * 设置编辑操作模板
     *
     * @param editTemplate
     */
    protected void setEditTemplate(String editTemplate) {
        this.editTemplate = editTemplate;
    }

    /**
     * 设置详情模板
     *
     * @param viewTemplate
     */
    protected void setViewTemplate(String viewTemplate) {
        this.viewTemplate = viewTemplate;
    }

    protected void setIndexSkipLayout(boolean skip) {
        this.indexSkipLayout = skip;
    }

    /**
     * 获取业务接口对象
     *
     * @return
     */
    public abstract BaseService<Long, T> getService();
}