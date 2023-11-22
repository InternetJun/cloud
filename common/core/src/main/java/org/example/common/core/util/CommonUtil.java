package org.example.common.core.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
}