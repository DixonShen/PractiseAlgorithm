package NLCM;

/**
 * Created by DixonShen on 2016/8/3.
 * Longest Common Multiple
 */
public class LongestCM {

    /**
     * 1-N的最小公倍数
     */
    public static void testLCM(){
        int result = 0;
        int countNum = 20;//需要计算的数目：1-20的最小公倍数
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            int num = countNum;
            while(num > 0) {
                int count = 0;
                for (int j = 1; j <= countNum; j++) {
                    if(i%j!=0){
                        break;
                    }else{
                        count ++;
                    }
                }
                if(count==countNum){
                    result = i;
                    break;
                }
                num --;
            }
            if(result > 0){
                System.out.println("1-"+countNum+"的最小公倍数为："+result);
                break;
            }
        }
    }

    /**
     * 多个不连续数字的最小公倍数
     */
    public static void testLCM2(){
        int result = 0;
        int[] arrays = {2,4,6,8,10,12,14,16,18,20};//需要计算的数组
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            int num = arrays.length;
            while(num > 0) {
                int count = 0;
                for(int array : arrays){
                    if(i%array!=0){
                        break;
                    }else{
                        count ++;
                    }
                }
                if(count==arrays.length){
                    result = i;
                    break;
                }
                num --;
            }
            if(result > 0){
                System.out.println(arrays.toString()+"的最小公倍数为："+result);
                break;
            }
        }
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
     * n个数的最大公约数算法
     * @param a
     * @param n
     * @return
     */
    public int ngcd(int[] a,int n){
        if (n == 1)
            return a[0];
        return gcd(a[n-1], ngcd(a, n-1));
    }


    public static void main(String[] args) {
        testLCM2();
    }
}
