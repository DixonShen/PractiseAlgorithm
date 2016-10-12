package CodeWars;

import java.util.ArrayList;

/**
 * Created by DixonShen on 2016/10/12.
 */
public class PrimeDecomp {

    public static void main(String[] args) {
        System.out.println(factors(72030));
    }

    /**
     * @param n
     * @return if n is a prime return true,else return false
     */
    public static boolean isPrime(long n){
        for (long i = 2; i<=(int)Math.sqrt(n); i++){
            if (n%i==0) {
                return false;
            }
        }
        return true;
    }

    public static String factors(int n) {
        ArrayList<Integer> primeFactors = new ArrayList<Integer>();
        int tempResult = n;
        String result = "";
        for (int i=2; i*i<=n;){
            if (isPrime(i) && tempResult%i==0){
                primeFactors.add(i);
                tempResult = tempResult/i;
                if (isPrime(tempResult)) {
                    primeFactors.add(tempResult);
                    break;
                }
            }
            else{
                i++;
            }
        }
        Integer[] res = primeFactors.toArray(new Integer[0]);
        tempResult = res[0];
        int count = 0;
        for (int i=0; i<res.length; i++){
            if (res[i]==tempResult){
                count++;
            }
            if (i == res.length-1){
                if (count == 1) {
                    result += "(" + tempResult + ")";
                }
                else {
                    result += "(" + tempResult + "**" + count + ")";
                }
                return result;
            }
            if (res[i]!=tempResult)
        }
//        for (int i: res){
//            if (i == tempResult){
//                count++;
//            }
//            else {
//                if (count == 1) {
//                    result += "(" + tempResult + ")";
//                }
//                else {
//                    result += "(" + tempResult + "**" + count + ")";
//                }
//                tempResult = i;
//                count = 1;
//            }
//        }
//        if (count == 1) {
//            result += "(" + tempResult + ")";
//        }
//        else {
//            result += "(" + tempResult + "**" + count + ")";
//        }
        return result;
    }
}
