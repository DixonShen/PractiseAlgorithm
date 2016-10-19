package CodeWars;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by dixonshen on 2016/10/19.
 * The prime factorization of a positive integer is a list of the integer's prime factors,
 * together with their multiplicities; t
 * he process of determining these factors is called integer factorization.
 * The fundamental theorem of arithmetic says that
 * every positive integer has a single unique prime factorization.
 * The prime factorization of 24, for instance, is (2^3) * (3^1).
 * Without using the prime library, write a class called PrimeFactorizer that takes in an integer greater than 1 and has a method called factor which returns a hash where the keys are prime numbers and the values are the multiplicities.
    new PrimeFactorizer().factor(24) //should return { 2 => 3, 3 => 1 }
 */


public class PrimeFactorizer {

    public static java.util.Map<Long, Integer> factor(long n){
        HashMap<Long, Integer> result = new HashMap<Long, Integer>();
        for (int fac=2; fac <= Math.sqrt(n); fac++){
            int count = 0;
            for (; n%fac==0; count++){
                n /= fac;
            }
            if (count>0){
                result.put((long)fac,count);
            }
        }
        if (n > 1) result.put(n, 1);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(factor(48769441578789L));
//        System.out.println(Arrays.toString(new boolean[3]));
    }
}
