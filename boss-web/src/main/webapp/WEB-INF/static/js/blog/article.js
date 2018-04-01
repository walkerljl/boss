/**
 * Article
 *
 * @author lijunlin
 */
(function ($$, NS) {
    var $$_NS = $$.register(NS);

    /**
     * 创建文章
     */
    $$_NS.add = function () {
        window.location.href = $$.MVC.context["contextPath"] + "/article/add";
    };

    /**
     * 删除文章
     */
    $$_NS.del = function (ids) {
        if (confirm("确认要删除此文章吗？")) {
            $$.MVC.doRequest($$.MVC.context["contextPath"] + "/article/delete?ids=" + ids, {}, function (response) {
                if (response[$$.MVC.response["status"]]) {
                    window.location.href = $$.MVC.context["contextPath"] + "/article";
                }
            });
        }
    };

    /**
     * 编辑文章
     */
    $$_NS.edit = function (id) {
        window.location.href = $$.MVC.context["contextPath"] + "/article/edit-" + id;
    };

    /**
     * 保存文章
     */
    $$_NS.save = function (formObj, editorObj) {
        editorObj.sync();
        $$.FORM.submit($(formObj), function (response) {
            window.location.href = $$.MVC.context["contextPath"] + "/article";
        });
    };

    /**
     * 评论
     */
    $$_NS.comment = function (articleId, formObj, editorObj) {
        editorObj.sync();
        var contentObj = $(formObj).find("#content");
        if (contentObj.val() == "") {
            alert("评论内容不能为空并且字符长度不能超过512个字符");
            contentObj.focus();
            return;
        }
        $$.FORM.submit($(formObj), function (response) {
            window.location.href = $$.MVC.context["contextPath"] + "/article/otherRead-" + articleId;
        });
    };

    /**
     * 阅读
     */
    $$_NS.read = function (id) {
        window.location.href = $$.MVC.context["contextPath"] + "/article/view-" + id;
    };

    /**
     * 其他人阅读
     */
    $$_NS.otherRead = function (id) {
        window.location.href = $$.MVC.context["contextPath"] + "/article/otherRead-" + id;
    };

    /**
     * 点赞
     */
    $$_NS.praise = function (articleId, refreshUrl) {
        $$.MVC.doRequest($$.MVC.context["contextPath"] + "/article/praise.json", {id: articleId}, function (response) {
            if (response[$$.MVC.response["status"]]) {
                window.location.href = refreshUrl;
            }
        });
    };

    /**
     * 收藏
     */
    $$_NS.collect = function (articleId, refreshUrl) {
        $$.MVC.doRequest($$.MVC.context["contextPath"] + "/article/collect", {id: articleId}, function (response) {
            if (response[$$.MVC.response["status"]]) {
                window.location.href = refreshUrl;
            }
        });
    };
})(GLOBAL_NS, "blog.article");