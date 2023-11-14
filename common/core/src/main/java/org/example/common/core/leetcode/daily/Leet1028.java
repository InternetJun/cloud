package org.example.common.core.leetcode.daily;

import java.util.Arrays;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/10/28 15:14
 */
public class Leet1028 {
    /**
     * <p>
     *    思想是没有错的，但是没有考虑到的是board - max【i】
     * </p>
     *
     * @param h
     * @param w
     * @param horizontalCuts
     * @param verticalCuts
     * @return
     */
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        // 需要对所有的蛋糕进行一个切割处理。
        int mod = (int) (1e9 + 7);
        int validH = horizontalCuts.length - 1;
        int validV = verticalCuts.length - 1;
        for (int i = 0; i < horizontalCuts.length; i++) {
            if (horizontalCuts[i] > h) {
                validH = i;
            }
        }
        for (int i = 0; i < verticalCuts.length; i++) {
            if (verticalCuts[i] > h) {
                validV = i;
            }
        }
        // 因为切割是只和上一个切割的位置有关，所有。他是一个dp处理？
        int max = Integer.MIN_VALUE;
        //计算出前缀和，比较数据
        int[] preSumH = new int[validH - 1];
        int[] preSumV = new int[validV - 1];
        for (int i = 0; i < validH; i++) {
            preSumH[i] = horizontalCuts[i + 1] - horizontalCuts[i];
        }
        for (int i = 0; i < validV; i++) {
            preSumV[i] = verticalCuts[i + 1] - verticalCuts[i];
        }
        // 1 3 5   2 2; 1 2 4  1 2
        return  max;
    }

    public int maxAreaSolution(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        return (int) ((long) calMax(horizontalCuts, h) * calMax(verticalCuts, w) % 1000000007);
    }

    public int calMax(int[] arr, int boardr) {
        int res = 0, pre = 0;
        for (int i : arr) {
            res = Math.max(i - pre, res);
            pre = i;
        }
        return Math.max(res, boardr - pre);
    }
}
