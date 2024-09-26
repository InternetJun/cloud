package org.example.common.core.leetcode.daily.december;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description: 1~1000的落差，然后进行回溯。
 * @time: 2023/12/11 10:50
 */
public class Leet1211 {

    private int minEffort = Integer.MAX_VALUE;

    /**
     * <h1>可达路径的最小体力</h1>
     *
     * @param height
     * @return
     */
    public int minimumEffortPathMe(int[][] height) {
        dfs(height, 0, 0, 0);
        return minEffort;
    }

    /**
     * 边界条件是什么？
     *
     * @param height
     * @param col
     * @param row
     * @param minTemp
     */
    public void dfs(int[][] height, int col, int row, int minTemp) {
        int m = height.length;
        int n = height[0].length;
        if (col < 0 || col >= n || row < 0 || row >= m) {
            return;
        }
        // 到达终点，比较
        if (col == n - 1 && row == m - 1) {
            minEffort = Math.min(minTemp, minEffort);
        }

        dfs(height, col + 1, row, col + 1 < n ? Math.max(minTemp, Math.abs(height[row][col + 1] - height[row][col])) : minTemp);
        dfs(height, col - 1, row, col - 1 > 0 ? Math.max(minTemp, Math.abs(height[row][col - 1] - height[row][col])) : minTemp);
        dfs(height, col, row + 1, row + 1 < m ? Math.max(minTemp, Math.abs(height[row + 1][col] - height[row][col])) : minTemp);
        dfs(height, col, row - 1, row - 1 > 0  ? Math.max(minTemp, Math.abs(height[row - 1][col] - height[row][col])) : minTemp);


        // 会有溢出的风险。需要进行判断处理。
//        if (col+1 < n) {
//            dfs(height, col + 1, row, Math.max(minTemp, Math.abs(height[row][col + 1] - height[row][col])));
//        } else {
//            dfs(height, col + 1, row, minTemp);
//        }
//        if (col-1 >= 0) {
//            dfs(height, col-1, row, Math.max(minTemp, Math.abs(height[row][col-1]-height[row][col])));
//        } else {
//            dfs(height, col - 1, row, minTemp);
//        }
//        if (row+1 < m) {
//            dfs(height, col, row + 1, Math.max(minTemp, Math.abs(height[row + 1][col] - height[row][col])));
//        } else {
//            dfs(height, col, row+1, minTemp);
//        }
//        if (row-1 >= 0) {
//            dfs(height, col, row - 1, Math.max(minTemp, Math.abs(height[row - 1][col] - height[row][col])));
//        } else {
//            dfs(height, col, row-1, minTemp);
//        }
    }

    @Test
    public void main() {
        final int[][] ints = JSONObject.parseObject("[[1,2,2],[3,8,2],[5,3,5]]", int[][].class);
        System.out.println(minimumEffortPath(ints));
    }

    int[][] directions = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    int ans = Integer.MAX_VALUE;
    int rows;
    int cols;
    public int minimumEffortPath(int[][] heights) {
        rows = heights.length;
        cols = heights[0].length;
        int left = 0;
        int right = 1000000;
        while(left<right){
            int mid = (right+left)/2;
            //更新左右边界
            if(dfs(heights,0,0,mid,new boolean[rows][cols])){
                right = mid;
            }else{
                left = mid+1;
            }
        }
        return left;
    }
    //dfs寻找是否存在一条连通左上角和右下角结点的道路
    public Boolean dfs(int[][] heights,int row,int col,int mid,boolean[][] visited){
        if(row==rows-1 && col==cols-1){
            return true;
        }
        // 是为了
        visited[row][col] = true;
        for(int[] dir:directions){
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if(newRow>=0&&newRow<rows&&newCol>=0&&newCol<cols &&
                    !visited[newRow][newCol] &&
                    Math.abs(heights[newRow][newCol]-heights[row][col])<=mid){
                if(dfs(heights,newRow,newCol,mid,visited)) {
                    return true;
                }
            }
        }
        return false;
    }

}
