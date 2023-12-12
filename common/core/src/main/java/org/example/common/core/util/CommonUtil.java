package org.example.common.core.util;

import com.alibaba.fastjson.JSONObject;
import org.example.common.core.leetcode.tree.TreeNode;
import org.example.common.core.util.fileDispose.FileCommentUtil;

import java.util.*;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 公共的方法的放置
 * @time: 2023/11/6 11:19
 */
public class CommonUtil {
    public static void formatAndDisplayArray(int[][] array) {
        int numRows = array.length;
        int numCols = array[0].length;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                System.out.printf("%4d", array[i][j]); // Adjust the width as needed
            }
            System.out.println(); // Move to the next row
        }
    }

    public static void formatAndDisplayArray(List<int[]> matrix) {
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println(); // 换行表示矩阵的下一行
        }
    }

    public static <T> T convertToArray(String input, Class<T> clazz) {
        List<List<Integer>> tempList = new ArrayList<>();
        List<Integer> currentList = new ArrayList<>();
        StringBuilder numBuilder = new StringBuilder();
        boolean is2D = input.contains("[[");

        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                numBuilder.append(c);
            } else if (c == ',') {
                // Separator between elements
                if (numBuilder.length() > 0) {
                    currentList.add(Integer.parseInt(numBuilder.toString()));
                    numBuilder.setLength(0);
                }
            } else if (c == '[') {
                // Start a new array
                currentList = new ArrayList<>();
            } else if (c == ']') {
                // End of the current array, add it to the result
                if (numBuilder.length() > 0) {
                    currentList.add(Integer.parseInt(numBuilder.toString()));
                    numBuilder.setLength(0);
                }
                tempList.add(new ArrayList<>(currentList));
            }
        }

        // Convert the list of lists to an array
        if (is2D) {
            int[][] resultArray = new int[tempList.size()][];
            for (int i = 0; i < tempList.size(); i++) {
                List<Integer> list = tempList.get(i);
                resultArray[i] = new int[list.size()];
                for (int j = 0; j < list.size(); j++) {
                    resultArray[i][j] = list.get(j);
                }
            }
            return clazz.cast(resultArray);
        } else {
            int[] resultArray = new int[tempList.get(0).size()];
            for (int i = 0; i < tempList.get(0).size(); i++) {
                resultArray[i] = tempList.get(0).get(i);
            }
            return clazz.cast(resultArray);
        }
    }

    // 定义一个方法，输出树的树状形式
    public static void printTree(TreeNode root) {
        if (root == null) {
            return; // 如果根节点为空，直接返回
        }
        List<List<String>> res = new ArrayList<>(); // 创建一个列表，用于存储每一层的节点值
        Queue<TreeNode> queue = new LinkedList<>(); // 创建一个队列，用于层次遍历
        queue.offer(root); // 将根节点入队
        while (!queue.isEmpty()) { // 当队列不为空时
            int size = queue.size(); // 获取当前层的节点数
            List<String> level = new ArrayList<>(); // 创建一个列表，用于存储当前层的节点值
            for (int i = 0; i < size; i++) { // 遍历当前层的节点
                TreeNode node = queue.poll(); // 出队一个节点
                if (node == null) { // 如果节点为空
                    level.add(" "); // 列表中添加一个空格
                    queue.offer(null); // 队列中入队一个空节点
                    queue.offer(null); // 队列中入队一个空节点
                } else { // 如果节点不为空
                    level.add(String.valueOf(node.val)); // 列表中添加节点的值
                    queue.offer(node.left); // 队列中入队左子节点
                    queue.offer(node.right); // 队列中入队右子节点
                }
            }
            res.add(level); // 将当前层的列表添加到结果列表中
        }
        // 计算树的高度
        int height = res.size();
        // 计算树的宽度
        int width = (int) Math.pow(2, height) - 1;
        // 遍历结果列表
        for (int i = 0; i < height; i++) {
            List<String> level = res.get(i); // 获取第i层的列表
            int interval = width / level.size(); // 计算每个节点之间的间隔
            StringBuilder sb = new StringBuilder(); // 创建一个字符串构造器，用于拼接输出
            for (int j = 0; j < level.size(); j++) { // 遍历第i层的节点
                String val = level.get(j); // 获取第j个节点的值
                // 在节点值前后添加空格，使其长度为间隔的一半
                while (val.length() < interval / 2) {
                    val = " " + val + " ";
                }
                // 如果节点值长度为奇数，再在后面添加一个空格
                if (val.length() % 2 == 1) {
                    val = val + " ";
                }
                // 在字符串构造器中拼接节点值和间隔
                sb.append(val).append(repeat(" ", interval));
            }
            // 输出第i层的字符串
            System.out.println(sb.toString());
        }
    }

    private static String repeat(String str, int n) {
        // 如果str为空或n小于等于0，返回空字符串
        if (str == null || n <= 0) {
            return "";
        }
        // 创建一个字符串构造器，用于拼接结果
        StringBuilder sb = new StringBuilder();
        // 循环n次，每次将str添加到字符串构造器中
        for (int i = 0; i < n; i++) {
            sb.append(str);
        }
        // 返回字符串构造器的字符串表示
        return sb.toString();
    }

    public static TreeNode buildTree(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) {
            return null;
        }

        return buildTreeHelper(arr, 0);
    }

    private static TreeNode buildTreeHelper(Integer[] arr, int index) {
        if (index >= arr.length || arr[index] == null) {
            return null;
        }

        TreeNode root = new TreeNode(arr[index]);

        root.left = buildTreeHelper(arr, 2 * index + 1);
        root.right = buildTreeHelper(arr, 2 * index + 2);

        return root;
    }

    public static <T> List<T> arrayToList(T... t) {
        final ArrayList<T> ts = new ArrayList<>();
        ts.addAll(Arrays.asList(t));
        return ts;
    }

    // 解析字符串为二维数组的方法
    public static int[][] parseStringToArray(String inputString) {
        int[][] result = JSONObject.parseObject(inputString, int[][].class);
        return result;
    }
}