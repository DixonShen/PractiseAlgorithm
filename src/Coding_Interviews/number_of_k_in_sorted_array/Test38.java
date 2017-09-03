package Coding_Interviews.number_of_k_in_sorted_array;

/**
 * 统计一个数字：在排序数组中出现的次数。
 * Created by dixonshen on 2017/9/3.
 */
public class Test38 {

    public static int getNumberOfK(int [] array , int k) {
        int count = 0;
        if (array != null || array.length != 0) {
            int first = getFirstK(array, k, 0, array.length-1);
            int last = getLastK(array, k, 0, array.length-1);
            if (first != -1 && last != -1) {
                count = last - first + 1;
            }
        }
        return count;
    }

    public static int getFirstK(int[] array, int k, int start, int end) {
        if (start > end)
            return -1;

        int mid = (start + end) >> 1;
        int midData = array[mid];
        if (midData == k) {
            if ((mid > 0 && array[mid-1] != k) || mid == 0)
                return mid;
            else
                end = mid - 1;
        } else if (midData < k)
            start = mid + 1;
        else
            end = mid - 1;
        return getFirstK(array, k, start, end);
    }

    public static int getLastK(int[] array, int k, int start, int end) {
        if (start > end)
            return -1;

        int mid = (start + end) >> 1;
        int midData = array[mid];

        if (midData == k) {
            if ((mid < array.length-1 && array[mid+1] != k) || mid == array.length-1)
                return mid;
            else
                start = mid + 1;
        } else if (midData < k)
            start = mid + 1;
        else
            end = mid - 1;
        return getLastK(array, k, start, end);
    }

    public static void main(String[] args) {
        // 查找的数字出现在数组的中间
        int[] data1 = {1, 2, 3, 3, 3, 3, 4, 5};
        System.out.println(getNumberOfK(data1, 3)); // 4

        // 查找的数组出现在数组的开头
        int[] data2 = {3, 3, 3, 3, 4, 5};
        System.out.println(getNumberOfK(data2, 3)); // 4

        // 查找的数组出现在数组的结尾
        int[] data3 = {1, 2, 3, 3, 3, 3};
        System.out.println(getNumberOfK(data3, 3)); // 4

        // 查找的数字不存在
        int[] data4 = {1, 3, 3, 3, 3, 4, 5};
        System.out.println(getNumberOfK(data4, 2)); // 0

        // 查找的数字比第一个数字还小，不存在
        int[] data5 = {1, 3, 3, 3, 3, 4, 5};
        System.out.println(getNumberOfK(data5, 0)); // 0

        // 查找的数字比最后一个数字还大，不存在
        int[] data6 = {1, 3, 3, 3, 3, 4, 5};
        System.out.println(getNumberOfK(data6, 0)); // 0

        // 数组中的数字从头到尾都是查找的数字
        int[] data7 = {3, 3, 3, 3};
        System.out.println(getNumberOfK(data7, 3)); // 4

        // 数组中的数字从头到尾只有一个重复的数字，不是查找的数字
        int[] data8 = {3, 3, 3, 3};
        System.out.println(getNumberOfK(data8, 4)); // 0

        // 数组中只有一个数字，是查找的数字
        int[] data9 = {3};
        System.out.println(getNumberOfK(data9, 3)); // 1

        // 数组中只有一个数字，不是查找的数字
        int[] data10 = {3};
        System.out.println(getNumberOfK(data10, 4)); // 0
    }
}
