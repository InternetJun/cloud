package org.example.common.core.leetcode.tree;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/8/22 16:07
 * 面试的加油哦，乐俊，既然要吃着一碗饭，有了牵挂，那就加油，不要做那些
 * 无畏的事情了，HZ的事情还有人，真的有点low的。
 */
@Slf4j
public class Flatten {

    /**
     * 最近的公共祖先是什么意思呢？
     * [图片实例](https://assets.leetcode.com/uploads/2018/12/14/binarytree.png)
     * <p>
     *      1,p 和 q 分别在节点 node 的左右子树中。
     *      2,node 即为节点 p，q 在节点 p 的左子树或右子树中。
     *      3,node 即为节点 q，p 在节点 q 的左子树或者右子树中。
     * </p>
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null && right == null) {
            return null;
        }
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }

    /**
     * Z形状的遍历。有一个控制方向的变量
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        int level = 1;
        List<List<Integer>> result = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> itemList = new ArrayList<>();
            TreeNode item = queue.pop();
            itemList.add(item.val);
            if (level % 2 == 0) {
                if (item.left != null) {
                    queue.add(item.left);
                }
                if (item.right != null) {
                    queue.add(item.right);
                }
            } else {
                if (item.right != null) {
                    queue.add(item.right);
                }
                if (item.left != null) {
                    queue.add(item.left);
                }
            }
            level++;
        }
        return result;
    }

    public List<List<Integer>> zigzagLevelOrderSolution(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        boolean reverseFlag = false;
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Deque<Integer> itemList = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode item = queue.poll();
                // 什么情况是要反转呢？
                if (reverseFlag) {
                    itemList.offerLast(item.val);
                } else {
                    itemList.offerFirst(item.val);
                }
                if (item.left != null) {
                    queue.add(item.left);
                }
                if (item.right != null) {
                    queue.add(item.right);
                }
            }
            result.add(new LinkedList<Integer>(itemList));
            reverseFlag = !reverseFlag;

        }
        return result;
    }

    public List<List<Integer>> LevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Deque<Integer> itemList = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode item = queue.poll();
                // 什么情况是要反转呢？
                if (ObjectUtil.isEmpty(item)) {
                    itemList.offerLast(null);
                } else {
                    itemList.offerLast(item.val);
                }
                queue.add(item.left);
                if (item.left != null) {

                }
                queue.add(item.right);
                if (item.right != null) {
                }
            }
            result.add(new LinkedList<Integer>(itemList));

        }
        return result;
    }

    public TreeNode buildTree(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) {
            return null;
        }

        return buildTreeHelper(arr, 0);
    }

    private TreeNode buildTreeHelper(Integer[] arr, int index) {
        if (index >= arr.length || arr[index] == null) {
            return null;
        }

        TreeNode root = new TreeNode(arr[index]);

        root.left = buildTreeHelper(arr, 2 * index + 1);
        root.right = buildTreeHelper(arr, 2 * index + 2);

        return root;
    }

    @Test
    public void main() {
        Integer[] arr = {5,3,6,2,4,null,null,1};
        TreeNode root = buildTree(arr);
//        List<List<Integer>> lists = levelOrder(root);
//        log.info("{}", lists);
    }

    public List<List<Integer>> levelOrderWithNull(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    currentLevel.add(node.val);
                    queue.offer(node.left);
                    queue.offer(node.right);
                } else {
                    currentLevel.add(null);
                }
            }

            // 只有当前层有非null值时才加入结果
            if (!currentLevel.isEmpty()) {
                result.add(currentLevel);
            }
        }

        return result;
    }

    /**
     * 先序遍历的第k个。但是有可以用二分法获取到吧。
     * <p>
     * 先序：是二叉树遍历中的一种，即先访问根结点，然后遍历左子树，后遍历右子树。遍历左、右子树时，先访问根结点，后遍历左子树，后遍历右子树，如果二叉树为空则返回。
     *根左右
     * 中序：是二叉树遍历中的一种，即先遍历左子树，后访问根结点，然后遍历右子树。若二叉树为空则结束返回。
     *左根右
      * 后序：是二叉树遍历中的一种，即先遍历左子树，后遍历右子树，然后访问根结点，遍历左、右子树时，仍先遍历左子树，后遍历右子树，最后遍历根结点。
     *左右根
     * </p>
     *
     * 还有普通的数的问题。
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return -1;
        }
        // 首先判出左子树的长度
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            --k;
            if (k == 0) {
                break;
            }
            root = root.right;
        }
        return root.val;
    }

    @Test
    public void mainValid() {
        Integer[] arr = {2,1,3};
        Integer[] arr2 = {5,1,4,null,null,3,6};
        TreeNode root = buildTree(arr2);
//        System.out.println(isValidBST(root));

    }
    /**
     * 合并区间
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        // 先按照区间起始位置排序
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        // 遍历区间
        int[][] res = new int[intervals.length][2];
        int idx = -1;
        for (int[] interval: intervals) {
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
            // 则不合并，直接将当前区间加入结果数组。
            if (idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval;
            } else {
                // 反之将当前区间合并至结果数组的最后区间
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, idx + 1);
    }

    @Test
    public void mergeTest() {
        int[][] ints = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] merge = merge(ints);
        log.info("{}", Arrays.deepToString(merge));
    }

    /**
     * s 仅由括号 '()[]{}' 组成
     *  <p>
     *      遇到左括号就入栈，遇到右括号就去栈中寻找最近的左括号，
     *      看是否匹配。
     *  </p>
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c== '[') {
                stack.push(c);
            } else {
                // 可能会出现这个情况。
                if (!stack.isEmpty() && stack.peek() == leftOf(c)) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
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
     * 镜像树的生成
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode leftNode = root.left;
        TreeNode temp = leftNode;
        TreeNode rightNode = root.right;

        root.left = rightNode;
        root.right = temp;
        if (root.left != null) {
            invertTree(root.left);
        }
        if (root.right != null) {
            invertTree(root.right);
        }
        return root;
    }

    @Test
    public void reverse() {
        // Integer[] ints = {3,9,20,null,null,15,7};
        Integer[] a = {3,9,20,null,null,15,7};
        TreeNode treeNode = buildTree(a);
        TreeNode root = null;
//        TreeNode reverseNode = invertTree(treeNode);
        List<List<Integer>> lists = levelOrderWithNullLastLevel(treeNode);
        log.info("{}", lists);
    }

    public List<List<Integer>> levelOrderWithNullLastLevel(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    currentLevel.add(node.val);
                    queue.offer(node.left);
                    queue.offer(node.right);
                } else {
                    currentLevel.add(null);
                }
            }

            result.add(currentLevel);
        }
        return result.subList(0,result.size() - 1);
    }

    /**
     * 对称的树。还有的是什么？
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return dfs(root.left, root.right);
    }

    private boolean dfs(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val){
            return false;
        }
        return dfs(left.left, right.right) && dfs(left.right, right.left);
    }

    // 还有的是什么呢？需要做的是
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean flagCol0 = false;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                flagCol0 = true;
            }
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (flagCol0) {
                matrix[i][0] = 0;
            }
        }
    }

    @Test
    public void test() {
        int[][] ints =  {{1,1,1},{1,0,1},{1,1,1}};
        int[][] int2 = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        setZeroes(int2);
        log.info("{}", int2);
    }

    /**
     * 整数转罗马
     * <p>
     *     10
     *     5
     *     100
     *
     * </p>
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        StringBuffer stringBuffer = new StringBuffer();
        while (num > 0) {
            int qian = num /1000;
            int bai = num % 1000;
        }
        return stringBuffer.toString();
    }

    /**
     * 相同，并且有下标不大于k；
     * 判断数组中是否存在两个不同的索引 i 和 j
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, LinkedList<Integer>> indexMap = new HashMap<>();
        int gap = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            LinkedList<Integer> indexMapOrDefault = indexMap.getOrDefault(num, new LinkedList<>());
            if (indexMapOrDefault.size() >= 1) {
                gap = Math.min(i - indexMapOrDefault.get(indexMapOrDefault.size()-1),  gap);
                if (gap <= k) {
                    return true;
                }
            }
            indexMapOrDefault.add(i);
            indexMap.put(num, indexMapOrDefault);
        }
        return false;
    }

    public static void main(String[] args) {
        final int test = finalTest();
        System.out.println(test);

    }
    private static int finalTest() {
        int temp = 1;
        try {
            System.out.println(temp);
            // 首先处理temp的值，最后返回的值没有经过finally的逻辑处理的
            return ++temp;
        } catch (Exception e) {
            System.out.println(temp);
            return ++temp;
        } finally {
            ++temp;
            System.out.println(temp);
        }
    }

    @Test
    public void mainL() {
        int[] nums = {1,0,1,1};
        boolean b = containsNearbyDuplicate(nums, 1);
        log.info("{}", b);
    }
}
