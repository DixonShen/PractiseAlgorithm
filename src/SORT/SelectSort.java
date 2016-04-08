package SORT;

/**
 * Created by DixonShen on 2016/4/8.
 */
public class SelectSort {

    public static void main(String[] args) {
        SelectSort ob = new SelectSort();
        int[] a = new int[50000];
        for (int i=0;i<50000;i++)
            a[i] = (int)(100000000*Math.random());
//        for (int e:a) System.out.print(e + " ");
        System.out.println();
        long startTime1 = System.currentTimeMillis();
        ob.selectSort(a);
        long endTime1 = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime1 - startTime1) + "毫秒");
//        for (int e:a) System.out.print(e + " ");
    }

    public void selectSort(int a[]){
        int i,temp;
        for (i=0;i<a.length;i++){
            int p = i;
            for (int j=i;j<a.length;j++){
                if (a[p]>a[j])
                    p = j;
            }
            if (p!=i){
                temp = a[p];
                a[p] = a[i];
                a[i] = temp;
            }
        }
    }
}
