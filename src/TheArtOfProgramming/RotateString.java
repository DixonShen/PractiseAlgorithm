package TheArtOfProgramming;

/**
 *
 * Created by leeon on 2017/3/7.
 */
public class RotateString {

    /**
     * 问题描述：
     * 给定一个字符串，要求把字符串前面的若干个字符移动到字符串的尾部，
     * 如把字符串“abcdef”前面的2个字符'a'和'b'移动到字符串的尾部，使得原字符串变成字符串“cdefab”。
     * 请写一个函数完成此功能，要求对长度为n的字符串操作的
        时间复杂度为 O(n)，
        空间复杂度为 O(1)。
     *
     * 三步反转法
     * @param chs
     * @param n
     * @return
     */
    public char[] LeftRotate(char[] chs, int n) {
        n %= chs.length;
        char[] reverseLeft = ReverseString(chs, 0, n-1);
        char[] reverseRight = ReverseString(reverseLeft, n, chs.length-1);
        char[] res = ReverseString(reverseRight, 0, chs.length-1);
        return res;
    }

    /**
     * 翻转字符串
     * @param chs
     * @param from
     * @param to
     * @return
     */
    public char[] ReverseString(char[] chs, int from, int to) {

        while (from < to) {
            char temp = chs[to];
            chs[to--] = chs[from];
            chs[from++] = temp;
        }

        return chs;
    }



    public static void main(String[] args) {
        RotateString test = new RotateString();
        String str = "abcdefg";
        System.out.println(test.LeftRotate(str.toCharArray(), 3));
    }
}
