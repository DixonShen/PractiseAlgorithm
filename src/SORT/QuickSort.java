package SORT;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by DixonShen on 2016/4/8.
 */
public class QuickSort {

    public static void main(String[] args) {
        QuickSort ob = new QuickSort();
        System.out.println("程序运行时间（毫秒）：");
        int[] a = new int[10000000];
        for (int i=0;i<a.length;i++)
            a[i] = (int)(1000000000*Math.random());
//        for (int e:a) System.out.print(e + " ");
        System.out.println();
        long startTime1 = System.currentTimeMillis();
//        ob.quickSort6(a, 0, a.length - 1);
        Arrays.sort(a);
        long endTime1 = System.currentTimeMillis();
        System.out.println("随机数组  " + (endTime1-startTime1));
        int[] b = new int[10000000];
        for (int i=0;i<b.length;i++)
            b[i] = i;
        long startTime2 = System.currentTimeMillis();
//        ob.quickSort6(b, 0, b.length - 1);
        Arrays.sort(b);
        long endTime2 = System.currentTimeMillis();
        System.out.println("升序数组  " + (endTime2-startTime2));
        int[] c = new int[10000000];
        for (int i=10000000,j=0;j<c.length;i--,j++)
            c[j] = i;
        long startTime3 = System.currentTimeMillis();
//        ob.quickSort6(c, 0, c.length - 1);
        Arrays.sort(c);
        long endTime3 = System.currentTimeMillis();
        System.out.println("降序数组  " + (endTime3-startTime3));

//        for (int e:a) System.out.print(e + " ");
    }

    //swap
    public void swap(int a[], int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    //median-of-three
    public static int med3(int x[], int a, int b, int c){
        int med;
        if (x[a]<x[b]) {
            if (x[b] < x[c]) {
                med = b;
            }
            else{
                if (x[a]<x[c]){
                    med = c;
                }
                else {
                    med = a;
                }
            }
        }
        else {
            if (x[b]>x[c]){
                med = b;
            }
            else {
                if (x[a]>x[c])
                    med = c;
                else
                    med = a;
            }
        }
        return med;
//        return x[a] < x[b] ? ( x[b] < x[c] ? b : x[a] <x[c] ? c:a)
//                : x[b] > x[c] ? b : x[a] > x[c] ? c : a;
    }

    /**
     * 将数组x中，从a开始连续n个数移动到数组末尾
     * @param x
     * @param a
     * @param b
     * @param n
     */
    public void vecswap(int x[], int a, int b, int n){
        for (int i = 0;i<n;i++,a++,b++)
            swap(x,a,b);
    }

    public int choosePivot(int[] a, int l, int r) {
        int len = r-l+1;
        int m = l + (len >> 1); // 取中间位置
        // 选取pivot位置
        if (len >7){
            int p = l;
            int q = r;
            if(len>40){
                int s = len/8;
                p = med3(a,p,p+s,p+2*s);
                m = med3(a,m-s,m,m+s);
                q = med3(a,q-2*s,q-s,q);
            }
            m = med3(a,p,q,m);
        }
        return m;
    }

    public void insertSort(int[] a, int l, int r) {
        for (int i=l+1;i<=r;i++){
            if (a[i]<a[i-1]){
                int temp = a[i];
                int j = i-1;
                for (;j>=l&&a[j]>temp;j--)
                    a[j+1] = a[j];
                a[j+1] = temp;
            }
        }
        return ;
    }

    //快速排序基本实现
    public void quickSort1(int a[],int l,int r) {
        if (l < r) {
            int x = a[r];
            int i = l - 1;  // i指向小于pivot的最右边的数
            int j;
            for (j = l; j < r; j++) {
                if (a[j] < x) {
                    i++;
                    swap(a,i,j);
                }
            }
            a[r] = a[i+1];
            a[i+1] = x;
            quickSort1(a, l, i);
            quickSort1(a, i + 2,r);
        }
    }

    //双向扫描+随机化
    public void quickSort2(int a[],int l,int r) {
        if (l < r) {
            int i = l, j = r+1;
            Random rand = new Random();
            int p = rand.nextInt(r - l + 1) + l;
            swap(a,i,p);
            int x = a[l];
            while (true){
                do {
                    i++;
                }while (i<=r&&a[i]<x);
                do {
                    j--;
                }while (a[j]>x);
                if(j<i)
                    break;
                swap(a,i,j);
            }
            swap(a,l,j);
            quickSort2(a,l,j-1);
            quickSort2(a,j+1,r);
        }
    }

    //快排随机化
    public void quickSort3(int a[],int l,int r) {
        if (l < r) {
            Random rand = new Random();
            int p = rand.nextInt(r - l + 1) + l;
//            int p = (l+r)/2;
            swap(a,r,p);
            int x = a[r];
            int i = l - 1;
            int j;
            for (j = l; j < r; j++) {
                if (a[j] < x) {
                    i++;
                    swap(a,i,j);
                }
            }
            a[r] = a[i+1];
            a[i+1] = x;
            quickSort3(a,l,i);
            quickSort3(a,i+2,r);
        }
    }

    public void quickSort4(int a[],int l,int r) {
        if (l < r) {
            //子序列长度小于某个阀值时，执行插入排序
            //还可以不进行排序，而在递归结束是对整个数组进行插入排序  本质上相同
            if((r-l+1)<7){
                insertSort(a, l, r);
                return;
            }
            int i = l, j = r+1;
            Random rand = new Random();
            int p = rand.nextInt(r - l + 1) + l;
            swap(a,i,p);
            int x = a[l];
            while (true){
                do {
                    i++;
                }while (i<=r&&a[i]<x);
                do {
                    j--;
                }while (a[j]>x);
                if(j<i)
                    break;
                swap(a,i,j);
            }
            swap(a,l,j);
            quickSort4(a,l,j-1);
            quickSort4(a,j+1,r);
        }
    }


    //取中间数作为pivot
    public void quickSort5(int a[], int l, int r) {
        if (l < r) {

            if((r-l+1)<7){
                for (int i = l+1; i<=r;i++ ){
                    if(a[i]<a[i-1]){
                        int temp = a[i];
                        int j;
                        for (j = i-1; j>=l&&a[j]>temp;j--){
                            a[j+1] = a[j];
                        }
                        a[j+1] = temp;
                    }
                }
                return;
            }

            int m = choosePivot(a, l, r);

            swap(a,l,m);

            int i = l, j = r + 1;
            int x = a[l];
            while (true) {
                do {
                    i++;
                } while (i <= r && a[i] < x);
                do {
                    j--;
                } while (a[j] > x);
                if (j < i)
                    break;
                swap(a, i, j);
            }
            swap(a, l, j);
            quickSort5(a, l, j - 1);
            quickSort5(a, j + 1, r);
        }
    }

    /**
     * 三分区快排
     * 连续、相等的元素不需要参加排序
     * version1
     * @param a
     * @param l
     * @param r
     */
    public void quickSort6(int a[], int l, int r){
        if (l<r){

            if((r-l+1)<7){
                insertSort(a, l, r);
                return ;
            }
            int len = r-l+1;
            int m = choosePivot(a, l, r);

            int p = l,q = p;
            int s = r, t = s;
            int x = a[m];
            // p, s为左右进行扫描的指针，直到p, s相遇，扫描结束
            // q, s两个指针用来记录左右两端各有多少等于pivot的元素[0,...,q-1], [t+1,...,r-l]为等于pivot的元素
            // 后面将这些等于pivot的值移动到中间
            while (true){
                while (p<=s&&a[p]<=x){
                    if (a[p]==x)
                        swap(a,q++,p);
                    p++;
                }
                while (p<=s&&a[s]>=x){
                    if (a[s]==x)
                        swap(a,t--,s);
                    s--;
                }
                if (p>s)
                    break;
                swap(a,p++,s--);
            }

            // 此处分别考察左右两端等于pivot的元素数量与小于和大于pivot的元素数量，依此进行交换，
            // 使等于pivot的元素移动到数组中间
            int i, n = l+len;
            i = Math.min(q-l,p-q);
            vecswap(a,l,p-i,i);
            i = Math.min(t-s,n-1-t);
            vecswap(a,p,n-i,i);

            if ((i = p-q)>1)
                quickSort6(a,l,l+i-1);
            if ((i= t-s)>1)
                quickSort6(a,r-i+1,r);
        }
    }

    /**
     * 三分区快排
     * @param a
     * @param l
     * @param r
     */
    public void quickSort7(int[] a, int l, int r) {
        if((r-l+1)<7){
            insertSort(a, l, r);
            return ;
        }

        int lt = l, i = l + 1, gt = r;
        int x = a[l];
        while (i <= gt) {
            if (a[i]<x){
                swap(a, lt++, i++);
            } else if (a[i]>x) {
                swap(a, i, gt--);
            } else if (a[i]==x) {
                i++;
            }
        }
        quickSort7(a, l, lt-1);
        quickSort7(a, gt+1, r);
    }
}

