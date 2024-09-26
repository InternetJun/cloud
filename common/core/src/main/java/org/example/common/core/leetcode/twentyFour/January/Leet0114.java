package org.example.common.core.leetcode.twentyFour.January;

import lombok.extern.slf4j.Slf4j;
import org.example.common.core.leetcode.link.ListNode;
import org.example.common.core.util.CommonUtil;
import org.junit.Test;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description:
 * @time: 2024/1/14 14:18
 */
@Slf4j
public class Leet0114 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy.next;
        while(cur != null){
            int val = cur.val;
            if (cur.next != null && cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    @Test
    public void testLink() {
        ListNode head = CommonUtil.buildLinkedList(new int[]{1, 1, 2});
        final ListNode node = deleteDuplicates(head);
        log.info("{}", node);
    }

    /**
     * 移除相同的元素
     *
     * @param head
     * @return
     */
    public ListNode removeDuplicateII(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            // 相同的元素都去除；
            if (cur.next.val == cur.next.next.val) {
                int val = cur.next.val;
                while (cur.next != null && cur.next.val == val) {
                    cur.next = cur.next.next;
                }
            } else {
                // 1-->2; cur.next = 2 cur = 1;是保留了cur的。所以要去除cur的。
//            pre = cur;
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    @Test
    public void testDuplicateII() {
        final ListNode node = CommonUtil.buildLinkedList(new int[]{1, 1, 2, 3, 3});
        log.info("{}", removeDuplicateII(node));
    }
}
