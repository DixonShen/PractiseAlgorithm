package CodeWars;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by dixonshen on 2016/10/18.
 * The number 81 has a special property,
 * a certain power of the sum of its digits is equal to 81 (nine squared).
 * Eighty one (81), is the first number in having this property (not considering numbers of one digit).
 * The next one, is 512. Let's see both cases with the details
    8 + 1 = 9 and 92 = 81
    512 = 5 + 1 + 2 = 8 and 83 = 512
 * We need to make a function, power_sumDigTerm(),
 * that receives a number n and may output the n-th term of this sequence of numbers.
 * The cases we presented above means that
    power_sumDigTerm(1) == 81
    power_sumDigTerm(2) == 512
 */
public class PowerSumDig {

    //best practise
    public static long powerSumDigTerm(int n){
        ArrayList<Long> result = new ArrayList<Long>();
        for (int i=2; i<400 ;i++){
            long value = i;
            for (int j=2; j<20; j++){
                value *= i;
                if (digitSum(value) == i)
                    result.add(value);
            }
        }
        Long[] res = result.toArray(new Long[0]);
        Arrays.sort(res);
        return res[n-1];
    }

    /**
     * calculate the sum of digits
     * @param n
     * @return
     */
    public static long digitSum(long n){
        if (String.valueOf(n).contains("-")) return 0;
        long sum = 0;
        char[] chs = String.valueOf(n).toCharArray();
        for (char ch : chs){
            sum += Long.parseLong(String.valueOf(ch));
        }
        return sum;
    }

    //brute force
    public static long powerSumDigTerm1(int n){
        if (n == 1) return 81;
        if (n == 2) return 512;
        long resCount = 513;
        for (int i=3; i<=n; i++){
            for (; ;resCount++){
                char[] chs = String.valueOf(resCount).toCharArray();
                long sum = 0;
                for (char ch : chs){
                    sum += Long.parseLong(String.valueOf(ch));
                }
                if (sum == 1) continue;
                long product = 1;
                while (true){
                    product *= sum;
                    if (product >= resCount) break;
                }
                if (product == resCount) {
                    resCount++;
                    break;
                }
                if (product > resCount) continue;
            }
        }
        return resCount-1;
    }

    public static void main(String[] args) {
//        for (int i=1; i<=15; i++){
//        System.out.println(powerSumDigTerm(2));
//        }
        System.out.println(powerSumDigTerm(4));
    }
}
