package org.example.common.core.leetcode.twentyFour.febuary;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.jnlp.ClipboardService;
import java.util.*;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description:
 * @time: 2024/2/17 13:01
 */
@Slf4j
public class Leet0218 {
    private class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        List<List<Integer>> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            List<Integer> item = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                final Node poll = queue.poll();
                item.add(poll.val);
                final List<Node> children = poll.children;
                for (Node child : children) {
                    queue.add(child);
                }
            }
            result.add(item);
        }
        return result;
    }

    @Test
    public void main() {

    }

    /**
     * 前序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {
//        Deque<Node> stack = new LinkedList<>();
//        stack.push(root);
//        List<Integer> res = new ArrayList<>();
//        while (!stack.isEmpty()) {
//            final List<Node> children = root.children;
//            for (Node node : children) {
//                while (!stack.isEmpty() && node != null) {
//                    res.add(node.val);
//                    stack.addFirst(node);
//                    // 进行一个循环
////                    node = node.left;
//                }
//                final Node poll = stack.poll();
//                // 把上一层的其他节点添加进去。
//            }
//        }
//        return res;
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<Node> stack = new ArrayDeque<Node>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            res.add(node.val);
            for (int i = node.children.size() - 1; i >= 0; --i) {
                stack.push(node.children.get(i));
            }
        }
        return res;
    }

    @Test
    public void testPreDfs() {
        final Node root = deserialize("[1,null,3,2,4,null,5,6]");
        final List<Integer> list = preOrderDfs(root);
        log.info("{}", list);
    }

    // 原因就在这里。都是新建的对象，没有一个状态而言，所以要重写。
    List<Integer> res = new ArrayList<>();
    public List<Integer> preOrderDfs(Node root) {
        if (root == null || root.children == null) {
            return res;
        }
        res.add(root.val);
        final List<Node> children = root.children;
        for (Node child : children) {
            preOrderDfs(child);
        }
        return res;
    }

    public Node deserialize(String data) {
        if ("null".equals(data)) {
            return null;
        }

        String[] parts = data.substring(1, data.length() - 1).split(",");
        Queue<String> queue = new LinkedList<>(Arrays.asList(parts));
        Queue<Node> nodeQueue = new LinkedList<>();

        Node root = new Node(Integer.parseInt(queue.poll()));
        nodeQueue.offer(root);

        while (!nodeQueue.isEmpty()) {
            Node current = nodeQueue.poll();
            List<Node> children = new ArrayList<>();
            queue.poll(); // Discard "null"
            while (!queue.isEmpty() && !queue.peek().equals("null")) {
                Node child = new Node(Integer.parseInt(queue.poll()));
                children.add(child);
                nodeQueue.offer(child);
            }

            current.children = children;
        }

        return root;
    }

    @Test
    public void testBuild() {
        final Node root = deserialize("[1,null,3,2,4,null,5,6]");
        log.info("{}", root);
    }
}

