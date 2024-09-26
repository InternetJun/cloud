package org.example.common.core.leetcode.twentyFour.febuary;

import lombok.extern.slf4j.Slf4j;
import org.example.common.core.leetcode.tree.TreeNode;
import org.example.common.core.util.CommonUtil;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: lejun
 * @project: cloud-socket
 * @description:
 * @time: 2024/2/7 15:41
 */
@Slf4j
public class Leet0207 {
    public TreeNode replaceValueInTree(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        root.val = 0;
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int sum = 0;
            for (TreeNode treeNode : queue) {
                if (treeNode.left != null) {
                    sum += treeNode.left.val;
                }
                if (treeNode.right != null) {
                    sum += treeNode.right.val;
                }
            }
            //对左右节点进行计算
            while (size-- > 0) {
                TreeNode node = queue.poll();
                int sumOfxy = (node.left == null ? 0 : node.left.val) +
                        (node.right == null ? 0 : node.right.val);
                if (node.left != null) {
                    node.left.val = sum - sumOfxy;
                    queue.offer(node.left);
                }
                if (node.right != null)  {
                    node.right.val = sum - sumOfxy;
                    queue.offer(node.right);
                }
            }
        }
        return root;
    }

    @Test
    public void ms() {
        // 5,4,9,1,10,null,7
//        TreeNode treeNode = CommonUtil.buildTree(new Integer[]{1});
        TreeNode treeNode = CommonUtil.buildTree(new Integer[]{5,4,9,1,10,null,7});
        TreeNode treeNode1 = replaceValueInTree2(treeNode);
        log.info("{}", treeNode1);

    }

    /**
     * 这里的数值的计算用的是一个同层的概念，不是下一层的东西
     *
     * @param root
     * @return
     */
    public TreeNode replaceValueInTree2(TreeNode root) {
        Queue<TreeNode> q = new ArrayDeque<>();
        int levelSum = -root.val;
        q.offer(root);
        while (!q.isEmpty()) {
            int count = q.size(), nextLevelSum = 0;
            while (count > 0) {
                TreeNode node = q.poll();
                node.val += levelSum;
                int childSum = (node.left != null ? node.left.val : 0) + (node.right != null ? node.right.val : 0);
                nextLevelSum += childSum;
                if (node.left != null) {
                    node.left.val = -childSum;
                    q.offer(node.left);
                }
                if (node.right != null) {
                    node.right.val = -childSum;
                    q.offer(node.right);
                }
                --count;
            }
            levelSum = nextLevelSum;
        }
        return root;
    }
}
