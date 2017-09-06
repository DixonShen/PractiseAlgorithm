package Coding_Interviews.continues_sequence_with_sum;

import java.util.ArrayList;

/**
 * 输入一个正数s，打印出所有和为s 的连续正数序列（至少两个数）。
 * Created by dixonshen on 2017/9/6.
 */
public class Test43 {

    public static ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (sum <= 1) return res;
        int small = 1, big = 2;
        int tempSum = small + big;
        while (true) {
            if (small == big) break;
            if (tempSum == sum) {
                ArrayList<Integer> tempRes = new ArrayList<>();
                for (int i=small; i<=big; i++) {
                    tempRes.add(i);
                }
                res.add(tempRes);
                tempSum = tempSum - (small++);
            } else if (tempSum > sum) {
                tempSum = tempSum - (small++);
            } else
                tempSum = tempSum + (++big);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(FindContinuousSequence(9));
    }
}
