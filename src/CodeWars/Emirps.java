package CodeWars;


import java.util.Arrays;

/**
 * Created by dixonshen on 2016/10/13.
 */
public class Emirps {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findEmirp(100)));
    }

    public static boolean isPrime(long n){
        if (n == 1) return false;
        for (long i = 2; i<=(int)Math.sqrt(n); i++){
            if (n%i==0) {
                return false;
            }
        }
        return true;
    }

    public static int[] findEmirp(long n){
        int[] result = {0, 0, 0};
        char ch;
        for (int i =1; i<=n; i++){
            if (isPrime(i)&&i>=11){
                String str1 = String.valueOf(i);
                char[] arr = str1.toCharArray();
                for (int j=0; j<arr.length/2; j++){
                    ch = arr[j];
                    arr[j] = arr[arr.length-1-j];
                    arr[arr.length-1-j] = ch;
                }
                if (isPrime(Integer.parseInt(new String(arr)))&&!str1.contentEquals(new String(arr))){
                    result[0]++;
                    result[1] = i;
                    result[2] += i;
                }
            }
        }
        return result;
    }
}
