/*
 * Copyright (c) 2010-present www.walkerljl.org All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.boss.sdk.auth.model.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.walkerljl.boss.sdk.BaseEntity;

/**
 * 多叉树
 *
 * @author lijunlin
 * @created 2012-03-09
 */
public class ManyNodeTree extends BaseEntity {

    private static final long serialVersionUID = 231339520385098083L;

    /**
     * 创建多叉树时间限制,单位毫秒
     */
    private static final long CREATE_TREE_TIME_LIMITS = 1 * 1000;

    /**
     * 根节点
     */
    private ManyTreeNode rootNode;

    /**
     * 多叉树是否完整
     */
    private boolean full;

    /**
     * 不完整多叉树剩余的节点
     */
    private List<TreeNode> leftNodes;

    private ManyNodeTree(ManyTreeNode rootNode) {
        this.rootNode = rootNode;
    }

    /**
     * 多叉树
     *
     * @param rootNodeId   根节点id
     * @param rootNodeName 根节点name
     */
    private ManyNodeTree(String rootNodeId, String rootNodeName) {
        TreeNode node = new TreeNode();
        node.setId(rootNodeId);
        node.setName(rootNodeName);
        node.setParentId("");
        node.setSortSequence(-1);
        node.setExpand(true);
        node.setRemark("多叉树根节点");
        this.rootNode = new ManyTreeNode(node);
    }

    public static ManyNodeTree createTree(ManyTreeNode rootNode) {
        return new ManyNodeTree(rootNode);
    }

    /**
     * 创建一棵多叉树<br>
     * <li>根节点id"root",根节点名称"根节点"</li>
     *
     * @param treeNodes         树节点集合
     * @param executeTimeLimits 执行时间限制,单位毫秒
     * @return 可能返回不完整的多叉树或NULL
     */
    public static ManyNodeTree createTree(Set<TreeNode> treeNodes, long executeTimeLimits) {
        return createTree("root", "根节点", treeNodes, executeTimeLimits);
    }

    /**
     * 创建一棵多叉树
     *
     * @param rootNodeId        根节点id
     * @param rootNodeName      根节点名称
     * @param treeNodes         树节点集合
     * @param executeTimeLimits 执行时间限制,单位毫秒
     * @return 可能返回不完整的多叉树或NULL
     */
    public static ManyNodeTree createTree(String rootNodeId, String rootNodeName, Set<TreeNode> treeNodes, long executeTimeLimits) {
        if (treeNodes == null || treeNodes.isEmpty()) {
            return null;
        }
        if (executeTimeLimits <= 0l) {
            executeTimeLimits = CREATE_TREE_TIME_LIMITS;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        ManyNodeTree manyNodeTree = new ManyNodeTree(rootNodeId, rootNodeName);
        //将节点添加到栈中
        for (TreeNode treeNode : treeNodes) {
            queue.add(treeNode);
        }
        long startTime = System.currentTimeMillis();
        while (!queue.isEmpty()) {
            //防止永远找不到父节点造成死循环
            if (System.currentTimeMillis() - startTime > executeTimeLimits) {
                List<TreeNode> ls = new ArrayList<TreeNode>();
                for (TreeNode treeNode : queue) {
                    ls.add(treeNode);
                }
                manyNodeTree.setLeftNodes(ls);
                manyNodeTree.setFull(false);
                break;
            }
            TreeNode treeNode = queue.remove();
            //将添加失败的节点重新放入节点队列的队尾
            if (!addChild(manyNodeTree.getRootNode(), treeNode)) {
                queue.add(treeNode);
            }
        }
        manyNodeTree.setFull(true);
        return manyNodeTree;
    }

    /**
     * 将指定节点添加到多叉树节点
     *
     * @param manyTreeNode
     * @param treeNode
     * @return 添加成功返回true, 添加失败返回false
     */
    private static boolean addChild(ManyTreeNode manyTreeNode, TreeNode treeNode) {
        //被添加的节点是当前节点的孩子
        if (manyTreeNode.getNode().getId().equals(treeNode.getParentId())) {
            //添加节点到当前节点
            manyTreeNode.getChildren().add(new ManyTreeNode(treeNode));
            return true;
        } else {//被添加节点不是当前节点的孩子
            //查看被添加的节点是否是当前节点孩子的孩子
            for (ManyTreeNode child : manyTreeNode.getChildren()) {
                if (addChild(child, treeNode)) { return true; }
            }
            return false;
        }
    }

    /**
     * 获取多叉树根节点
     *
     * @return
     */
    public ManyTreeNode getRootNode() {
        return this.rootNode;
    }

    /**
     * 多叉树是否完整
     *
     * @return 完整:true,不完整:false
     */
    public boolean isFull() {
        return full;
    }

    /**
     * 设置多叉树的完整性
     *
     * @param full
     */
    private void setFull(boolean full) {
        this.full = full;
    }

    /**
     * 获取一棵不完整多叉树剩余的节点
     *
     * @return
     */
    public List<TreeNode> getLeftNodes() {
        return leftNodes;
    }

    /**
     * 一棵不完整多叉树剩余的节点
     *
     * @param leftNodes
     */
    public void setLeftNodes(List<TreeNode> leftNodes) {
        this.leftNodes = leftNodes;
    }

    /**
     * 层次遍历多叉树
     *
     * @return
     */
    public List<TreeNode> levelTraverse() {
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        List<TreeNode> partTreeNodes = levelTraverse(getRootNode());
        if (partTreeNodes != null) {
            treeNodes.addAll(partTreeNodes);
        }
        return treeNodes;
    }

    /**
     * 层次遍历
     *
     * @param manyTreeNode
     * @return
     */
    public List<TreeNode> levelTraverse(ManyTreeNode manyTreeNode) {
        if (manyTreeNode == null) {
            return null;
        }
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        treeNodes.add(manyTreeNode.getNode());
        List<ManyTreeNode> children = manyTreeNode.getChildren();
        if (!isEmpty(children)) {
            for (ManyTreeNode child : children) {
                List<TreeNode> childTreeNodes = levelTraverse(child);
                if (!isEmpty(children)) {
                    treeNodes.addAll(childTreeNodes);
                }
            }
        }

        return treeNodes;
    }

    /**
     * 层次遍历
     *
     * @param manyTreeNodes
     * @return
     */
    public List<TreeNode> levelTraverse(List<ManyTreeNode> manyTreeNodes) {
        if (isEmpty(manyTreeNodes)) {
            return null;
        }
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        for (ManyTreeNode manyTreeNode : manyTreeNodes) {
            List<TreeNode> partTreeNodes = levelTraverse(manyTreeNode);
            if (!isEmpty(partTreeNodes)) {
                treeNodes.addAll(partTreeNodes);
            }
        }

        return treeNodes;
    }

    /**
     * 获取多叉树的所有叶子节点
     *
     * @return
     */
    public List<TreeNode> listLeaves() {
        return listLeaves(getRootNode());
    }

    /**
     * 递归获取多叉树的所有叶子节点
     *
     * @param manyTreeNode
     * @return
     */
    private List<TreeNode> listLeaves(ManyTreeNode manyTreeNode) {
        if (manyTreeNode == null) {
            return null;
        }
        List<TreeNode> leaves = new ArrayList<TreeNode>();
        List<ManyTreeNode> children = manyTreeNode.getChildren();
        if (isEmpty(children)) {//叶子节点
            leaves.add(manyTreeNode.getNode());
        } else {
            for (ManyTreeNode child : children) {
                List<TreeNode> childLeaves = listLeaves(child);
                if (childLeaves != null) {
                    leaves.addAll(childLeaves);
                }
            }
        }
        return leaves;
    }

    /**
     * 选取树节点
     *
     * @param treeNode
     * @return
     */
    public ManyTreeNode pickPartTreeNode(TreeNode treeNode) {
        if (treeNode == null) {
            return ((this == null) ? null : this.getRootNode());
        }
        return pickPartTree(this.getRootNode(), treeNode);
    }

    /**
     * 递归选取树节点
     *
     * @param manyTreeNode
     * @param treeNode
     * @return
     */
    private ManyTreeNode pickPartTree(ManyTreeNode manyTreeNode, TreeNode treeNode) {
        if (manyTreeNode == null || treeNode == null) {
            return null;
        }
        if (manyTreeNode.getNode().getId().equals(treeNode.getId())) {
            return manyTreeNode;
        }
        List<ManyTreeNode> children = manyTreeNode.getChildren();
        ManyTreeNode resultManyTreeNode = null;
        if (!isEmpty(children)) {
            for (ManyTreeNode child : children) {
                resultManyTreeNode = pickPartTree(child, treeNode);
                if (resultManyTreeNode != null) {
                    return resultManyTreeNode;
                }
            }
        }
        return null;
    }

    /**
     * 是否为空
     *
     * @param collection
     * @return
     */
    private boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}