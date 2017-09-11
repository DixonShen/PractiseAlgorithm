package Coding_Interviews.dices_probability;

/**
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s 的所有可能的值出现的概率。
 * Created by dixonshen on 2017/9/11.
 */
public class Test46 {

    public static int maxValue = 4;

    /**
     * 递归求法，时间复杂度高
     * @param n
     */
    public static void printProbabilities(int n) {
        if (n < 1)
            return;
        int maxSum = maxValue * n;
        int[] counts = new int[maxSum - n + 1];
        for (int i=0; i<counts.length; i++) {
            counts[i] = 0;
        }
        for (int i=1; i<=maxValue; i++) {
            probability(n, n, i, counts);
        }

        int total = 1;
        for (int i=1; i<=n; i++)
            total *= maxValue;
        for (int i=n; i<=maxSum; i++){
            double ratio = (double)counts[i-n] / total;
            System.out.println(i + ": " + ratio);
        }

    }

    public static void probability(int original, int current, int sum, int[] counts) {
        if (current == 1) {
            counts[sum-original]++;
        } else {
            for (int i=1; i<=maxValue; i++) {
                probability(original, current-1, i + sum, counts);
            }
        }
    }

    /**
     * 循环解法，时间复杂度低
     * 使用两个数组来存储骰子点数的每一个总数出现的次数
     * @param number
     */
    public static void printProbabilities2(int number) {
        if (number < 1)
            return;
        int[][] counts = new int[2][number * maxValue + 1];
        for (int i=0; i<number * maxValue + 1; i++) {
            counts[0][i] = 0;
            counts[1][i] = 0;
        }
        int flag = 0;
        for (int i=1; i<=maxValue; i++) {
            counts[flag][i] = 1;
        }
        for (int k=2; k<=number; k++) {
            for (int i=0; i<k; i++)
                counts[1-flag][i] = 0;
            for (int i=k; i <= k * maxValue; i++) {
                for (int j=1; j<=i && j<=maxValue; j++) {
                    counts[1-flag][i] += counts[flag][i-j];
                }
            }
            flag = 1 - flag;
        }
        int total = 1;
        for (int i=1; i<=number; i++)
            total *= maxValue;
        for (int i=number; i<=maxValue * number; i++){
            double ratio = (double)counts[flag][i] / total;
            System.out.println(i + ": " + ratio);
        }
    }

    public static void main(String[] args) {
        printProbabilities2(2);
    }
}
