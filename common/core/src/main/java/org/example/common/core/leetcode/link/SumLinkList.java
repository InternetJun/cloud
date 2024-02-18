package org.example.common.core.leetcode.link;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Test;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 实现链表的值相加
 * @time: 2023/6/5 21:41
 */
@Slf4j
public class SumLinkList {
    /**
     * 实现值的相加， 生成对应的链表
     * <p>
     * 9 -- 3 -- 7
     * <br></br>
     * 6 -- 3
     * <br>test 换行</br>
     * <br>1 -- 0 -- 0 -- 0</br>
     *
     *
     * </p>
     * <p>
     * 思路：首先要做的是REVERSE_ORDER，然后进行一个处理，有进位和移动？
     *
     * </p>
     *
     * @param node1
     * @param node2
     * @return
     */
    public ListNode getSum(ListNode node1, ListNode node2) {
        if (ObjectUtil.isEmpty(node1) && ObjectUtil.isEmpty(node2)) {
            return null;
        }
        if (ObjectUtil.isEmpty(node1)) {
            return node2;
        }
        if (ObjectUtil.isEmpty(node2)) {
            return node1;
        }
        // 实现值的相加；
        ListNode reverseNode1 = reverse(node1);
        ListNode reverseNode2 = reverse(node2);
        int step = 1;
        int plus = 0;
        // 有一个到底了，就退出。
        while (reverseNode1.next == null || reverseNode2.next == null) {
            Integer sum = reverseNode1.val + reverseNode2.val;
            if (sum > 10) {
                plus = 1;
            }
            if (plus == 1) {
                // 下次相加进位！
            }
        }

//        LinkList linkList = new LinkList(-1);
//        reverse(linkList, 1);
        return null;
    }

    /**
     * 从第几位开始反转链表
     *
     * @param linkList
     * @param i
     * @return
     */
    private ListNode reverse(ListNode linkList, Integer i) {
        if (ObjectUtil.isEmpty(i)) {
            // 反转全部的链表
        }
        return null;
    }

    /**
     * 标准的反转链表
     * <p>
     * 有 a->(b -> c -> d)
     * <br></br>
     * a --> (d --> c --> b)
     * </p>
     *
     * @param node
     */
    private ListNode reverse(ListNode node) {
        if (ObjectUtil.isEmpty(node.next)) {
            return node;
        }
        ListNode reverse = reverse(node.next);
        node.next.next = node;
        node.next = null;
        return reverse;
    }

    @Test
    public void test() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        ListNode reverse = reverse(head);
        System.out.println(reverse);
    }

    /**
     * 反转整个链表
     * <p>
     * a -> b -> c
     *
     * @param head
     */
    public ListNode reverseByOther(ListNode head) {
        ListNode pre, cur, next;
        pre = null;
        cur = head;
        while (cur != null) {
            next = cur.next;
            // 一定要先连线的。后面在处理。
            cur.next = pre;
            // 原来的值先给出去。
            pre = cur;
            // 在处理自己的事情！！
            cur = next;
        }
        return pre;
    }

    @Test
    public void testR() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        ListNode reverse = reverseByOther(head);
        System.out.println(reverse);
    }

    ListNode reverse(ListNode head, ListNode b) {
        ListNode pre, cur, next;
        pre = null;
        cur = head;
        while (cur != b) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    @Test
    public void testK() {
        int[] ints = {1,2,3,4,5};
        final ListNode head = buildLinkedList(ints);
        final ListNode listNode = reverseKGroup(head, 2);
        log.info("{}", listNode);
    }

    // 每k个进行一个反转；
    public ListNode reverseKGroup1(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        ListNode a, b;
        a = b = head;
        for (int i = 0; i < k; i++) {
            // 没有k的时候，直接返回。
            // 要是也要进行呢？
            if (b.next == null) {
                break;
            }
            // 要是舍弃掉这个后续的元素呢？
//            if (b == null) {
//                return head;
//            }
            b = b.next;
        }

        ListNode newHead = reverse(a, b);
        a.next = reverseKGroup1(b, k);
        return newHead;
    }

    public ListNode partition(ListNode head, int x) {
//        ListNode cur = head, newHead = new ListNode(-1), newPoint = newHead,
//        greaterLink = new ListNode(-1), greatPoint = greaterLink;
//        // 需要记录最后的数据。
//        while (cur != null) {
//            if (cur.val <  x) {
//                newPoint.next = cur;
//                newPoint = newPoint.next;
//            } else {
//                greatPoint.next = cur;
//                greatPoint = greatPoint.next;
//            }
//            cur = cur.next;
//        }
//        ListNode last = newHead.next;
//        while (last.next != null) {
//            last = last.next;
//        }
//        last.next = greaterLink.next;
//        return newHead.next;
        ListNode small = new ListNode(0);
        ListNode smallHead = small;
        ListNode large = new ListNode(0);
        ListNode largeHead = large;
        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        large.next = null;
        small.next = largeHead.next;
        return smallHead.next;
    }

    @Test
    public void testPa() {
        final ListNode listNode = buildLinkedList(new int[]{1, 4, 3, 2, 5, 2});
        System.out.println(partition(listNode, 3));
    }

    @Test
    public void testGroup() {
        int[] arr = {1, 2, 3, 4, 5};
        ListNode linkList = buildLinkedList(arr);
        log.info("{}", linkList);
    }

    public ListNode buildLinkedList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        for (int value : arr) {
            current.next = new ListNode(value);
            current = current.next;
        }

        return dummy.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = l + (r - l) / 2;
        return mergeTwoLinkList(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    public ListNode mergeTwoLinkList(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        // 指针和一个链表的元素一定要搞清楚啊。
        ListNode tail = head, aPtr = a, bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
    }

    @Test
    public void testMerge() {
        ListNode head = buildLinkedList(new int[]{1, 2, 3, 3, 4, 4, 5});
        ListNode head2 = buildLinkedList(new int[]{1, 1, 3, 4});
        ListNode linkList = mergeTwoLinkList(head, head2);
        log.info("{}", linkList);

        int[][] ints = {{1, 4, 5}, {1, 3, 4}, {2, 6}};
        ListNode[] list = new ListNode[3];
        for (int[] anInt : ints) {

        }
    }


    @Test
    public void a() {
        ListNode head = buildLinkedList(new int[]{1, 1, 2, 3, 3});
//        LinkList linkList = deleteDuplicates(head);
        ListNode linkListI = deleteDuplicatesI(head);
        log.info("{}", linkListI);
    }

    /**
     * 保留一个元素。
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicatesI(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy.next;
        while (cur.next != null && cur != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }

        return dummy.next;
    }

    public ListNode reverseByPointer(ListNode head) {
        ListNode pre, cur, next;
        pre = null;
        cur = head;
        while (cur != null) {

        }
        return pre;
    }

    ListNode successor = null;

    /**
     * 需要有后续的接点
     *
     * @param b
     * @param head
     * @return
     */
    public ListNode reverseMe(int b, ListNode head) {
        if (b == 1) {
            successor = head.next;
            return head;
        }
        ListNode reverse = reverseMe(b - 1, head.next);
        head.next.next = head;
        head.next = successor;
        return reverse;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        // base case
        if (left == 1) {
            return reverseMe(right, head);
        }
        // 前进到反转的起点触发 base case
        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
    }

    ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            // 记录第 n + 1 个节点
            successor = head.next;
            return head;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = reverseN(head.next, n - 1);

        head.next.next = head;
        // 让反转之后的 head 节点和后面的节点连起来
        head.next = successor;
        return last;

    }

    @Test
    public void testRMe() {
        ListNode linkList = buildLinkedList(new int[]{1, 2, 3, 4, 5});
        ListNode reverse = reverseBetween(linkList, 2, 4);
        log.info("{}", reverse);
    }

    /**
     * k个一组进行翻转。
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) {
            return head;
        }
        if (head == null) {
            return null;
        }
        // 区间 [a, b) 包含 k 个待反转元素
        ListNode a, b;
        a = b = head;
        for (int i = 0; i < k; i++) {
            // 不足 k 个，不需要反转，base case
            if (b == null) {
                return head;
            }
            b = b.next;
        }
        // 反转前 k 个元素
        ListNode newHead = reverse(a, b);
        // 递归反转后续链表并连接起来
        a.next = reverseKGroup(b, k);

        return newHead;
    }

    /**
     * 翻转a~b内的元素
     * 左闭右开
     *
     * @param a
     * @param b
     * @return
     */
    public ListNode reverseII(ListNode a, ListNode b) {
        ListNode pre, cur, next;
        pre = null;
        cur = a;
        while (cur != b) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * 保全自己
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates12(ListNode head) {

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.next != null && cur.next.val == cur.next.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    /**
     * 全部删除
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.next.val == cur.next.val) {
                // 这一步很关键啊。会把自己也删除掉的。
                int x = cur.next.val;
                while (cur.next != null && cur.next.val == x) {
                    cur.next = cur.next.next;
                }
            } else {
                // 要是一直那么写就没有了选择，就是一个简单的指针了
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    @Test
    public void test12() {
//        ListNode listNode = buildLinkedList(new int[]{1,2,3,3,4,4,5,6,6});
        ListNode listNode = buildLinkedList(new int[]{1, 2, 3, 4, 5});
        ListNode rotateRight = rotateRightSolution(listNode, 3);
//        ListNode listNode1 = rightOneStep(listNode);
        log.info("{}", rotateRight);
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }
        ListNode inParam = head;
        ListNode rotate = null;
        for (int i = 0; i < k; i++) {
            rotate = rightOneStep(inParam);
            inParam = rotate;
        }
        return rotate;
    }

    public ListNode rightOneStep(ListNode node) {
        ListNode last, pre = null;
        ListNode cur = node;
        while (cur.next != null) {
            pre = cur;
            cur = cur.next;
        }
        pre.next = null;
        // 新的节点；
        last = cur;
        last.next = node;
        return last;
    }

    /**
     * 新链表的最后一个节点为原链表的第 (n−1)−(k%n)(n - 1) - (k \bmod n)(n−1)−(kmodn) 个节点（从 000 开始计数）。
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRightSolution(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        int n = 1;
        ListNode iter = head;
        while (iter.next != null) {
            iter = iter.next;
            n++;
        }
        int add = n - k % n;
        if (add == n) {
            return head;
        }
        // 环形。然后有？
        iter.next = head;
        while (add-- > 0) {
            iter = iter.next;
        }
        ListNode ret = iter.next;
        iter.next = null;
        return ret;
    }

    /**
     * 旋转数组。和上面的选择列表很像
     *
     * @param nums
     * @param k
     */
    public void rotateMe(int[] nums, int k) {
        if (k == 0) {
            return;
        }

        int len = nums.length;
        int move = k % len;
        if (move == 0) {
            return;
        }
        // 怎么解决覆盖的问题？？
        for (int i = len - 1; i >= 0; i--) {
            int temp = nums[i];
            int index = (temp + k) % len;

        }
    }

    @Test
    public void testRotate() {
        rotate(new int[]{1, 2, 3, 4, 5}, 3);
    }

    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        // 2,5 ==> 1
        int count = gcd(k, n);
        for (int start = 0; start < count; ++start) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % n;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
            } while (start != current);
        }
    }

    public int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }

    /**
     * 和最大的连续子数组
     * <p>
     * dp的定义是什么？
     * <p>
     * 代表的是第i个位置处，前i-1的最大值。
     * dp(i) = max(dp(i-1)+nums(i), dp(i-1))
     * <p>
     * 初始的情况是dp（i） = nums(i)；
     * 上面描述的dp的定义是错误的！！
     * <p>
     * 实际上用的是
     * dp_i = max(nums[i], dp_(i-1)+nums[i])
     * </p>
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 这是序列的意思
//            dp[i] = Math.max(dp[i-1]+nums[i], dp[i-1]);

            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }
        log.info("{}", dp);
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * 更近一步的处理：压缩空间 因为有i 只和 i-1有关，所以有
     *
     * @param nums
     * @return
     */
    public int maxSubArrayII(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int dp_0 = nums[0];
        int dp_1 = 0, res = dp_0;
        for (int i = 1; i < n; i++) {
            dp_1 = Math.max(dp_0 + nums[i], nums[i]);
            // dp_0 是上一步的值有一步的延迟；
            dp_0 = dp_1;
            res = Math.max(res, dp_1);
        }
        return res;
    }

    /**
     * 此数组是一个环形的；可以首尾相连。
     * <p>
     * 有 n为他们的长度，这时有num[n] = nums[0];
     * 用数学的表达式说明如下：
     * <p>
     * nums[i] = nums[i%n];
     * 但是有一个问题不是说在之前的就会算数的。
     * 只能包含数组一次。
     * </p>
     *
     * @param nums
     * @return
     */
    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        int[] leftMax = new int[n];
        // 对坐标为 0 处的元素单独处理，避免考虑子数组为空的情况
        leftMax[0] = nums[0];
        int leftSum = nums[0];
        int pre = nums[0];
        int res = nums[0];
        for (int i = 1; i < n; i++) {
            pre = Math.max(pre + nums[i], nums[i]);
            res = Math.max(res, pre);
            leftSum += nums[i];
            leftMax[i] = Math.max(leftMax[i - 1], leftSum);
        }

        // 从右到左枚举后缀，固定后缀，选择最大前缀
        int rightSum = 0;
        for (int i = n - 1; i > 0; i--) {
            rightSum += nums[i];
            res = Math.max(res, rightSum + leftMax[i - 1]);
        }
        return res;
    }

    @Test
    public void main() {
        int[] nums = {5, -3, 5};
        System.out.println(maxSubarraySumCircular(nums));
    }


    @Test
    public void testSubArray() {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        maxSubArray(nums);
    }

    public int findKthLargest(int[] nums, int k) {
        if (k == 0 || nums == null || nums.length < k) {
            return -1;
        }

        // 这个是一个快速排序的问题；
        int n = nums.length;
        int num = quickSort(nums, 0, n - 1, k);
        return num;
    }

    public int quickSort(int[] nums, int left, int right, int k) {
        if (k == 0) {
            return nums[left];
        }
        return 0;
    }

    /**
     * 从头删除第nth节点
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromBegin(ListNode head, int n) {
        if (head == null || n == 0) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = head;
        for (int i = n - 2; i > 0; i--) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return dummy.next;
    }

    // 主函数
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 虚拟头结点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 删除倒数第 n 个，要先找倒数第 n + 1 个节点
        ListNode x = findFromEnd(dummy, n + 1);
        // 删掉倒数第 n 个节点
        x.next = x.next.next;
        return dummy.next;
    }

    // 返回链表的倒数第 k 个节点
    ListNode findFromEnd(ListNode head, int k) {
        ListNode p1 = head;
        // p1 先走 k 步
        for (int i = 0; i < k; i++) {
            p1 = p1.next;
        }
        ListNode p2 = head;
        // p1 和 p2 同时走 n - k 步
        while (p1 != null) {
            p2 = p2.next;
            p1 = p1.next;
        }
        // p2 现在指向第 n - k 个节点
        return p2;
    }


    @Test
    public void delete() {
        ListNode listNode = buildLinkedList(new int[]{1, 2, 3, 4, 5});
        val listNode1 = removeNthFromBegin(listNode, 4);
        log.info("{}", listNode1);
    }

    /**
     * 从头节点开始【旋转k次，可以看见值的大小】
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight1010(ListNode head, int k) {
        if (head == null || k == 0 || head.next == null) {
            return head;
        }

        // 计算几个节点
        int cnt = 1;
        while (head != null) {
            cnt++;
            head = head.next;
        }
        int res = cnt - k % cnt;
        if (res == cnt) {
            return head;
        }
        // 移动res个。什么情况呢？
        ListNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = head;
        while (res > 0) {
            head = head.next;
            res--;
        }
        // 断节点；
        ListNode ret = cur.next;

        return head;
    }
}