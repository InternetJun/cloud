package org.example.common.core.leetcode.twentyFour.febuary;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.example.common.core.leetcode.link.ListNode;
import org.example.common.core.leetcode.tree.TreeNode;
import org.example.common.core.util.CommonUtil;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description:
 * @time: 2024/2/21 21:29
 */
@Slf4j
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
     * <p>
     * dp的定义是 以i的最长。
     * <p>
     * 4(1 2 3)
     * </p>
     * <p>
     * 初始条件dp = 1；
     *
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
        if (head == null || head.next == null) {
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

    /**
     * 滑动的窗口max值。
     * <p>
     * 8 - 3 + 1
     * 滑动窗口的位置                最大值
     * ---------------               -----
     * [1  【3】  -1] -3  5  3  6  7      3
     * 1 [【3】  -1  -3] 5  3  6  7       3
     * 1  3 [-1  -3  【5】] 3  6  7       5
     * 1  3  -1 [-3  【5】  3] 6  7       5
     * 1  3  -1  -3 [5  3  【6】] 7       6
     * 1  3  -1  -3  5 [3  6  【7】]      7
     * 需要记录的是最大的坐标，要是在其中的话还可以比较，不在的话就要重新计算了。
     * </p>
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 特殊情况的判断。
        if (nums.length == k || nums.length < k) {
            return new int[]{Arrays.stream(nums).sum()};
        }
        final int[] res = new int[nums.length - k + 1];
        int len = nums.length;
        // 倒序，加上多一个信息，这时可以处理
        final PriorityQueue<int[]> priorityQueue = new PriorityQueue(
                (Comparator<int[]>) (pair1, pair2) -> {
                    // 降序处理数据。
                    return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
                }
        );
        for (int i = 0; i < k; i++) {
            priorityQueue.add(new int[]{nums[i], i});
        }
        // 3 1 -1
        res[0] = priorityQueue.peek()[0];
        for (int i = k; i < nums.length; i++) {
            priorityQueue.offer(new int[]{nums[i], i});
            // 最大值是peek();还有的是 3 - k
            priorityQueue.stream().forEach(m->{
                System.out.println(Arrays.toString(m));
            });
            log.info("{}",i-k);
            // 就是说最大的值是否在范围内，没有就出去罗。不好理解吗？
            while (priorityQueue.peek()[1] <= i - k) {
                priorityQueue.poll();
            }
            res[i-k+1] = priorityQueue.peek()[0];
        }
        return res;
    }

    @Test
    public void testWindow() {
        int[] nums = {
                1, 3, -1, -3, 5, 3, 6, 7
        };
        System.out.println(Arrays.toString(maxSlidingWindow(nums, 3)));
    }

    /**
     * 轮转数组
     * <p>
     * 1,2,3,4,5,6,7
     * 5,6,7,1,2,3,4
     * </p>
     *
     * @param nums
     * @param k
     */
    public void rotateMe(int[] nums, int k) {
        int len = nums.length;
        // 真实移动的东西。
        int remain = k % len;
        int tmp = -1;
        for (int i = 0; i < len; i++) {
            // i -> index; index -> 另外一个 index + k
            int index = (i + remain) % len;
            tmp = nums[index];
            nums[index] = nums[i];

        }
    }

    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
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
     * 除自身以外的乘积。不允许使用除法。
     * 使用左右两边的乘积。
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] L = new int[len];
        int[] R = new int[len];
        int[] res = new int[len];
        L[0] = 1;
        for (int i = 1; i < len; i++) {
            L[i] = nums[i - 1] * L[i - 1];
        }
        R[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            R[i] = R[i + 1] * nums[i + 1];
        }
        // L[0] * R[0] ==> nums[0]...nums[i-1] * nums[i+1]...nums[len]
        log.info("left:{}\nright:{}", L, R);
        for (int i = 0; i < len; i++) {
            // 0 L[0] * R[0]
            res[i] = L[i] * R[i];
        }
        return res;
    }

    @Test
    public void testExceptSelf() {
        // [24,12,8,6]
        int[] nums = {1, 2, 3, 4};
        System.out.println(Arrays.toString(productExceptSelf(nums)));
    }

    /**
     * 最大的子数组和
     * <p>
     * 输入：nums = [-2,1,-3,【4,-1,2,1】,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6
     * dp_ij ==> i_j的最大的和。
     * [0,j+1] == [o,j] *  nums[j+1];
     * dp[i][i] = nums[i];
     * dp_i,j+1 = dp_ij * nums[j+1];
     * dp_i-1,j ==>
     * </p>
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        // 定义：dp[i] 记录以 nums[i] 为结尾的「最大子数组和」
        int[] dp = new int[n];
        // base case
        // 第一个元素前面没有子数组
        dp[0] = nums[0];
        // 状态转移方程
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
        }
        // 得到 nums 的最大子数组
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }


    int maxSubArrayZip(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        // base case
        int dp_0 = nums[0];
        int dp_1 = 0, res = dp_0;

        for (int i = 1; i < n; i++) {
            // dp[i] = max(nums[i], nums[i] + dp[i-1])
            dp_1 = Math.max(nums[i], nums[i] + dp_0);
            dp_0 = dp_1;
            // 顺便计算最大的结果
            res = Math.max(res, dp_1);
        }

        return res;
    }

    final List<LinkedList<Integer>> listPath = new LinkedList<>();

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // base case
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 情况 1
        if (left != null && right != null) {
            return root;
        }
        // 情况 2
        if (left == null && right == null) {
            return null;
        }
        // 情况 3
        return left == null ? right : left;
    }

    /**
     * 不必要从根节点开始。只要满足从上到下就可以了。
     *
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        final LinkedList<Integer> list = new LinkedList<>();
        int ret = dfsTree(root, targetSum, list);
        ret += pathSum(root.left, targetSum);
        ret += pathSum(root.right, targetSum);
        log.info("所有路径的记录如下：{}", listPath);
        return ret;
    }

    /**
     * 但是路径方向必须是向下的（只能从父节点到子节点）
     * <p>
     * 怎么保证他的方向是一致的，不要左右的摇摆处理？？？
     * <p>
     * 是的。要有左右开弓的处理。res[left]+res[right];
     * </p>
     * </p>
     *
     * @param root
     * @param targetSum
     * @param pathRecord
     */
    public int dfsTree(TreeNode root, int targetSum, LinkedList<Integer> pathRecord) {
        int ret = 0;

        if (root == null) {
            return 0;
        }
        // 记录下经过的路径值。
        int val = root.val;
        pathRecord.addLast(val);
        if (val == targetSum) {
            // 记录下该有的值。
            ret++;
        }
        ret += dfsTree(root.left, targetSum - val, pathRecord);
        ret += dfsTree(root.right, targetSum - val, pathRecord);
        return ret;
    }

    @Test
    public void testSumTree() {
        final TreeNode root = CommonUtil.buildTree(new Integer[]{5,4,8,11,null,13,4,7,2,null,null,5,1});
        final List<List<Integer>> lists = pathSumRecord(root, 22);
        log.info("总路径的条是：{}", res);
    }
    LinkedList<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();
    public List<List<Integer>> pathSumRecord(TreeNode root, int targetSum) {
        recur(root, targetSum);
        return res;
    }
    void recur(TreeNode root, int tar) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        tar -= root.val;
        // 表明了是一个叶子节点啊
        if (tar == 0 && root.left == null && root.right == null) {
            res.add(new LinkedList<Integer>(path));
        }
        recur(root.left, tar);
        recur(root.right, tar);
        path.removeLast();
    }

    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        List<Integer> arr = new ArrayList<Integer>();
        dfs(root, arr);

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (int val : queries) {
            int maxVal = -1, minVal = -1;
            int idx = binarySearch(arr, val);
            if (idx != arr.size()) {
                maxVal = arr.get(idx);
                if (arr.get(idx) == val) {
                    minVal = val;
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(minVal);
                    list.add(maxVal);
                    res.add(list);
                    continue;
                }
            }
            if (idx > 0) {
                minVal = arr.get(idx - 1);
            }
            List<Integer> list2 = new ArrayList<Integer>();
            list2.add(minVal);
            list2.add(maxVal);
            res.add(list2);
        }
        return res;
    }

    public void dfs(TreeNode root, List<Integer> arr) {
        if (root == null) {
            return;
        }
        dfs(root.left, arr);
        arr.add(root.val);
        dfs(root.right, arr);
    }

    /**
     * 大于val的最左的index
     *
     * @param arr
     * @param target
     * @return
     */
    public int binarySearch(List<Integer> arr, int target) {
        int low = 0, high = arr.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            // 并使用二分查找在索引中找到大于等于 val 最左侧的索引 index
            if (arr.get(mid) >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    class Solution {
        /**
         * 获取val的第一个位置和最后一个位置。
         *
         * @param nums
         * @param target
         * @return
         */
        public int[] searchRange(int[] nums, int target) {
            return new int[]{left_bound(nums, target), right_bound(nums, target)};
        }

        int left_bound(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            // 搜索区间为 [left, right]
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] < target) {
                    // 搜索区间变为 [mid+1, right]
                    left = mid + 1;
                } else if (nums[mid] > target) {
                    // 搜索区间变为 [left, mid-1]
                    right = mid - 1;
                } else if (nums[mid] == target) {
                    // 收缩右侧边界
                    right = mid - 1;
                }
            }
            // 检查出界情况
            if (left >= nums.length || nums[left] != target) {

                return -1;
            }
            return left;
        }

        int right_bound(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] > target) {
                    right = mid - 1;
                } else if (nums[mid] == target) {
                    // 这里改成收缩左侧边界即可
                    left = mid + 1;
                }
            }
            // 这里改为检查 right 越界的情况，见下图
            if (right < 0 || nums[right] != target) {

                return -1;
            }
            return right;
        }
    }

    @Test
    public void main(){
        final List<Integer> list = Arrays.asList(1, 2, 3, 3, 4);
        System.out.println(binarySearch(list, 3));
    }

    /**
     * 最长的有效括号。和连续子数组很像啊。
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        Stack<Integer> stk = new Stack<>();
        // dp[i] 的定义：记录以 s[i-1] 结尾的最长合法括号子串长度
        int[] dp = new int[s.length() + 1];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                // 遇到左括号，记录索引
                stk.push(i);
                // 左括号不可能是合法括号子串的结尾
                dp[i + 1] = 0;
            } else {
                // 遇到右括号
                if (!stk.isEmpty()) {
                    // 配对的左括号对应索引
                    int leftIndex = stk.pop();
                    // 以这个右括号结尾的最长子串长度
                    int len = 1 + i - leftIndex + dp[leftIndex];
                    dp[i + 1] = len;
                } else {
                    // 没有配对的左括号
                    dp[i + 1] = 0;
                }
            }
        }
        // 计算最长子串的长度
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public boolean isValid(String str) {
        Stack<Character> left = new Stack<>();
        for (char c : str.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                left.push(c);
            } else // 字符 c 是右括号
                if (!left.isEmpty() && leftOf(c) == left.peek()) {
                    left.pop();
                } else
                    // 和最近的左括号不匹配
                {
                    return false;
                }
        }
        // 是否所有的左括号都被匹配了
        return left.isEmpty();
    }

    char leftOf(char c) {
        if (c == '}') {
            return '{';
        }
        if (c == ')') {
            return '(';
        }
        return '[';
    }

    /**
     * 通过率65/85；
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root != null) {
            if ((root.right != null && root.right.val <= root.val) ||
                    (root.left != null && root.left.val >= root.val)) {
                return false;
            }
            return true;
        }
        return isValidBST(root.left) && isValidBST(root.right);
    }

    public boolean isValidBSTSolution(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * 是有一个上下限的，对此节点进行一个判断就好了。
     *
     * @param node
     * @param lower
     * @param upper
     * @return
     */
    public boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }

    /**
     * 获取到第k个数。
     * <p>
     *  思路是左右数的二叉遍历吧。
     * </p>
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        // 利用 BST 的中序遍历特性
        traverse(root, k);
        return result;
    }

    // 记录结果
    int result = 0;
    // 记录当前元素的排名
    int rank = 0;
    void traverse(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        traverse(root.left, k);
        /* 中序遍历代码位置 */
        rank++;
        if (k == rank) {
            // 找到第 k 小的元素
            result = root.val;
            return;
        }
        /*****************/
        traverse(root.right, k);
    }

    /**
     * <p>
     * [[2,1,1],[0,1,1],[1,0,1]]
     * X O O
     * [ O O
     * O [ O
     * 什么情况来说明是可以说明就全部感染了呢？用count？
     * 【0 2】说明就全部感染了啊。
     * 要是有多个感染源呢？==就是用count的。
     * </p>
     *
     * @param grid
     * @return
     */
    public int orangesRotting(int[][] grid) {
        //1.定义2个int数组，2个一组来记录腐烂橘子的上下左右位置。腐烂橘子(0，0)
        //在矩阵中 上{-1,0}   下{1,0}  左{0,-1}   右{0,1}
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int step = 0;//感染次数
        int flash = 0;//新鲜橘子数（后面用于判定是否为-1）

        int row = grid.length;//所给矩阵行
        int col = grid[0].length;//列

        Queue<int[]> queue = new ArrayDeque<>();
        //2.遍历矩阵 将所有的腐烂橘子入队，并且记录初始新鲜橘子数
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    flash++;
                }
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        //3.遍历所有腐烂橘子，同时感染四周
        while (flash > 0 && !queue.isEmpty()) {//有橘子且队列不空
            step++;
            //队列中现有的所有腐烂橘子都要进行一次感染
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int[] poll = queue.poll();//腐烂橘子
                for (int i = 0; i < 4; i++) {
                    //4个位置dx[i] dy[i]  ， xy 为要感染的橘子位置
                    int x = poll[0] + dx[i];//第x行
                    int y = poll[1] + dy[i];//第y列
                    if ((x >= 0 && x < row) && (y >= 0 && y < col) && grid[x][y] == 1) {
                        //xy不越界，并且要感染的地方是 新鲜橘子
                        grid[x][y] = 2;
                        //把被感染的橘子 入队
                        queue.offer(new int[]{x, y});
                        //新鲜橘子-1
                        flash--;
                    }
                }
            }

        }

        //感染完了之后如果还有新鲜橘子
        if (flash > 0) {
            return -1;
        } else {
            return step;
        }
    }

    /**
     * 判断是否可以认为是一个质数的方法
     *
     * @param n
     * @return
     */
    public static boolean isPrime(int n) {
        if (n <= 3) {
            return n > 1;
        }
        //只需判断一个数能否被小于sqrt(n)的奇数整除
        int sqrt = (int)Math.sqrt(n);
        for (int i = 3; i <= sqrt; i += 2) {
            if(n % 2 == 0 || n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取到右视图的数据。
     * <p>
     *     思路是什么？每一层的最后一个节点。怎么判断是最后一个节点呢？
     *     1
     *   2  3
     * 5
     * </p>
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }
        final ArrayList<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            final ArrayList<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                // 特殊情况为root的时候。2，普通节点；--> 有1,后序都没有啊。
                final TreeNode poll = queue.poll();
                temp.add(poll.val);
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
            list.add(temp.get(temp.size()-1));
        }
        return list;
    }

    @Test
    public void mainRight() {
        /**
         * [1,null,3]
         * []
         */
        final TreeNode root = CommonUtil.buildTree(new Integer[]{1,null,3});
        System.out.println(rightSideView(root));
    }

    /**
     * 2节点的最长路径。 其实就是最长的深度+1; left + right+ 1;
     *
     * @param root
     * @return
     */
    int ans;
    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        depth(root);
        return ans - 1;
    }
    public int depth(TreeNode node) {
        if (node == null) {
            return 0; // 访问到空节点了，返回0
        }
        int L = depth(node.left);
        int R = depth(node.right);
        ans = Math.max(ans, L+R+1);
        return Math.max(L, R) + 1;
    }

//    /**
//     * 8分的代码。没有使用原始的字符。
//     * @param s
//     * @return
//     */
//    public boolean isTrue(String s) {
//        // 小写的处理。还有要去除空格的因素。
//        String ss = s.toLowerCase().replace(" ", "");
//        int left = 0, right = s.length()-1;
//        while (left <= right) {
//            char leftC = ss.charAt(left);
//
//            char leftR = ss.charAt(right);
//            if (leftC != leftR) {
//                return false;
//            }
//            left++;
//            right--;
//        }
//        return true;
//    }
//
//    public int[] sumIndex(int[] nums, int target) {
//        Map<Integer, Integer> map = new HashMap<>();
//        int[] res = new int[2];
//        int len = nums.length;
//        for (int i = 0; i < len; i++) {
//            map.put(nums[i], i);
//        }
//        for (int i = 0; i < len; i++) {
//            if (map.containsKey(target - nums[i])) {
//                res[0] = map.get(target - nums[i]);
//                res[1] = map.get(i);
//            }
//        }
//        return res;
//    }
//
//    public int longestConsecutive0228(int[] nums) {
//        Set set = new HashSet<Integer>();
//        int res = Integer.MIN_VALUE;
//        for(int i = 0; i < nums.length; i++) {
//            set.add(nums[i]);
//        }
//
//        for(int i = 0; i < nums.length; i++) {
//            int curNum;
//            int currentCount = 1;
//            if (!set.contains(nums[i]+1)) {
//                continue;
//            }
//            curNum = nums[i];
//            while (set.contains(curNum + 1)) {
//                //从开始到结束。
//                currentCount++;
//                curNum++;
//            }
//            res = Math.max(res, currentCount);
//        }
//
//        return res;
//    }
//
//    @Test
//    public void test0228() {
//        int[]  num ={100,4,200,1,3,2};
//        System.out.println(longestConsecutive0228(num));
//    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        // n 为 3，从 nums[0] 开始计算和为 0 的三元组
        return nSumTarget(nums, 3, 0, 0);
    }

    /* 注意：调用这个函数之前一定要先给 nums 排序 */
    // n 填写想求的是几数之和，start 从哪个索引开始计算（一般填 0），target 填想凑出的目标和
    public List<List<Integer>> nSumTarget(
            int[] nums, int n, int start, int target) {

        int sz = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        // 至少是 2Sum，且数组大小不应该小于 n
        if (n < 2 || sz < n) {
            return res;
        }
        // 2Sum 是 base case
        if (n == 2) {
            // 双指针那一套操作
            int lo = start, hi = sz - 1;
            while (lo < hi) {
                int sum = nums[lo] + nums[hi];
                int left = nums[lo], right = nums[hi];
                if (sum < target) {
                    while (lo < hi && nums[lo] == left) {
                        lo++;
                    }
                } else if (sum > target) {
                    while (lo < hi && nums[hi] == right) {
                        hi--;
                    }
                } else {
                    res.add(new ArrayList<>(Arrays.asList(left, right)));
                    while (lo < hi && nums[lo] == left) {
                        lo++;
                    }
                    while (lo < hi && nums[hi] == right) {
                        hi--;
                    }
                }
            }
        } else {
            // n > 2 时，递归计算 (n-1)Sum 的结果
            for (int i = start; i < sz; i++) {
                List<List<Integer>>
                        sub = nSumTarget(nums, n - 1, i + 1, target - nums[i]);
                for (List<Integer> arr : sub) {
                    // (n-1)Sum 加上 nums[i] 就是 nSum
                    arr.add(nums[i]);
                    res.add(arr);
                }
                // 一个剪枝的操作。
                while (i < sz - 1 && nums[i] == nums[i + 1]) {
                    i++;
                }
            }
        }
        return res;
    }

    /**
     * 对字母异位处理。
     * <p>
     *     1，对字符串的个数统计。<br>
     *     2，依次进行计算?
     *     用的还是滑动窗口，
     * </p>
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char c : p.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;
        List<Integer> res = new ArrayList<>();
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            // 有值。说明了？
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }
            // 判断收缩的条件是什么？
            while (right - left >= p.length()) {
                if (valid == need.size()) {
                    // 对left,right 的值都列出来。
                    log.info("{}", s.substring(left, right));
                    res.add(left);
                }
                char d = s.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }

        }
        return res;
    }

    @Test
    public void testString() {
//        String s = "cbaebabacd", p = "abc";
        String s = "abab", p = "ab";
//        String s = "", p = "";
        System.out.println(findAnagrams(s, p));
    }

    public String minWindow1001(String s, String t) {
        String res = "";
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> windows = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int left = 0, right = 0, len = Integer.MAX_VALUE, start = 0;
        int valid = 0;
        // 左右指针的移动。
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (windows.containsKey(c)) {
                windows.put(c, windows.getOrDefault(c, 0) + 1);
                if (windows.get(c).equals(need.get(c))) {
                    valid++;
                }
            }

            while (valid == need.size()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                char d = s.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (windows.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    windows.put(d, windows.get(d) - 1);
                }
            }

        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

    /**
     * 获取链表是否有环形
     * <p>
     *     用什么条件来进行中止呢？
     * </p>
     *
     * @param head
     * @return
     */
    public boolean hasCircle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        // fast 遇到空了，说明了
        while (fast != null && fast.next != null) {
            if (fast == slow) {
                return true;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return false;
    }

    public ListNode deleteCycle(ListNode head) {
        ListNode fast, slow;
        fast = slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        // 上面的代码类似 hasCycle 函数
        if (fast == null || fast.next == null) {
            // fast 遇到空指针说明没有环
            return null;
        }
        // 重新指向头结点
        slow = head;
    // 快慢指针同步前进，相交点就是环起点
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists== null || lists.length == 0) {
            return null;
        }
        ListNode node = null;
        // 分支的算法。
        int size = lists.length-1;
        return mergeHelper(lists, 0, size);
    }

    private ListNode mergeHelper(ListNode[] lists, int left, int right) {
        if (right == left) {
            return lists[left];
        }
        int mid = left + (right - left) / 2;
        final ListNode node = mergeHelper(lists, left, mid);
        final ListNode node1 = mergeHelper(lists, mid + 1, right);
        return mergeTwoListNode(node1, node);
    }

    /**
     * 合并2个有序链表。
     *
     * @param left
     * @param right
     * @return
     */
    public ListNode mergeTwoListNode(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(-1), dummyPoint = dummy;
        ListNode curL = left, curR = right;
        while (curL != null && curR != null) {
            if (curL.val >= curR.val) {
                dummyPoint.next = curR;
                curR = curR.next;
            } else {
                dummyPoint.next = curL;
                curL = curL.next;
            }
            dummyPoint = dummyPoint.next;
        }
        if (curL != null) {
            dummyPoint.next = curL;
        }
        if (curR != null) {
            dummyPoint.next = curR;
        }
        return dummy.next;
    }

    @Test
    public void testMerge() {
        final ListNode node = CommonUtil.buildLinkedList(new int[]{1, 3, 5});
        final ListNode node1 = CommonUtil.buildLinkedList(new int[]{2, 4, 6});
        final ListNode node2 = mergeTwoListNode(node1, node);
        log.info("{}", node2);
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
     * 对num的数组进行构建树。
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return null;
    }

    @Test
    public void testTree() {
        sortedArrayToBST(new int[]{-10,-3,0,5,9});
    }

    /**
     * 左右节点值的交换。可以
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {

        if(root == null || (root.left == null && root.right == null)) {
            return root;
        }

        // root.left = root.right || left.left = right.right
        final TreeNode left = invertTree(root.left);
        final TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     *  <p>
     *      对课程的学习，必须有一个先后的顺序。判断是否是可以有count == dfs的数据。
     *      用的是一个union-find的环计算。equals（count）？？
     *  </p>
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        UnionFind unionFind = new UnionFind(numCourses);
        for (int[] prerequisite : prerequisites) {
            unionFind.union(prerequisite[0], prerequisite[1]);
        }
        log.info("{}", unionFind.parent);
        return unionFind.getCount() == numCourses;
    }

    public boolean canFinish0229(int numCourses, int[][] prerequisites){
        int count = 0;
        return count == numCourses;
    }

    void dfs(int parent, int[][] prerequisites, boolean[] used, int count) {
        // 基本的条件是什么？可以退出呢？
        int countUsed = 0;
        for (int i = 0; i < used.length; i++) {
            if (used[i]) {
                countUsed++;
            }
        }
        if (countUsed == used.length) {
            return;
        }
        for (int[] prerequisite : prerequisites) {
            int p = prerequisite[0];
            int next = prerequisite[1];
            // 肯定是会有的。
            if (parent == p) {
                if (used[parent]) {
                    break;
                }
                used[parent] = true;
                dfs(next, prerequisites, used, ++count);
                used[parent] = false;
            }
        }
    }

    public boolean canFinishSolution(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacency = new ArrayList<>();
        for(int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }
        int[] flags = new int[numCourses];
        for(int[] cp : prerequisites) {
            adjacency.get(cp[1]).add(cp[0]);
        }
        for(int i = 0; i < numCourses; i++) {
            if(!dfs(adjacency, flags, i)) {
                return false;
            }
        }
        return true;
    }
    private boolean dfs(List<List<Integer>> adjacency, int[] flags, int i) {
        if(flags[i] == 1) {
            return false;
        }
        if(flags[i] == -1) {
            return true;
        }
        flags[i] = 1;
        for(Integer j : adjacency.get(i)) {
            if(!dfs(adjacency, flags, j)) {
                return false;
            }
        }
        flags[i] = -1;
        return true;
    }


    @Test
    public void testCan() {
        // numCourses = 2, prerequisites = [[1,0]]
        System.out.println(canFinish(2, new int[][]{{0, 1}, {1, 0}}));
//        System.out.println(canFinish(2, new int[][]{{0, 1}}));
    }

    // unionFind 一个圈内的数据。
    private class UnionFind {

        private int[] parent;

        private int count;

        public int getCount() {
            return count;
        }

        public UnionFind(int n) {
            this.count = n;
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            log.info("{}：{}；{}:{}", x, y, rootX, rootY);
            if (rootX == rootY) {
                return;
            }

            parent[rootX] = rootY;
            count--;
        }
    }
}
