package SORT;

/**
 * Created by DixonShen on 2016/4/6.
 * 记录下交换位置，对后面的有序数据无需重复遍历
 */
public class BubbleSort2 {

    public static void main(String[] args) {
        int[] a = new int[50000];
        for (int i=0;i<50000;i++)
            a[i] = (int)(10000000*Math.random());
//        for (int e:a) System.out.print(e + " ");
        System.out.println();
        long startTime = System.currentTimeMillis();
        bubbleSort(a);
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "毫秒");
//        for (int e:a) System.out.print(e + " ");
    }

    public static void bubbleSort(int a[]){
        int flag;
        int i = 0;
        int j;
        flag = a.length-1;
        int temp;
        while (flag>0){
            j = flag;
            flag = 0;
            for (i=0; i < j; i++) {
                if (a[i] > a[i + 1]) {
                    temp = a[i];
                    a[i] = a[i+1];
                    a[i+1] = temp;
                    flag = i;
                }
            }
        }
    }
}
