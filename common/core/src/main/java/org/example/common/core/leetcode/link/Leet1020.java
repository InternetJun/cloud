package org.example.common.core.leetcode.link;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/10/20 15:05
 */
@Slf4j
public class Leet1020 {
    /**
     * 全部删除的情况。
     *
     * @param head
     * @return
     */
    public ListNode deleteDup(ListNode head) {
        final ListNode dummy = new ListNode(-1);
        dummy.next = head;
        /**这里是从虚拟的节点开始。
         * */
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int val = cur.next.val;
                while (cur.next != null && val == cur.next.val) {
                    // 没有删除掉自己
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    /**
     * 保留一个重复的元素。
     *
     * @param head
     * @return
     */
    public ListNode deleteRemainOne(ListNode head) {
        final ListNode dummy = new ListNode(-1);
        dummy.next = head;
        /**
         * 这里的是从真实节点开始的。
         * */
        ListNode cur = head;
        while (cur != null) {
            int val = cur.val;
            while (cur.next != null && val == cur.next.val) {
                cur.next = cur.next.next;
                // 没有删除掉自己
            }
            cur = cur.next;
        }


        return dummy.next;
    }

    /**
     * 这时要求有要是有多个（大于2个的时候，要求保留2个重复元素呢？）
     */

    @Test
    public void main() {
        final SumLinkList sumLinkList = new SumLinkList();
        final ListNode listNode = sumLinkList.buildLinkedList(new int[]{1, 1, 1, 2, 2, 3, 4, 5, 5});
        // 1 2 2 3 4 5
        final ListNode listNode1 = deleteRemainOne(listNode);
//        final ListNode listNode1 = sumLinkList.deleteDuplicates(listNode);
        log.info("{}", listNode1);
    }


    public ListNode rotateN() {
        return null;
    }

    public static void main(String[] args) {
        final ArrayList<Integer> list = new ArrayList<>();
        final Integer item = new Integer(1);
        list.remove(item);

    }

    @Test
    public void testDu() {
        int[] nums = {0,0,1,1,1,1,2,3,3};
        System.out.println(removeDuplicatesSolution(nums));
    }

    /**
     * 对于数组来说，要求保留2个元素，不要多留。
     *
     * @param nums
     * @return
     */
    public int removeDuplicatesSolution(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }
        int slow = 2, fast = 2;
        while (fast < n) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }
}
