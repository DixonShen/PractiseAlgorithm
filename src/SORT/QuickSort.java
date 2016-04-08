package SORT;

/**
 * Created by DixonShen on 2016/4/8.
 */
public class QuickSort {

    public static void main(String[] args) {
        QuickSort ob = new QuickSort();
        int[] a = new int[1000000];
        for (int i=0;i<1000000;i++)
            a[i] = (int)(100000000*Math.random());
//        for (int e:a) System.out.print(e + " ");
        System.out.println();
        long startTime1 = System.currentTimeMillis();
        ob.quickSort2(a, 0, a.length - 1);
        long endTime1 = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime1 - startTime1) + "毫秒");
//        for (int e:a) System.out.print(e + " ");
    }

    //快速排序基本实现
    public void quickSort1(int a[],int l,int r) {
        if (l < r) {
            int x = a[r];
            int i = l - 1;
            int j,temp;
            for (j = l; j < r; j++) {
                if (a[j] < x) {
                    i++;
                    temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
            a[r] = a[i+1];
            a[i+1] = x;
            quickSort1(a, l, i);
            quickSort1(a, i + 2,r);
        }
    }

    //挖坑填数
    public void quickSort2(int a[],int l,int r) {
        if (l < r) {
            int i = l, j = r;
            int x = a[l];
            while (i<j){
                while(i<j&&a[j]>x)
                    j--;
                if (i<j)
                    a[i++] = a[j];
                while (i<j&&a[i]<x)
                    i++;
                if (i<j)
                    a[j--] = a[i];
            }
            a[i] = x;
            quickSort2(a,l,i-1);
            quickSort2(a,i+1,r);
        }
    }

    public void quickSort3(int[] array,int l,int r){

    }
}
