package org.example.common.core.leetcode.daily.october;

import org.example.common.core.leetcode.link.ListNode;
import org.example.common.core.leetcode.link.SumLinkList;
import org.junit.Test;

import java.nio.file.Paths;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/10/24 13:58
 */
public class Leet1024 {
    public String simplifyPath(String path) {
        return Paths.get(path).normalize().toString();
    }

    @Test
    public void main() {
        System.out.println(simplifyPath("/home/"));
        System.out.println(simplifyPath("/../"));
        System.out.println(simplifyPath("/home//foo/"));
//        System.out.println(simplifyPath("/home/"));
    }

    /**
     * 移动k步的作用是什么？
     *
     * @param listNode
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode listNode, int k) {
        ListNode cur = listNode, node = null;
        int n = 0;
        while (cur != null) {
            node = cur;
            cur = cur.next;
            n++;
        }
        int res = n - k % n;
        // 首尾联起来。
        node.next = listNode;
        while (res > 0) {
            node = node.next;
            res--;
        }
        ListNode rotateHead = node.next;
        node.next = null;
        return rotateHead;
    }

    @Test
    public void testLink() {
        final SumLinkList sumLinkList = new SumLinkList();
        final ListNode listNode = sumLinkList.buildLinkedList(new int[]{1, 2, 3, 4, 5});
        System.out.println(rotateRight(listNode, 12));
    }
}
