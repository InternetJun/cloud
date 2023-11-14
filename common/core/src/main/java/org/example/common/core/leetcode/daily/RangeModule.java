package org.example.common.core.leetcode.daily;

import org.junit.Test;

import java.util.*;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 增加，删除、获取区间中的值合并代码。
 * @time: 2023/11/12 17:14
 */
public class RangeModule {
    ArrayList<int[]> list;
    TreeMap<Integer, Integer> intervals;
    public RangeModule() {
        this.list = new ArrayList<>();
        this.intervals = new TreeMap<Integer, Integer>();
    }

    public void addRange(int left, int right) {
        // list会是一个有序的列表。
        /** add:[10, 20) --> 10~19
         *
         * 1, 05
         * 2,
         * remove:[10,14)
         * query(13,15)
         * query(16,17)
         */
        if (list.size() == 0) {
            list.add(new int[]{left, right - 1});
            return;
        }
        list.sort((Comparator.comparingInt(o -> o[0])));
        int index = 0;
        int[][] temp = new int[list.size()+1][2];
        // 比他小的直接忽略；
        while (list.get(index)[1] < left) {
            temp[index] = list.get(index);
            index++;
        }
        // 需要有合并的
        int[] insert = new int[]{left, right-1};
        int leftMin = Integer.MAX_VALUE, rightMax = Integer.MIN_VALUE;
        while (list.get(index++)[0] <= right - 1) {
            leftMin = Math.min(leftMin, insert[0]);
            rightMax = Math.max(rightMax, insert[1]);
        }
        temp[++index] = new int[]{leftMin, rightMax};
        // 其他的就是向后处理了。这里的index获取失败的。
        while (index < list.size()) {
            temp[index++] = list.get(index);
        }
        // 更新temp 为list的内容。

        Map.Entry<Integer, Integer> entry = intervals.higherEntry(left);
    }

    /**
     * 获取到[left, right)是否在其中
     *
     * @param left
     * @param right
     * @return
     */
    public boolean queryRange(int left, int right) {
        return false;
    }

    public void removeRange(int left, int right) {

    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int[][] res = new int[intervals.length + 1][2];
        int idx = 0;
        // 遍历区间列表：
        // 首先将新区间左边且相离的区间加入结果集
        int i = 0;
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            res[idx++] = intervals[i++];
        }
        // 接着判断当前区间是否与新区间重叠，重叠的话就进行合并，直到遍历到当前区间在新区间的右边且相离，
        // 将最终合并后的新区间加入结果集
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        res[idx++] = newInterval;
        // 最后将新区间右边且相离的区间加入结果集
        while (i < intervals.length) {
            res[idx++] = intervals[i++];
        }

        return Arrays.copyOf(res, idx);
    }

    @Test
    public void testI() {
        int[][] ints= {{1,3}, {6,9}};
        int[] newI = {2, 5};
        insert(ints, newI);
    }
}
