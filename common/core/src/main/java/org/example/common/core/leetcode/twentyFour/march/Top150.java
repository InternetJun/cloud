package org.example.common.core.leetcode.twentyFour.march;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description:
 * @time: 2024/3/28 11:26
 */
@Slf4j
public class Top150 {
    /**
     * [3 0 6 1 5] ==> 3
     * 原因是有3篇。
     *
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
//        Arrays.sort(citations);
//        int res = 0;
        int left = 0, right = citations.length;
        while (left < right) {
            int mid = (left + right) / 2;
            int cnt = 0;
            for (int i = 0; i < citations.length; i++) {
                if (citations[i] >= mid) {
                    cnt++;
                }
            }
            // 引用多余 h；
            if (cnt >= mid) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    @Test
    public void main() {
        System.out.println(hIndex(new int[]{3, 0, 6, 1, 5}));
    }

    /**
     * 输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
     * 输出: 3
     * 解释:
     * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
     * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
     * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
     * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
     * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
     * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
     * 因此，3 可为起始索引。
     * <h2>有一个环的意思。</h2>
     * <b>基本的规律是什么？有gas[i] + gas[i+1] - cost[i] &gt; 0</b>
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteRoad(int[] gas, int[] cost) {
        int len = gas.length;
        int minSum = 0, sum = 0;
        int start = 0;
        for (int i = 0; i < len; i++) {
            sum += gas[i] - cost[i];
            if (sum < minSum) {
                start = i + 1;
                minSum = sum;
            }
        }
        if (sum < 0) {
            return -1;
        }
        return start == len ? 0 : start;
    }

    @Test
    public void testRoad() {
        int[] gas = {1, 2, 3, 4, 5}, cost = {3, 4, 5, 1, 2};
        System.out.println(canCompleteRoad(gas, cost));
    }

    /**
     * 1/20 1/25
     * 1/10 3/25
     * 这时候会有的情况是什么？
     * x(1/10+3/25) == 有的情况是什么？
     * 就是说追上了效率了。
     * 1 = 1/10 * x + 1/20*y
     * 1 = 3/25 * x + 1/25*y
     * 求解x，y
     * <p>
     * 100 = 10x + 5y 20x = 100 --> x = 5; y = 10
     * 100 = 12x + 4y ==》 y = 2x ==>
     * <p>
     * x y z q
     * 10 -1  + z  + q/2  == q+2
     * z+9 + q/2 = q+2
     * z+7 = q/2
     */

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1;
        int p = nums1.length - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[p] = nums1[i];
                log.info("{}", nums1);
                i--;
            } else {
                nums1[p] = nums2[j];
                log.info("{}", nums1);
                j--;
            }
            p--;
        }
        while (j >= 0) {
            nums1[p] = nums2[j];
            j--;
            p--;
        }
    }

    @Test
    public void test() {
        /// nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        merge(nums1, 3, nums2, 3);
    }

    /**
     * 原地修改对相等于val的值移除。
     * <p>
     *     对值后开始。返回值。
     * </p>
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int fast = 0, slow = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        log.info("{}", nums);
        return slow;
    }

    @Test
    public void testRemove() {
        System.out.println(removeElement(new int[]{2, 2, 3, 3}, 3));
    }
}
