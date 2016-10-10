package CodeWars;

/**
 * Created by dixonshen on 2016/10/10.
 */

import java.util.ArrayList;
import java.util.Arrays;

public class PrimeNumberDecomposer {


    public static void main(String args[]){
        long a = 100;
        Long[] res = getAllPrimeFactors(a);
        System.out.println(Arrays.toString(res));
        System.out.println(Arrays.deepToString(getUniquePrimeFactorsWithCount(a)));
        System.out.println(Arrays.toString(getPrimeFactorPotencies(a)));
//        System.out.println(res.length);
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

    /**
     Return value: List of all prime factors of a given number n
     */
    public static Long[] getAllPrimeFactors(long n) {
        if (n <= 0) return new Long[] {};
        if (n == 1) return new Long[] {1L};
        if (n == 2) return new Long[] {2L};
        ArrayList<Long> primeFactors = new ArrayList<Long>();
        long tempResult = n;
        for (long i=2; i*i<=n;){
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
        return primeFactors.toArray(new Long[0]);
    }
    /**
     Return value: List containing two lists, first containg all prime factors without duplicates,
     second containing the count, how often each prime factor occured.
     Return code always contains two lists.

     e.g.: getUniquePrimeFactorsWithCount(100) = {{2,5},{2,2}) // prime 2 occurs 2 times, prime 2 occurs 5 times,
     */
    public static Long[][] getUniquePrimeFactorsWithCount(long n) {
        if (n <= 0) return new Long[][] {{},{}};
        if (n == 1) return new Long[][] {{1L},{1L}};
        if (n == 2) return new Long[][] {{2L}, {1L}};
        Long[] primeFactors = getAllPrimeFactors(n);
        ArrayList<Long> result1 = new ArrayList<Long>();
        ArrayList<Long> result2 = new ArrayList<Long>(primeFactors.length);
        long cache = 0;
        int pivot = -1;
        for (int i=0; i<primeFactors.length; i++ ){
            if (primeFactors[i] == cache){
                if (!result1.contains(cache))
                    result1.add(cache);
                result2.set(pivot, result2.get(pivot)+1);
            }
            else {
                cache = primeFactors[i];
                pivot++;
                result1.add(cache);
                result2.add(0L);
                result2.set(pivot, result2.get(pivot)+1);
            }
        }
        return new Long[][] {
                result1.toArray(new Long[0]),
                result2.toArray(new Long[0])
        };
    }
    /**
     Return value: List containing product of same prime factors,
     e.g.: 45 = 3*3*5 ==>  {3^2,5^1} == {9,5}
     e.g.: getUniquePrimeFactorsWithCount(100) = {{2,5},{2,2}) // prime 2 occurs 2 times, prime 2 occurs 5 times,
     */
    public static Long[] getPrimeFactorPotencies(long n) {
        if (n <= 0) return new Long[] {};
        if (n == 1) return new Long[] {1L};
        if (n == 2) return new Long[] {2L};
        Long[][] factorsWithCount = getUniquePrimeFactorsWithCount(n);
        Long[] result = new Long[factorsWithCount[0].length];
        for (int i=0; i<result.length; i++){
            result[i] = factorsWithCount[0][i];
            for (int j=1; j<=factorsWithCount[1][i]-1; j++){
                result[i] *= factorsWithCount[0][i];
            }
        }
        return result;
    }
}
