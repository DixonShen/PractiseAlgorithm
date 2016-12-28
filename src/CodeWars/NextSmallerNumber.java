package CodeWars;

import java.util.Arrays;

/**
 * Write a function that takes a positive integer and returns the next smaller
 * positive integer containing the same digits.
 * Return -1 , when there is no smaller number that contains the same digits or
 * the next smaller number with the same digits would require the leading digit
 * to be zero.
 * Created by DixonShen on 2016/12/28.
 */
public class NextSmallerNumber {

    public static long nextSmaller(long n){
        int[] digits = decompLong(n);
        if (digits.length == 1) return -1;
        for (int i = digits.length-1 ; i>=0; i--){
            if (i == 0) return -1;
            if (digits[i-1] > digits[i]) {
                for (int j = digits.length-1; j>=i; j--) {
                    if (digits[i-1] > digits[j]) {
                        int temp = digits[j];
                        digits[j] = digits[i-1];
                        digits[i-1] = temp;
                        for (int k=0; k<((digits.length-i)/2); k++){
                            int m = digits[i+k];
                            digits[i+k] = digits[digits.length-1-k];
                            digits[digits.length-1-k] = m;
                        }
                        if (digits[0] == 0) return -1;
                        System.out.println(Arrays.toString(digits));
                        return compLong(digits);
                    }
                }
            }
        }
        System.out.println(Arrays.toString(digits));
        return compLong(digits);
    }

    public static int[] decompLong(long n){
        char[] temp = Long.toString(n).toCharArray();
        int i = 0;
        int[] digits = new int[temp.length];
        for (char ch : temp){
            digits[i] = Integer.parseInt(String.valueOf(ch));
            i++;
        }
        return digits;
    }

    public static long compLong(int[] a){
        String res = "";
        for (int i : a){
            res += i;
        }
        return Long.parseLong(res);
    }

    public static void main(String[] args) {
        System.out.println(nextSmaller(123456798));
    }
}

