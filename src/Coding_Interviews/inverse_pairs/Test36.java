package Coding_Interviews.inverse_pairs;

/**
 * 在数组中的两个数字如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 * Created by dixonshen on 2017/9/2.
 */
public class Test36 {

    public static int InversePairs(int [] array) {
        if (array == null || array.length == 0)
            return 0;
        int[] temp = new int[array.length];
        return (int)(InversePairs(array, temp,0, array.length-1)%1000000007);
    }

    public static long InversePairs(int[] array, int[] temp, int start, int end) {
        long count = 0;
        if (start == end) {
            temp[start] = array[start];
            return count;
        }
        int mid = (end + start) >> 1;
        long left = InversePairs(array, temp, start, mid);
        long right = InversePairs(array, temp, mid+1, end);
        int p = mid;
        int q = end;
        int r = end;
        while (p>=start && q>=mid+1) {
            if (array[p] > array[q]) {
                temp[r--] = array[p--];
                count += q-mid;
            } else {
                temp[r--] = array[q--];
            }
        }
        while (p>=start)
            temp[r--] = array[p--];
        while (q>mid)
            temp[r--] = array[q--];

        for (int i=start; i<=end; i++) {
            array[i] = temp[i];
        }

        return count + left + right;
    }

    public static void main(String[] args) {
        int[] data = {7, 5, 6, 4};
        System.out.println(InversePairs(data)); // 3
        int[] data2 = {6, 5, 4, 3, 2, 1};
        System.out.println(InversePairs(data2)); //  15
        int[] data3 = {1, 2, 3, 4, 5, 6};
        System.out.println(InversePairs(data3)); // 0
        int[] data4 = {1};
        System.out.println(InversePairs(data4)); // 0
        int[] data5 = {1, 2};
        System.out.println(InversePairs(data5)); // 0
        int[] data6 = {2, 1};
        System.out.println(InversePairs(data6)); // 1
        int[] data7 = {1, 2, 1, 2, 1};
        System.out.println(InversePairs(data7)); // 3
    }
}
