package org.example.common.core.leetcode.daily.november;

import org.example.common.core.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/23 14:37
 */
// 定义一个方法，输出树的树状形式
public class test {
    public static void printTree(TreeNode root) {
        if (root == null) {
// 如果根节点为空，直接返回
            return;
        }
// 创建一个列表，用于存储每一层的节点值
        List<List<String>> res = new ArrayList<>();
// 创建一个队列，用于层次遍历
        Queue<TreeNode> queue = new LinkedList<>();
// 将根节点入队
        queue.offer(root);
// 当队列不为空时
        while (!queue.isEmpty()) {
// 获取当前层的节点数
            int size = queue.size();
// 创建一个列表，用于存储当前层的节点值
            List<String> level = new ArrayList<>();
// 遍历当前层的节点
            for (int i = 0; i < size; i++) {
// 出队一个节点
                TreeNode node = queue.poll();
// 如果节点为空
                if (node == null) {
// 列表中添加一个空格
                    level.add(" ");
// 队列中入队一个空节点
                    queue.offer(null);
// 队列中入队一个空节点
                    queue.offer(null);
// 如果节点不为空
                } else {
// 列表中添加节点的值
                    level.add(String.valueOf(node.val));
// 队列中入队左子节点
                    queue.offer(node.left);
// 队列中入队右子节点
                    queue.offer(node.right);
                }
            }
// 将当前层的列表添加到结果列表中
            res.add(level);
// 计算树的高度
        }
// 计算树的宽度
        int height = res.size();
// 遍历结果列表
        int width = (int) Math.pow(2, height) - 1;
        for (int i = 0; i < height; i++) {
// 获取第i层的列表
            List<String> level = res.get(i);
// 计算每个节点之间的间隔
            int interval = width / level.size();
// 创建一个字符串构造器，用于拼接输出
            StringBuilder sb = new StringBuilder();
// 遍历第i层的节点
            for (int j = 0; j < level.size(); j++) {
// 获取第j个节点的值
                String val = level.get(j);
// 在节点值前后添加空格，使其长度为间隔的一半

                while (val.length() < interval / 2) {
                    val = " " + val + " ";
// 如果节点值长度为奇数，再在后面添加一个空格
                }
                if (val.length() % 2 == 1) {
                    val = val + " ";
// 在字符串构造器中拼接节点值和间隔
                }
                sb.append(val).append(repeat(" ", interval));
// 输出第i层的字符串
            }
            System.out.println(sb.toString());
        }
    }

    // 如果str为空或n小于等于0，返回空字符串
    private static String repeat(String str, int n) {
        if (str == null || n <= 0) {
            return "";
// 创建一个字符串构造器，用于拼接结果
        }
// 循环n次，每次将str添加到字符串构造器中
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(str);
// 返回字符串构造器的字符串表示
        }
        return sb.toString();
    }
}
