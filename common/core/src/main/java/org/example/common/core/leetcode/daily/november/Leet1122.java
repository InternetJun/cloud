package org.example.common.core.leetcode.daily.november;

import lombok.extern.slf4j.Slf4j;
import org.example.common.core.util.CommonUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 1122的图算法
 * @time: 2023/11/21 14:13
 */
@Slf4j
public class Leet1122 {

    public int minPathCost(int[][] grid, int[][] moveCost) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[2][n];
        dp[0] = grid[0].clone();
        int cur = 0;
        for (int i = 1; i < m; i++) {
            int next = 1 - cur;
            log.info("next:{}", next);
            for (int j = 0; j < n; j++) {
                dp[next][j] = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    // next = 1-cur的作用是什么？
                    dp[next][j] = Math.min(dp[next][j], dp[cur][k] + moveCost[grid[i - 1][k]][j] + grid[i][j]);
                }
            }
            log.info("cur:{} and next:{}", cur, next);
            cur = next;
        }
        return Arrays.stream(dp[cur]).min().getAsInt();
    }

    @Test
    public void main() {
        Integer integer = new Integer(200);
        int z = 200;
        System.out.println(integer == z);
        System.out.println(integer.equals(z));
    }

    @Test
    public void testDp() {
        String s = "[[5,3],[4,0],[2,1]]";
        String moveCost = "[[9,8],[1,5],[10,12],[18,6],[2,4],[14,3]]";
        int[][] grid = CommonUtil.convertToArray(s, int[][].class);
        int[][] ms = CommonUtil.convertToArray(moveCost, int[][].class);
        minPathCost(grid, ms);
    }

}

