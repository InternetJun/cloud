package org.example.common.core.leetcode.daily.december;

import org.example.common.core.leetcode.tree.TreeNode;
import org.example.common.core.util.CommonUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 对树中的节点进行替换
 * @time: 2023/12/5 15:39
 */
public class Leet1205 {

    /**
     * 请将它的每个节点的值替换成树中大于或者等于该节点值的所有节点值之和。
     * <p>
     * 非递归形式的后序遍历的变种。右边  根节点 左节点。
     * 但实际可以利用递归来控制。
     * </p>
     *
     * @param root
     * @return
     */
    public TreeNode bstToGst(TreeNode root) {
        dfs(root);
        return root;
    }

    private int s = 0;

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.right);
        root.val = s + root.val;
        dfs(root.left);
    }

    @Test
    public void test() {
        final TreeNode treeNode = CommonUtil.buildTree(new Integer[]{4, 1, 6, 0, 2, 5, 7, null, null, null, 3, null, null, null, 8});
        System.out.println(postOrderSameDifferent(treeNode));
    }

    public List<Integer> postOrder(TreeNode root) {
        // 首先写出非递归形式的后序遍历。
        Deque<TreeNode> deque = new LinkedList();
        final ArrayList<Integer> list = new ArrayList<>();
        TreeNode prev = null;
        while (root != null || !deque.isEmpty()) {
            while (root != null) {
                deque.addFirst(root);
                root = root.left;
            }

            root = deque.pollFirst();
            // 最后的节点或者右边计算已经完成了。
            // 解释：
            if (root.right == null || root.right == prev) {
                list.add(root.val);
                prev = root;
                root = null;
            } else {
                deque.addFirst(root);
                root = root.right;
            }
        }
        return list;
    }

    /**
     * 获取不到左节点的。
     *
     * @param root
     * @return
     */
    public List<Integer> postOrderSameDifferent(TreeNode root) {
        // 首先写出非递归形式的后序遍历。
        Deque deque = new LinkedList<TreeNode>();
        final ArrayList<Integer> list = new ArrayList<>();
        TreeNode prev = null;
        while (root != null || !deque.isEmpty()) {
            while (root != null) {
                deque.addFirst(root);
                root = root.right;
            }

            root = (TreeNode) deque.poll();
            // 最后的节点或者右边计算已经完成了。
            // 解释：
            if (root.right == null || root.right == prev) {
                list.add(root.val);
                prev = root;
                root = null;
            } else {
                deque.addFirst(root);
                root = root.left;
            }
        }
        return list;
    }
}
