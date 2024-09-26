package org.example.common.core.leetcode.twentyFour.January;

import lombok.extern.slf4j.Slf4j;
import org.example.common.core.leetcode.link.ListNode;
import org.junit.Test;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description:
 * @time: 2024/1/6 13:54
 */
@Slf4j
public class Leet0106 {
    /**
     * 插入公约数
     *
     * @param head
     * @return
     */
    public ListNode insert(ListNode head) {
        ListNode node = head;
        while (node.next != null) {
            node.next = new ListNode(gcd(node.val, node.next.val), node.next);
            node = node.next.next;
        }
        return head;
    }

    public int gcd(int num1, int num2) {
        while (num2 != 0) {
            log.info("num1:{}, num2:{}", num1, num2);
            int tmp = num1 % num2;
            num1 = num2;
            num2 = tmp;
        }
        return num1;
    }

    @Test
    public void test(){
        System.out.println(gcd(6, 10));
    }
}
