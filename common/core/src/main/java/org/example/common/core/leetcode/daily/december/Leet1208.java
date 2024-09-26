package org.example.common.core.leetcode.daily.december;

import lombok.extern.slf4j.Slf4j;
import org.example.common.core.util.CommonUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description:
 * @time: 2023/12/8 16:54
 */
@Slf4j
public class Leet1208 {
    /**
     * <p>
     *     1~n地方，选择乘客拉取。<br>
     *     [[1,6,1],  [3,10,2],    [10,12,3],[11,12,2],[12,15,2],    [13,18,1]]
     * </p>
     *
     * @param n
     * @param rides
     * @return
     */
//    public long maxTaxiEarnings(int n, int[][] rides) {
//        // 对末尾的位置排列
//        Arrays.sort((o1, o2) -> o1[1] - o2[1]);
//
//        /** 表示的是第i处的最大的收益。
//         *   dp[i] = dp[i-1] + (choose or not choose)
//         *
//         *   在给定的数组中，dp获取的子数列中，最大的和。
//         */
//        int[] dp = new int[n+1];
//        dp[0] = 0;
//        int step = 1;
//        for (int[] ride : rides) {
//            int end = ride[1];
//            // 以end为处理。
//            while (step < n) {
//                if (step > end && ) {
//                    dp[end] =
//                }
//            }
//        }
//        return dp[n+1];
//
//    }

    public long maxTaxiEarningsSolution(int n, int[][] rides) {
        Arrays.sort(rides, Comparator.comparingInt(a -> a[1]));
        int m = rides.length;
        long[] dp = new long[m + 1];
        for (int i = 0; i < m; i++) {
            int j = binarySearch(rides, i, rides[i][0]);
            dp[i + 1] = Math.max(dp[i], dp[j] + rides[i][1] - rides[i][0] + rides[i][2]);
        }
        log.info("{}", dp);
        return dp[m];
    }

    public int binarySearch(int[][] rides, int high, int target) {
        int low = 0;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (rides[mid][1] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    @Test
    public void main() {
        int[][] ints = CommonUtil.parseStringToArray("[[1,6,1],[3,10,2],[10,12,3],[11,12,2],[12,15,2],[13,18,1]]");
        maxTaxiEarningsSolution(20, ints);

    }
}
