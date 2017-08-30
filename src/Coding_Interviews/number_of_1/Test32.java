package Coding_Interviews.number_of_1;

/**
 * Created by dixonshen on 2017/8/30.
 */
public class Test32 {

    /**
     * 题目：输入一个整数n求从1 到n这n个整数的十进制表示中1 出现的次数。
     * @param n 最大的数字
     * @return 1-n中，1出现的次数
     */
    public static int numberOf1Between1AndN(int n) {
        if (n <= 0) return 0;

        String s = n + "";
        int[] digits = new int[s.length()];

        for (int i=0; i<digits.length; i++)
            digits[i] = s.charAt(i) - '0';
        return numberOf1(digits, 0);
    }

    public static int numberOf1(int[] digits, int current) {
        int length = digits.length - current;
        int first = digits[current];
        if (length == 1 && first == 0)
            return 0;
        if (length == 1 && first > 0 )
            return 1;
        int numFirstDigit = 0;
        if (first > 1) {
            numFirstDigit = powerBase10(length-1);
        }
        if (first == 1) {
            numFirstDigit = atoi(digits, current+1) + 1;
        }

        int numOtherDigits = first * (length-1) * powerBase10(length-2);
        int numRecursive = numberOf1(digits, current+1);

        return numFirstDigit + numOtherDigits + numRecursive;
    }

    /**
     * 将数字数组转换为数字
     * @param digits
     * @param index
     * @return
     */
    public static int atoi(int[] digits, int index) {
        StringBuilder builder = new StringBuilder();
        for (int i=index; i<digits.length; i++)
            builder.append(digits[i]);
        return Integer.parseInt(builder.toString());
    }

    /**
     * 求10的n次方，假定n不为负数
     * @param n
     * @return
     */
    private static int powerBase10(int n) {
        int result = 1;
        for (int i = 0; i < n; i++) {
            result *= 10;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(numberOf1Between1AndN(1)); // 1
        System.out.println(numberOf1Between1AndN(5)); // 1
        System.out.println(numberOf1Between1AndN(10)); // 2
        System.out.println(numberOf1Between1AndN(55)); // 16
        System.out.println(numberOf1Between1AndN(99)); // 20
        System.out.println(numberOf1Between1AndN(9999)); // 4000
        System.out.println(numberOf1Between1AndN(21345)); // 18821
        System.out.println(numberOf1Between1AndN(0)); // 0
    }
}
