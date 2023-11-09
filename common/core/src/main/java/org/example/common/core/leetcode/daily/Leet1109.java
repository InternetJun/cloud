package org.example.common.core.leetcode.daily;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/8 10:44
 */
@Slf4j
public class Leet1109 {
    /**
     * 在 Java 中，队列接口提供了多种方法，例如 add、offer、put 等，它们在处理元素时的阻塞行为是不同的。下面是这些方法的主要特点以及一个用于记住它们的方法：
     * add 方法：
     * add 方法用于将元素添加到队列，如果队列已满，则会抛出异常。
     * 阻塞：add 不会阻塞，而是在队列已满时立即抛出 IllegalStateException 异常。
     * 记忆方法："Add and explode" - add 添加元素，如果队列已满，会引发异常，就像一颗定时炸弹。
     * offer 方法：
     * offer 方法用于将元素添加到队列，如果队列已满，则返回 false。
     * 阻塞：offer 不会阻塞，而是在队列已满时返回 false。
     * 记忆方法："Offer and be polite" - offer 提供元素，如果队列已满，会礼貌地返回 false。
     * put 方法：
     * put 方法用于将元素添加到队列，如果队列已满，则会一直等待，直到有空间可用。
     * 阻塞：put 会阻塞，直到队列有足够的空间来容纳元素。
     * 记忆方法："Put and patiently wait" - put 放入元素，然后耐心等待，直到有足够的空间。
     *
     */

//    public boolean offer(E e) {
//        if (null == e) {
//            throw new NullPointerException();
//        } else {
//            long mask = this.mask;
//            long producerLimit = this.lvProducerLimit(); // 获取⽣产者索引最⼤限制
//            long pIndex;
//            long offset;
//            do {
//                pIndex = this.lvProducerIndex(); // 获取⽣产者索引
//                if (pIndex >= producerLimit) {
//                    offset = this.lvConsumerIndex(); // 获取消费者索引
//                    producerLimit = offset + mask + 1L;
//                    if (pIndex >= producerLimit) {
//                        return false; // 队列已满
//                    }
//                    this.soProducerLimit(producerLimit); // 更新 producerLimit
//                }
//            } while(!this.casProducerIndex(pIndex, pIndex + 1L)); // CAS 更新⽣产
//            offset = calcElementOffset(pIndex, mask); // 计算⽣产者索引在数组中下标
//            UnsafeRefArrayAccess.soElement(this.buffer, offset, e); // 向数组中放⼊
//            return true;
//        }
//    }
}
