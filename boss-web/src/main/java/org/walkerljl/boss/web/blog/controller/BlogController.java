package org.walkerljl.boss.web.blog.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.walkerljl.boss.dao.dataobject.blog.ArticleDO;
import org.walkerljl.boss.dao.dataobject.blog.ArticleTypeDO;
import org.walkerljl.boss.service.blog.ArticleService;
import org.walkerljl.boss.service.blog.ArticleTypeService;
import org.walkerljl.boss.support.ViewResult;
import org.walkerljl.boss.support.mvc.MvcSupporter;
import org.walkerljl.boss.support.mvc.controller.BaseIndexController;
import org.walkerljl.boss.support.mvc.model.ObjectIdentifier;
import org.walkerljl.boss.support.mvc.model.context.ServletContext;

/**
 * IndexController
 *
 * @author lijunlin
 */
@Controller
@RequestMapping(value = "/blog", method = {RequestMethod.POST, RequestMethod.GET})
public class BlogController extends BaseIndexController {

    @Resource
    private MvcSupporter mvcSupporter;
    @Resource
    private ArticleTypeService articleTypeService;
    @Resource
    private ArticleService articleService;

    public BlogController() {
        setObjectIdentifier(new ObjectIdentifier("index", "common"));
    }

    @RequestMapping(value = "/#")
    public ModelAndView index() {
        return null;
    }

    @RequestMapping(value = "/logout")
    public void logout() {
        mvcSupporter.logout();
        sendRedirect(ServletContext.getContextPath());
    }

    @RequestMapping(value = "")
    public ModelAndView index(ArticleDO article) {
        ViewResult viewResult = new ViewResult();
        ArticleTypeDO articleTypeCondition = new ArticleTypeDO();
        viewResult.addModel("articleTypes", articleTypeService.selectList(articleTypeCondition));
        viewResult.addModel("page", articleService.selectPage(article));
        viewResult.addModel("curArticleTypeId", article.getTypeId());
        return toViewResult(getTemplate("/index", "index"), viewResult);
    }
}