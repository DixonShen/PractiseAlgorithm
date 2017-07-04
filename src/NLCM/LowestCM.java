package NLCM;

/**
 * Created by DixonShen on 2016/8/3.
 * Longest Common Multiple
 */
public class LowestCM {

    /**
     * 1-N的最小公倍数
     */
    public static void testLCM(){
        int result = 0;
        int countNum = 20;//需要计算的数目：1-20的最小公倍数
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            if (i%2!=0) continue;
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
                System.out.println("最小公倍数为："+result);
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
     * 把n个数保存为一个数组，先求出gcd(a[0],a[1]),然后将所求出的gcd与数组的下一个元素作为参数继续求gcd
     * 这样就产生一个递归的求ngcd的算法
     * @param a
     * @param n
     * @return
     */
    public int ngcd(int[] a,int n){
        if (n == 1)
            return a[n-1];
        return gcd(a[n-1], ngcd(a, n-1));
    }

    /**
     * 两个数的最小公倍数
     * @param a
     * @param b
     * @return
     */
    public int lcm(int a, int b){
        int gcd = gcd(a,b);
        return (b/gcd)*a;  //有可能会超出Integer的范围
    }

    /**
     * n个数的最小公倍数算法
     * 算法过程与n个数的最大公约数求法类似
     * @param a
     * @param n
     * @return
     */
    public int nlcm(int[] a, int n){
        if (n==1)
            return a[n-1];
        else
            return lcm(a[n-1],nlcm(a, n-1));
    }


    public static void main(String[] args) {
//        testLCM2();
        LowestCM test = new LowestCM();
        int[] a = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
//        for (int i=1;i<=a.length;i++)
//            a[i-1] = i;
        System.out.println(test.nlcm(a,a.length));
    }
}
