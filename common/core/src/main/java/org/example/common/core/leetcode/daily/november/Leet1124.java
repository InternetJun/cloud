package org.example.common.core.leetcode.daily.november;

import lombok.extern.slf4j.Slf4j;
import org.example.common.core.util.CommonUtil;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/24 10:29
 */
@Slf4j
public class Leet1124 {
    public int countPairs(List<Integer> nums, int target) {
        Map<Integer, List<int[]>> map = new TreeMap();
        for (int i = 0; i < nums.size(); i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                final List<int[]> aDefault = map.getOrDefault(nums.get(i) + nums.get(j), new ArrayList<>());
                aDefault.add(new int[]{i, j});
                map.put(nums.get(i) + nums.get(j), aDefault);
            }
        }
        // 进行一个二分查找。
        Set<Integer> integers = map.keySet();
        final int[] search = search(integers, target);
        // 在map中进行下标的搜索。最小的值为nums.get(index);
        int count = 0;
        final ArrayList<int[]> ints = new ArrayList<>();
        for (int i : search) {
            count += map.get(i).size();
            ints.addAll(map.get(i));
        }
        CommonUtil.formatAndDisplayArray(ints);
        return count;
    }

    /**
     * 二分查找数据
     *
     * @param nums
     * @param target
     */
    public int[] search(Set<Integer> nums, int target) {
        int[] array = new int[nums.size()];
        int index = 0;
        for (Integer num : nums) {
            array[index++] = num;
        }
        int left = 0, right = nums.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        // 返回 nums[0, left的元素是最好的]
        int[] result = new int[left];
        for (int i = 0; i < left; i++) {
            result[i] = array[i];
        }
        return result;
    }

    @Test
    public void main() {
        //     -6,2,5,-2,-7,-1,3
        final List<Integer> list = Arrays.asList(-1,1,2,3,1);
        int target = 2;
        System.out.println(countPairs(list, target));
    }

    public int countPairsSolution(List<Integer> nums, int target) {
        Collections.sort(nums);
        int res = 0;
        for (int i = 1; i < nums.size(); i++) {
            int k = binarySearch(nums, 0, i - 1, target - nums.get(i));
            res += k;
        }
        return res;
    }

    public int binarySearch(List<Integer> nums, int lo, int hi, int target) {
        int res = hi + 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums.get(mid) >= target) {
                res = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return res;
    }
}
