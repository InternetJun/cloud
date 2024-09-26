package org.example.common.core.leetcode.twentyFour.January;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

@Slf4j
public class Leet0130 {

    /**
     * 将 nums[i] 替换成 nums[i] ，<b>nums[(i - 1 + n) % n] 或者 nums[(i + 1) % n] 三者之一</b>。
     * 会全部的替换
     * <p>
     *     可以理解成仅能双向发散的光源，在有限空间中完成扩散需要的时间（速度为每秒一个索引），
     *     对于多个光源（相同数），扩散完成的时间取决于相隔最远（水桶效应）的两个光源双向奔赴的时间（最大距离除以二）。
     *     用索引相减计算出的距离实际上比相隔元素数多一，所以最终花费时间还要向下取整，如果用相隔元素数量表示距离，那时间就是向上取整。
     * </p>
     *
     * @param nums
     * @return
     */
    public int minimumSeconds(List<Integer> nums) {
        //特殊情况；5555
//        int count = nums.get(0);
//        for (int i = 1; i < nums.size(); i++) {
//            int num = nums.get(i);
//            count = num ^ count;
//        }
//        if (count == 0) {
//            return 0;
//        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        int n = nums.size(), res = n;
        for (int i = 0; i < n; i++) {
            int finalI = i;
            map.computeIfAbsent(nums.get(i), k->new ArrayList<>()).add(finalI);
        }
        for (List<Integer> positions : map.values()) {
            //mp[x] 表示 x 所出现的位置
            int mx = positions.get(0) + n - positions.size() - 1;
            for (int i = 1; i < positions.size(); i++) {
                mx = Math.max(mx, positions.get(i) - positions.get(i-1));
            }
            res = Math.min(res, mx/2);
        }
        // 继续处理
        return res;
    }

    @Test
    public void test() {
        log.info("========0130测试开始============");
        List<Integer> list = Arrays.asList(1, 1, 1, 1);
//        List<Integer> list = Arrays.asList(1, 2, 1, 2);
        /*
        *[nums[3],nums[1],nums[3],nums[3]] 。变化后，nums = [2,2,2,2]
        *这就是最小的秒数1秒
        */
        int i = minimumSeconds(list);
        System.out.println(i);
        log.info("========0130测试结束============");
    }
}
