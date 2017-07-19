package Coding_Interviews.exponent;

/**
 * 实现函数double Power(double base, int exponent)，求base的exponent次方。
 * 不需要考虑大数问题
 * Created by dixonshen on 2017/7/19.
 */
public class Test11 {

    public static double Power(double base, int exponent) {
        if (base == 0.0)
            return 0.0;
        if (exponent < 0)
            return 1.0 / powerWithExponent(base, -exponent);
        else
            return powerWithExponent(base, exponent);
    }

    public static double powerWithExponent(double base, int exponent) {
        if (base == 1.0 || exponent == 0)
            return 1.0;
        double res = 1.0;
        for (int i=1; i<=exponent; i++) {
            res *= base;
        }
        return res;
    }

    /**
     * O(log n)时间复杂度，更加高效
     * @param base
     * @param exponent
     * @return
     */
    public static double powerWithExponent2(double base, int exponent) {
        if (base == 1.0)
            return 1;
        if (exponent == 0)
            return 1;
        if (exponent == 1)
            return base;
        double res = powerWithExponent2(base, exponent >> 1);
        res *= res;
        if ((exponent & 0x1) == 1)
            res *= base;
        return base;
    }

    public static void main(String[] args) {
        System.out.println(Power(2.0, -4));
    }
}
