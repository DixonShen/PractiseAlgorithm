package CodeWars;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by DixonShen on 2016/10/12.
 * Given a positive number n > 1 (Javascript, PHP: n >= 0) find the prime factor decomposition of n.
 * The result will be a string with the following form :
    "(p1**n1)(p2**n2)...(pk**nk)"
 * with the p(i) in increasing order and n(i) empty if n(i) is 1.
 * Example: n = 86240 should return "(2**5)(5)(7**2)(11)"
 */
public class PrimeDecomp {

    public static void main(String[] args) {
        System.out.println(factorsBetter(777696));
    }

    /**
     * @param n
     * @return if n is a prime return true,else return false
     */
    public static boolean isPrime(long n){
        for (long i = 2; i<=(int)Math.sqrt(n); i++){
            if (n%i==0) {
                return false;
            }
        }
        return true;
    }

    //better practise
    public static String factorsBetter(int n){
        String result = "";
        for (int fac = 2; fac <= n; fac++ ){
            int count;
            for (count = 0; n%fac==0; count++){
                n /= fac;
            }
            if (count>0) result += "(" + fac + (count>1 ? "**" + count : "") + ")";
        }
        return result;
    }

    public static String factors(int n) {
        if (isPrime(n)) return "(" + n + ")"; //注意n为质数的情况
        ArrayList<Integer> primeFactors = new ArrayList<Integer>();
        int tempResult = n;
        String result = "";
        for (int i=2; i*i<=n;){
            if (isPrime(i) && tempResult%i==0){
                primeFactors.add(i);
                tempResult = tempResult/i;
                if (isPrime(tempResult)) {
                    primeFactors.add(tempResult);
                    break;
                }
            }
            else{
                i++;
            }
        }
        Integer[] res = primeFactors.toArray(new Integer[0]);
        System.out.println(Arrays.toString(res));
        tempResult = res[0];
        int count = 0;
        for (int i=0; i<res.length; i++) {
            if (res[i] == tempResult) {
                count++;
            } else {
                if (count == 1) {
                    result += "(" + tempResult + ")";
                } else {
                    result += "(" + tempResult + "**" + count + ")";
                }
                count = 1;
                tempResult = res[i];
                if (i == res.length - 1) {
                    if (count == 1) {
                        result += "(" + tempResult + ")";
                    } else {
                        result += "(" + tempResult + "**" + count + ")";
                    }
                    return result;
                }
            }
        }
//        for (int i: res){
//            if (i == tempResult){
//                count++;
//            }
//            else {
//                if (count == 1) {
//                    result += "(" + tempResult + ")";
//                }
//                else {
//                    result += "(" + tempResult + "**" + count + ")";
//                }
//                tempResult = i;
//                count = 1;
//            }
//        }
//        if (count == 1) {
//            result += "(" + tempResult + ")";
//        }
//        else {
//            result += "(" + tempResult + "**" + count + ")";
//        }
        return result;
    }
}
