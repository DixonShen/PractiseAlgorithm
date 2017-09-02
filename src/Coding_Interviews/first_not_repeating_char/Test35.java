package Coding_Interviews.first_not_repeating_char;

/**
 * 在字符串中找出第一个只出现一次的字符。
 * Created by dixonshen on 2017/8/31.
 */
public class Test35 {

    public static int firstNotRepeatingChar(String str) {
        if (str == null) return -1;
        char[] chs = str.toCharArray();
        int[] flag = new int[256];
        int res = -1;
        for (char ch : chs) {
            flag[ch]++;
        }
        for (int i=0; i<chs.length; i++){
            if (flag[chs[i]] == 1) {
                res = i;
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(firstNotRepeatingChar("google")); // l
        System.out.println(firstNotRepeatingChar("aabccdbd")); // '\0'
        System.out.println(firstNotRepeatingChar("abcdefg")); // a
        System.out.println(firstNotRepeatingChar("gfedcba")); // g
        System.out.println(firstNotRepeatingChar("zgfedcba")); // g
    }
}
