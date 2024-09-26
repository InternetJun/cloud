package org.example.common.core.leetcode.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/8/30 9:36
 */
@Slf4j
public class MidAlg {
    /**
     * 寻找目标的位置。
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[] ints = new int[m * n];
        for (int i = 0; i < m; i++) {
            // 对数组的复制。
        }
        return false;
    }

    public boolean findTargetByMidSearch(int[] arr, int target, int left, int right) {

        while(left <= right) {
            int mid = left + (right - left)/2;
            if (arr[mid] > target) {
                right = mid-1;
            } else if (arr[mid] == target) {
                return true;
            } else {
                left = mid+1;
            }
        }
        findTargetByMidSearch(arr, target, left, right);
        findTargetByMidSearch(arr, target, left, right);
        return false;
    }

    @Test
    public void main() {
        int[] matrix = {1,3,4, 10,11,20, 33,34,60};
        System.out.println(findTargetByMidSearch(matrix, 3, 0, matrix.length - 1));
    }

    public int binarySearchFirstColumn(int[][] matrix, int target) {
        int len = matrix.length;
        int left = 0;
        while (left <= len -1) {
            int mid = (left + len) / 2;
            if (matrix[mid][0] <= target) {
                left = mid;
            } else {
                len = mid - 1;
            }
        }
        return left;
    }

    /**
     * 螺旋输出所有的数据。
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int left = 0, right = matrix[0].length-1,
        bottom = matrix.length-1, upper = 0;

        List<Integer> list = new LinkedList<>();
        if (matrix == null) {
            return null;
        }
        while (true) {
            // left --> right
            for (int i = left; i <= right; i++) {
                list.add(matrix[upper][i]);
            }
            // 下一个步骤
            if (++upper > bottom) {
                break;
            }
            // upper --> bottom
            for (int i = upper; i <= bottom; i++) {
                list.add(matrix[i][right]);
            }
            if (left > --right) {
                break;
            }
            // right --> left
            for (int i = right; i >= left; i--) {
                list.add(matrix[bottom][i]);
            }
            if (upper > --bottom) {
                break;
            }
            // bottom --> upper
            for (int i = bottom; i >= upper; i--) {
                list.add(matrix[i][left]);
            }
            if (++left > right) {
                break;
            }
        }
        return list;
    }

    @Test
    public void sout() {
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        List<Integer> integers = spiralOrder(matrix);
        log.info("{}", integers);
    }

}
