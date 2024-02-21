package org.example.common.core.leetcode.twentyFour.febuary;

import org.example.common.core.leetcode.tree.TreeNode;

import java.util.HashMap;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description: 从前中后序中构建树
 * @time: 2024/2/21 16:08
 */
public class Leet0221 {
    final HashMap<Integer, Integer> indexMap2 = new HashMap<>();
    int postIndex;

    /**
     * 后序和中序构造树的思路：
     * <p>
     * 用后序遍历的最后一个下标进行向前。
     * root = new TreeNode(postOrder[postIndex]);
     * </p>
     * 先序遍历和中序遍历来构造树
     * <p>
     *    <b>因为前序遍历的原因，只有明白了有多少个左节点才能确定下来root节点的。</b>
     * // 在中序遍历中定位根节点
     * // 得到左子树中的节点数目
     * </p>
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            indexMap2.put(inorder[i], i);
        }
        postIndex = postorder.length - 1;
        return buildFromArr(0, inorder.length - 1, postorder);
    }

    private TreeNode buildFromArr(int inLeft, int inRight, int[] postorder) {
        if (inLeft > inRight) {
            return null;
        }
        int rootIndex = indexMap2.get(postorder[postIndex]);
        final TreeNode root = new TreeNode(rootIndex);
        root.right = buildFromArr(rootIndex + 1, inRight, postorder);
        root.left = buildFromArr(inLeft, rootIndex - 1, postorder);
        return root;
    }
}
