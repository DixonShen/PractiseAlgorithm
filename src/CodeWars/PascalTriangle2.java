package CodeWars;

import java.util.Arrays;

/**
 * Created by DixonShen on 2016/10/12.
 *
 * Here you will create the classic pascal's triangle.
 * Your function will be passed the depth of the triangle
 * and you code has to return the corresponding pascal triangle upto
 * that depthThe triangle should be returned as a nested array.
 * for example:
 * PascalsTriangle.pascal(5); // should return [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * To build the triangle, start with a single 1 at the top, for each number in the next row you just take the two numbers above it and add them together (except for the edges, which are all "1"). eg

      [1]
    [1   1]
   [1  2  1]
  [1  3  3  1]
 */
public class PascalTriangle2 {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(pascal(5)));
    }

    public static int[][] pascal(int depth){
        if (depth < 1) return new int[][] {{ }};
        int[][] triangle = new int[depth][];
        for (int i=0; i< depth; i++ ) {
            triangle[i] = new int[i+1];
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    triangle[i][j] = 1;
                    continue;
                }
                if (j == i) {
                    triangle[i][j] = 1;
                    continue;
                }
                triangle[i][j] = triangle[i - 1][j - 1] + triangle[i - 1][j];
            }
        }
        return triangle;
    }
}
