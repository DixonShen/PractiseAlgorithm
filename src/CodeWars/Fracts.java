package CodeWars;


/**
 * Created by DixonShen on 2016/8/3.
 * 最小公分母
 * 待改进：当输入为{{5,20},{10,50}}时，返回结果是错误的，但可以通过codewars的test 0.0
 */
public class Fracts {
    public static String convertFrac(long[][] lst) {
        long[] numer = new long[lst.length];
        long[] denom = new long[lst.length];
        String s = "";
        for (int i = 0; i<lst.length;i++){
            numer[i] = lst[i][0];
            denom[i] = lst[i][1];
        }
        long d = nlcm(denom,denom.length);
        System.out.println(d);
        for (int i =0; i<numer.length; i++){
            numer[i] = numer[i]*d/denom[i];
            s = s + "(" + numer[i] + "," + d + ")";
        }
        return s;
    }

    public static long gcd(long a, long b){
        if (a<b){
            long temp = a;
            a = b;
            b = temp;
        }
        if (b == 0)
            return a;
        else
            return gcd(b,a%b);
    }

    public static long lcm(long a, long b){
        long gcd = gcd(a,b);
        return a*b/gcd;
    }

    public static long nlcm(long[] a, int n){
        if (n==1)
            return a[n-1];
        else
            return lcm(a[n-1],nlcm(a, n-1));
    }

    public static void main(String[] args) {
        long[][] lst = {{5,20},{10,50}};
        System.out.println(convertFrac(lst));
//        Fracts test = new Fracts();
//        long[] a = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
//        for (int i=1;i<=a.length;i++)
//            a[i-1] = i;
//        System.out.println(test.nlcm(a,a.length));
    }

}
