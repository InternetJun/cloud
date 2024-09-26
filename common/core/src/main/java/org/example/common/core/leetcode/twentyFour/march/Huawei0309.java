package org.example.common.core.leetcode.twentyFour.march;

import lombok.val;

import java.util.Scanner;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description:
 * @time: 2024/3/9 17:43
 */
public class Huawei0309 {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            int n = Integer.parseInt(scanner.nextLine());
            final String s = scanner.nextLine();
            final int[] ints = new int[n];
            final String[] s1 = s.split(" ");
            for (int i = 0; i < n; i++) {
                ints[i] = Integer.parseInt(s1[i]);
            }

            // 构建树；
            final ThreeNode threeNode = buildTree(ints);
            final int i = treeHeight(threeNode);
            System.out.println(i);
        }
    }

    public static int treeHeight(ThreeNode root) {
        if (root == null) {
            return 0;
        }
        int heightL = treeHeight(root.left);
        int heightM = treeHeight(root.middle);
        int heightR = treeHeight(root.right);
        return Math.max(heightL, Math.max(heightM, heightR)) + 1;
    }

    /**
     * 如果数小于节点的数减去500，则将数插入节点的左子树
     * 如果数大于节点的数加上500，则将数插入节点的右子树
     * 否则，将数插入节点的中子树
     *
     * @param val
     * @return
     */
    public static ThreeNode buildTree(int[] val) {
        if (val == null) {
            return null;
        }
        int n = val.length;
        final ThreeNode root = new ThreeNode(val[0]);
        buildHelper(root, val, 1);
        return root;
    }

    /**
     * 9
     * 5000 2000 5000 8000 1800 7500 4500 1400 8100
     * 会搜索同层的所有的节点来构建树，并不是一层的。所以要用的是一个队列。
     * 那什么情况是会有退出呢？
     * 构建的不太对劲啊。这么麻烦呢？
     *
     * @param node
     * @param val
     * @param index
     */
    public static void buildHelper(ThreeNode node, int[] val, int index) {
        if (index == val.length) {
            return;
        }
        int leftPoint = node.val - 500;
        int rightPoint = node.val + 500;
        for (int i = index; i < val.length; i++) {
            int tempValue = val[i];
            final ThreeNode threeNode = new ThreeNode(tempValue);
            if (node.left == null && tempValue < leftPoint) {
                node.left = threeNode;
            } else {
                buildHelper(node.left, val, i + 1);
            }

            if (node.right == null && tempValue > rightPoint) {
//                /**//if () {
                node.right = threeNode;
//                }
            }

            if (node.middle == null && (tempValue >= leftPoint || tempValue <= rightPoint)) {
//                if () {
                node.middle = threeNode;
//                }
            }
        }
    }
}

class TernarySearchTree {

    private Node root;

    public TernarySearchTree() {
        this.root = null;
    }

    public void insert(int value) {
        Node newNode = new Node(value);
        if (root == null) {
            root = newNode;
            return;
        }

        Node currentNode = root;
        while (true) {
            if (value < currentNode.value - 500) {
                if (currentNode.left == null) {
                    currentNode.left = newNode;
                    break;
                } else {
                    currentNode = currentNode.left;
                }
            } else if (value > currentNode.value + 500) {
                if (currentNode.right == null) {
                    currentNode.right = newNode;
                    break;
                } else {
                    currentNode = currentNode.right;
                }
            } else {
                if (currentNode.center == null) {
                    currentNode.center = newNode;
                    break;
                } else {
                    currentNode = currentNode.center;
                }
            }
        }
    }

    public boolean contains(int value) {
        Node currentNode = root;
        while (currentNode != null) {
            if (value < currentNode.value - 500) {
                currentNode = currentNode.left;
            } else if (value > currentNode.value + 500) {
                currentNode = currentNode.right;
            } else {
                return true;
            }
        }
        return false;
    }

    public void printTree() {
        printTree(root);
    }

    private void printTree(Node node) {
        if (node == null) {
            return;
        }

        System.out.println(node.value);
        printTree(node.left);
        printTree(node.center);
        printTree(node.right);
    }

    public static void main(String[] args) {
        TernarySearchTree tree = new TernarySearchTree();
        int[] values = {
                5000, 2000, 5000, 8000, 1800, 7500, 4500, 1400, 8100
        };
        for (int value : values) {
            tree.insert(value);
        }

        System.out.println("Tree contains 500: " + tree.contains(500));
        System.out.println("Tree contains 1000: " + tree.contains(1000));

        tree.printTree();
    }
}

class Node {

    int value;
    Node left;
    Node center;
    Node right;

    public Node(int value) {
        this.value = value;
        this.left = null;
        this.center = null;
        this.right = null;
    }
}

