package SORT;

import java.util.Arrays;

/**
 * Created by DixonShen on 2016/4/8.
 */
public class InsertSort {

    public static void main(String[] args) {
        InsertSort ob = new InsertSort();
        int[] a = new int[100000];
        for (int i=0;i<100000;i++)
            a[i] = (int)(100000000*Math.random());
//        for (int e:a) System.out.print(e + " ");
        System.out.println();
        long startTime1 = System.currentTimeMillis();
        ob.insertSort1(a);
        long endTime1 = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime1 - startTime1) + "毫秒");
//        for (int e:a) System.out.print(e + " ");
    }

    public void insertSort1(int a[]){
        int i,j,temp;
        for (i=1;i<a.length;i++) {
            if (a[i]<a[i-1]) {
                temp = a[i];
                for (j = i - 1; j >= 0 && a[j]> temp; j--) {
                    a[j+1] = a[j];
                }
                a[j + 1] = temp;
            }
        }
    }

}
