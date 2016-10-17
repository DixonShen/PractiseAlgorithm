package CodeWars;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by dixonshen on 2016/10/17.
 */
public class Decompose {

    public static String decompose(long n){
        String result = "";
        for (long i = n-1; i>=1; i--){
            ArrayList<Long> tempResult = new ArrayList<Long>();
            tempResult.add(i);
            long temp = n*n - i*i;
            for (long j = i; j>=1;){
                j = (long) Math.sqrt(temp);
                tempResult.add(j);
                temp -= j*j;
                if (temp == 0){
                    return tempResult.toString();
                }
                if (temp == 1){
                    tempResult.add(1L);
                    return tempResult.toString();
                }
                if (temp == 2) break;
                if (temp == 3) break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(decompose(14));
    }
}
