package org.example.common.core.leetcode.twentyFour.febuary;

import com.alibaba.fastjson.JSONObject;
import org.example.common.core.leetcode.link.ListNode;
import org.example.common.core.util.CommonUtil;
import org.junit.Test;

import java.util.*;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description:
 * @time: 2024/2/21 21:29
 */
public class Hot100 {
    /**
     * t:79
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        //    char[] words = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (search(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean search(char[][] board, String word,
                          int row, int col, int ind) {
        int m = board.length;
        int n = board[0].length;
        if (row >= m || row < 0
                || col >= n || col < 0 || board[row][col] != word.charAt(ind)) {
            return false;
        }
        if (ind == word.length() - 1) {
            return true;
        }
        board[row][col] = '\0';
        boolean res = search(board, word, row + 1, col, ind + 1)
                || search(board, word, row - 1, col, ind + 1)
                || search(board, word, row, col - 1, ind + 1)
                || search(board, word, row, col + 1, ind + 1);
        board[row][col] = word.charAt(ind);
        return res;
    }

    @Test
    public void test76() {
        final char[][] chars = JSONObject.parseObject("[[\"A\",\"B\",\"C\",\"E\"],[\"S\",\"F\",\"C\",\"S\"],[\"A\",\"D\",\"E\",\"E\"]]", char[][].class);
        String word = "ABCCED";
        System.out.println(exist(chars, word));
    }

    /**
     * 最长的公共序列。
     * dp_ij 代表了转移的东西。要怎么处理呢？
     * 初始的方程是什么呢？
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        final int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 在数组中获取最长的递增序列长度。
     *
     * dp的定义是 以i的最长。
     * <p>
     *     4(1 2 3)
     * </p>
     *
     * 初始条件dp = 1；
     * @param nums
     * @return
     */
    public int longestConsecutiveMe(int[] nums) {
//        int len = nums.length;
//        final int[] dp = new int[len];
//        Arrays.fill(dp, 1);
//        // [100,4,200,1,3,2]
//        return Arrays.stream(dp).max().getAsInt(); 要用的是队列。
        /**
         * 100 4 200 1 3 2;
         */
        return 0;
    }

    public int longestConsecutive(int[] nums) {
        // 转化成哈希集合，方便快速查找是否存在某个元素
        HashSet<Integer> set = new HashSet<Integer>();
        for (int num : nums) {
            set.add(num);
        }

        int res = 0;

        for (int num : set) {
            if (set.contains(num - 1)) {
                // num 不是连续子序列的第一个，跳过
                continue;
            }
            // num 是连续子序列的第一个，开始向上计算连续子序列的长度
            int curNum = num;
            int curLen = 1;

            while (set.contains(curNum + 1)) {
                curNum += 1;
                curLen += 1;
            }
            // 更新最长连续序列的长度
            res = Math.max(res, curLen);
        }

        return res;
    }

    /**
     *
     * 获取最长的无重复的字符长度。
     *
     * @param s
     * @return
     */
    public String repeatString(String s) {
        /**
         * leftIndex = -1；
         * if(right - left > maxLen) {
         *     leftIndex = left;
         * }
         * s.substring();
         */
        return "";
    }

    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        final ArrayList<int[]> list = new ArrayList();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 记录下坐标。
                if (matrix[i][j] == 0) {
                    list.add(new int[]{i, j});
                }
            }
        }
        // 00 02;
        list.sort((Comparator.comparingInt(o -> o[0])));
        int preRow = -1, preCol = -1;
    }

    /**
     * 排序列表
     * 这是错的。解答要用到的是归并排序和二分的。
     *
     * @param head
     * @return
     */
    public ListNode sortListMe(ListNode head) {
        // 1用的记录的方式；
        // 2移动节点的方式呢？
        final ListNode dummy = new ListNode(-1);
        ListNode ind = dummy;
        ListNode cur = head;
        while (cur != null) {
            if (ind.next == null) {
                ind.next = cur;
                ind = ind.next;
            } else {
                // 比较值的大小需要有链表的指向变化。 -1 -> 4;
                /**
                 * 2 === -1 -> 2 -> 4
                 */
                int val = cur.val;

            }
            cur = cur.next;
        }
        return dummy.next;
    }

    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    public ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        ListNode sorted = merge(list1, list2);
        return sorted;
    }

    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }

    /**
     * 两两的节点交换。有什么的需求？
     *
     * @param head
     * @return
     */
    public ListNode swapPairsMe(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        final ListNode dummy = new ListNode(-1);
        dummy.next = head;
        if (head != null && head.next != null) {
            ListNode next = head.next;
            head.next.next = head;
            head.next = next;
        }

        //后续的2个节点。需要进行一个连接。
        ListNode node = swapPairsMe(head.next.next);
        // 用哪个节点吧后续的连起来呢？
        ListNode connect = head.next;
        connect.next = node;
        return dummy.next;
    }

    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }

    @Test
    public void testSwap() {
        final ListNode node = CommonUtil.buildLinkedList(new int[]{1, 2, 3, 4, 5});
        System.out.println(swapPairs(node));
    }
}
