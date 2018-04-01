package org.walkerljl.boss.web.blog.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.walkerljl.boss.dao.dataobject.blog.ArticleDO;
import org.walkerljl.boss.dao.dataobject.blog.ArticleTypeDO;
import org.walkerljl.boss.dao.dataobject.blog.CollectionDO;
import org.walkerljl.boss.dao.dataobject.blog.CommentDO;
import org.walkerljl.boss.sdk.auth.annotation.Authentication;
import org.walkerljl.boss.service.blog.ArticleService;
import org.walkerljl.boss.service.blog.ArticleTypeService;
import org.walkerljl.boss.service.blog.CommentService;
import org.walkerljl.boss.support.mvc.controller.template.CurdTemplate;
import org.walkerljl.boss.support.mvc.model.ObjectIdentifier;
import org.walkerljl.boss.support.mvc.model.ViewResult;
import org.walkerljl.boss.support.sdk.Message;
import org.walkerljl.boss.support.service.BaseService;
import org.walkerljl.toolkit.lang.MapUtils;
import org.walkerljl.toolkit.standard.model.paging.Paginator;

/**
 * ArticleController
 *
 * @author lijunlin
 */
@Controller
@Authentication
@RequestMapping(value = "/blog/article", method = {RequestMethod.POST, RequestMethod.GET})
public class ArticleController extends CurdTemplate<ArticleDO> {

    @Resource
    private ArticleService articleService;
    @Resource
    private ArticleTypeService articleTypeService;
    @Resource
    private CommentService commentService;

    @Value(value = "${article.image.path}")
    private String articleImagePath;
    @Value(value = "${article.image.address}")
    private String articleImageAddress;

    public ArticleController() {
        ObjectIdentifier objectIdentifier = new ObjectIdentifier("article", "article");
        objectIdentifier.setLayout("/layout/default-oneself");
        setObjectIdentifier(objectIdentifier);
    }

    @Override
    public BaseService<Long, ArticleDO> getService() {
        return articleService;
    }

    @Override
    public Map<String, Object> getDefaultContext() {
        Map<String, Object> context = super.getDefaultContext();
        if (context == null) {
            context = MapUtils.newHashMap();
        }
        ArticleTypeDO articleTypeCondition = new ArticleTypeDO();
        context.put("articleTypes", articleTypeService.selectList(articleTypeCondition));
        return context;
    }

    @RequestMapping(value = "/praise")
    public void praise(Long id) {
        toJSON(null, articleService.praise(id));
    }

    @RequestMapping(value = "/collect")
    public void collect(Long id) {
        CollectionDO collection = new CollectionDO();
        collection.setArticleId(id);
        toJSON(null, articleService.collect(collection));
    }

    @Override
    protected Paginator<ArticleDO> processSelectPage(ArticleDO article) {
        article.setCreator(getCurrentUserId());
        return getService().selectPage(article);
    }

    @Override
    protected ArticleDO processView(Long id) {
        return articleService.read(id, getCurrentUserId());
    }

    @RequestMapping(value = "/uploadImage")
    public void uploadImage(MultipartHttpServletRequest request) {
        try {
            MultipartFile multipartFile = request.getFile("imgFile");
            if (multipartFile == null) {
                LOG.warn("无法获取到文件");
                return;
            }
            String tempArticleImagePath = articleImagePath + getCurrentUserId() + File.separator;
            String originalFileName = multipartFile.getOriginalFilename();
            String fileName = System.currentTimeMillis() + originalFileName.substring(originalFileName.lastIndexOf("."));
            File saveFile = new File(tempArticleImagePath, fileName);
            if (!saveFile.exists()) {
                saveFile.mkdirs();
            }
            multipartFile.transferTo(saveFile);
            ViewResult viewResult = new ViewResult();
            viewResult.addModel("error", 0);
            String tempArticleImageAddress = articleImageAddress + getCurrentUserId() + File.separator + fileName;
            viewResult.addModel("url", tempArticleImageAddress);
            if (LOG.isDebugEnabled()) {
                LOG.debug("文件上传成功,路径:" + tempArticleImageAddress);
            }
            toSimpleJSON(viewResult);
        } catch (Exception e) {
            LOG.error("上传文件失败,详细信息:" + e.getMessage(), e);
        }
    }

    /**
     * 非主人阅读
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/otherRead-{id}")
    public ModelAndView otherRead(@PathVariable Long id) {
        CommentDO commentCondition = new CommentDO();
        commentCondition.setArticleId(id);
        List<CommentDO> comments = commentService.selectList(commentCondition);
        ViewResult viewResult = new ViewResult();
        ArticleDO article = articleService.read(id, getCurrentUserId());
        viewResult.addModel("article", article);
        viewResult.addModel("comments", comments);
        return toViewResult("/layout/default", getTemplate("other-view"), viewResult);
    }

    /**
     * 评论
     *
     * @param comment
     * @return
     */
    @RequestMapping(value = "/comment")
    public ModelAndView comment(CommentDO comment) {
        //TODO
        //initBaseDomainWhenAdd(comment);
        comment.setParentId(0L);
        Message<Object> message = Message.create(articleService.comment(comment));
        return toJSON(null, message.isSuccess(), message.getBody());
    }
}