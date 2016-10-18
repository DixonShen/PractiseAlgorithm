package CodeWars;


/**
 * Created by dixonshen on 2016/10/17.
 *
 * Task
 * Given a positive integral number n, return a strictly increasing sequence
 * (list/array/string depending on the language) of numbers,
 * so that the sum of the squares is equal to n².
 * If there are multiple solutions (and there will be),
 * return the result with the largest possible values:
 * Examples
    decompose(11) must return [1,2,4,10].
    Note that there are actually two ways to decompose 11²,
    11² = 121 = 1 + 4 + 16 + 100 = 1² + 2² + 4² + 10² but don't return [2,6,9],
    since 9 is smaller than 10.
    For decompose(50) don't return [1, 1, 4, 9, 49]
    but [1, 3, 5, 8, 49] since [1, 1, 4, 9, 49] doesn't form a strictly increasing sequence.
 * Note
 * Neither [n] nor [1,1,1,…,1] are valid solutions. If no valid solution exists,
 * return nil, null, Nothing, None (depending on the language) or "" (Java, C#) or {} (C++).
 * The function "decompose" will take a positive integer n and return the decomposition of N = n² as:
    "x1 ... xk"
 * Hint
 *  Very often xk will be n-1.
 */

import java.util.Arrays;

public class Decompose {

    public static String decompose(long n){
        String result = "";
        long[] res = findSequence(n*n, n);
        Arrays.sort(res);
        for (long i : res){
            result += i + " ";
        }
        result = result.trim();
        return result;
    }

    public static long[] findSequence(long n, long last){
        long k = ((long)Math.sqrt(n) > last-1 ? last-1 : (long)Math.sqrt(n));
        for ( ; k>=1; k--){
            long temp = n - k * k;
            if (temp == 0)
                return new long[] {k};
            if (temp == 2 || temp == 3)
                continue;
            if (temp == 1)
                return new long[] {k,1L};
            long[] res = findSequence(temp, k);
            if (res == null) continue;
            long[] result = new long[res.length + 1];
            result[0] = k;
            for (int i=1; i<result.length; i++){
                result[i] = res[i-1];
            }
            return result;
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(decompose(12));
    }
}
