package Coding_Interviews.numbers_appear_once;

/**
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次，请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 * Created by dixonshen on 2017/9/4.
 */
public class Test41 {

    public static void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        if (array == null || array.length < 2)
            return;
        int tempNum = 0;
        for (int i=0; i<array.length; i++)
            tempNum = tempNum ^ array[i];
        int index = findFirstBitIs1(tempNum);
        for (int i=0; i<array.length; i++) {
            if (isBit1(array[i], index) == 0)
                num1[0] = num1[0] ^ array[i];
            else
                num2[0] = num2[0] ^ array[i];
        }
    }

    public static int findFirstBitIs1(int number) {
        int index = 0;
        while ((number & 1) == 0 && index < 32) {
            number = number >> 1;
            index++;
        }
        return index;
    }

    public static int isBit1(int number, int index) {
        number = number >> index;
        return (number & 1);
    }

    public static void main(String[] args) {
        int[] data1 = {2, 4, 3, 6, 3, 2, 5, 5};
        int[] num1 = {0};
        int[] num2 = {0};
        FindNumsAppearOnce(data1, num1, num2);
        System.out.println(num1[0]);
        System.out.println(num2[0]);
    }
}
