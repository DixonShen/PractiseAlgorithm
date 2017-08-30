package Coding_Interviews.ugly_number;

/**
 * 我们把只包含因子2、3 和5 的数称作丑数（Ugly Number）。求从小到大的顺序的第1500个丑数。
 * Created by dixonshen on 2017/8/30.
 */
public class Test34 {

    public static int getUglyNumber(int index) {
        if (index == 0) return 0;
        int[] numbers = new int[index];
        numbers[0] = 1;
        if (index == 1) return 1;
        int index2 = 0;
        int index3 = 0;
        int index5 = 0;
        int next2 = 0, next3 = 0, next5 = 0;
        int lastIndex = 0;
        while (true) {
            while (numbers[index2] * 2 <= numbers[lastIndex])
                index2++;
            next2 = numbers[index2] * 2;
            while (numbers[index3] * 3 <= numbers[lastIndex])
                index3++;
            next3 = numbers[index3] * 3;
            while (numbers[index5] * 5 <= numbers[lastIndex])
                index5++;
            next5 = numbers[index5] * 5;
            numbers[++lastIndex] = min(next2, next3, next5);
            if (lastIndex == index-1){
                break;
            }
        }
        return numbers[lastIndex];
    }

    public static int min(int a, int b, int c) {
        return a < b ? (a < c ? a : c) : ( b < c ? b : c);
    }

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        System.out.println(getUglyNumber(1)); // 1
        System.out.println(getUglyNumber(2)); // 2
        System.out.println(getUglyNumber(3)); // 3
        System.out.println(getUglyNumber(4)); // 4
        System.out.println(getUglyNumber(5)); // 5
        System.out.println(getUglyNumber(6)); // 6
        System.out.println(getUglyNumber(7)); // 8
        System.out.println(getUglyNumber(8)); // 9
        System.out.println(getUglyNumber(9)); // 10
        System.out.println(getUglyNumber(10)); // 12
        System.out.println(getUglyNumber(11)); // 15
        System.out.println(getUglyNumber(1500)); // 859963392
        System.out.println(getUglyNumber(0)); // 0
    }
}
