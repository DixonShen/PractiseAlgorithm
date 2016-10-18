package CodeWars;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by dixonshen on 2016/10/17.
 */
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
