package TheArtOfProgramming;

/**
 * Created by leeon on 2017/3/8.
 */
public class StringToInt {

    public static final int MAX_INT = Integer.MAX_VALUE;
    public static final int MIN_INT = Integer.MIN_VALUE;

    /**
     * 将STRING类型转化为int类型，有如下几个步骤
     * 1、判断正负
     * 2、判断非整数
     * 3、“321” 转为 321 可以看做是 3*100 + 2*10 + 1 这等于是每次循环时，都对前几位数字*10再累加
     * 4、判断是否超出int的最大值范围(-2^31~2^31-1)
     *    判断是否超过最大值范围 不能直接拿值和Integer的max比较, 因为可能会发生溢出  应该使用 max/10来比较
     *
     * @param s
     * @return
     */
    public static int stringToInt(String s) {
        int digitIndex = 0;

        if (s.equals("") || s == null) {
            return 0;
        }

        s = s.trim();
        char[] digits = s.toCharArray();
        int sign = 1;
        if (digits[0] == '+' || digits[0] == '-'){
            if (digits[0] == '-')
                sign = -1;
            digitIndex++;
        }
        int result = 0;
        while (digitIndex < digits.length &&isDigit(digits[digitIndex])) {
            int c = digits[digitIndex] - '0';
            if (sign == 1 && (result > MAX_INT/10 || (result == MAX_INT/10 && c >= MAX_INT%10))) {
                result = MAX_INT;
                break;
            }
            if (sign == -1 && (-result < MIN_INT/10 || (-result == MIN_INT/10 && -c <= MIN_INT%10))){
                result = MIN_INT;
                break;
            }
            result = result * 10 + digits[digitIndex] - '0';
            digitIndex++;
        }
        return sign > 0 ? result : -result;
    }

    public static boolean isDigit(char ch) {
        if (ch >= '0' && ch <= '9')
            return true;
        return false;
    }

    public static void main(String[] args) {
        String s = "2147483650";
        System.out.println(stringToInt(s));
        System.out.println(Integer.MAX_VALUE);
    }
}
