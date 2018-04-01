/*
 * Copyright (c) 2010-present www.walkerljl.org All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.boss.sdk.auth.model.tree;

import java.util.ArrayList;
import java.util.List;

import org.walkerljl.boss.sdk.BaseEntity;

/**
 * 多叉树节点
 *
 * @author lijunlin
 * @created 2012-03-09
 */
public class ManyTreeNode extends BaseEntity {

    private static final long serialVersionUID = 6494463240845484718L;

    /**
     * 树节点
     */
    private TreeNode           node;
    /**
     * 子树集合
     */
    private List<ManyTreeNode> children;

    public ManyTreeNode(TreeNode node) {
        this.node = node;
        this.children = new ArrayList<ManyTreeNode>(0);
    }

    public ManyTreeNode(TreeNode node, List<ManyTreeNode> children) {
        this.node = node;
        this.children = children;
    }

    public TreeNode getNode() {
        return node;
    }

    public void setNode(TreeNode node) {
        this.node = node;
    }

    public List<ManyTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<ManyTreeNode> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}