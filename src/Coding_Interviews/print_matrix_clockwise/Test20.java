package Coding_Interviews.print_matrix_clockwise;

import utils.ArrayUtil;

import java.util.ArrayList;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次扫印出每一个数字
 * Created by dixonshen on 2017/8/16.
 */
public class Test20 {

    public static ArrayList<Integer> printMatrix(int [][] matrix) {
        if (matrix == null)
            return null;
        ArrayList<Integer> result = new ArrayList<>();
        result.ensureCapacity(matrix.length * matrix[0].length);
        int start = 0;
        while (start*2 < matrix.length && start*2 < matrix[0].length) {
            printMatrixInCircle(matrix, result, start);
            start++;
        }
        return result;
    }

    public static void printMatrixInCircle(int[][] matrix, ArrayList res, int start) {
        int endX = matrix[0].length - 1 - start;
        int endY = matrix.length - 1 - start;
        for (int i = start; i<=endX; i++) {
            res.add(matrix[start][i]);
        }
        if (start < endY) {
            for (int i=start+1; i<=endY; i++)
                res.add(matrix[i][endX]);
        }
        if (start < endX && start < endY) {
            for (int i=endX-1; i>=start; i--)
                res.add(matrix[endY][i]);
        }
        if (start < endX && start < endY-1){
            for (int i=endY; i>=start+1; i--)
                res.add(matrix[i][start]);
        }
    }

    public static void main(String[] args) {
        int[][] numbers = {
                {1, 2, 3, 4, 5},
                {16, 17, 18, 19, 6},
                {15, 24, 25, 20, 7},
                {14, 23, 22, 21, 8},
                {13, 12, 11, 10, 9},
        };
        ArrayList<Integer> res1 = printMatrix(numbers);
        for (int a : res1)
            System.out.print(a + " ");
        System.out.println();

        int[][] numbers2 = {
                {1, 2, 3, 4, 5, 6, 7, 8},
                {22, 23, 24, 25, 26, 27, 28, 9},
                {21, 36, 37, 38, 39, 40, 29, 10},
                {20, 35, 34, 33, 32, 31, 30, 11},
                {19, 18, 17, 16, 15, 14, 13, 12},

        };
        ArrayList<Integer> res2 = printMatrix(numbers2);
        for (int a : res2)
            System.out.print(a + " ");
        System.out.println();


        int[][] numbers3 = {
                {1, 2, 3, 4, 5, 6, 7, 8}
        };
        ArrayList<Integer> res3 = printMatrix(numbers3);
        for (int a : res3)
            System.out.print(a + " ");
        System.out.println();

        int[][] numbers4 = {
                {1, 2, 3, 4, 5, 6, 7, 8},
                {16, 15, 14, 13, 12, 11, 10, 9}
        };
        ArrayList<Integer> res4 = printMatrix(numbers4);
        for (int a : res4)
            System.out.print(a + " ");
        System.out.println();


        int[][] numbers5 = {
                {1},
                {2},
                {3},
                {4},
                {5},
                {6},
                {7},
                {8}
        };
        ArrayList<Integer> res5 = printMatrix(numbers5);
        for (int a : res5)
            System.out.print(a + " ");
        System.out.println();

        int[][] numbers6 = {
                {0, 1},
                {15, 2},
                {14, 3},
                {13, 4},
                {12, 5},
                {11, 6},
                {10, 7},
                {9, 8}
        };
        ArrayList<Integer> res6 = printMatrix(numbers6);
        for (int a : res6)
            System.out.print(a + " ");
        System.out.println();


        int[][] numbers7 = {
                {1, 2},
                {4, 3}
        };
        ArrayList<Integer> res7 = printMatrix(numbers7);
        for (int a : res7)
            System.out.print(a + " ");
        System.out.println();

        int[][] numbers8 = {
                {1}
        };
        ArrayList<Integer> res8 = printMatrix(numbers8);
        for (int a : res8)
            System.out.print(a + " ");
        System.out.println();

        // 0个元素的数组
        ArrayList<Integer> res9 = printMatrix(new int[][] {{}});
        for (int a : res9)
            System.out.print(a + " ");
        // 空数组
        ArrayList<Integer> res10 = printMatrix(null);
        if (res10 != null) {
            for (int a : res2)
                System.out.print(a + " ");
        }

    }
}
