package org.example.common.core.leetcode.twentyFour.January;

import lombok.extern.slf4j.Slf4j;
import org.example.common.core.leetcode.link.ListNode;
import org.example.common.core.util.CommonUtil;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description: 链表的移除
 * @time: 2024/1/3 14:27
 */
@Slf4j
public class Leet0103 {
    /**
     * 对链表进行移除和处理。
     * 只要找出第一个比他大的节点，就进行一个重组。
     * 【 1 1 1 1】没有移除的
     * 【5 2 13 3 8】要移除的是5 2 3
     * @param head
     * @return
     */
    public ListNode removeNodesMe(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode dummyPoint = dummy;
//        dummy.next = head;
        ListNode cur = head;
        // 要有移除的结果。
        while (cur != null) {
            ListNode compare = cur.next;
            while (compare != null && compare.val < cur.val) {
                compare = compare.next;
            }
            ListNode nextCur = compare.next;
            if (compare != null && compare.val >= cur.val) {
                compare.next = null;
                if (cur.val == compare.val) {
                    // 从cur开始进行处理。
                    dummyPoint.next = cur;
                    dummyPoint = dummyPoint.next;
                }
                dummyPoint.next = compare;
                // 这句话会把dummy处理不好的。
                dummyPoint = dummyPoint.next;
            }
            cur = nextCur;
        }
        // 获取到所有的节点。
        return dummy.next;
    }

    public ListNode removeNodes(ListNode head) {
        if (head == null) {
            return null;
        }
        head.next = removeNodes(head.next);
        // 对值进行判断，要是大于的化就是
        if (head.next != null && head.val < head.next.val) {
            return head.next;
        } else {
            return head;
        }
    }

    public ListNode removeNodesII(ListNode head) {
        Deque<ListNode> stack = new ArrayDeque<ListNode>();
        for (; head != null; head = head.next) {
            stack.push(head);
        }
        for (; !stack.isEmpty(); stack.pop()) {
            if (head == null || stack.peek().val >= head.val) {
                stack.peek().next = head;
                head = stack.peek();
            }
        }
        return head;
    }

    @Test
    public void test() {
        final ListNode listNode1 = CommonUtil.buildLinkedList(new int[]{5, 2, 13, 3, 8});
        final ListNode listNode = CommonUtil.buildLinkedList(new int[]{1,1,1,1});
        final ListNode res = removeNodesII(listNode);
        log.info("{}", res);
    }

    /**
     * 对值进行覆盖，
     * 【0 0 0】要选择第1列。
     * 【1 0 1】
     * 【0 1 1】
     * 【0 0 1】
     *
     * <p>
     *     贪心算法。1 2 3 count = 2 2 1;
     *     要的选择策略是什么？ 列最多覆盖的值？
     * </p>
     * @param matrix
     * @param numSelect
     * @return
     */
    public int maximumRowsMe(int[][] matrix, int numSelect) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (n == 1) {
            return m;
        }
        int res = Integer.MIN_VALUE;
        final HashMap map = new HashMap<Integer, Integer>();

        for (int i = 0; i < n; i++) {
//            final ArrayList<Integer> list = new ArrayList<>();
            int count = 0;
            for (int j = 0; j < m; j++) {
                if (matrix[j][i] == 1) {
                    count++;
                }
            }
            if (count > 0) {
                map.put(i, count);
            }
        }
        while (numSelect > 0) {

            numSelect--;
        }
        return res;
    }

    /**
     * 二进制进行比较处理。
     * @param matrix
     * @param numSelect
     * @return
     */
    public int maximumRows(int[][] matrix, int numSelect) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] mask = new int[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++){
                // 每一行的二进制。
                mask[i] += matrix[i][j] << (n - j - 1);
            }
        }
        int res = 0;
        int cur = 0;
        // 最大
        int limit = (1 << n);
        while (++cur < limit) {
            // cur的作用是什么？遍历2^n.
            if (Integer.bitCount(cur) != numSelect) {
                continue;
            }
            int t = 0;
            for (int j = 0; j < m; j++) {
                // 行中有多少被选中。
                if ((mask[j] & cur) == mask[j]) {
                    ++t;
                }
            }
            res = Math.max(res, t);
        }
        return res;
    }
}
