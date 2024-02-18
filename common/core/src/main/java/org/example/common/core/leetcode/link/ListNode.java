package org.example.common.core.leetcode.link;

import lombok.Data;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/6/5 21:42
 */
@Data
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(Integer val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
