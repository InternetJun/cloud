package org.example.common.core.leetcode.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/9/7 17:24
 */
@Slf4j
public class Leet150 {
    /**
     * 获取最长的连续子序列,因为不要求那个下标。
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        // 转化成哈希集合，方便快速查找是否存在某个元素
        HashSet<Integer> set = new HashSet<Integer>();
        for (int num : nums) {
            set.add(num);
        }

        int res = 0;

        for (int num : set) {
            if (set.contains(num - 1)) {
                // num 不是连续子序列的第一个，跳过
                continue;
            }
            // num 是连续子序列的第一个，开始向上计算连续子序列的长度
            int curNum = num;
            int curLen = 1;

            while (set.contains(curNum + 1)) {
                curNum += 1;
                curLen += 1;
            }
            // 更新最长连续序列的长度
            res = Math.max(res, curLen);
        }

        return res;
    }

    /**
     * 最少数量的箭引爆气球。就是找出有交集的个数。
     *
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        int count = 1;
        int len = points.length;
        Arrays.sort(points, (Comparator.comparingInt(o -> o[1])));
        int end = points[0][1];
        for (int i = 0; i < len; i++) {
            int start = points[i][0];
            // 只有大于的时候才要加起来的。要不然就不要处理了。
            if (start > end) {
                end = points[i][1];
                count++;
            }
        }
        return count;
    }

    @Test
    public void mainShe() {
        int[][] ints = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        System.out.println(findMinArrowShots(ints));
        final boolean interrupted = Thread.interrupted();
    }

    /**
     * 3,5,7所有的1~n
     *
     * <p>
     * 3 6 9
     * 5 10 15
     * 7 14 21 28 ...
     * </p>
     *
     * @param n
     * @return
     */
    public int sumOfMultiples(int n) {
        if (n < 3) {
            return 0;
        }
        return sumOfMultiplesSolution(n);
    }

    /**
     * 为什么正向的就错了？有公共的公约，没有办法做到一次处理！
     *
     * @param n
     * @return
     */
    public int sumOfMultiplesSolution(int n) {
        int constant3 = 3, constant5 = 5, constant7 = 7;
        int sum = 0;
        int step = 1;
        int tempConstant3 = 0, tempConstant5 = 0, tempConstant7 = 0;
        while (tempConstant3 <= n || tempConstant5 <= n || tempConstant7 <= n) {

            tempConstant3 = step * constant3;
            tempConstant5 = step * constant5;
            tempConstant7 = step * constant7;
            step++;
            StringBuilder stringBuilder = new StringBuilder();
            if (tempConstant3 <= n) {
                stringBuilder.append(tempConstant3);
                sum += tempConstant3;
            }

            if (tempConstant5 <= n) {
                stringBuilder.append("\t"+tempConstant5);
                sum += tempConstant5;
            }

            if (tempConstant7 <= n) {
                stringBuilder.append("\t"+tempConstant7);
                sum += tempConstant7;
            }
            log.info("{}", stringBuilder.toString());
            stringBuilder.setLength(0);
        }

        return sum;
    }

    @Test
    public void mainSum() {
        int n = 15;
        /**
         * 3
         * 5
         * 6
         * 7
         * 9
         * 10
         * 12
         * 14
         * 15
         */
        System.out.println(sumOfMultiples(n));
    }

    public int sumOfMultiplesBeautify(int n) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 || i % 5 == 0 || i % 7 == 0) {
                log.info("{}", i);
                res += i;
            }

        }
        return res;
    }
}
