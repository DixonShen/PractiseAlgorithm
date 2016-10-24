package CodeWars;

import java.util.Arrays;

/**
 * Created by dixonshen on 2016/10/24.
 */
public class DataReverse {

    public static int[] DataReverse(int[] data){
        int[] result = new int[data.length];
        String str = "";
        for (int i : data)
            str += String.valueOf(i);
        String temp = "";
        for (int i=data.length; i>0; i-=8){
            temp = temp + str.substring(i-8, i);
        }
        System.out.println(temp);
        char[] chs = temp.toCharArray();
        for (int i=0; i<chs.length; i++){
            result[i] = Integer.parseInt(String.valueOf(chs[i]));
        }
        return result;
    }

    public static int[] DataReverse1(int[] data){
        String string = "";
        for (int i = 0; i < data.length; i++) {
            string += String.valueOf(data[i]);
        }
        String str = "";
        for (int i = data.length; i > 0; i -= 8) {
            str = str + string.substring(i - 8, i);
        }
        char[] chs = str.toCharArray();
        int[] res = new int[chs.length];
        for (int i = 0; i < chs.length; i++) {
            res[i] = Integer.parseInt(String.valueOf(chs[i]));
        }
        return res;
    }

    public static int[] DataReverse2(int[] data){
        int[] results = new int[data.length];
        String string = "";
        for (int i = 0; i < data.length; i++) {
            string += String.valueOf(data[i]);
        }
        String str = "";
        for (int i = string.length(); i > 0; i -= 8) {
            str = str + string.substring(i - 8, i);
        }
        char[] chs = str.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            results[i] = Integer.parseInt(String.valueOf(chs[i]));
        }

        return results;
    }

    public static void main(String[] args) {
        int[] a = {1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,1,0,1,0,1,0};
        System.out.println(Arrays.toString(DataReverse(a)));
    }
}
