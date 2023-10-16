package org.example.common.core.leetcode.dfs;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/8/24 16:26
 */
@Slf4j
public class Solution {
    /**
     * 生成括号对！
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        StringBuffer stringBuffer = new StringBuffer();
        int left = n, right = n;
        List<String> list = new ArrayList<>();
        generateDfs("", n, n, list);
        return list;
    }

    public void generateDfs(String s, int left, int right, List<String> list) {
        if (left == 0 && right == 0) {
            list.add(s);
            return;
        }
        if(right < left) {
             return;
        }
        if (left > 0) {
            generateDfs(s+"(", left - 1, right, list);
        }
        if (right > left && right > 0) {
            generateDfs(s+")", left, right-1, list);
        }
    }

    @Test
    public void main() {
        System.out.println(generateParenthesis(3));
    }

    /**
     * -2: 表示了 (-1,0)
     * -1:表示了（1,0）
     * 1<=x<=9表示了向前走动了多少。
     * 障碍物的作用是有：在移动的前一个位置。
     * <p>
     *     右：就是（x-1， y）
     *     左：（x+1，y）
     *     上:(x,y-1)
     * </p>
     * @param commands
     * @param obstacles
     * @return
     */
    public int robotSim(int[] commands, int[][] obstacles) {
        if (commands == null) {
            return 0;
        }
        int x = 0, y = 0;

        for (int i = 0; i < commands.length; i++) {
            // 判断出方向；
            if (commands[i] > 0) {
                y += commands[i];
            } else if (commands[i] == -1) {
                x += commands[i];
            } else {
                x -= commands[i];
            }

        }
        return uDist(x, y);
    }

    public int uDist(int x, int y) {
        return (int) (Math.pow(x, 2) + Math.pow(y, 2));
    }

    public int[] returnPosition(int x, int y, int[][] grid, int direction) {
        int[] res = new int[2];
        int[] obstacle = Arrays.stream(grid)
                .filter(item -> item[0] == x || item[1] == y).findAny()
                .get();
        String dir = getDirection(direction);
        if ("LEFT".equals(dir)) {
            res[0] = x+1;
            res[1] = y;
        } else if ("RIGHT".equals(dir)) {
            res[0] = x-1;
            res[1] = y;
        } else {
            res[0] = x;
            res[1]= y-1;
        }

        return res;
    }

    public String getDirection(int dir) {
        String diretion;
        switch (dir) {
            case -2:
                diretion = "LEFT";
                break;
            case -1:
                diretion = "RIGHT";
                break;
            default:
                diretion = "UPPER";
                break;
        }
        return diretion;
    }

    public int robotsSimSolution(int[] commands, int[][] obstacles) {
        int[][] dirs = {{-1,0}, {0, 1}, {1,0}, {0, -1}};
        int  px = 0, py = 0, d = 1;
        Set<Integer> set = new HashSet<>();
        for (int[] obstacle : obstacles) {
            set.add(obstacle[0] * 60001 + obstacle[1]);
        }

        int res = 0;
        for (int command : commands) {
            if (command < 0) {
                //                 right    down   left    upper
                // int[][] dirs = {{-1,0}, {0, 1}, {1,0}, {0, -1}};
                d += command == -1 ? 1 :-1;
                d %= 4;
                if (d < 0) {
                    d += 4;
                }
            } else {
                // 一步一步的走。要是有障碍物就处理。
                for (int i = 0; i < command; i++) {
                    if (set.contains(px +  dirs[d][0] *  60001 + py + dirs[d][1])) {
                        break;
                    }
                    px += dirs[d][0];
                    py += dirs[d][1];
                    res = Math.max(res, px*px + py*py);
                }
            }
        }
        return res;
    }

    @Test
    public void obs() {
        int[] commands = {4,-1,4,-2,4};
        int[][] obstacles = {{2,4}};
        int i = robotsSimSolution(commands, obstacles);
        log.info("{}", i);
    }

    //记录下所有的移动路径数据
    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        List<List<Integer>> res = new ArrayList<>();
        if (obstacleGrid[obstacleGrid.length - 1][obstacleGrid[0].length - 1] == 1) {
            return res;
        }
        dfs(obstacleGrid, 0, 0, res);
        return res;
    }

    /**
     * 一直想要的方法。获取到所有的路径。
     *
     * @param grid
     * @param r
     * @param c
     * @param res
     * @return
     */
    private boolean dfs(int[][] grid, int r, int c, List<List<Integer>> res) {
        if (r == grid.length || c == grid[0].length || grid[r][c] == 1) {
            return false;
        }
        if (r == grid.length - 1 && c == grid[0].length - 1) {
            res.add(Arrays.asList(grid.length - 1, grid[0].length - 1));
            return true;
        }
        res.add(Arrays.asList(r,c));
        if (dfs(grid, r + 1, c, res)) {
            return true;
        }
        if (dfs(grid, r, c+1, res)) {
            return true;
        }
        grid[r][c] = 1;
        res.remove(res.size() - 1);
        return false;
    }

    // 回溯。获取到所有的全排列
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        boolean[] flag = new boolean[nums.length];
        Arrays.fill(flag, false);
        List<Integer> path = new ArrayList<>();
        dfsNum(nums, nums.length, 0, path, res, flag);
        return res;
    }

    private void dfsNum(int[] nums, int len, int depth, List<Integer> path,
                        List<List<Integer>> res, boolean[] flag) {
        if (depth == len) {
            res.add(path);
            return;
        }
        for (int i = 0; i < len; i++) {
            if (!flag[i]) {
                ArrayList<Integer> newPath = new ArrayList<>(path);
                newPath.add(nums[i]);

                boolean[] newUsed = new boolean[len];
                System.arraycopy(flag, 0, newUsed, 0, len);
                newUsed[i] = true;

                dfsNum(nums, len, depth + 1, newPath, res, newUsed);
            }
        }
    }

    @Test
    public void genNums() {
        System.out.println(permute(new int[]{1, 2, 3}));
    }

    /**
     * [1,1,2] ==》
     * <p>
     *  [[1,1,2],
     *  [1,2,1],
     *  [2,1,1]]
     * </p>
     *
     * @param nums
     * @return
     */
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // 先排序，让相同的元素靠在一起
        Arrays.sort(nums);
        backtrack(nums, 0);
        return res;
    }

    void backtrack(int[] nums, int start) {
        // 前序位置，每个节点的值都是一个子集
        res.add(new LinkedList<>(track));

        for (int i = start; i < nums.length; i++) {
            // 剪枝逻辑，值相同的相邻树枝，只遍历第一条
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            track.addLast(nums[i]);
            backtrack(nums, i + 1);
            track.removeLast();
        }
    }

    @Test
    public void test() {
        System.out.println(subsetsWithDup(new int[]{1, 1, 2}));
    }

    /**
     * 判断数独的有效性
     * 1.数字 1-9 在每一行只能出现一次。
     * 2.数字 1-9 在每一列只能出现一次。
     * 3.数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
     * @param board
     * @return
     */
    public boolean isValidSudo(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] cols = new int[9][9];
        int[][][] subboxes = new int[ 3][3][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int index = c - '0' - 1;
                    rows[i][index]++;
                    cols[j][index]++;
                    subboxes[i/3][j/3][index]++;
                    if (rows[i][index] > 1 || cols[j][index] > 1 ||
                            subboxes[i][j][index] > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }



    public boolean isValidSudoku(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        // 要动态的表示出啊
        int[][][] subBox = new int[3][3][9];
        // rows
        int[][] rows = new int[9][9];
        // cols
        int[][] cols = new int[9][9];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int index = c - '0';
                    rows[i][index]++;
                    cols[j][index]++;
                    subBox[i/3][j/3][index]++;
                    // 不能有多个的意思！
                    if (rows[i][index] > 1 || cols[j][index] > 1 ||
                            subBox[i][j][index] > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        if(magazine == null || magazine.length() == 0||
                ransomNote == null || ransomNote.length() == 0) {
            return false;
        }
        Map<Character, Integer> map = new HashMap();
        for (char c : magazine.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (char c : ransomNote.toCharArray()) {
            if (!map.containsKey(c)) {
                return false;
            }

            int value = map.get(c) - 1;
            if (value < 0) {
                return false;
            }
            map.put(c, value);
        }
        return true;
    }

    public int[][] insert(int[][] intervals, int[] newIntervals) {
        int n = intervals.length;
        int[][] newInts = new int[n+1][2];
        int index = 0, i = 0;
        while (i < n && intervals[i][1] < newIntervals[0]) {
            newInts[index++] = intervals[i++];
        }
        // 比较
        while (i < n && intervals[i][0] <= newIntervals[1]) {
            newIntervals[0] = Math.min(intervals[i][0], newIntervals[0]);
            newIntervals[1] = Math.min(intervals[i][1], newIntervals[1]);
            i++;
        }
        newInts[index++] = newIntervals;
        // 结尾
        while (i < n) {
            newInts[index++] = intervals[i++];
        }
        return Arrays.copyOf(newInts, index);
    }

    /**
     * 最少的箭引爆所有的气球区间
     * <p>
     *     有的是什么呢？就是说有a[1] < b[0]的时候，就有箭的数量加一了。
     * </p>
     *
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (Comparator.comparingInt(o -> o[1])));
        int cnt = 1;
        int n = points.length;
        int end = points[0][1];
        for (int i = 0; i < n; i++) {
            int start = points[i][0];
            if (start > end) {
                end = points[i][1];
                cnt++;
            }
        }
        return cnt;
    }

    @Test
    public void minShoot() {
//        int[][] point = [[10,16],[2,8],[1,6],[7,12]];
        
    }
}
