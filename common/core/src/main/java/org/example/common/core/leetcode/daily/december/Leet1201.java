package org.example.common.core.leetcode.daily.december;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/12/1 10:18
 */
@Slf4j
public class Leet1201 {
    /**
     * <h1>用Map进行存储</h1>
     * <p>
     * 从左到右遍历arr，检查arr[i]在mat的位置[x, y]，
     * x行到目前为止是不是已经涂了mat列的数量个格子，
     * 或者y列到目前为止是不是已经涂了mat行的数量个格子，如果是，返回 i
     * </p>
     * <p>
     * arr = {1 3 4 2}
     * arr[0] = 1 ==> (0 0)
     * arr[1] = 3 ==> (1 1)
     * arr[2] = 4 ==> (0 1)
     * arr[3] = 2 ==> (1 0)
     * <br>
     * 1  4
     * 2  3
     * <br>
     * x或者y 有元素被着色。
     * </p>
     * <p>
     * 普通的方法，用map进行存储数据。
     * </p>
     *
     * @param arr
     * @param mat
     * @return
     */
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        //1~n*m的范围；记录每个元素的下标位置。
        int len = m * n;
        /** 思路，可以对二维数组进行一个unionFind处理。
         * a[0]~a[m-1]的所有元素都被设置为a[i]
         */
        final Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map.put(mat[i][j], new int[]{i,j});
            }
        }
        int[] c1 = new int[m];
        int[] c2 = new int[n];
        for (int i = 0; i < len; i++) {
            int[] info = map.get(arr[i]);
            int x = info[0], y = info[1];
            if (++c1[x] == n || ++c2[y] == m) {
                return i;
            }
        }
        return -1;
    }

    /**
     *<h1>不使用map进行存储</h1>
     *
     * @param arr
     * @param mat
     * @return
     */
    public int firstCompleteIndexNoMap(int[] arr, int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] pos = new int[m * n + 1][2];
        int[] col = new int[n];
        int[] row = new int[m];

        Arrays.fill(col, 0);
        Arrays.fill(row, 0);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                pos[mat[i][j]] = new int[]{i, j};
            }
        }
        for (int i = 0; i < arr.length; i++) {
            int[] position = pos[arr[i]];

            if (++row[position[0]] == n) {
                return i;
            }

            if (++col[position[1]] == m) {
                return i;
            }
        }
        return 0;
    }


}
