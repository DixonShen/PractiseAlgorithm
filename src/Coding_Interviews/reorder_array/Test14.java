package Coding_Interviews.reorder_array;

import utils.ArrayUtil;

/**
 * Created by dixonshen on 2017/7/26.
 */
public class Test14 {

    public static void reOrderArray(int [] array) {
        int p = -1;
        for (int i=0; i<array.length; i++) {
            if (!isEven(array[i])) {
                int temp = array[i];
                for (int j=i-1; j>p; j--) {
                    array[j+1] = array[j];
                }
                p++;
                array[p] = temp;
            }
        }
    }

    public static boolean isEven(int n) {
        return (n & 1) == 0;
    }

    public static void main(String[] args) {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        reOrderArray(array);
        ArrayUtil.printArray(array);
    }
}
