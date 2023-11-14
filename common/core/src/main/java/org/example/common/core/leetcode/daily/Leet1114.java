package org.example.common.core.leetcode.daily;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

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

        return 0;
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
    public void main(){
        dirsExercise();
    }
}
