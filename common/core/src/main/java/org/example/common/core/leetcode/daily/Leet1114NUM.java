package org.example.common.core.leetcode.daily;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 图的算法
 * @time: 2023/11/14 13:47
 */
@Slf4j
public class Leet1114NUM {
    private static final Random RANDOM = new Random();

    @Test
    public void main() {
        int[] num = {2, 3, 4, 5};
        int n = 2345;
        System.out.println(getMaxLessNum(num, n));
    }

    //
    private int getMaxLessNum(int[] nums, int n) {
        Arrays.sort(nums);
        int minNum = nums[0];
        String maxNum = getMaxString(minNum, n);
        log.info("{}", maxNum);
        StringBuilder s = new StringBuilder();
        boolean preIndexLess = false;
        for (int i = 0; i < maxNum.length(); i++) {
            int index = preIndexLess ? nums.length - 1 : search(maxNum, i, nums);
            log.info("{}；；；；i:{}", search(maxNum, i, nums), index);
            s.append(nums[index]);
            if (nums[index] < (maxNum.charAt(i) - '0')) {
                preIndexLess = true;
            }
        }
        log.info("{}", s.toString());
        return Integer.parseInt(s.toString());

    }


    /**
     * 获取要查询的最大值， 可能出现拼接不出与原来数字长度相等的数，那么就返回长度减一的最大值
     *
     * @param minNum
     * @param n
     * @return
     */
    private String getMaxString(int minNum, int n) {
        boolean flag = false;

        String s = String.valueOf(n);
        // 解决前一个版本出现的bug
        for (char c : s.toCharArray()) {
            if ((c - '0') > minNum) {
                flag = true;
                break;
            } else if ((c - '0') < minNum) {
                break;
            }
        }

        int maxNum = flag ? (n - 1) : (int) (Math.pow(10, s.length() - 1) - 1);

        return String.valueOf(maxNum);
    }

    /**
     * 二分查找，查找等于或者小于findNum的最右边位置
     */
    private int search(String maxNum, int index, int[] nums) {
        int curMax = maxNum.charAt(index) - '0';
        int minNum = nums[0];
        int findNum = curMax;
        if (index < maxNum.length() - 1) {
            findNum = (maxNum.charAt(index + 1) - '0') < minNum ? curMax - 1 : curMax;
        }

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > findNum) {
                right = mid - 1;
            } else if (nums[mid] < findNum) {
                left = mid + 1;
            } else {
                return mid;
            }
        }

        return right;
    }

    /**
     * 第k大的数
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthNum(int[] nums, int k) {
        int target = nums.length - k;
        int start = 0, end = nums.length - 1;
        int index = getPivot(nums, start, end);
        while (index != target) {
            if (index > target) {
                end = index - 1;
            } else {
                start = index + 1;
            }
            index = getPivot(nums, start, end);
        }
        return nums[index];
    }

    private int getPivot(int[] nums, int start, int end) {
        int random = RANDOM.nextInt(end - start + 1) + start;
        swap(nums, random, end);
        int index = start - 1;
        log.info("index的值是多少：{}", index);
        for (int i = start; i < end; i++) {
            if (nums[i] < nums[end]) {
                swap(nums, i, ++index);
            }
        }
        swap(nums, ++index, end);
        return index;
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    @Test
    public void findKth() {
        int[] nums = {1, 5, 4, 2, 6};
        System.out.println(findKthNum(nums, 3));
    }

    private int[] nums;
    private int max = Integer.MIN_VALUE;
    private int len;
    private int target;

    /**
     * 从给定的nums要组成比n小的最大数。
     *
     * @param n
     * @param num
     * @return
     */
    public int maxNumLessN(int n, int[] num) {
        Arrays.sort(num);
        // 进行一个回溯操作，可以要比较。
        len = num.length;
        nums = num;
        target = n;
        dfs(0, 0);
        return max;
    }

    public void dfs(int curLen, int num) {
        max = Math.max(num, max);
        if (curLen == len) {
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 计算
            int temp = num * 10 + nums[i];
            if (temp >= target) {
                // return ? continue
                continue;
            }
            num = num * 10 + nums[i];
            curLen += 1;
            dfs(curLen, num);
            num /= 10;
            curLen -= 1;
        }
    }

    @Test
    public void testMaxNum() {
        int n = 2345;
        int[] nums = {2, 3, 4, 5};
        System.out.println(maxNumLessN(n, nums));
    }
}
