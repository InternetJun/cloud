package org.example.common.core.leetcode.algorithm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lejun
 * @project: cloud
 * @description:归并排序的应用
 * @time: 2023/9/3 14:35
 */
public class MergeSort {
    /**
     * 合并2个数组，利用的是归并排序。
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int len = m + n;
        int[] temp = new int[len];
        mergeTwoArray(nums1, nums2, temp);
    }

    public void mergeTwoArray(int[] left, int[] right, int[] temp) {
        int rP = 0, lP = 0, index = 0;
        while (rP < right.length && lP < left.length) {
            if (left[lP] < right[rP]) {
                temp[index++] = left[lP];
                lP++;
            } else {
                temp[index++] = right[rP];
                rP++;
            }
        }
        // 还会遗漏下右边或者左边的数据。
        if (rP >= right.length) {

        }

        if (lP >= left.length) {

        }
    }

    /**
     * <p>
     * 对于单链表来说，我们直接用双指针从头开始合并即可，但对于数组来说会出问题。因为题目让我直接把结果存到 nums1 中，
     * 而 nums1 的开头有元素，如果我们无脑复制单链表的逻辑，会覆盖掉 nums1 的原始元素，导致错误。
     * <p>
     * 但 nums1 后面是空的呀，所以这道题需要我们稍微变通一下：将双指针初始化在数组的尾部，然后从后向前进行合并，
     * 这样即便覆盖了 nums1 中的元素，这些元素也必然早就被用过了，不会影响答案的正确性。
     * </p>
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void mergeSolution(int[] nums1, int m, int[] nums2, int n) {
        // 两个指针分别初始化在两个数组的最后一个元素（类似拉链两端的锯齿）
        int i = m - 1, j = n - 1;
        // 生成排序的结果（类似拉链的拉锁）
        int p = nums1.length - 1;
        // 从后向前生成结果数组，类似合并两个有序链表的逻辑
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[p] = nums1[i];
                i--;
            } else {
                nums1[p] = nums2[j];
                j--;
            }
            p--;
        }
        // 可能其中一个数组的指针走到尽头了，而另一个还没走完
        // 因为我们本身就是在往 nums1 中放元素，所以只需考虑 nums2 是否剩元素即可
        while (j >= 0) {
            nums1[p] = nums2[j];
            j--;
            p--;
        }
    }

    @Test
    public void main() {
        int[] a = {1,2,3,0,0,0};
        int[] b = {2,5,6};
        int m = 3, n = 3;
        mergeSolution(a, m, b, n);
    }

    /**
     * 相同的元素要被保留一份
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= n-1; i++) {
            if (i < n-1 && nums[i] == nums[i+1]) {
                while (nums[i] == nums[i + 1]) {
                    if (!list.contains(nums[i])) {
                        list.add(nums[i]);
                    }
                    // 储存一份；
                    i++;
                }
            } else {
                list.add(nums[i]);
            }
        }
        return list.size();
    }

    @Test
    public void saveOneTest() {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        removeDuplicatesSol(nums);
    }

    public int removeDuplicatesSol(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int p = 0;
        int q = 1;
        while(q < nums.length){
            if(nums[p] != nums[q]){
                nums[p + 1] = nums[q];
                p++;
            }
            q++;
        }
        return p + 1;
    }

    public int removeDuplication(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            // 满足此条件才会加入
//            if (nums[i] == nums[i+1] && i+1 <  nums.length) {
//                int x = nums[i];
//                while (nums[i++] == x && count <2) {
//                    count++;
//                    nums[index++] = nums[i];
//                }
//                count = 0;
//            } else {
//                nums[index++] = Integer.MIN_VALUE;
//            }

        }
        // nums要进行处理。不要后面的元素了。其实会有的情况是在范围内就代表了是重复的nums[n-k] != nums[n];

        return index;
    }

    @Test
    public void testDuplication() {
        int[] nums = {1,1,1,2,2,3};
        removeDuplicatesII(nums);
    }

    public int removeDuplicatesII(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }
        int slow = 2, fast = 2;
        while (fast < n) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }

    public int majorityElement(int[] nums) {
        // 我们想寻找的那个众数
        int target = 0;
        // 计数器（类比带电粒子例子中的带电性）
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            // 这里设计的太巧妙了。太厉害了。
            if (count == 0) {
                // 当计数器为 0 时，假设 nums[i] 就是众数
                target = nums[i];
                // 众数出现了一次
                count = 1;
            } else if (nums[i] == target) {
                // 如果遇到的是目标众数，计数器累加
                count++;
            } else {
                // 如果遇到的不是目标众数，计数器递减
                count--;
            }
        }
        // 回想带电粒子的例子
        // 此时的 count 必然大于 0，此时的 target 必然就是目标众数
        return target;
    }

    @Test
    public void testMajor() {
        int[] nums = {3,3,4};
        System.out.println(majorityElement(nums));
    }

}
