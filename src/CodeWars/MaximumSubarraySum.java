package CodeWars;

/**The maximum sum subarray problem consists in finding
 * the maximum sum of a contiguous subsequence in an array or list of integers:
    Max.sequence(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});  should be 6: {4, -1, 2, 1}
 *Easy case is when the list is made up of
 * only positive numbers and the maximum sum is the sum of the whole array.
 * If the list is made up of only negative numbers, return 0 instead.
 *Empty list is considered to have zero greatest sum.
 * Note that the empty list or array is also a valid sublist/subarray.
 * Created by DixonShen on 2016/9/13.
 */
public class MaximumSubarraySum {

    /**
     * @param arr
     * @return
     */
    public static int MySequence(int[] arr){

        if (arr.length == 0) return 0;
        int maxSum = 0;
        int flag = 0;

        //make sure if the arr is made up of only positive numbers or negative numbers
        for (int a: arr){
            if (a>0) flag++;
            if (a<0) flag--;
            maxSum += a;
        }
        if (flag == arr.length){
            return maxSum;
        }
        if (-flag == arr.length) return 0;

        for (int i=0; i< arr.length; i++){
            for (int j=1; j <= arr.length-i; j++){
                int sum = 0;
                for (int k=0; k<j; k++){
                    sum += arr[k+i];
                }
                if (sum > maxSum) maxSum = sum;
            }
        }
        return maxSum;
    }

    public static int sequence(int[] arr){
        int max_ending_here = 0, max_so_far = 0;
        for (int v : arr){
            max_ending_here = Math.max(0, max_ending_here + v);
            max_so_far = Math.max(max_so_far, max_ending_here);
        }
        return max_so_far;
    }

    public static void main(String[] args) {
        int[] a = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(sequence(a));
    }
}
