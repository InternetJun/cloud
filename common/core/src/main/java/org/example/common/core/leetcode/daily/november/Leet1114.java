package org.example.common.core.leetcode.daily.november;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 图的算法
 * @time: 2023/11/14 13:47
 */
@Slf4j
public class Leet1114 {
    /**
     * floyd算法
     *
     * @param n
     * @param edges
     * @param distanceThreshold
     * @return
     */
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[] ans = {Integer.MAX_VALUE / 2, -1};
        int[][] mp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(mp[i], Integer.MAX_VALUE / 2);
        }
        for (int[] edge : edges) {
            int from = edge[0], to = edge[1], weight = edge[2];
            // 初始化
            mp[from][to] = mp[to][from] = weight;
        }
        for (int k = 0; k < n; k++) {
            // 自己到自己的距离为0，其他的需一个计算处理。
            mp[k][k] = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // 核心算法代码行
                    mp[i][j] = Math.min(mp[i][j], mp[i][k] + mp[k][j]);
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            int cnt = 0;
            for (int j = 0; j < n; ++j) {
                if (mp[i][j] <= distanceThreshold) {
                    cnt++;
                }
            }
            log.info("{}", cnt);
            if (cnt <= ans[0]) {
                ans[0] = cnt;
                ans[1] = i;
                log.info("ans:{}",ans[1]);
            }
        }
        return ans[1];
    }

    @Test
    public void testFloy() {
        int n = 4, distanceThreshold = 4;
        int[][] edges = {{0, 1, 3}, {1, 2, 1}, {1, 3, 4}, {2, 3, 1}};
        System.out.println(findTheCity(n, edges, distanceThreshold));
    }

    public void dirsExercise() {
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int i = 0; i < 4; i++) {
            int x = dirs[i];
            int y = dirs[i + 1];
            log.info("x:{};y:{}", x, y);
        }
    }

    @Test
    public void main() {
        dirsExercise();
    }
}
