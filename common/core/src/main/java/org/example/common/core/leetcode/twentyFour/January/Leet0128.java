package org.example.common.core.leetcode.twentyFour.January;

import com.sun.xml.internal.messaging.saaj.util.CharWriter;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description:
 * @time: 2024/1/28 10:03
 */
public class Leet0128 {
    /**
     * 2个水壶的容量都达到target的容量
     * 只有3个操作：1，装满；2，清空；3，互相转移。
     * <p>
     * 装满任意一个水壶
     * 清空任意一个水壶
     * 从一个水壶向另外一个水壶倒水，直到装满或者倒空
     *
     * @param x
     * @param y
     * @param z
     * @return
     */
    public boolean canMeasureWater(int x, int y, int z) {

        final LinkedList<int[]> stack = new LinkedList<>();
        stack.push(new int[]{0, 0});
        final HashSet<Long> seen = new HashSet<>();
        while (!stack.isEmpty()) {
            if (seen.contains(hash(stack.peek()))) {
                stack.pop();
                continue;
            }
            seen.add(hash(stack.peek()));
            int[] state = stack.pop();
            int remain_x = state[0], remain_y = state[1];
            // 有一个是可以满足就可以了。
            if (remain_x == z || remain_y == z ||
            remain_y+remain_x == z) {
                return true;
            }
            // 把 X 壶灌满。
            stack.push(new int[]{x, remain_y});
            // 把 Y 壶灌满。
            stack.push(new int[]{remain_x, y});
            // 把 X 壶倒空。
            stack.push(new int[]{0, remain_y});
            // 把 Y 壶倒空。
            stack.push(new int[]{remain_x, 0});
            // 把 X 壶的水灌进 Y 壶，直至灌满或倒空。
            stack.push(new int[]{remain_x - Math.min(remain_x, y - remain_y), remain_y + Math.min(remain_x, y - remain_y)});
            // 把 Y 壶的水灌进 X 壶，直至灌满或倒空。
            stack.push(new int[]{remain_x + Math.min(remain_y, x - remain_x), remain_y - Math.min(remain_y, x - remain_x)});
        }
        return false;
    }

    public long hash(int[] state) {
        return (long) state[0] * 10001 + state[1];
    }
}
