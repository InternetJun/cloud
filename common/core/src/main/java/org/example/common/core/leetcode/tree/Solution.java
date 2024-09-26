package org.example.common.core.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/9/30 16:58
 */
public class Solution {
    public Node connect(Node root) {
        // 先进行一个层序遍历；
        if (root == null) {
            return root;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                /** 1. 本节点的左右节点
                 * 2. 不是本节点的左右节点
                 * 1  2  4  8  16
                 * 我在不同的节点的处理上犯难了。应该要怎么去做呢？
                 * A:用一个pre节点来记录；xx*/
                final Node poll = queue.poll();
                if (i == levelSize - 1) {
                    poll.next = null;
                }
                // 这里可以保存下上一个节点存在的
                Node right;
                if (poll != null) {
                    final Node left = poll.left;
                    right = poll.right;
                    queue.add(left);
                    if (left != null && right != null) {
                        left.next = right;
                    }
                }

            }
        }
        // 在每一层的中间进行处理，最后一位元素没有next元素
        return root;
    }
}
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}