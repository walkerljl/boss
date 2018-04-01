/**
 * 菜单
 *
 * @author lijunlin
 */
(function ($$, NS) {

    var $$_NS = $$.register(NS);

    $$_NS.treeObj;

    $$_NS.getFontCss = function (treeId, treeNode) {
        if (treeNode.status == $$.MVC.status["enabled"]) {
            return {color: "#629B58"};
        } else if (treeNode.status == $$.MVC.status["disabled"]) {
            return {color: "#FFB752"};
        } else if (treeNode.status == $$.MVC.status["deleted"]) {
            return {color: "#D15B47"};
        }
    };
    $$_NS.initTree = function () {
        $$_NS.treeObj = $.fn.zTree.init($("#appResMenuTree"), {
            async: {
                enable: true,
                url: $$.MVC.context["contextPath"] + "/auth/res/menu/loadMenuTree.json?appId=" + $("#appId").val(),
                autoParam: ["id"],
                type: "post"
            },
            check: {
                enable: true,
                chkStyle: "checkbox",
                chkboxType: {"Y": "s", "N": "ps"}
            },
            view: {
                fontCss: $$_NS.getFontCss
            },
            data: {
                key: {title: "showTitle"}
            },
            callback: {
                onClick: function (event, treeId, treeNode) {
                    if (treeNode.resType == 'dir') {
                        return;
                    }
                    $$.loadPage($$.MVC.URL.edit + "?menuId=" + treeNode.id, "appResMenuForm",
                        {});
                },
                onAsyncSuccess: function (event, treeId, treeNode, msg) {
                    //appResMenuMgr.expandNodes(treeNode.children);
                }
            }
        });
    };

    /**
     * 初始化
     */
    $$.addConstructor(function () {
        $$_NS.initTree();
    });
})(GLOBAL_NS, "auth.res.menu");