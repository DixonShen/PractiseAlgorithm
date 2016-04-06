package SORT;

/**
 * Created by DixonShen on 2016/4/6.
 * 冒泡排序
 * 设置flag标记当前循环是否发生交换
 * 若为发生交换，则当前已为有序数组
 */
public class BubbleSort1 {

    public static void main(String[] args) {
        int[] a = new int[200000];
        for (int i=0;i<200000;i++)
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
        boolean flag = true;
        int i = 0;
        int j = a.length-1;
        int temp;
        while (flag){
            flag = false;
            for (i=0; i < j; i++) {
                if (a[i] > a[i + 1]) {
                    temp = a[i];
                    a[i] = a[i+1];
                    a[i+1] = temp;
                    flag = true;
                }
            }
            j--;
        }
    }
}
