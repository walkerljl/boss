package org.walkerljl.boss.sdk.auth.model.tree;

import java.util.Comparator;

/**
 * 树节点升序Comparator
 *
 * @author lijunlin
 * @created 2012-03-09
 */
public class TreeNodeAscOrderComparator implements Comparator<TreeNode> {

    @Override
    public int compare(TreeNode node1, TreeNode node2) {
        if (node1.getSortSequence() > node2.getSortSequence()) {
            return 1;
        } else if (node1.getSortSequence() < node2.getSortSequence()) {
            return -1;
        } else {
            return node1.getId().compareTo(node2.getId());
        }
    }
}
