package org.example.common.core.leetcode.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/8/20 18:07
 */
@Slf4j
public class Maoao {
    public void bubbleSort(int[] arr) {
        if (arr == null) {
            return;
        }
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // 交换位置
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            int s = arr[start];
            int low = start, high = end;
            while (low < high) {
                // 寻找小的
                while (low < high && arr[high] >= s) {
                    high--;
                }
                arr[low] = arr[high];
                // 寻找大的！
                while (low < high && arr[low] < s) {
                    low++;
                }
                arr[high] = arr[low];
            }
            arr[low] = s;
            quickSort(arr, start, low);
            quickSort(arr, low + 1, end);
        }
    }

    @Test
    public void test() {
        int[] arr = {1, 5, 4, 3};
        quickSort(arr, 0, 3);
        System.out.println(Arrays.toString(arr));
    }

    // 插入排序、选择排序是什么呢？
    public void insertSort(int[] arr) {
        int len = arr.length;
        for (int i = 1; i <= len - 1; i++) {
            // 对比的是比i小的数据
            if (arr[i] < arr[i - 1]) {
                int j;
                int temp = arr[i];
                for (j = i - 1; j >= 0; j--) {
                    arr[j + 1] = arr[j];
                }
                arr[j + 1] = temp;
            }
        }
    }

    public int search(int[] arr, int target) {
        if (arr[0] == target) {
            return 0;
        }
        int right = arr.length - 1;
        int left = 0;
        int mid = 0;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                while (mid > 0 && arr[mid - 1] == arr[mid]) {
                    mid--;
                }
                return mid;
            }
            // 0~mid 递增 mid ~ right递增
            if (arr[mid] < arr[right]) {
                if (arr[mid] < target && target <= arr[right]) {
                    left = mid+1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 删除有序数组中的重复项 II 所有的元素只出现2次
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {

        int left = 0, right = 0, count = 0;
        int len = nums.length;
        while (right < len) {
            while (nums[left] == nums[right]) {
                count++;
                if (count > 2) {
                    nums[right] = Integer.MIN_VALUE;
                }
                right++;
            }
            count = 0;
            if (nums[left] < nums[right]) {
                left = right++;
            }
        }
        System.out.println(Arrays.toString(nums));
        Long count1 = Arrays.stream(nums).filter(m -> m != Integer.MIN_VALUE).count();
        return count1.intValue();
    }

    /**
     * 输入: nums = [1,2,3,4,5,6,7], k = 3
     * 输出: [5,6,7,1,2,3,4]
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        int[] newArr = new int[len];
        for (int i = 0; i < len; i++) {
            int rotateIndex = (i + k) % len;
            newArr[rotateIndex] = nums[i];
        }
        System.out.println(Arrays.toString(newArr));
    }

    @Test
    public void roTest() {
        int[] ro = {1,2,3,4,6,7,5};
        System.out.println(findKthMaxNum(ro, 3));
        log.info("{}", ro);
    }


    public int findKthMaxNum(int[] nums, int k) {
        int n = nums.length;
        return quickSortSelect(nums, 0, n-1, n-k);
    }

    //  注意下标的表示方法。
    private int quickSortSelect(int[] nums, int l, int r, int k) {
        if (l == r) {
            return nums[k];
        }
        int x = nums[l], i = l - 1, j = r+1;
        while (i < j) {
            do {
                i++;
            }
            while (nums[i] < x);
            do {
                j--;
            }
            while (nums[j] > x);
            if (i < j) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }
        if (k <= j) {
            return quickSortSelect(nums, l, j, k);
        } else {
            return quickSortSelect(nums, j + 1, r, k);
        }
    }
    
    /**
     * 1组成的最大的面积
     * <p>
     *     特点是：
     *     左右搜索，最长的长度。Square = Min(len, width)^2
     *     广度搜索算法
     * </p>
     * 
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix == null) {
            return 0;
        }
        int l = matrix.length;
        int w = matrix[0].length;
        int sq = 0;
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < w; j++) {
                int LENGTH = 0, WIDTH = 0;
                if (matrix[i][j] == '1') {
                    dfs(matrix, LENGTH, WIDTH, i, j);
                }
                // 比较最大值。
            }
        }
        return sq;
    }

    private void dfs(char[][] matrix, int length, int width, int wi, int li) {
        if (matrix[wi][li] == '0' || matrix[++wi][li] == '0'
                ||matrix[wi][++li] == '0') {
            return;
        }
        int l = matrix.length;
        int w = matrix[0].length;
        // 什么时候，触发呢？
    }

    public int maximalSquareSolution(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        int maxSquare = maxSide * maxSide;
        return maxSquare;
    }

    /**
     * 选择排序：每次都选择最小的数据。
     *
     * @param a
     */
    public void chooseSort(int[] a) {
        int n = a.length;
        if (n <= 1) {
            return;
        }
        // 只要n-1次比较就好。
        for (int i = 0; i < n -1; i++) {
            int minPos = i;
            for (int j = i; j < n; j++) {
                if (a[j] < a[minPos]) {
                    minPos = j;
                }
            }
            int temp = a[i];
            a[i] = a[minPos];
            a[minPos] = temp;
        }
    }

    /**
     * 主要是要一个分支算法。
     *
     * @param nums
     * @return
     */
    public int reversePairsSolution(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // 执行归并排序
        sort(nums);
        return count;
    }

    private int[] temp;

    public void sort(int[] nums) {
        temp = new int[nums.length];
        sort(nums, 0, nums.length - 1);
    }

    // 归并排序
    private void sort(int[] nums, int lo, int hi) {
        if (lo == hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(nums, lo, mid);
        sort(nums, mid + 1, hi);
        merge(nums, lo, mid, hi);
    }

    // 记录「翻转对」的个数
    private int count = 0;

    private void merge(int[] nums, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            temp[i] = nums[i];
        }

        // 进行效率优化，维护左闭右开区间 [mid+1, end) 中的元素乘 2 小于 nums[i]
        // 为什么 end 是开区间？因为这样的话可以保证初始区间 [mid+1, mid+1) 是一个空区间
        int end = mid + 1;
        for (int i = lo; i <= mid; i++) {
            // nums 中的元素可能较大，乘 2 可能溢出，所以转化成 long
            while (end <= hi && nums[i] > nums[end]) {
                end++;
            }
            count += end - (mid + 1);
        }

        // 数组双指针技巧，合并两个有序数组
        int i = lo, j = mid + 1;
        for (int p = lo; p <= hi; p++) {
            if (i == mid + 1) {
                nums[p] = temp[j++];
            } else if (j == hi + 1) {
                nums[p] = temp[i++];
            } else if (temp[i] > temp[j]) {
                nums[p] = temp[j++];
            } else {
                nums[p] = temp[i++];
            }
        }
    }


    public int reversePairsMe(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // 执行归并排序
        sort1(nums);
        return count;
    }

    int[] storage = null;
    public void sort1(int[] nums) {
        storage = nums;

        int left = 0, right = nums.length, mid = left +  (right - left )/2;
    }

    public void mergeByMe(int[] nums, int l, int r, int mid) {
        // 计数逆序对的个数
        int end = mid + 1;
        for (int i = l; i <= mid; i++) {
            // nums 中的元素可能较大，乘 2 可能溢出，所以转化成 long
            while (end <= r && nums[i] > nums[end]) {
                end++;
            }
            count += end - (mid + 1);
        }
        // 数组双指针技巧，合并两个有序数组
        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i == mid + 1) {
                nums[k] = temp[j++];
            } else if (j == r+1) {
                nums[k] = temp[i++];
            } else if(temp[i] > temp[j]) {
                nums[k] = nums[j++];
            } else  {
                nums[k] =  nums[i++];
            }
        }
    }

}
