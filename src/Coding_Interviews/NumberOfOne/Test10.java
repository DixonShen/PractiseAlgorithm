package Coding_Interviews.NumberOfOne;

/**
 * Created by dixonshen on 2017/7/18.
 */
public class Test10 {

    public static int NumberOf1(int n) {
        int result = 0;
        for (int i=0; i<32; i++) {
            result += (n & 1);
            n = n >>> 1;
        }
        return result;
    }

    /**
     * 更高效解法
     * 讲一个数和它减去1的结果按位与，相当于将最右边的1变为0
     * @param n
     * @return
     */
    public static int NumberOfOne(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = (n-1) & n;
        }
        return count;
    }

    public static void main(String[] args) {

        System.out.println(NumberOfOne(0B00000000_00000000_00000000_00000000)); // 0
        System.out.println(NumberOfOne(0B00000000_00000000_00000000_00000001)); // 1
        System.out.println(NumberOfOne(0B11111111_11111111_11111111_11111111)); // -1
        System.out.println(0B01111111_11111111_11111111_11111111 == Integer.MAX_VALUE);
        System.out.println(NumberOfOne(0B01111111_11111111_11111111_11111111)); // Integer.MAX_VALUE
        System.out.println(0B10000000_00000000_00000000_00000000 == Integer.MIN_VALUE);
        System.out.println(NumberOfOne(0B10000000_00000000_00000000_00000000)); // Integer.MIN_VALUE
    }
}
