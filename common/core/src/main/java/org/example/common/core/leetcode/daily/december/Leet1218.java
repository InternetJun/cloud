package org.example.common.core.leetcode.daily.december;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.example.common.core.util.CommonUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description:
 * @time: 2023/12/18 11:00
 */
@Slf4j
public class Leet1218 {
    /**
     * 数组中有多个波峰，返回任意一个数据就可以了。
     *
     * @param nums
     * @return
     */
    public int findPeekElement(int[] nums) {
        final int length = nums.length;
        final ArrayList<Integer> highList = new ArrayList<>();
        final ArrayList<Integer> lowList = new ArrayList<>();
        for (int i = 0; i < length - 1; i++) {
            int highIndex = 0;
            // 会有多个值
            while (nums[i] < nums[i + 1]) {
                i++;
                highIndex = i;
            }
            if (highIndex != 0) {
                highList.add(highIndex);
            }
        }
        for (int i = length - 1; i >= 1; i--) {
            int lowIndex = length - 1;
            while (nums[i] < nums[i - 1]) {
                i--;
                lowIndex = i;
            }
            // 没有经过判断的就加入了，有问题。/
            if (lowIndex != length - 1) {
                lowList.add(lowIndex);
            }
        }
        log.info("low:{}", lowList);
        log.info("high:{}", highList);
        return -1;
    }

    @Test
    public void test() {
        int[] nums = {1, 2, 1, 3, 5, 6, 4};
        findPeekElement(nums);
    }

    /**
     * 二段性的分析<href>https://leetcode.cn/problems/find-peak-element/solutions/998441/gong-shui-san-xie-noxiang-xin-ke-xue-xi-qva7v/?envType=daily-question&envId=2023-12-18</href>
     * <b>二分法的本质是二段性，说明的是</b>
     * <p>在以 midmidmid 为分割点的数组上，根据 nums[mid]nums[mid]nums[mid] 与 nums[mid±1]nums[mid \pm 1]nums[mid±1] 的大小关系，可以确定其中一段满足「必然有解」，另外一段不满足「必然有解」（可能有解，可能无解）。
     * </p>
     *
     * @param nums
     * @return
     */
    public int findPeekElementLog(int[] nums) {
        return -1;
    }

    public int maxAreaOfIsland(int[][] grid) {
        // 通过统计1的个数来计算
        int m = grid.length;
        int n = grid[0].length;
        Boolean[][] used = new Boolean[m][n];
        for (Boolean[] row : used) {
            Arrays.fill(row, Boolean.FALSE);
        }
        int max = Integer.MIN_VALUE;
        CommonUtil.formatArray(used);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !used[i][j]) {
                    itemArea = 0;
                    dfs(grid, i, j, used);
                    CommonUtil.formatArray(used);
                    log.info("i：{},j:{}, area:{}", i, j, itemArea);
                    max = Math.max(itemArea, max);
                }
            }
        }
        return max;
    }

    private int itemArea = 0;

    /**
     * 计算此处的面积
     *
     * @param grid
     * @param i
     * @param j
     * @param used
     */
    private void dfs(int[][] grid, int i, int j, Boolean[][] used) {
        int m = grid.length;
        int n = grid[0].length;
        if (i >= m || i < 0 || j >= n || j < 0 || used[i][j]) {
            return;
        }
        // 会把所有的海水慢慢的填充上
        if (grid[i][j] == 0){
            return;
        }
        used[i][j] = true;
        ++itemArea;
        // 会在同一层上square失效了？
        dfs(grid, i + 1, j, used);
        dfs(grid, i - 1, j, used);
        dfs(grid, i, j + 1, used);
        dfs(grid, i, j - 1, used);
    }

    @Test
    public void maxArea() {
        String s = "[[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]";
        final int[][] ints = JSONObject.parseObject(s, int[][].class);
//        JSONArray.parse()
        System.out.println(maxAreaOfIsland(ints));
    }
}
