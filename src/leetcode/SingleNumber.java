package leetcode;

/**
 * Given an array of integers, every element appears twice except for one. Find that single one.

 Note:
 Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * https://leetcode.com/problems/single-number/description/
 * Created by dixonshen on 2017/9/4.
 */
public class SingleNumber {

    public static int singleNumber(int[] nums) {
        for (int i=0; i < nums.length-1; i++) {
            nums[i+1] = nums[i] ^ nums[i+1];
        }
        return nums[nums.length-1];
    }

    public static void main(String[] args) {
        int[] data1 = {2, 3, 6, 3, 2, 5, 5};
        System.out.println(singleNumber(data1));
    }
}
