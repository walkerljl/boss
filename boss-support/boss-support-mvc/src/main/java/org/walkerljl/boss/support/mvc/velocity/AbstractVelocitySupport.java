package org.walkerljl.boss.support.mvc.velocity;

import java.io.StringWriter;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.walkerljl.boss.support.ViewResult;
import org.walkerljl.boss.support.mvc.WebConstants;
import org.walkerljl.toolkit.lang.MapUtils;
import org.walkerljl.toolkit.standard.exception.AppException;

/**
 * AbstractVelocitySupport
 *
 * @author xingxun
 */
public abstract class AbstractVelocitySupport {

    protected static final Logger LOG = LoggerFactory.getLogger(AbstractVelocitySupport.class);

    @Resource
    private VelocityConfigurer velocityConfigurer;

    @SuppressWarnings("unchecked")
    protected ModelAndView toVM(String layout, String view, Object model) {
        VelocityEngine engine = velocityConfigurer.getVelocityEngine();
        // 判断模板是否存在
        String viewVM = view + WebConstants.VM_SUFFIX;
        boolean exists = engine.resourceExists(viewVM);
        if (!exists) {
            throw new AppException("未找到指定视图[" + view + "].");
        }
        Map<String, Object> context = getDefaultContext();
        if (context == null) {
            context = MapUtils.newHashMap();
        }
        // 添加默认数据
        if (model instanceof Map) {
            context.putAll((Map<String, Object>) model);
        } else if (model instanceof ViewResult) {
            context.putAll(((ViewResult) model).getMap());
        } else {
            context.put(ViewResult.DEFAULT_KEY, model);
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug("processing view " + viewVM);
        }
        // 跳过模板直接输出
        if (layout == null) {
            return new ModelAndView(view, context);
        }
        // 判断布局模板是否存在
        exists = engine.resourceExists(layout + WebConstants.VM_SUFFIX);
        if (!exists) {
            throw new AppException("未找到指定布局模板[" + layout + "].");
        }
        // 输入输出编码
        Template bodyVM = engine.getTemplate(viewVM, (String) engine.getProperty(VelocityEngine.INPUT_ENCODING));
        // 合并screen_content
        StringWriter screenContent = new StringWriter();
        bodyVM.merge(new VelocityContext(context), screenContent);
        // 添加到默认布局中
        context.put(WebConstants.VELOCITY_SCREEN_CONTENT, screenContent.toString());
        return new ModelAndView(layout, context);
    }

    /**
     * Velocity 默认数据
     *
     * @return
     */
    public abstract Map<String, Object> getDefaultContext();
}