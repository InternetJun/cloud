package org.example.common.core.leetcode.daily.november;

import org.example.common.core.problem.ScheduleAtFixProblem;
import org.junit.Test;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/10 13:19
 */
public class Leet1110 {
    /**
     * 相乘大于success的对数。
     * x * y = z >= success
     * <p>
     * 有success/x <= y = 5.3  y中有6 则有6 * x > success; 5 * x < success;
     * 其实就是一个二维数组的二分查找啊。
     * </p>
     *
     * @param spells
     * @param potions
     * @param success
     * @return
     */
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length;
        int m = potions.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            // 寻找出  long t = (success + spells[i] - 1) / spells[i] - 1;
            int target = (int) (success / spells[i] + 1);
            res[i] = m - binarySearch(potions, target);
        }
        return res;
    }

    /**
     * 找出比target的数小的。[12345]  3 left = 0, right = 4;
     * <p>
     *
     * </p>
     *
     * @param points
     * @param target
     * @return 小于target的最大坐标
     */
    public int binarySearch(int[] points, int target) {
        int left = 0, right = points.length;
        int mid = 0;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (points[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return mid;
    }

    @Test
    public void testBinary() {

    }

    /**
     * 获取到最近的一个回文数字。可以有以下几种情况。
     *
     * @param num
     * @return
     */
    public int nearestParaNum(int num) {

        return 0;
    }

    /**
     * 123  --> 12321
     * 124  -->  12421
     * 12==> 121
     *
     * @param left
     * @param even
     * @return
     */
    public long getPalindrome(long left, boolean even) {
        long res = left;
        // 不是奇数
        if (!even) {
            left = left / 10;
        }

        while (left > 0) {
            res = res * 10 +left % 10;
            left /= 10;
        }
        return res;
    }

    @Test
    public void testGenerate() {
        String s = "12";
        boolean isEven = (s.length() % 2 == 0);
//        System.out.println(getPalindrome(12, true));
        System.out.println(getPalindrome(123, false));
//        System.out.println(getPalindrome(143, false));
    }
}
