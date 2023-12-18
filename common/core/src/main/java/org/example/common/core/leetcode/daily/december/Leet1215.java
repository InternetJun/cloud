package org.example.common.core.leetcode.daily.december;

import lombok.extern.slf4j.Slf4j;
import org.example.common.core.leetcode.tree.TreeNode;
import org.example.common.core.util.CommonUtil;
import org.junit.Test;

import java.util.*;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description:
 * @time: 2023/12/15 10:42
 */
@Slf4j
public class Leet1215 {
    /**
     * 1，原地的替换可以吗？
     *
     * @param root
     * @return
     */
    public TreeNode reverseOddLevels(TreeNode root) {
        if (root == null) {
            return root;
        }
        final Deque<TreeNode> queue = new LinkedList<>();
        final ArrayList<Integer> res = new ArrayList<>();
        queue.add(root);
        boolean isOdd = false;
        while (!queue.isEmpty()) {
            Deque<Integer> list = new LinkedList<>();
            int size = queue.size();
            // 没有固定一个元素，导致在变量中进行的处理。所以有问题。
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = null;
                TreeNode left = null;
                TreeNode right = null;
                treeNode = queue.pollFirst();
                if (treeNode != null) {
                    left = treeNode.left;
                    right = treeNode.right;
                }
                if (isOdd) {
                    list.add(treeNode.val);
                } else {
                    list.addFirst(treeNode.val);
                }
                if (left != null) {
                    queue.addLast(left);
                }
                if (right != null) {
                    queue.addLast(right);
                }
                log.info("list:{}", list);
                log.info("size:{}; queue:{}", size, queue);
            }

            res.addAll(list);
            isOdd = !isOdd;
        }
        // [2, 3, 5, 34, 21, 13, 8]
        log.info("{}", res);
        final Integer[] objects = (Integer[]) res.toArray();
        final TreeNode treeNode = CommonUtil.buildTree(objects);
        return treeNode;
    }

    @Test
    public void ts() {
        // [2, 3, 5, 21, 34, 8, 13]
        final TreeNode treeNode = CommonUtil.buildTree(new Integer[]{2, 3, 5, 8, 13, 21, 34});
        final TreeNode treeNode1 = reverseOddLevelsSolution(treeNode);

    }

    public TreeNode reverseOddLevelsSolution(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.offer(root);
        boolean isOdd = false;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            List<TreeNode> arr = new ArrayList<TreeNode>();
            for (int i = 0; i < sz; i++) {
                TreeNode node = queue.poll();
                if (isOdd) {
                    arr.add(node);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
                log.info("{}", queue);
            }
            if (isOdd) {
                for (int l = 0, r = sz - 1; l < r; l++, r--) {
                    int temp = arr.get(l).val;
                    arr.get(l).val = arr.get(r).val;
                    arr.get(r).val = temp;
                }
            }
            isOdd ^= true;
        }
        return root;
    }


}
