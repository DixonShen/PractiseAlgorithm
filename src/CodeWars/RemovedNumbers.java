package CodeWars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by DixonShen on 2016/10/19.
 */
public class RemovedNumbers {

    public static List<long[]> removNb(long n){
        List<long[]> result = new ArrayList<long[]>();
        for (long i=1; i<=n; i++){
            if ((n*(n+1)/2-i)%(i+1)==0 && (n*(n+1)/2-i)/(i+1)<=n)
                result.add(new long[]{i,(n*(n+1)/2-i)/(i+1)});
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(removNb(100).toArray()));
    }
}
