package leetcode;

/**
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
   Do not allocate extra space for another array, you must do this in place with constant memory.
     For example,
     Given input array nums = [1,1,2],
     Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.
 * @see <a href="https://leetcode.com/problems/remove-duplicates-from-sorted-array/#/description"> https://leetcode.com/problems/remove-duplicates-from-sorted-array/#/description </a>
 * Created by dixonshen on 2017/7/5.
 */
public class RemoveDuolicates {

    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int count = 1;
        int p = 1, q = 0;
        for (;p<nums.length;) {
            if (nums[p] != nums[q]) {
                q++;
                swap(nums, p, q);
                p++;
                count++;
            } else {
                p++;
            }
        }

        return count;
    }

    public static void swap(int[] a, int p, int q) {
        int temp = a[p];
        a[p] = a[q];
        a[q] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 2, 2, 3, 4, 4, 5, 6, 6, 6};
        System.out.println(removeDuplicates(nums));
        for (int i : nums)
            System.out.print(i + " ");
    }
}
