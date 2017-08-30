package Coding_Interviews.sort_array_for_min_number;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * Created by dixonshen on 2017/8/30.
 */
public class Test33 {

    public static String printMinNumber(int [] numbers) {
        String[] strs = new String[numbers.length];
        for (int i=0; i<numbers.length; i++) {
            strs[i] = numbers[i] + "";
        }
        Arrays.sort(strs, new MComparator());
        StringBuilder builder = new StringBuilder();
        for (String s : strs)
            builder.append(s);
        return builder.toString();
    }

    public static class MComparator implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            if (s1 == null || s2 == null)
                throw new IllegalArgumentException("Arg should not be null");
            String str1 = s1 + s2;
            String str2 = s2 + s1;
            return str1.compareTo(str2);
        }
    }

    public static void main(String[] args) {

//        String[] data = {"3", "5", "1", "4", "2"};
        int[] data = {3, 5, 1, 4, 2};
        System.out.println(printMinNumber(data));

//        String[] data2 = {"3", "32", "321"};
        int[] data2 = {3, 32, 321};
        System.out.println(printMinNumber(data2));

//        String[] data3 = {"3", "323", "32123"};
        int[] data3 = {3, 323, 32123};
        System.out.println(printMinNumber(data3));

//        String[] data4 = {"1", "11", "111"};
        int[] data4 = {1, 11, 111};
        System.out.println(printMinNumber(data4));

//        String[] data5 = {"321"};
        int[] data5 = {321};
        System.out.println(printMinNumber(data5));
    }
}
