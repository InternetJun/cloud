package org.example.common.core.leetcode.daily.december;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description:
 * @time: 2023/12/16 14:18
 */
@Slf4j
public class Leet1216 {
    class CountIntervals {
        /**
         * 所有的区间列表
         */
        List<int[]> list;
        /**
         * 计数每个区间的个数
         */
        List<Integer> countList;

        public CountIntervals() {
            this.list = new ArrayList<>();
            this.countList = new ArrayList<>();
        }

        public void add(int left, int right) {
            int i = 0, size = list.size();
            int[][] temp = new int[size + 1][];
            if (size == 0) {
                list.add(new int[]{left, right});
                return;
            }
            // 在左边
            while (i < size && left > list.get(i)[1]) {
                temp[i] = list.get(i);
                i++;
            }
            // 在右边,需要合并的区间了。2 3 (5 7)这时会有
            int leftMin = Integer.MAX_VALUE;
            int rightMax = Integer.MIN_VALUE;
            while (i < size && list.get(i)[0] <= right) {
                leftMin = Math.min(leftMin, Math.min(left, list.get(i)[0]));
                rightMax = Math.max(rightMax, Math.max(list.get(i)[1], right));
                i++;
            }
            temp[i++] = new int[]{leftMin, rightMax};
            // 在中间
            for (int j = i; j < size; j++) {
                temp[i++] = list.get(j);
            }
            log.info("{}", Arrays.deepToString(temp));
            list.clear();
            for (int[] ints : temp) {
                list.add(ints);
            }
        }
// http://10.0.18.10:9098/Radius/reader/routerFirst?userip=10.0.173.201&apmac=0C-DA-41-1F-8C-E0&nasip=10.0.172.253&usermac=CC-FC-E3-8F-A9-DC&userurl=http://www.msftconnecttest.com/redirect
        public int[][] insert(int[][] intervals, int[] newInterval) {
            List<int[]> ans = new ArrayList<>();
            int i = 0;
            while (i < intervals.length && intervals[i][1] < newInterval[0]) {
                ans.add(intervals[i]);
                i++;
            }

            while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
                newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
                newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
                i++;
            }
            ans.add(newInterval);
            while (i < intervals.length) {
                ans.add(intervals[i]);
                i++;
            }
            return ans.toArray(new int[0][]);
        }

        public int count() {
            int sum = 0;
            for (int[] item : list) {
                sum += item[1] - item[0] + 1;
            }
            return sum;
        }
    }

    @Test
    public void main() {
        final CountIntervals countIntervals = new CountIntervals();
        countIntervals.add(2, 3);
        countIntervals.add(7, 10);
        System.out.println(countIntervals.count());
        countIntervals.add(5, 8);
        System.out.println(countIntervals.count());
    }
}
