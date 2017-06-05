package leetcode;

/**
 * Created by DixonShen on 2017/6/5.
 */
public class MaximumSubarray {

    // 时间复杂度 O(N)
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

    /**
     * 递归版本，待完成
     * @param nums
     * @return
     */
    public static int maxSubSum2(int[] nums) {
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(maxSubSum(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
}
