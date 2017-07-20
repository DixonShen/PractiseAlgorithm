package Coding_Interviews.print_numbers_to_Ndigits;

import java.util.Arrays;

/**
 * 输入数字n，按顺序打印出从1最大的n位十进制数。比如输入3，则打印出1、2、3 一直到最大的3位数即999。
 * Created by dixonshen on 2017/7/20.
 */
public class Test12 {

    public static void print1ToMaxOfNDigits(int n) {
        if (n <= 0) {
            return;
        }

        char[] number = new char[n];
        for (int i=0; i<number.length; i++) {
            number[i] = '0';
        }

        while (!increment(number)) {
            printNumber(number);
        }
    }

    /**
     * 字符数组表示数字，模拟加法
     * @param number
     * @return
     */
    public static boolean increment(char[] number) {
        boolean isOverflow = false;
        int takeOver = 0;   // 进位
        int length = number.length;
        for (int i=length-1; i>=0; i--) {
            int sum = number[i] - '0' + takeOver;
            if (i == length-1)
                sum++;
            if (sum >=10 ) {
                if (i == 0)
                    isOverflow = true;
                else {
                    sum -= 10;
                    takeOver = 1;
                    number[i] = (char) ('0' + sum);
                }
            } else {
                number[i] =(char) ('0' + sum);
                break;
            }
        }
        return isOverflow;
    }

    public static void printNumber(char[] number) {
        int index = 0;
        while (index<number.length && number[index] == '0')
            index++;
//        if (index >= number.length)
//            return;
        for (int i=index; i<number.length; i++){
            System.out.print(number[i]);
        }
        System.out.println();
    }

    /**
     * 数字排列解法
     * @param n
     */
    public static void print1ToMaxOfNDigits2(int n) {
        if (n<=0)
            return;

        char[] number = new char[n];
        for (int i=0; i<number.length; i++)
            number[i] = '0';

        for (int i=0; i<10; i++) {
            number[0] = (char)('0' + i);
            print1ToMaxOfNDigitsRecursively(number, n, 0);
        }

    }

    public static void print1ToMaxOfNDigitsRecursively(char[] number, int length, int index) {
        if (index == length-1){
            printNumber(number);
            return;
        }

        for (int i=0; i<10; i++) {
            number[index+1] = (char) ('0' + i);
            print1ToMaxOfNDigitsRecursively(number, length, index+1);
        }
    }

    public static void main(String[] args) {
        print1ToMaxOfNDigits2(5);
    }
}
