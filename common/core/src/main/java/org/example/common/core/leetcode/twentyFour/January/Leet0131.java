package org.example.common.core.leetcode.twentyFour.January;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class Leet0131 {
    @Test
    public void main() {

    }

    /**
     *
     *
     * @param a
     * @param group
     * @param len
     * @param m
     * @param groupSize
     * @param groupId
     * @param curSize
     * @return
     */
    public static boolean isShare(int[] a, int[] group, int len, int m, int groupSize, int groupId, int curSize) {
        // 每个分组的各个值为sum/m ==>
        if (curSize == 0) {
            // 对groupId的值进行一个赋值。还有的情况是什么？
            groupId++;
            curSize = groupSize;
            log.info("m的值是：{}", m);
            if (groupId == m + 1) {
                return true;
            }
        }
        for (int i = 0; i < len; ++i) {
//            log.info("group[i]的值是：{}", group[i]);
            if (group[i] != 0) {
                continue;
            }
            log.info("groupId:{}", groupId);
            group[i] = groupId;
            if (isShare(a, group, len, m, groupSize, groupId, curSize - a[i])) {
                return true;
            }
            group[i] = 0;
        }
        return false;
    }

    public static int maxShare(int[] a, int len) {
        int sum = getArraySum(a, len);
        int[] group = new int[len];

        for (int m = len; m >= 2; --m) {
            if (sum % m != 0) {
                continue;
            }
            for (int i = 0; i < len; ++i) {
                group[i] = 0;
            }
            if (isShare(a, group, len, m, sum / m, 1, sum / m)) {
                System.out.println("分组情况：");
                for (int i = 1; i <= m; ++i) {
                    for (int j = 0; j < len; j++) {
                        if (i == group[j]) {
                            System.out.print(a[j] + " ");
                        }
                    }
                    System.out.println();
                }
                return m;
            }
        }
        return 1;
    }

    public static int getArraySum(int[] arr, int len) {
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += arr[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] array = {2, 4, 1, 3, 5};
        int maxLength = maxShare(array, array.length);
        System.out.println("Max length: " + maxLength);
    }

    /**
     * 其中 diff[i] 等于前缀 nums[0, ..., i] 中不同元素的数目
     * 减去 后缀 nums[i + 1, ..., n - 1] 中不同元素的数目。
     *<p>
     *     [1 2 3 4 5]  ==>
     *     1 - 4 = -3;
     *     2 - 3 = -1;
     *</p>
     * @param nums
     * @return
     */
    public int[] distinctDifferenceArray(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<Integer>();
        int[] sufCnt = new int[n + 1];
        for (int i = n - 1; i > 0; i--) {
            set.add(nums[i]);
            sufCnt[i] = set.size();
        }
        int[] res = new int[n];
        set.clear();
        for (int i = 0; i < n; i++) {
            set.add(nums[i]);
            res[i] = set.size() - sufCnt[i + 1];
        }
        return res;
    }
}
