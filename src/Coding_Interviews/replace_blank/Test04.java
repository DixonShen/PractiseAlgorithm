package Coding_Interviews.replace_blank;

/**
 * 请实现一个函数，把字符串中的每个空格替换成"%20"，例如“We are happy.“，则输出”We%20are%20happy.“。
 * Created by dixonshen on 2017/7/6.
 */
public class Test04 {

    public static String replaceBlank(StringBuffer str) {
        if (str.equals("") || str == null) return null;
        int count = 0;
        int p = str.length();
        for (int i=0; i<str.length(); i++) {
            if (str.charAt(i) == ' ')
                count++;
        }
        for (int i=0; i<count; i++) {
            str.append("  ");
        }
        int q = str.length();
        while (p!=q) {
            p--;
            if (str.charAt(p) == ' '){
                q = q-3;
                str.setCharAt(q, '%');
                str.setCharAt(q+1, '2');
                str.setCharAt(q+2, '0');
            } else {
                q--;
                str.setCharAt(q, str.charAt(p));
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
        StringBuffer str = new StringBuffer(" are happy ");
        System.out.println(replaceBlank(str));
    }
}
