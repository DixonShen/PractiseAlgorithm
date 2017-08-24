package Coding_Interviews.string_permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * 例如输入字符串abc。则打印出由字符a、b、c 所能排列出来的所有字符串abc、acb、bac 、bca、cab 和cba 。
 * 本题特别要求输出按照字典。
 * Created by dixonshen on 2017/8/24.
 */
public class Test28 {

    public static ArrayList<String> Permutation(String str) {
        char[] chs = str.toCharArray();
        ArrayList<String> result = new ArrayList<>();
        permutation(result, chs, 0);
        Collections.sort(result);
        return result;
    }

    public static void permutation(ArrayList<String> result, char[] str, int begin) {
        if (begin == str.length-1) {
            String s = new String(str);
            if (!result.contains(s))
                result.add(new String(str));
        }
        else {
            for (int i = begin; i<str.length; i++) {
                char temp = str[i];
                str[i] = str[begin];
                str[begin] = temp;
                permutation(result, str, begin+1);

                temp = str[i];
                str[i] = str[begin];
                str[begin] = temp;
            }
        }
    }

    /**
     * 字典序法生成全排列
     * @param str
     * @return
     */
    public static ArrayList<String> Permutation1(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str != null && str.length() > 0) {
            char[] seq = str.toCharArray();
            Arrays.sort(seq); //排列
            res.add(String.valueOf(seq)); //先输出一个解
            int len = seq.length;
            while (true) {
                int p = len - 1, q;
                //从后向前找一个seq[p - 1] < seq[p]
                while (p >= 1 && seq[p - 1] >= seq[p]) --p;
                if (p == 0) break; //已经是“最小”的排列，退出
                //从p向后找最后一个比seq[p]大的数
                q = p; --p;
                while (q < len && seq[q] > seq[p]) q++;
                --q;
                //交换这两个位置上的值
                swap(seq, q, p);
                //将p之后的序列倒序排列
                reverse(seq, p + 1);
                res.add(String.valueOf(seq));
            }
        }

        return res;
    }

    public static void reverse(char[] seq, int start) {
        int len;
        if(seq == null || (len = seq.length) <= start)
            return;
        for (int i = 0; i < ((len - start) >> 1); i++) {
            int p = start + i, q = len - 1 - i;
            if (p != q)
                swap(seq, p, q);
        }
    }

    public static void swap(char[] cs, int i, int j) {
        char temp = cs[i];
        cs[i] = cs[j];
        cs[j] = temp;
    }

    public static void main(String[] args) {
        String str = "aabbcdeff";
        System.out.println(Permutation1(str));
    }
}
