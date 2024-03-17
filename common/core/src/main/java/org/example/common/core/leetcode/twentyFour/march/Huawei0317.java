package org.example.common.core.leetcode.twentyFour.march;

import lombok.extern.slf4j.Slf4j;
import org.example.common.core.util.CommonUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description:
 * @time: 2024/3/17 13:38
 */
@Slf4j
public class Huawei0317 {

    /**
     * 我们使用动态规划来求解最短路径。定义dp[state][last]表示当前情况下已经投递的客户集合为state，上一次投递的客户为last时，已经走过的最短距离。状态转移方程为：
     * <p>
     * dp[state | (1 << last)][last] = min(dp[state | (1 << last)][last], dp[state][i] + dist[i][last])
     * 其中，state为二进制表示的已经投递的客户集合，state | (1 << last)
     * 表示将state中last位置设置为1，last 表示上一次投递的状态。
     * dist[i][last]表示投递的客户的最短距离。
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt(), m = scanner.nextInt();
        int[][] dist = new int[n + 1][n + 1];
        for (int i = 0; i < dist.length; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        // 客户id 和索引下标的对照表
        Map<Integer, Integer> idxMap = new HashMap<>();

        // 初始化客户id 到 投递站(0) 之间的距离
        for (int idx = 1; idx <= n; idx++) {
            int cid = scanner.nextInt();
            int distance = scanner.nextInt();
            dist[0][idx] = dist[idx][0] = distance;
            idxMap.put(cid, idx);
        }

        // 初始化客户与客户之间的距离
        for (int i = 0; i < m; i++) {
            int cid1 = scanner.nextInt(),
                    cid2 = scanner.nextInt(),
                    distance = scanner.nextInt();
            int idx1 = idxMap.get(cid1), idx2 = idxMap.get(cid2);
            dist[idx1][idx2] = dist[idx2][idx1] = distance;
        }

        // Floyd-Warshall算法 求出所有点之间的最短距离 时间复杂度为O(n^3)
        for (int k = 0; k <= n; k++) {
            // 自己到自己的距离为0
            dist[k][k] = 0;
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        CommonUtil.formatAndDisplayArray(dist);
//        log.info("{}", );
        // dp[state][last] 当前情况走过的最短距离
        // state 表示已经投递的客户 （指定二进制位为1表示已经投递），last表示上一次投递的客户
        int[][] dp = new int[1 << (n + 1)][n + 1];
        for (int i = 0; i < (1 << (n + 1)); i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        // 初始状态，在投递站
        dp[1][0] = 0;
        //在Java中，表达式 1 << (n + 1) 表示对数字 1 进行左移操作，移动的位数是 (n + 1)。这是一个位运算操作，称为左移运算符
        for (int state = 0; state < (1 << (n + 1)); state++) {
            // 这里表示了i是一个站点的。有012
            for (int i = 0; i <= n; i++) {
                // 如果 i 已经投递 且 可达
                if ((state >> i & 1) == 1 &&
                        dp[state][i] != Integer.MAX_VALUE) {
                    for (int last = 0; last <= n; last++) {
                        dp[state | (1 << last)][last] = Math.min(dp[state | (1 << last)][last], dp[state][i] + dist[i][last]);
                    }
                }
            }
        }

        System.out.println(dp[(1 << (n + 1)) - 1][0]);
    }

    public int maxHomeValue(Node root) {
        return 0;
    }

    @Test
    public void main() {
        int[][] data = {
                {1, 2},
                {1, 3},
                {2, 4}
        };
        final Node node = constructBinaryTree(data);
        log.info("{}", node);
    }


    public Node constructBinaryTree(int[][] data) {
        Map<Integer, Node> nodes = new HashMap<>();

        // 构建所有节点
        for (int[] pair : data) {
            int parent = pair[0];
            int child = pair[1];

            if (!nodes.containsKey(parent)) {
                nodes.put(parent, new Node(parent));
            }
            if (!nodes.containsKey(child)) {
                nodes.put(child, new Node(child));
            }
        }

        // 将子节点连接到父节点
        for (int[] pair : data) {
            int parentValue = pair[0];
            int childValue = pair[1];

            Node parentNode = nodes.get(parentValue);
            Node childNode = nodes.get(childValue);

            if (parentNode.left == null) {
                parentNode.left = childNode;
            } else {
                parentNode.right = childNode;
            }
        }

        // 找到根节点.要怎么获取的？？？
        Node root = null;
//        for (Node node : nodes.values()) {
//            if (!nodes.values().stream().anyMatch(n -> n.right == node)) {
//                root = node;
//                break;
//            }
//        }

        return root;
    }

    private static class Node {
        int val;
        int weight;
        Node left;
        Node right;

        public Node(int val, int weight, Node left, Node right) {
            this.val = val;
            this.weight = weight;
            this.left = left;
            this.right = right;
        }

        public Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}


