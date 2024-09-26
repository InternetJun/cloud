package org.example.common.core.leetcode.daily.november;

import lombok.extern.slf4j.Slf4j;
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
 * @description: 二叉树前中后序的非递归形式的输出。
 * @time: 2023/11/2 14:09
 */
@Slf4j
public class Leet1102 {
    /**
     * sql:获取某部门的第二名的员工信息
     * mysql：
     *SELECT department, employee_id, salary
     * FROM your_table_name
     * WHERE department = 'a'
     * ORDER BY salary DESC
     * LIMIT 1 OFFSET 1;
     *
     * oracle：
     * SELECT department, employee_id, salary
     * FROM (
     *     SELECT department, employee_id, salary,
     *            DENSE_RANK() OVER (PARTITION BY department ORDER BY salary DESC) as ranking
     *     FROM your_table_name
     * ) ranked_data
     * WHERE ranked_data.ranking = 2
     *    AND department = 'a';
     */

    /**
     * 中序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> inOrderList(TreeNode root) {
        if (root == null) {
            return null;
        }
        final ArrayList<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            // 没有修饰符号，
            while(root != null) {
                stack.addFirst(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);
            root = root.right;
        }
        return list;
    }

    @Test
    public void testOrders() {
        /**
         *      1
         *    2    3
         * 4    5
         */
        final TreeNode treeNode = CommonUtil.buildTree(new Integer[]{1, 2, 3, 4, 5});
        final List<Integer> list = inOrderList(treeNode);
        final List<Integer> listPre = preOrderList(treeNode);
        final List<Integer> listPost = postOrderList(treeNode);
        log.info("{}", list);
        log.info("{}", listPre);
        log.info("{}", listPost);
    }

    /**
     * 中序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> preOrderList(TreeNode root) {
        if (root == null) {
            return null;
        }
        final ArrayList<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while(root != null) {
                list.add(root.val);
                stack.addFirst(root);
                root = root.left;
            }
            root = stack.pop();
            // 这里会发生空指针的异常数据。要怎么处理呢？
            root = root.right;
        }
        return list;
    }

    /**
     * 后序遍历：左右中的遍历顺序。
     * <p>
     *     主要的问题是什么？
     *     需要有向上的动作
     * </p>
     *
     * @param root
     * @return
     */
    public List<Integer> postOrderList(TreeNode root) {
        if (root == null) {
            return null;
        }
        final ArrayList<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while(root != null) {
                stack.addFirst(root);
                root = root.left;
            }
            root = stack.pop();
            // 解释：
            if (root.right == null || root.right == prev) {
                list.add(root.val);
                prev = root;
                root = null;
            } else {
                stack.addFirst(root);
                root = root.right;
            }
        }
        return list;
    }

}
