package org.example.common.core.leetcode.daily;

import lombok.extern.slf4j.Slf4j;
import org.example.common.core.util.CommonUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/10/29 10:07
 */
@Slf4j
public class Leet1029 {
    // todo
    public int hIndex(int[] citations) {
        return 0;
    }

    /**
     * 接雨水的问题
     * <p>
     * 每个位置的左右最小，
     * s = (min(left, right) - height[i]) * (k - j)
     * </p>
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int len = height.length;
        int sum = 0;
        int leftHeight = 0, rightHeight = 0;
        for (int i = 1; i <= len - 1; i++) {
            /** s = (min(left, right) - height[i]) * (k - j)
             * 什么情况是不用计算值的？
             * A:左右边界
             */
            int j = 0;
            // 会重复记录多次，不建议
            while (j < i) {
                // 只会记录高的
                if (height[i] > height[j]) {
                    leftHeight = Math.max(leftHeight, height[j]);
                }
                j++;
            }


        }

        // 一次遍历，把所有的值给计算出来？
        int[] leftArea = new int[len];
        int left = height[0], right = height[len - 1];
        leftArea[0] = left;
        int[] rightArea = new int[len];
        rightArea[len - 1] = right;
        for (int i = 1; i < len; i++) {
            // 思想正确
            if (height[i] > leftArea[i - 1]) {
                leftArea[i] = height[i];
            }
            if (height[len - 1 - i] > rightArea[len - i]) {
                rightArea[len - 1 - i] = 0;
            }
        }

        // 用一个变量存储呢？
        int L = height[0], R = height[len - 1];
        return sum;
    }

    /**
     * 接雨水。
     *
     * @param height
     * @return
     */
    public int trapSolution(int[] height) {
        int left = 0, right = height.length - 1;
        int res = 0;
        int leftMax = Integer.MIN_VALUE, rightMax = Integer.MIN_VALUE;
        // 计算leftMax, rightMax;
        while (left < right) {
            leftMax = Math.max(height[left], leftMax);
            log.info("l_max:{}", leftMax);
            rightMax = Math.max(height[right], rightMax);
            log.info("r_max:{}", rightMax);
            if (leftMax < rightMax) {
                res += leftMax - height[left];
                log.info("此时的高度为：{}", height[left]);
                left++;
            } else {
                log.info("此时的高度为：{}", height[right]);
                res += rightMax - height[right];
                right--;
            }
            log.info("left:{};right:{}, 获取到的结果是：{}", left, right, res);
        }
        return res;
    }

    /**
     * 盛最多水的区域
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = Integer.MIN_VALUE, l_max = Integer.MIN_VALUE, r_max = Integer.MIN_VALUE;
        while (left < right) {
            l_max = Math.max(l_max, height[left]);
            r_max = Math.max(r_max, height[right]);
            // 计算出面积。
            maxArea = Math.max(maxArea, Math.min(l_max, r_max) * (right - left));
            // l,r要怎么移动呢？l>r  ==>
        }
        return maxArea;
    }

    /**
     * 要从小开始计算。
     * water[i][j]=max(heightMap[i][j],min(water[i−1][j],water[i+1][j],water[i][j−1],water[i][j+1]))
     * https://leetcode.cn/problems/trapping-rain-water-ii/solutions/162877/you-xian-dui-lie-de-si-lu-jie-jue-jie-yu-shui-ii-b/
     *
     * @param heightMap
     * @return
     */
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;
        if (m <= 2 || n <= 2) {
            return 0;
        }
        boolean[][] visit = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    pq.offer(new int[]{i * n + j, heightMap[i][j]});
                    visit[i][j] = true;
                }
            }
        }
        log.info("{}", Arrays.deepToString(pq.toArray()));
        int res = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!pq.isEmpty()) {
            // 他代表了什么？
            int[] cur = pq.poll();
            for (int k = 0; k < 4; ++k) {
                // (-1,0),(0,1),(1,0),(0,-1)
                int nx = cur[0] / n + dirs[k];
                //
                int ny = cur[0] % n + dirs[k + 1];
//                log.info("k");
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visit[nx][ny]) {
                    if (cur[1] > heightMap[nx][ny]) {
                        res += cur[1] - heightMap[nx][ny];
                    }
                    pq.offer(new int[]{nx * n + ny, Math.max(heightMap[nx][ny], cur[1])});
                    visit[nx][ny] = true;
                }
            }
        }
        return res;
    }

    @Test
    public void main() {
        int[][] height = {{1,4,3,1,3,2},{3,2,1,3,2,4},{2,3,3,2,3,1}};
        System.out.println(trapRainWater(height));
    }

}