package SORT;

/**
 * Created by DixonShen on 2016/4/8.
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] a = new int[100000];
        for (int i=0;i<100000;i++)
            a[i] = (int)(10000000*Math.random());
//        for (int e:a) System.out.print(e + " ");
        System.out.println();
        long startTime = System.currentTimeMillis();
        insertSort(a);
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "毫秒");
//        for (int e:a) System.out.print(e + " ");
    }

    public static void insertSort(int a[]){
        int temp;
        for (int i=1;i<a.length;i++){
            int p = i;
            for (int k=i-1;k>=0;k--){
                if(a[p]<a[k]){
                    temp = a[k];
                    a[k] = a[p];
                    a[p] = temp;
                    p--;
                }
                else break;
            }
        }
    }
}
