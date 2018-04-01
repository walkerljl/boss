package org.walkerljl.boss.sdk.auth.tree;

import java.util.HashSet;
import java.util.Set;


import org.testng.annotations.Test;
import org.walkerljl.boss.sdk.JSONUtil;
import org.walkerljl.boss.sdk.auth.model.tree.ManyNodeTree;
import org.walkerljl.boss.sdk.auth.model.tree.ManyNodeTreeUtil;
import org.walkerljl.boss.sdk.auth.model.tree.TreeNode;

/**
 *
 * @author xingxun
 */
public class ManyNodeTreeTest {

    private Set<TreeNode> treeNodes   = new HashSet<TreeNode>();
    private Set<String>   treeNodeIds = new HashSet<String>();

    @Test
    public void test() {
        init();

        ManyNodeTree tree = ManyNodeTree.createTree("-1", "root", treeNodes, 1000);
        //System.out.println(JSONUtil.toJSONString(tree));
        System.out.println("=========================");
        System.out.println(JSONUtil.toJSONString(ManyNodeTreeUtil.filterManyTreeNodes(tree.getRootNode().getChildren(), treeNodeIds)));

    }

    private void init() {
        treeNodes.add(new TreeNode("10000000", "收款信息维护", "-1"));
        treeNodes.add(new TreeNode("10010000", "班组管理", "10000000"));
        treeNodes.add(new TreeNode("10010100", "班组信息编辑", "10010000"));
        treeNodes.add(new TreeNode("10010200", "班组信息查询", "10010000"));
        treeNodes.add(new TreeNode("10010300", "出乘", "10010000"));
        treeNodes.add(new TreeNode("10020000", "补票机管理", "10000000"));
        treeNodes.add(new TreeNode("10020100", "补票机基础管理", "10020000"));
        treeNodes.add(new TreeNode("10020200", "补票机信息编辑", "10020000"));
        treeNodes.add(new TreeNode("10020300", "补票机信息查询", "10020000"));
        treeNodes.add(new TreeNode("10020400", "下载收钱码", "10020000"));

        treeNodes.add(new TreeNode("11000000", "资金管理", "-1"));
        treeNodes.add(new TreeNode("11010000", "账单", "11000000"));
        treeNodes.add(new TreeNode("11010100", "账单查询查看", "11010000"));
        treeNodes.add(new TreeNode("11010200", "手机端账单查询", "11010000"));
        treeNodes.add(new TreeNode("11010300", "退乘结账", "11010000"));
        treeNodes.add(new TreeNode("11020000", "转账", "11000000"));
        treeNodes.add(new TreeNode("11020100", "转账", "11020000"));
        treeNodes.add(new TreeNode("11020200", "银行卡管理", "11020000"));

        //treeNodeIds.add("10010300");
        treeNodeIds.add("11020200");
        //treeNodeIds.add("11010200");

    }
}
