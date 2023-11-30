package org.example.common.core.leetcode.daily.november;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.PriorityQueue;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 现有一个包含所有正整数的集合 [1, 2, 3, 4, 5, ...].每次都移开最小的值。
 * @time: 2023/11/25 10:12
 */
@Slf4j
public class Leet1129 {
    class SmallestInfiniteSet {
        // 0~ 1000的递增序列。
        private int minNum;
        /**
         * 记录添加的值，用什么数据结构呢？
         * */
        private PriorityQueue<Integer> queue;

        public SmallestInfiniteSet() {
            minNum = 1;
            queue = new PriorityQueue();
        }

        public int popSmallest() {
            // 比较最小的值和插入的值；
            int res;
            if (!queue.isEmpty()) {
                res = queue.poll();
            } else {
                res = minNum;
                minNum++;
            }
            return res;

        }

        /**
         * 对值进行添加。
         * <b>存在的bug：在queue中重复添加相同的元素的时候，会有问题的。所以要考虑完全</b>
         *
         * @param num
         */
        public void addBack(int num) {
            // 不做事
            if (num >= minNum) {
                return;
            } else {
                queue.offer(num);
            }
        }
    }

    @Test
    public void testMin() {
        SmallestInfiniteSet smallestInfiniteSet = new SmallestInfiniteSet();
        smallestInfiniteSet.addBack(2);
        System.out.println(smallestInfiniteSet.popSmallest());
        System.out.println(smallestInfiniteSet.popSmallest());
        System.out.println(smallestInfiniteSet.popSmallest());

        smallestInfiniteSet.addBack(1);
        System.out.println(smallestInfiniteSet.popSmallest());
        System.out.println(smallestInfiniteSet.popSmallest());
        System.out.println(smallestInfiniteSet.popSmallest());
        log.info("2023.11.29测试完成");
    }



}
