package org.walkerljl.boss.support.mvc.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.walkerljl.boss.support.BaseBizEntity;
import org.walkerljl.boss.support.BaseModel;
import org.walkerljl.boss.support.BizPaginator;
import org.walkerljl.boss.support.UserInfo;
import org.walkerljl.boss.support.common.log.SysLog;
import org.walkerljl.boss.support.dao.dataobject.BaseDO;
import org.walkerljl.boss.support.enums.StatusEnum;
import org.walkerljl.boss.support.enums.SysLogTypeEnum;
import org.walkerljl.boss.support.mvc.MvcSupporter;
import org.walkerljl.boss.support.mvc.WebConstants;
import org.walkerljl.boss.support.mvc.i18n.I18N;
import org.walkerljl.boss.support.mvc.i18n.MessageResourceService;
import org.walkerljl.boss.support.mvc.model.ObjectIdentifier;
import org.walkerljl.boss.support.mvc.model.ViewResult;
import org.walkerljl.boss.support.mvc.model.context.ServletContext;
import org.walkerljl.boss.support.mvc.model.context.UserContextFactory;
import org.walkerljl.boss.support.mvc.util.MvcUtils;
import org.walkerljl.boss.support.mvc.util.ResponseUtils;
import org.walkerljl.boss.support.mvc.velocity.AbstractVelocitySupport;
import org.walkerljl.boss.support.mvc.velocity.VelocityUtils;
import org.walkerljl.configuration.client.ConfiguratorFactory;
import org.walkerljl.toolkit.lang.MapUtils;
import org.walkerljl.toolkit.lang.StringPool;
import org.walkerljl.toolkit.lang.StringUtils;
import org.walkerljl.toolkit.lang.datetime.DateUtils;
import org.walkerljl.toolkit.standard.Result;

/**
 * BaseController
 *
 * @author xingxun
 */
public abstract class BaseController extends AbstractVelocitySupport {

    /**
     * 菜单条Key
     */
    private static final String APP_MENUBAR_KEY = "appMenu";

    @Value(value = "${static.resource.address}")
    private String staticResourceAddress;

    /**
     * 对象标识符
     */
    private ObjectIdentifier objectIdentifier;
    /**
     * 　默认布局模板
     */
    private String layout = "layout/default";

    @Resource
    private MvcSupporter           mvcSupporter;
    @Resource
    private MessageResourceService messageResourceService;

    protected Locale getLocale() {
        HttpServletRequest request = ServletContext.getRequest();
        return RequestContextUtils.getLocale(request);
    }

    /**
     * Get resource message
     *
     * @param code
     * @param args
     * @return
     */
    protected String getMessage(String code, Object... args) {
        return messageResourceService.get(code, args);
    }

    @Override
    public Map<String, Object> getDefaultContext() {
        Map<String, Object> context = MapUtils.newHashMap();
        String contextPath = ServletContext.getContextPath();
        context.put("contextPath", contextPath);
        if (StringUtils.isNotBlank(staticResourceAddress)) {
            context.put("staticRes", staticResourceAddress);
            context.put("localStaticRes", contextPath + "/resource");
        } else {
            context.put("staticRes", contextPath + "/resource");
        }
        context.put("v", WebConstants.V);
        context.put("utils", VelocityUtils.class);
        context.put("dateUtils", DateUtils.class);
        UserInfo currentUser = getCurrentUser();
        context.put("currentUser", currentUser);
        context.put("objectIdentifier", objectIdentifier);
        context.put("js_ns", ConfiguratorFactory.getStdConfigurator().getAsString("js.ns", "JARVIS"));
        context.put("ssoLoginAddress", mvcSupporter.getSsoLoginAddress());
        context.put("appName", getMessage("app.name"));
        context.put("appCode", getAppCode());
        context.put("currentYear", DateUtils.dateFormat(new Date(), "yyyy"));
        context.put("isJarvisJsDebugEnabled", ConfiguratorFactory.getStdConfigurator().getAsBoolean("isJarvisJsDebugEnabled", false));
        //是否加载菜单条
        if (objectIdentifier != null && objectIdentifier.isLoadMenuBar() && currentUser != null) {
            String uri = ServletContext.getRequestURI().replaceAll(contextPath, "");
            String menuBarString = mvcSupporter.getMenuBar(currentUser.getId(), contextPath, uri);
            if (StringUtils.isNotBlank(menuBarString)) {
                context.put(APP_MENUBAR_KEY, menuBarString);
            }
        }
        context.put("isLogin", mvcSupporter.isLogin());
        Map<String, Object> bussinessContext = mvcSupporter.getBussinessContext();
        if (bussinessContext != null) {
            context.putAll(bussinessContext);
        }
        //i18n
        context.put("i18n", I18N.class);
        return context;
    }

    /**
     * 获取系统编码
     *
     * @return
     */
    private String getAppCode() {
        return ConfiguratorFactory.getStdConfigurator().getAsString("app.code");
    }

    /**
     * 重定向
     *
     * @param url
     * @param message
     */
    public void sendRedirect(String url, String message) {
        MvcUtils.sendRedirect(url, message);
    }

    /**
     * 重定向
     *
     * @param url
     */
    public void sendRedirect(String url) {
        MvcUtils.sendRedirect(url, null);
    }

    /**
     * 输出数据到模板,可指定默认布局
     *
     * @param layout 默认布局
     * @param view   模块
     * @param model  数据模型
     * @return
     */
    protected ModelAndView toViewResult(String layout, String view, Object model) {
        return toVM(layout, view, model);
    }

    /**
     * 输出数据到模板
     *
     * @param view  模板
     * @param model 数据模型
     * @return
     */
    protected ModelAndView toViewResult(String view, Object model) {
        return toVM(layout, view, model);
    }

    /**
     * 输出数据到模板
     *
     * @param view 模板
     * @return
     */
    protected ModelAndView toViewResult(String view) {
        return toVM(layout, view, null);
    }

    /**
     * 输出数据到模板,跳过默认布局
     *
     * @param view 模板
     * @return
     */
    protected ModelAndView toViewResultSkipLayout(String view) {
        return toViewResultSkipLayout(view, null);
    }

    /**
     * 输出数据到模板,跳过默认布局
     *
     * @param view  模板
     * @param model 数据模型
     * @return
     */
    protected ModelAndView toViewResultSkipLayout(String view, Object model) {
        return toVM(null, view, model);
    }

    /**
     * 数据JSON格式数据
     *
     * @param data   数据模型
     * @param format key:bean自身属性,value:转换后的属性 orgID -> id, 不需要转换就传递null
     * @return
     */
    protected final ModelAndView toSimpleJSON(Object data, Map<String, String> format) {
        if (data instanceof ViewResult) {
            ResponseUtils.toJSON(ServletContext.getResponse(), ((ViewResult) data).getMap(), format);
        } else {
            ResponseUtils.toJSON(ServletContext.getResponse(), data, format);
        }
        return null;
    }

    /**
     * 输出JSON格式数据
     *
     * @param data 数据模型
     * @return
     */
    protected final ModelAndView toSimpleJSON(Object data) {
        return toSimpleJSON(data, null);
    }

    /**
     * 输出JSON格式数据,JSON格式经过包装
     *
     * @param data   数据模型
     * @param flag   页面提示标志,true:操作成功,false:操作失败
     * @param msg    页面提示信息
     * @param format JSON格式化
     * @return
     */
    protected final ModelAndView toJSON(Object data, boolean flag, String msg, Map<String, String> format) {
        if (null == data) {
            data = MapUtils.newHashMap();
        }
        Map<String, Object> context = MapUtils.newHashMap();
        context.put(Result.RESULT_KEY, flag);
        context.put(Result.MESSAGE_KEY, msg);
        if (data instanceof ViewResult) {
            context.put(Result.DATA_KEY, ((ViewResult) data).getMap());
        } else {
            context.put(Result.DATA_KEY, data);
        }
        ResponseUtils.toJSON(ServletContext.getResponse(), context, format);
        return null;
    }

    /**
     * 返回JSONP类型的数据
     *
     * @param outputResult
     * @throws IOException
     */
    protected void toJSONP(Object outputResult) throws IOException {
        ResponseUtils.toJSONP(ServletContext.getRequest(), ServletContext.getResponse(), outputResult);
    }

    /**
     * 返回JSONP类型的数据
     *
     * @param outputResult
     * @param charset
     * @param callback
     * @throws IOException
     */
    protected void toJSONP(Object outputResult, String charset, String callback) throws IOException {
        ResponseUtils.toJSONP(ServletContext.getResponse(), outputResult, charset, callback);
    }

    /**
     * 输出JSON格式数据,JSON格式经过包装
     *
     * @param data 数据模型
     * @param flag 页面提示标志,true:操作成功,false:操作失败
     * @param msg  页面提示信息
     * @return
     */
    protected final ModelAndView toJSON(Object data, boolean flag, String msg) {
        return toJSON(data, flag, msg, null);
    }

    /**
     * 输出JSON格式数据,JSON格式经过包装
     *
     * @param data 数据模型
     * @param flag 页面提示标志,true:操作成功,false:操作失败
     * @return
     */
    protected final ModelAndView toJSON(Object data, boolean flag) {
        return toJSON(data, flag, flag ? "操作成功" : "操作失败", null);
    }

    /**
     * 输出JSON格式数据,JSON格式经过包装
     *
     * @param data   数据模型
     * @param format JSON格式化
     * @return
     */
    protected final ModelAndView toJSON(Object data, Map<String, String> format) {
        return toJSON(data, true, StringPool.EMPTY, format);
    }

    /**
     * 输出JSON格式数据,JSON格式经过包装
     *
     * @param data 数据模型
     * @return
     */
    protected final ModelAndView toJSON(Object data) {
        return toJSON(data, true, StringPool.EMPTY, null);
    }

    protected SysLog buildSysLog(String request, SysLogTypeEnum type) {
        return buildSysLog(request, null, type);
    }

    protected SysLog buildSysLog(String request, String response, SysLogTypeEnum type) {
        SysLog sysLog = new SysLog();
        sysLog.setKeyword(objectIdentifier.getName() + "_" + type.getCode());
        sysLog.setRequest(request);
        sysLog.setResponse(response);
        sysLog.setOperator(getCurrentUserId());
        sysLog.setOperated(new Date());
        return sysLog;
    }

    /**
     * 获取模板
     *
     * @param templateName
     * @return
     */
    protected String getTemplate(String templateName) {
        return getTemplate(objectIdentifier.getTemplatePath(), templateName);
    }

    /**
     * 获取模板
     *
     * @param templateBasePath
     * @param templateName
     * @return
     */
    protected String getTemplate(String templateBasePath, String templateName) {
        StringBuilder sb = new StringBuilder();
        sb.append(templateBasePath).append(StringPool.SLASH).append(templateName);
        return sb.toString();
    }

    /**
     * 初始化对象
     *
     * @param baseDomain
     */
    protected void initBaseDomain(BaseDO baseDomain) {
        if (baseDomain == null) {
            return;
        }
        UserInfo user = getCurrentUser();
        if (user != null) {
            baseDomain.setCreator(user.getId());
        }
    }

    /**
     * 创建对象时初始化基础模型
     *
     * @param baseModel
     */
    protected void initBaseDomainWhenAdd(BaseModel baseModel) {
        if (baseModel == null) {
            return;
        }
        UserInfo user = getCurrentUser();
        if (user != null) {
            baseModel.setCreator(user.getId());
            baseModel.setModifier(baseModel.getCreator());
        }
        baseModel.setCreatedTime(new Date());
        baseModel.setModifiedTime(baseModel.getCreatedTime());
        baseModel.setStatusType(StatusEnum.ENABLED);
    }

    /**
     * 获取当前登录用户
     *
     * @return
     */
    protected UserInfo getCurrentUser() {
        return UserContextFactory.getContext().getUser();
    }

    /**
     * 获取当前登录用户Id
     *
     * @return
     */
    protected String getCurrentUserId() {
        UserInfo currentUser = getCurrentUser();
        return currentUser == null ? null : currentUser.getId();
    }

    /**
     * 获取对象标识符
     *
     * @return
     */
    protected ObjectIdentifier getObjectIdentifier() {
        return objectIdentifier;
    }

    /**
     * 设置对象标识符
     *
     * @param objectIdentifier
     */
    protected void setObjectIdentifier(ObjectIdentifier objectIdentifier) {
        this.objectIdentifier = objectIdentifier;
        if (StringUtils.isNotBlank(objectIdentifier.getLayout())) {
            layout = objectIdentifier.getLayout();
        }
    }

    /**
     * 初始化基础属性
     *
     * @param baseBizEntity
     */
    protected void initBaseAttribute(BaseBizEntity baseBizEntity) {
        if (baseBizEntity == null) {
            return;
        }
        baseBizEntity.setOperator(getCurrentUser());
    }

    /**
     * 初始化基础属性
     *
     * @param bizPaginator
     * @param <T>
     */
    protected <T> void initBaseAttribute(BizPaginator<T> bizPaginator) {
        if (bizPaginator == null) {
            return;
        }
        bizPaginator.setOperator(getCurrentUser());
    }
}