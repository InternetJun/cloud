package org.example.common.core.leetcode.tree;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/9/4 10:00
 */
@Slf4j
public class BuildSolution {

    private Map<Integer, Integer> indexMap;

    /**
     * 先序遍历：
     *
     * @param preorder
     * @param inorder
     * @param preorder_left
     * @param preorder_right
     * @param inorder_left
     * @param inorder_right
     * @return
     */
    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }

        // 前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        // 在中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder[preorder_root]);

        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_root]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    public TreeNode buildMe(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        TreeNode treeNode = new TreeNode(preorder[preorder_left]);
        int rootIndex = indexMap.get(preorder[preorder_left]);
        // 计算左子树有多大。
        int size = rootIndex - inorder_left;
        TreeNode left = buildMe(preorder, inorder, preorder_left + 1, preorder_left + size, inorder_left, rootIndex - 1);
        TreeNode right = buildMe(preorder, inorder, preorder_left + size + 1, preorder_right, rootIndex + 1, inorder_right);
        treeNode.left = left;
        treeNode.right = right;
        return treeNode;
    }

    // 通过层序遍历来构建树。

    /**
     * 需要用到的是建堆的操作的。
     * Integer[] a = {3,9,20,null,null,15,7};
     * TreeNode treeNode = buildTree(a);
     *
     * @param arr
     * @return
     */
    public TreeNode buildTreeByLevelOrder(Integer[] arr) {
        return null;
    }

    final HashMap<Integer, Integer> indexMap2 = new HashMap<>();
    int postIndex;

    /**
     * <h1>后序和中序构造二叉树</h1>
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTreeFromPostAndInOrder(int[] inorder, int[] postorder) {
        int len = postorder.length;

        for (int i = 0; i < inorder.length; i++) {
            indexMap2.put(inorder[i], i);
        }
        postIndex = postorder.length - 1;
        return buildFromArr(0, inorder.length-1, postorder);
    }

    public TreeNode buildFromArr(int inLeft, int inRight, int[] postOrder) {
        // 什么情况是没有元素的？
        if (inLeft > inRight) {
            return null;
        }
        final Integer rootIndex = indexMap2.get(postOrder[postIndex]);
        TreeNode root = new TreeNode(postOrder[postIndex]);
        postIndex--;
        root.right = buildFromArr(rootIndex + 1, inRight, postOrder);
        root.left = buildFromArr(inLeft, rootIndex - 1, postOrder);
        return root;
    }

    @Test
    public void testIn(){
        int[] inorder = {9,3,15,20,7}, postorder = {9,15,7,20,3};

        final TreeNode treeNode = buildTreeFromPostAndInOrder(inorder, postorder);
        log.info("{}", treeNode);
    }

}
