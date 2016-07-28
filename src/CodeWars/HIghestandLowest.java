package CodeWars;

import java.util.Arrays;

/**
 * Created by DixonShen on 2016/7/28.
 */
public class HIghestandLowest {

    public static String HighAndLow(String numbers){
        String[] num = numbers.split(" ");
        int[] a = new int[num.length];
        for (int i=0; i<num.length; i++){
            a[i] = Integer.parseInt(num[i]);
        }
        Arrays.sort(a);
        return String.format("%d %d",a[a.length-1],a[0]);
    }

    public static void main(String[] args) {
        HighAndLow("1 9 3 4 -5");
    }
}
