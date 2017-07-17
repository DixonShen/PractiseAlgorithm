package Coding_Interviews.min_in_rotate_array;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾， 我们称之数组的旋转。
 * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3, 4, 5, 1, 2｝为｛l ,2, 3, 4, 5}的一个旋转，该数组的最小值为
 * Created by dixonshen on 2017/7/13.
 */
public class Test08 {

    public static int minNumberInRotateArray(int [] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int lo = 0;
        int hi = array.length-1;
        int mi = lo;
//        if (array[lo] == array[hi])
//            return array[lo];
        while (array[lo] >= array[hi]) {
            if (hi-lo == 1) return array[hi];
            mi = (lo+hi)/2;

            // 当位于lo、mi、hi的三个数都相等时，无法判断中间数位于前面的子数组还是后面的子数组，需要进行顺序处理，从头到尾找到最小的值
            if (array[lo] == array[hi] && array[hi] == array[mi])
                return minInOrder(array, lo, hi);

            if (array[mi] >= array[lo])
                lo = mi;
            if (array[mi] <= array[hi])
                hi = mi;
        }
        return array[mi];
    }

    public static int minInOrder(int[] array, int lo, int hi){
        int res = array[lo];
        for (int i=lo+1; i<array.length; i++) {
            if (res > array[i])
                res = array[i];
        }
        return res;
    }

    public static void main(String[] args) {
        // 典型输入，单调升序的数组的一个旋转
        int[] array1 = {3, 4, 5, 1, 2};
        System.out.println(minNumberInRotateArray(array1));

        // 有重复数字，并且重复的数字刚好的最小的数字
        int[] array2 = {3, 4, 5, 1, 1, 2};
        System.out.println(minNumberInRotateArray(array2));

        // 有重复数字，但重复的数字不是第一个数字和最后一个数字
        int[] array3 = {3, 4, 5, 1, 2, 2};
        System.out.println(minNumberInRotateArray(array3));

        // 有重复的数字，并且重复的数字刚好是第一个数字和最后一个数字
        int[] array4 = {1, 0, 1, 1, 1};
        System.out.println(minNumberInRotateArray(array4));

        // 单调升序数组，旋转0个元素，也就是单调升序数组本身
        int[] array5 = {1, 2, 3, 4, 5};
        System.out.println(minNumberInRotateArray(array5));

        // 数组中只有一个数字
        int[] array6 = {2};
        System.out.println(minNumberInRotateArray(array6));

        // 数组中数字都相同
        int[] array7 = {1, 1, 1, 1, 1, 1, 1};
        System.out.println(minNumberInRotateArray(array7));
        System.out.println(minNumberInRotateArray(array6));

        // 输入NULL
        System.out.println(minNumberInRotateArray(null));
    }
}
