package utils;

import java.util.Collection;

/**
 * Created by dixonshen on 2017/7/26.
 */
public class ArrayUtil {

    /**
     * print array
     * @param arr
     */
    public static void printArray(int[] arr) {
        if (arr != null && arr.length > 0) {
            for (int i : arr) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
