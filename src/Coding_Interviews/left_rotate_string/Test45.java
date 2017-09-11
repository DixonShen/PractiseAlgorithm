package Coding_Interviews.left_rotate_string;

/**
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。

    举例说明

    比如输入字符串”abcefg”和数字2，该函数将返回左旋转2 位得到的结”cdefab”。
 * Created by dixonshen on 2017/9/11.
 */
public class Test45 {

    public static String LeftRotateString(String str,int n) {
        if (str == null || str.trim().length() == 0 || str.trim().length() == 1) return str;
        char[] chs = str.toCharArray();
        int left = 0;
        int right = chs.length-1;
        for (int i=1; i<=n; i++) {
            if (left == chs.length-1) {
                left = 0;
            } else
                left++;
            if (right == chs.length-1) {
                right = 0;
            } else
                right++;
        }
        StringBuilder res = new StringBuilder();
        for (int i=0; i<chs.length; i++) {
            res.append(chs[left++]);
            if (left == chs.length)
                left = 0;
        }
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(LeftRotateString("abcdefg", 2));
    }
}
