package org.walkerljl.boss.web.blog.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.walkerljl.boss.dao.dataobject.blog.CommentDO;
import org.walkerljl.boss.sdk.auth.annotation.Authentication;
import org.walkerljl.boss.service.blog.CommentService;
import org.walkerljl.boss.support.mvc.controller.template.CurdTemplate;
import org.walkerljl.boss.support.mvc.model.ObjectIdentifier;
import org.walkerljl.boss.support.service.BaseService;

/**
 * 评论
 *
 * @author lijunlin
 */
@Controller
@Authentication
@RequestMapping(value = "/blog/comment", method = {RequestMethod.POST, RequestMethod.GET})
public class CommentController extends CurdTemplate<CommentDO> {

    @Resource
    private CommentService commentService;

    public CommentController() {
        setObjectIdentifier(new ObjectIdentifier("comment", "comment"));
        setIndexSkipLayout(true);
    }

    @Override
    public BaseService<Long, CommentDO> getService() {
        return commentService;
    }
}
