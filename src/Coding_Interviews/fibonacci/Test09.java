package Coding_Interviews.fibonacci;

/**
 * 现在要求输入一个整数n，请你输出斐波那契数列的第n项。
 * Created by dixonshen on 2017/7/17.
 */
public class Test09 {

    public static int Fibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int temp1 = 0;
        int temp2 = 1;
        int res = 0;
        for (int i=2; i<=n; i++) {
            res = temp1 + temp2;
            temp1 = temp2;
            temp2 = res;
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
