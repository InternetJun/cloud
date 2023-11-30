package org.example.common.core.leetcode.tree;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/8/22 16:06
 */
public class TreeNode {
    public Integer val;
    public TreeNode left;
    public TreeNode right;

    TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val
                +
//                ", left=" + left +
//                ", right=" + right +
                '}';
    }
}
