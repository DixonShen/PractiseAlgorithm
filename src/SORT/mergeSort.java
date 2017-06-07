package SORT;

/**
 * Created by DixonShen on 2016/4/6.
 */

//import java.util.Random;

public class mergeSort {
    public static void main(String[] args) {
        int[] a = new int[1000000];
        int[] temp = new int[1000000];
        for (int i=0;i<1000000;i++)
            a[i] = (int)(100000000*Math.random());
//        for (int e:a) System.out.print(e + " ");
        System.out.println();
        long startTime = System.currentTimeMillis();
        MergeSort(a,temp);
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime-startTime) + "毫秒");
//        for (int e:a) System.out.print(e + " ");
    }

    //将有序列[start...mid]和[mid+1...end]合并
    public static void merge(int a[],int temp[],int start,int mid,int end){
        int i = start, j = mid+1;
        int k =0;
        while(i<mid+1&&j<=end){
            if(a[i]>a[j]){
                temp[k++] = a[j++];
            }
            else {
                temp[k++] = a[i++];
            }
        }
        while (i<mid+1)
            temp[k++] = a[i++];
        while (j<=end)
            temp[k++] = a[j++];
        for (i=0,j=start;j<end+1;i++,j++)
            a[j] = temp[i];
    }

    public static void mergeSort(int a[],int temp[],int start,int end){
        // 递归将数组分解
        // 可改进
        // 改进1：子序列数量较少时，可直接使用选择排序
        // 改进2：当左侧最大值<=右侧最小值时，序列已经有序，无需归并
        if (start<end){
            int mid = (start+end)/2;
            mergeSort(a,temp,start,mid);  //左边有序
            mergeSort(a,temp,mid+1,end);  //右边有序
            merge(a,temp,start,mid,end);  //归并两边
        }
    }

    public static boolean MergeSort(int a[],int temp[]){
        if (temp==null)
            return false;
        mergeSort(a,temp,0,a.length-1);
        return true;
    }
}
