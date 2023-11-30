package org.example.common.core.leetcode.daily.november;

import lombok.extern.slf4j.Slf4j;
import org.example.common.core.util.CommonUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 在给定的2个数组中，👉给出坐标后满足数组元组都大于坐标的，和最大的一组数组坐标;要的是一个单调栈+二分查找
 * @time: 2023/11/17 10:26
 */
@Slf4j
public class Leet1117 {

    /**
     * [4321]
     * 【2495】
     * 【【4,1】,[1,3],[2,5]】
     * 4 >= 4 3 2 1
     * nums2[j] >= 1
     *
     * @param nums1
     * @param nums2
     * @param queries
     * @return
     */
    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length;
        int[][] res = new int[n][2];
        for (int i = 0; i < nums1.length; i++) {
            res[i][0] = nums1[i];
            res[i][1] = nums2[i];
        }
        final ArrayList<List<int[]>> list = new ArrayList<>();
        Arrays.sort(res, (Comparator.comparingInt(o -> o[0])));
        for (int i = 0; i < queries.length; i++) {
            int x = queries[i][0];
            int y = queries[i][1];
            if (i == queries.length - 1) {
                List<int[]> ints = binarySearch(res, x, y);
                list.add(ints);
            }
        }

        return null;
    }

    @Test
    public void main() {
        /*nums1 = [3,2,5], nums2 = [2,3,4], queries = [[4,4],[3,2],[1,1]]*/
        int[] nums1 = {2, 2, 5};
        int[] nums2 = {2, 3, 4};
        int[][] queies = {{4, 4}, {3, 2}, {1, 1}};
        maximumSumQueriesS(nums1, nums2, queies);
    }

    /**
     * 对x进行一个二分查询。对可能满足要求的都记录下来。<br>
     * <b>寻找第一个满足条件的二分写法：返回的值是left;还有寻找最右边满足要求的写法</b>
     *
     * @param array: [1, 9], []
     * @param x
     * @param y
     */
    public List<int[]> binarySearch(int[][] array, int x, int y) {
        int n = array.length;
        int left = 0, right = n;
        int mid = -1;
        while (left < right) {
            // 找出第一个满足的。
            mid = left + (right - left) / 2;
            if (array[mid][0] >= x) {
                // 因为是size()所以是mid  2 3 比较的2这时有的是mid = 0；
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        // 为什么是使用mid+1；就是left的呢？
        log.info("mid:{}", left);
        final ArrayList<int[]> ints = new ArrayList<>();
        // 找出第一个mid满足的。进行一个比较处理。
        for (int i = left; i < n; i++) {
            int[] item = array[i];
            if (item[1] >= y) {
                ints.add(item);
            }
        }
        return ints;
    }

    int right_bound(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] == target) {
                // 这里改成收缩左侧边界即可
                left = mid + 1;
            }
        }
        // 这里改为检查 right 越界的情况，见下图
        if (right < 0 || nums[right - 1] != target) {
            return -1;
        }
        return right - 1;
    }

    @Test
    public void testRightBound() {
        int[] num = {1, 3, 3, 4};
        int target = 2;
        System.out.println(right_bound(num, target));
    }

    /**
     * 需要来标记哪个被使用了。
     *
     * @param list
     * @param res
     * @return
     */
    public int[] queryForMax(ArrayList<List<int[]>> list, int[][] res) {

        final boolean[] used = new boolean[res.length];
        for (List<int[]> ints : list) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < ints.size(); i++) {
                max = Math.max(ints.get(i)[0] + ints.get(i)[1], max);
                // 更新最后哪个被使用。
            }
        }
        return null;
    }

    // 二分算法出现了问题，不知道该用什么处理。

    public int[] maximumSumQueriesS(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length;
        int[][] sortedNums = new int[n][2];
        for (int i = 0; i < n; i++) {
            sortedNums[i][0] = nums1[i];
            sortedNums[i][1] = nums2[i];
        }
        Arrays.sort(sortedNums, (a, b) -> b[0] - a[0]);
        int q = queries.length;
        int[][] sortedQueries = new int[q][3];
        for (int i = 0; i < q; i++) {
            sortedQueries[i][0] = i;
            sortedQueries[i][1] = queries[i][0];
            sortedQueries[i][2] = queries[i][1];
        }
        Arrays.sort(sortedQueries, (a, b) -> b[1] - a[1]);
        List<int[]> stack = new ArrayList<>();
        int[] answer = new int[q];
        Arrays.fill(answer, -1);
        int j = 0;
        for (int[] query : sortedQueries) {
            int i = query[0], x = query[1], y = query[2];
            while (j < n && sortedNums[j][0] >= x) {
                int[] pair = sortedNums[j];
                int num1 = pair[0], num2 = pair[1];
                while (!stack.isEmpty() && stack.get(stack.size() - 1)[1] <= num1 + num2) {
                    stack.remove(stack.size() - 1);
                }
                if (stack.isEmpty() || stack.get(stack.size() - 1)[0] < num2) {
                    stack.add(new int[]{num2, num1 + num2});
                }
                j++;
            }
            CommonUtil.formatAndDisplayArray(stack);
            int k = binarySearch(stack, y);
            if (k < stack.size()) {
                log.info("{}", stack.get(k)[1]);
                answer[i] = stack.get(k)[1];
            }
        }
        return answer;
    }

    public int binarySearch(List<int[]> list, int target) {
        int low = 0, high = list.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid)[0] >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
