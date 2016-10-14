package CodeWars;

/**
 * Created by dixonshen on 2016/10/14.
 */
public class Fraction {

    private int numerator;
    private int denominator;
    private int wholes;
    private String sign;

    public Fraction(int numerator, int denominator){
        this.numerator = numerator;
        this.denominator = denominator;
        this.wholes = (int)Math.sqrt(numerator*numerator)/(int)Math.sqrt(denominator*denominator);
        this.sign = ((double)numerator/denominator > 0 ? "" : "-");
    }

    /**
     * 两个数的最大公约数，欧几里得算法
     * @param a
     * @param b
     * @return
     */
    public int gcd(int a, int b){
        if (a<b){
            int temp = a;
            a = b;
            b = temp;
        }
        if (b == 0)
            return a;
        else
            return gcd(b,a%b);
    }

    /**
     * 获得化简后的分子分母， 仅在化简后不为整数的情况
     * @return
     */
    public int[] getNumerDenom(){
        int temp1 = (int)Math.sqrt(numerator*numerator);
        int temp2 = (int)Math.sqrt(denominator*denominator);
        int tempDenominator = temp2/gcd(temp1, temp2);
        int tempNumerator = temp1/gcd(temp1, temp2) - this.wholes * tempDenominator;
        return new int[] {tempNumerator, tempDenominator};
    }

    public String toString(){
        if (numerator%denominator == 0) return "" + numerator/denominator;
        return sign + (wholes>0 ? wholes + " " : "") + this.getNumerDenom()[0] + "/" + this.getNumerDenom()[1];
    }

    public double toDecimal(){
        return (double) numerator/denominator;
    }

    public Fraction add(int value){
        return this.add(new Fraction(value, 1));
    }

    public Fraction add(Fraction value){
        int numerator, denominator;
        numerator = this.numerator * value.denominator + this.denominator * value.numerator;
        denominator = this.denominator * value.denominator;
        return new Fraction(numerator, denominator);
    }

    public Fraction substract(int value){
        return this.substract(new Fraction(value, 1));
    }

    public Fraction substract(Fraction value){
        int numerator, denominator;
        numerator = this.numerator * value.denominator - this.denominator * value.numerator;
        denominator = this.denominator * value.denominator;
        return new Fraction(numerator, denominator);
    }

    public Fraction multiply(int value){
        return new Fraction(this.numerator*value, this.denominator);
    }

    public Fraction multiply(Fraction value){
        return new Fraction(this.numerator * value.numerator, this.denominator * value.denominator);
    }

    public Fraction divide(int value){
        return new Fraction(this.numerator, this.denominator * value);
    }

    public Fraction divide(Fraction value){
        return new Fraction(this.numerator * value.denominator, this.denominator * value.numerator);
    }

    public static void main(String[] args) {
        Fraction f = new Fraction(24,21);
        System.out.println(f.toDecimal());
        System.out.println(f.toString());
        System.out.println(f.add(new Fraction(2,-1)).toString());
        System.out.println(new Fraction(1, 3).multiply(new Fraction(2, 5)).toString());
    }
}
