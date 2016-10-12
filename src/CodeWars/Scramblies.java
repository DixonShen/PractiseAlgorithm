package CodeWars;

import java.util.Arrays;

/**
 * Created by DixonShen on 2016/10/12.
 *
 * Write function scramble(str1,str2) that returns true
 * if a portion of str1 characters can be rearranged to match str2,
 * otherwise returns false.
 * For example:
 * str1 is 'rkqodlw' and str2 is 'world' the output should return true.
 * str1 is 'cedewaraaossoqqyt' and str2 is 'codewars' should return true.
 * str1 is 'katas' and str2 is 'steak' should return false.
 * Only lower case letters will be used (a-z).
 * No punctuation or digits will be included.
 * Performance needs to be considered
 */
public class Scramblies {

    public static void main(String[] args) {
        System.out.println(scramble("katas","steak"));
    }

    public static boolean scramble(String str1, String str2){
        if (str2.length()>str1.length()) return false;
        for (String s: str2.split("")){
            if (!str1.contains(s)) return false;
            str1 = str1.replace(s, "");
        }
        return true;
    }

    public static boolean scramble1(String str1, String str2){
        char[] ch1 = str1.toCharArray();
        char[] ch2 = str2.toCharArray();
        if (ch2.length>ch1.length) return false;
        Arrays.sort(ch1);
        Arrays.sort(ch2);
        int pivot = 0;
        for (char ch : ch2){
            if (pivot>=ch1.length) return false;
            while (ch1[pivot]!=ch) {
                pivot++;
                if (pivot>=ch1.length) return false;
            }
            pivot++;
        }
        return true;
    }
}
