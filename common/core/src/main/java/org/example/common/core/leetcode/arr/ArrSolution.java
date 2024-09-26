package org.example.common.core.leetcode.arr;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/9/22 14:48
 */
@Slf4j
public class ArrSolution {


    /**
     * 只出现一次的数组
     *
     * @param nums
     * @return
     */
    public int removeDuplicatesOnlyOne(int[] nums) {
        // 和上面一样的思路！
        return 0;
    }

    /**
     * 左右规则。
     *
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        int count = left[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
                count += Math.max(left[i], right[i]);
            }
        }
        return count;
    }

    /**
     * 环形的数组求一个可行解
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int sum = 0, minSum = 0;
        int start = 0;
        for (int i = 0; i < n; i++) {
            sum += gas[i] - cost[i];
            log.info("第{}处相差：{}", i, sum);
            /**
             * 这里的解释是什么？
             * <p>
             *     为什么他的起点是变为了i+1?
             *     A:因为有题目的要求的。
             * </p>
             */
            if (sum < minSum) {
                // 因为有题目说明的意思。
                start = i + 1;
                minSum = sum;
            }
        }
        if (sum < 0) {
            log.info("此问题无解，请检查是否有可行解！");
            return -1;
        }
        // 会有start == n 的情况出现？为啥
        return start == n ? 0 : start;
    }

    @Test
    public void testGas() {
        int[] gas = {4, 5, 1, 2, 3};
        int[] cost = {1, 2, 3, 4, 5};
        System.out.println(canCompleteCircuit(gas, cost));
    }

    /**
     * <h1>最长的公共前缀</h1>
     *
     * <b>我是连基本的参照物准则都没有搞懂的人啊！</b>
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefixMe(String[] strs) {
        if (strs == null || strs.length == 1) {
            return "";
        }
        int n = strs.length;
        StringBuilder sb = new StringBuilder();
        String s = strs[0];
        for (int i = 1; i < n; i++) {
            String temp = strs[i];
            char[] chars = temp.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                if (s.charAt(j) != 'a' ) {
                    return "";
                }
            }
        }
        return sb.toString();
    }

    public String longestCommonPrefix(String[] strs) {
        ReentrantLock lock = new ReentrantLock();
        int m = strs.length;
        // 以第一行的列数为基准
        int n = strs[0].length();
        for (int col = 0; col < n; col++) {
            for (int row = 1; row < m; row++) {
                String thisStr = strs[row], prevStr = strs[row - 1];
                // 判断每个字符串的 col 索引是否都相同
                if (col >= thisStr.length() || col >= prevStr.length() ||
                        thisStr.charAt(col) != prevStr.charAt(col)) {
                    // 发现不匹配的字符，只有 strs[row][0..col-1] 是公共前缀
                    return strs[row].substring(0, col);
                }
            }
        }
        return strs[0];
    }

    @Test
    public void testThree() {
        int[] nums = {-1,0,1,2,-1,-4};
        for (int i = 3; i < 6; i++) {
            final List<List<Integer>> lists = nSum(nums, i);
            log.info("{}", lists);
        }
    }

    /**
     * 3数之和
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> nSum(int[] nums, int n) {
        Arrays.sort(nums);
        return nSumTarget(nums, n, 0, 0);
    }

    private List<List<Integer>> nSumTarget(int[] nums, int n, int start, int target) {
        int sz = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (n < 2 || sz < n) {
            return res;
        }
        if (n == 2) {
            int lo = start, hi = sz - 1;
            while (lo < hi) {
                int sum = nums[lo] + nums[hi];
                int left = nums[lo], right = nums[hi];
                if (sum < target) {
                    while (lo < hi && nums[lo] == left) {
                        lo++;
                    }
                } else if (sum > target) {
                    while (lo < hi && nums[hi] == right) {
                        hi--;
                    }
                } else {
                    res.add(new ArrayList<Integer>(Arrays.asList(left, right)));
                    while (lo < hi && nums[lo] == left) {
                        lo++;
                    }
                    while (lo < hi && nums[hi] == right) {
                        hi--;
                    }
                }

            }
        } else {
            for (int i = start; i < sz; i++) {
                List<List<Integer>>
                        sub = nSumTarget(nums, n - 1, i + 1, target - nums[i]);
                for (List<Integer> arr : sub) {
                    // (n-1)Sum 加上 nums[i] 就是 nSum
                    arr.add(nums[i]);
                    res.add(arr);
                }
                while (i < sz - 1 && nums[i] == nums[i + 1]) {
                    i++;
                }
            }
        }
        return res;
    }

    /**
     * https://labuladong.gitee.io/algo/di-san-zha-24031/jing-dian--a94a0/ru-he-gao--0d5eb/
     *
     * @param nums
     * @return 盛最多水的容器。
     */
    public int maxRain(int[] nums) {
        return 0;
    }

    /**
     * <h1>最长连续序列</h1>
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        Arrays.sort(nums);
        //
        int maxLen = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
//            // 有很多的重复解。需要优化。
//            int num = nums[i];
//            int j = i+1;
//            while (j < len && (num == nums[j] - 1)) {
//                num = nums[j];
//                j++;
//            }
//            // 说明不行用下标
//            maxLen = Math.max(j - i, maxLen);
//            i = j-1;
        }
        return maxLen;
    }

    @Test
    public void testLong() {
        int[] nums = {0,3,7,2,5,8,4,6,0,1};
        System.out.println(longestConsecutive(nums));
    }

    public int maxArea(int[] height) {
        int n = height.length;
        int maxVolume = 0;
        int leftMax = 0, rightMax = 0;
        // 最重要的思路是什么？要求有
        for(int i = 0, j = n - 1; i < n; i++, j--) {
            // 有小于maxVolume的话，左边大于右边的话是要怎么移动呢？
            int cur_area = Math.min(height[i], height[j]) * (j - i);
            maxVolume = Math.max(maxVolume, cur_area);
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return maxVolume;
    }

    /**
     * 出现了2个数字是单个其他的都是出现了2次。
     * x1 ^ x2 == y
     * y & (-y) ==> z 获取到？
     * z & x1 = (y & (-y)) ^ x
     *
     * @param nums
     * @return
     */
    public int[] singleNumber(int[] nums) {
        int xorAll = 0;
        for (int x : nums) {
            xorAll ^= x;
        }
        int lowbit = xorAll & -xorAll;
        int[] ans = new int[2];
        for (int x : nums) {
            int res = lowbit ^ x;
            log.info("{}", res);
            ans[(x & lowbit) == 0 ? 0 : 1] ^= x; // 分组异或
            log.info("{}", ans);
        }
        return ans;
    }

    @Test
    public void mainSingle() {
        int[] res = new int[]{1,1,2,2,3,5};
        System.out.println(Arrays.toString(singleNumber(res)));
    }

}
