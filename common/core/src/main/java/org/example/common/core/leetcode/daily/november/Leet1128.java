package org.example.common.core.leetcode.daily.november;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/25 10:12
 */
@Slf4j
public class Leet1128 {
//    /**
//     * 长度
//     * */
//    private int index;
//
//    /**
//     * 双端的队列
//     * */
//    private LinkedList<Integer> list;
//
//    /**
//     * 队列的长度
//     * */
//    private int len;
//
//    public Leet1128() {
//        index = 0;
//        list = new LinkedList<Integer>();
//        len = 0;
//    }
//
//    /**
//     * 向中间插入值
//     *
//     * @param val
//     */
//    public void pushMiddle(int val) {
//        int mid = len / 2;
//        list.add(mid, val);
//        len++;
//    }
//
//    public void pushBack(int val) {
//        list.addLast(val);
//        len = list.size();
//    }
//
//    public int popFront() {
//
//    }
//
//    public int popMiddle() {
//
//    }
//
//    public int popBack() {
//
//    }
}

/**
 *
 * 因为有中间的位置插入元素，
 * 用2个队列实现。
 */
class FrontMiddleBackQueue {
    Deque left = new ArrayDeque<Integer>();
    Deque right = new ArrayDeque<Integer>();

    public void pushFront(int val) {
        left.addFirst(val);
        update();
    }

    public void pushMiddle(int val) {
        left.add(val);
        update();
    }

    /**
     * 返回中间的值 ==> 有
     * 【1 4 3 2】
     * left 4
     * 【4 3 2】
     * right  3
     * @return
     */
    public int popMiddle() {
        if (left.size() + right.size() == 0) {
            return -1;
        }
        int ans = (int) (left.size() == right.size() ? left.pollLast() : right.pollFirst());
        update();
        return ans;
    }

    /**
     * 保持left、right是处于一个平衡的状态。
     * right 总是比left多一个元素。
     *
     * add --> add and  explode 抛出异常
     * offer ==> offer and polite 返回false
     * put ==》 put and patiently wait 直到有空间为止
     */
    public void update() {
//        if (right.size() >= left.size()) {
//
//        }
        while (left.size() > right.size()) {
            right.addFirst(left.pollLast());
        }
        // 相等的情况了。
        while (right.size() - left.size() > 1) {
            left.addLast(right.pollFirst());
        }
    }
}
