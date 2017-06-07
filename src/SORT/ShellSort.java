package SORT;

/**
 * Created by DixonShen on 2016/4/8.
 */
public class ShellSort {

    public static void main(String[] args) {
        ShellSort ob = new ShellSort();
        int[] a = new int[1000000];
        int[] b = new int[1000000];
        for (int i=0;i<100000;i++) {
            a[i] = (int) (1000000000 * Math.random());
            b[i] = (int) (1000000000 * Math.random());
        }
//        for (int e:a) System.out.print(e + " ");
        System.out.println();
        long startTime1 = System.currentTimeMillis();
        ob.shellSort1(a);
        long endTime1 = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime1 - startTime1) + "毫秒");

        long startTime2 = System.currentTimeMillis();
        ob.shellSort2(b);
        long endTime2 = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime2 - startTime2) + "毫秒");
//        for (int e:a) System.out.print(e + " ");
    }


    public void shellSort1(int a[]){
        int i,j,gap;
        int k,temp;
        for (gap=a.length/2;gap>0;gap/=2){
            for (i=0;i<gap;i++){
                for (j=i+gap;j<a.length;j+=gap){
                    if (a[j]<a[j-gap]) {
                        temp = a[j];
                        for (k = j - gap; k >= 0 && a[k] > temp; k -= gap) {
                            a[k+gap] = a[k];
                        }
                        a[k+gap] = temp;
                    }
                }
            }
        }
    }

    // 从数组gap位置开始
    // 比第一种快
    public void shellSort2(int a[]){
        int i,gap,temp;
        int k;
        for (gap=a.length/2;gap>0;gap/=2){
            for (i=gap;i<a.length;i++){
                if (a[i]<a[i-gap]){
                    temp = a[i];
                    k = i-gap;
                    while (k>=0&&a[k]>temp){
                        a[k+gap] = a[k];
                        k -= gap;
                    }
                    a[k+gap] = temp;
                }
            }
        }
    }
}
