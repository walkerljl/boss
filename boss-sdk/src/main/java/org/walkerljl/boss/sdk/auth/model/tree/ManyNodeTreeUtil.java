package org.walkerljl.boss.sdk.auth.model.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 *
 * @author xingxun
 */
public class ManyNodeTreeUtil {

    public static List<ManyTreeNode> filterManyTreeNodes(List<ManyTreeNode> manyTreeNodes, Set<String> treeNodeIds) {
        if (isEmpty(treeNodeIds)) {
            return null;
        }
        if (isEmpty(manyTreeNodes)) {
            return null;
        }
        List<ManyTreeNode> filteredManyTreeNodes = new ArrayList<ManyTreeNode>(manyTreeNodes.size());
        for (ManyTreeNode manyTreeNode : manyTreeNodes) {
            boolean isContainManyTreeNode = isContainManyTreeNode(manyTreeNode, treeNodeIds);
            if (!isContainManyTreeNode) {
                continue;
            }
            ManyTreeNode newManyTreeNode = new ManyTreeNode(manyTreeNode.getNode());
            filteredManyTreeNodes.add(newManyTreeNode);
            if (isEmpty(manyTreeNode.getChildren())) {
                continue;
            }
            List<ManyTreeNode> filteredChildManyTreeNodes = filterManyTreeNodes(manyTreeNode.getChildren(), treeNodeIds);
            if (filteredChildManyTreeNodes != null) {
                newManyTreeNode.setChildren(filteredChildManyTreeNodes);
            }
        }
        return filteredManyTreeNodes;
    }

    private static boolean isContainManyTreeNode(ManyTreeNode manyTreeNode, Set<String> treeNodeIds) {
        if (manyTreeNode == null) {
            return false;
        }
        if (manyTreeNode.getNode() == null) {
            return false;
        }
        if (manyTreeNode.getNode().getId() == null) {
            return false;
        }
        boolean isContainManyTreeNode = treeNodeIds.contains(manyTreeNode.getNode().getId());
        if (isContainManyTreeNode) {
            return true;
        }
        boolean hasContainChildManyTreeNode = hasContainChildManyTreeNode(manyTreeNode.getChildren(), treeNodeIds);
        return hasContainChildManyTreeNode;
    }

    private static boolean hasContainChildManyTreeNode(List<ManyTreeNode> children, Set<String> treeNodeIds) {
        if (isEmpty(children)) {
            return false;
        }
        for (ManyTreeNode child : children) {
            if (child == null) {
                continue;
            }
            boolean isContainManyTreeNode = isContainManyTreeNode(child, treeNodeIds);
            if (isContainManyTreeNode) {
                return true;
            }
            if (isEmpty(child.getChildren())) {
                continue;
            }
            boolean isContainChildManyTreeNodes = hasContainChildManyTreeNode(child.getChildren(), treeNodeIds);
            if (isContainChildManyTreeNodes) {
                return true;
            }
        }
        return false;
    }

    private static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
}