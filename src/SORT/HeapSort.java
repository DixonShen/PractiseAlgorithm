package SORT;

/**
 * Created by DixonShen on 2016/4/13.
 */
public class HeapSort {

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] =a[j];
        a[j] = temp;
    }

    public void sink(int[] a, int k, int n) {
        while ((2*k) < n-1) {
            int j = 2*k;
            if (a[j] < a[j+1]) j++;
            if (a[k] > a[j]) break;
            swap(a, k, j);
            k = j;
        }
    }

    public void heapSort(int[] a) {
        // 建堆
        for (int i=(a.length-1)/2; i>=1; i--) {
            sink(a, i, a.length);
        }

        int n = a.length-1;
        while (n>1) {
            swap(a, 1, n--);
            sink(a, 1, n);
        }

    }

    public static void main(String[] args) {
        HeapSort hs = new HeapSort();
        int[] a = new int[10000001];
        for (int i=1; i<a.length; i++) {
            a[i] = (int)(10000000*Math.random());
        }
        System.out.println();
        long startTime1 = System.currentTimeMillis();
        hs.heapSort(a);
//        Arrays.sort(a);
        long endTime1 = System.currentTimeMillis();
        System.out.println("随机数组  " + (endTime1-startTime1));
    }


}
