package Coding_Interviews.greatest_sum_of_subarrays;

/**
 * Created by dixonshen on 2017/8/27.
 */
public class Test31 {

    public static int maxSubSum(int[] nums) {
        int maxSum = nums[0];
        int thisSum = nums[0];
        for (int i=1; i<nums.length; i++) {
            thisSum = thisSum + nums[i];
            if (thisSum < nums[i])
                thisSum = nums[i];
            if (thisSum > maxSum)
                maxSum = thisSum;

        }
        return maxSum;
    }

    public static void main(String[] args) {

    }
}
