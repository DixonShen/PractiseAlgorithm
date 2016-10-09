package CodeWars;

/**
 * Created by dixonshen on 2016/10/8.
 */
public class LongestSlideDown {
    public static void main(String[] args) {
        int[][] a = {{75},
                {95, 64},
                {17, 47, 82},
                {18, 35, 87, 10},
                {20, 4, 82, 47, 65},
                {19, 1, 23, 75, 3, 34},
                {88, 2, 77, 73, 7, 63, 67},
                {99, 65, 4, 28, 6, 16, 70, 92},
                {41, 41, 26, 56, 83, 40, 80, 70, 33},
                {41, 48, 72, 33, 47, 32, 37, 16, 94, 29},
                {53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14},
                {70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57},
                {91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48},
                {63, 66, 4, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31},
                {4, 62, 98, 27, 23, 9, 70, 98, 73, 93, 38, 53, 60, 4, 23},
        };
        System.out.println(longestSlideDown(a));
    }

    //clever
    public static int longestSlideDown(int[][] pyramid){
        for (int row = pyramid.length-2; row>=0; row--)
            for (int col = 0; col<pyramid[row].length; col++){
                pyramid[row][col] += Math.max(pyramid[row+1][col], pyramid[row+1][col+1]);
            }
        return pyramid[0][0];
    }

    //Brute-force
    public static int longestSlideDown1(int[][] pyramid){
        if (pyramid.length == 0) return 0;
        if (pyramid.length == 1){
            return pyramid[0][0];
        }
        else {
            return pyramid[0][0] + Math.max(longestSlideDown(leftSubPyramid(pyramid)), longestSlideDown(rightSubPyramid(pyramid)));
        }
    }

    public static int[][] leftSubPyramid(int[][] pyramid){
        int[][] subPyramid = new int[pyramid.length-1][pyramid.length-1];
        for (int i=1; i<pyramid.length; i++)
            for (int j=0; j<i; j++){
                subPyramid[i-1][j] = pyramid[i][j];
            }
        return subPyramid;
    }

    public static int[][] rightSubPyramid(int[][] pyramid){
        int[][] subPyramid = new int[pyramid.length-1][pyramid.length-1];
        for (int i=1; i<pyramid.length; i++)
            for (int j = 1; j<= i; j++){
                subPyramid[i-1][j-1] = pyramid[i][j];
            }
        return subPyramid;
    }
}
