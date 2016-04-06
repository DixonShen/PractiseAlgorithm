package 排序;

/**
 * Created by DixonShen on 2016/4/6.
 */

//import java.util.Random;

public class mergeSort {
    public static void main(String[] args) {
        int[] a = new int[50];
        int[] temp = new int[50];
        for (int i=0;i<50;i++)
            a[i]=(int)(10000*Math.random());
//        for (int e:a) System.out.print(e + " ");
        System.out.println();
        long startTime = System.currentTimeMillis();
        MergeSort(a,temp);
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime-startTime) + "毫秒");

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

    public static void mergesort(int a[],int temp[],int start,int end){
        //递归将数组分解
        if (start<end){
            int mid = (start+end)/2;
            mergesort(a,temp,start,mid);  //左边有序
            mergesort(a,temp,mid+1,end);  //右边有序
            merge(a,temp,start,mid,end);  //归并两边
        }
    }

    public static boolean MergeSort(int a[],int temp[]){
        if (temp==null)
            return false;
        mergesort(a,temp,0,a.length-1);
        return true;
    }
}
