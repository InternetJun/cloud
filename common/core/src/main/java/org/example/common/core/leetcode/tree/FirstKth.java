package org.example.common.core.leetcode.tree;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/9/14 14:01
 */
@Slf4j
public class FirstKth {

    @Test
    public void main() {
        int[] nums = {3, 2, 1, 5, 6, 4};
        System.out.println(findKthLargest(nums, 2));
    }

    /**
     * 用nums构造树
     *
     * @param nums
     */
    public int findKthLargest(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        // 首先要知道的是一个大顶堆的概念。还有什么呢？
        for (int i = nums.length - 1; i >= nums.length - k + 1; i--) {
            swap(nums, 0, i);
            --heapSize;
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public void buildMaxHeap(int[] a, int heapSize) {
        for (int i = heapSize / 2; i >= 0; --i) {
            maxHeapify(a, i, heapSize);
        }
    }

    public void maxHeapify(int[] a, int i, int heapSize) {
        int l = i * 2 + 1, r = i * 2 + 2, largest = i;
        if (l < heapSize && a[l] > a[largest]) {
            largest = l;
        }
        if (r < heapSize && a[r] > a[largest]) {
            largest = r;
        }
        if (largest != i) {
            swap(a, i, largest);
            maxHeapify(a, largest, heapSize);
        }
    }

    public int findKthLargestI(int[] nums, int k) {
        // 小顶堆，堆顶是最小元素
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int e : nums) {
            // 每个元素都要过一遍二叉堆
            pq.offer(e);
            // 堆中元素多于 k 个时，删除堆顶元素
            if (pq.size() > k) {
                pq.poll();
            }
        }
        // pq 中剩下的是 nums 中 k 个最大元素，
        // 堆顶是最小的那个，即第 k 个最大元素
        return pq.peek();
    }

    @Test
    public void testWithApi() {
        int[] nums = {3, 2, 1, 5, 6, 4};
        System.out.println(findKthLargestI(nums, 2));
    }

    /**
     * 要比较的是a(i)+b(j+1)和a(i+1)+b(j)的大小 到底是谁动呢
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(nums1[0], nums2[0]));
        int i = 0, j = 0;
        while (k > 1) {
            while (i < nums1.length && j < nums2.length) {
                List<Integer> integers = new ArrayList<>();
                if (k <= 1) {
                    break;
                }
                // j 更大 -->  选择j
                if (nums1[i] + nums2[j+1] > nums1[i+1] + nums2[j]) {
                    integers.addAll(Arrays.asList(nums1[i+1], nums2[j]));
                    i++;
                } else {
                    integers.addAll(Arrays.asList(nums1[i], nums2[j+1]));
                    j++;
                }
                list.add(integers);
                k--;
            }
        }
        return list;
    }

    @Test
    public void mainKPair() {
        int[] nums1 = {1, 7, 11};
        int[] nums2 = {2, 4, 6};
        List<List<Integer>> lists = kSmallestPairsSolution(nums1, nums2, 3);
        log.info("{}", lists);
    }

    /**
     * 用二分法？
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairsSolution(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;

        /*二分查找第 k 小的数对和的大小*/
        int left = nums1[0] + nums2[0];
        int right = nums1[m - 1] + nums2[n - 1];
        int pairSum = right;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            long cnt = 0;
            int start = 0;
            int end = n - 1;
            while (start < m && end >= 0) {
                if (nums1[start] + nums2[end] > mid) {
                    end--;
                } else {
                    cnt += end + 1;
                    start++;
                }
            }
            if (cnt < k) {
                left = mid + 1;
            } else {
                pairSum = mid;
                right = mid - 1;
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        int pos = n - 1;
        /*找到小于目标值 pairSum 的数对*/
        for (int i = 0; i < m; i++) {
            while (pos >= 0 && nums1[i] + nums2[pos] >= pairSum) {
                pos--;
            }
            for (int j = 0; j <= pos && k > 0; j++, k--) {
                List<Integer> list = new ArrayList<>();
                list.add(nums1[i]);
                list.add(nums2[j]);
                ans.add(list);
            }
        }

        /*找到等于目标值 pairSum 的数对*/
        pos = n - 1;
        for (int i = 0; i < m && k > 0; i++) {
            int start1 = i;
            while (i < m - 1 && nums1[i] == nums1[i + 1]) {
                i++;
            }
            while (pos >= 0 && nums1[i] + nums2[pos] > pairSum) {
                pos--;
            }
            int start2 = pos;
            while (pos > 0 && nums2[pos] == nums2[pos - 1]) {
                pos--;
            }
            if (nums1[i] + nums2[pos] != pairSum) {
                continue;
            }
            int count = (int) Math.min(k, (long) (i - start1 + 1) * (start2 - pos + 1));
            for (int j = 0; j < count && k > 0; j++, k--) {
                List<Integer> list = new ArrayList<>();
                list.add(nums1[i]);
                list.add(nums2[pos]);
                ans.add(list);
            }
        }
        return ans;
    }
}
