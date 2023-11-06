package org.example.common.core.util;

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
}