package Coding_Interviews.reverse_words_in_sentence;

/**
 * 输入一个英文句子，翻转句子中单词的顺序，但单词内字啊的顺序不变。为简单起见，标点符号和普通字母一样处理。
    举例说明

    例如输入字符串”I am a student. ”，则输出”student. a am I”。
 * Created by dixonshen on 2017/9/6.
 */
public class Test44 {

    public static String ReverseSentence(String str) {
        if (str.trim().equals("")) return str;
        String[] words = str.split(" ");
        StringBuilder res = new StringBuilder();
        for (int i=words.length-1; i>=0; i--) {
            res.append(words[i] + " ");
        }
        return res.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println(ReverseSentence("student. a am I"));
    }
}
