package org.example.common.core.leetcode.daily.november;

import lombok.extern.slf4j.Slf4j;
import org.example.common.core.leetcode.tree.TreeNode;
import org.example.common.core.util.CommonUtil;
import org.junit.Test;

import java.util.LinkedList;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/25 10:12
 */
@Slf4j
public class Leet1125 {

    private static LinkedList<TreeNode> list = new LinkedList<>();

    /**
     * 从<b>根节点</b>开始的路径中是否有回文？
     * 为什么可以用dfs去做？
     *
     * @param root
     * @return
     */
    public int pseudoPalindromicPaths(TreeNode root) {
        int[] counter = new int[10];
        return dfs(root, counter);
    }

    public int dfs(TreeNode root, int[] counter) {
        if (root == null) {
            return 0;
        }
        counter[root.val]++;
        list.add(root);
        int res = 0;
        if (root.left == null && root.right == null) {
            log.info("{}", list);
            if (isPseudoPalindrome(counter)) {
                res= 1;
            }
        } else {
            res = dfs(root.left, counter) + dfs(root.right, counter);
        }
        counter[root.val]--;
        list.removeLast();
        return res;
    }

    public boolean isPseudoPalindrome(int[] counter) {
        int odd = 0;
        for (int value : counter) {
            if (value % 2 == 1) {
                odd++;
            }
        }
        return odd <= 1;
    }

    @Test
    public void testMain() {
        final TreeNode treeNode = CommonUtil.buildTree(new Integer[]{2, 3, 1, 3, 3, null, 1});
        System.out.println(pseudoPalindromicPaths(treeNode));
    }
}
