package Coding_Interviews.k_least_numbers;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入n个整数，找出其中最小的k个数。
 * 例子说明：
 * 例如输入4 、5 、1、6、2、7、3 、8 这8 个数字，则最小的4 个数字是1 、2、3 、4
 *
 * 解题思路：

 * 解法一：O(n)时间算法，只有可以修改输入数组时可用。
    可以基于Partition函数来解决这个问题。如果基于数组的第k个数字来调整，使得比第k个数字小的所有数字都位于数组的左边，比第k个数字大的所有数字都位于数组的右边。
    这样调整之后，位于数组中左边的k个数字就是最小的k 个数字（这k 个数字不一定是排序的〉。

 * 解法二： O（nlogk）的算法，精剧适合处理海量数据。
    先创建一个大小为k的数据容器来存储最小的k个数字，接下来我们每次从输入的n个整数中读入一个数．
    如果容器中已有的数字少于k个，则直接把这次读入的整数放入容器之中：如果容器中己有k 数字了，也就是容器己满，此时我们不能再插入新的数字而只能替换已有的数字。
    找出这己有的k 个数中的最大值，然后1在这次待插入的整数和最大值进行比较。
    如果待插入的值比当前己有的最大值小，则用这个数替换当前已有的最大值：如果待插入的值比当前已有的最大值还要大，那么这个数不可能是最小的k个整数之一，于是我们可以抛弃这个整数。
    因此当容器满了之后，我们要做3 件事情： 一是在k 个整数中找到最大数： 二是有可能在这个容器中删除最大数： 三是有可能要插入一个新的数字。我们可以使用一个大顶堆在O(logk）时间内实现这三步操作。
 * Created by dixonshen on 2017/8/25.
 */
public class Test30 {

    /**
     * 大顶堆
     * @param <T>
     */
    private static class MaxHeap<T extends Comparable<T>> {
        private List<T> elements;
        private int cursor;

        public MaxHeap(int size) {
            this.elements = new ArrayList<>(size);
            this.cursor = -1;
        }

        /**
         * 向上调整堆
         * @param index 被上移元素的起始位置
         */
        public void shiftUp(int index) {
            T temp = elements.get(index);

            while (index > 0) {
                int parentIndex = (index - 1) >> 1;
                T parent = elements.get(parentIndex);
                if (elements.get(parentIndex).compareTo(temp) < 0) {
                    elements.set(index, parent);
                    index = parentIndex;
                } else {
                    break;
                }
            }

            elements.set(index, temp);
        }

        /**
         * 向下调整堆
         * @param index 被下移元素的起始位置
         */
        public void shiftDown(int index) {
            T temp = elements.get(index);
            int leftIndex = index << 1 + 1;

            while (leftIndex < elements.size()) {
                T maxChild = elements.get(leftIndex);
                int maxIndex = leftIndex;

                int rightIndex = leftIndex + 1;
                if (rightIndex < elements.size()) {
                    T rightChild = elements.get(rightIndex);
                    if (rightChild.compareTo(maxChild) > 0) {
                        maxChild = rightChild;
                        maxIndex = rightIndex;
                    }
                }

                if (maxChild.compareTo(temp) > 0) {
                    elements.set(index, maxChild);
                    index = maxIndex;
                    leftIndex = index << 1 + 1;
                } else {
                    break;
                }
            }

            elements.set(index, temp);
        }

        /**
         * 添加一个元素
         * @param item
         */
        public void add(T item) {
            elements.add(item);
            shiftUp(elements.size()-1);
        }

        /**
         * 删除堆顶元素
         * @return
         */
        public T deleteTop() {
            if (elements.isEmpty()) {
                throw new RuntimeException("The heap is empty");
            }

            T top = elements.get(0);
            T lastItem = elements.remove(elements.size()-1);
            if (elements.isEmpty()) {
                return lastItem;
            } else {
                elements.set(0, lastItem);
                shiftDown(0);
            }
            return top;
        }

        public T next() {
            if (cursor >= elements.size()) {
                throw new RuntimeException("No more element");
            }
            return elements.get(cursor);
        }

        public boolean hasNext() {
            cursor++;
            return cursor < elements.size();
        }

        public boolean isEmpty() {
            return elements.isEmpty();
        }

        public T first() {
            if (!elements.isEmpty())
                return elements.get(0);
            else
                throw new RuntimeException("The heap is empty");
        }

        public void clear(){
            elements.clear();
        }

        public int size(){
            return elements.size();
        }



    }

    // 解法二
    public static ArrayList<Integer> getLeastNumbers2(int [] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if (input == null || k >input.length || k == 0) return res;
        MaxHeap<Integer> maxHeap = new MaxHeap<>(k);
        for (int a : input) {
            if (maxHeap.size() < k) {
                maxHeap.add(a);
            } else {
                if (maxHeap.first() > a) {
                    maxHeap.deleteTop();
                    maxHeap.add(a);
                }
            }
        }
        for (int i=0; maxHeap.hasNext(); i++) {
            res.add(maxHeap.next());
        }
        return res;
    }


    /**
     * 题目： 输入n个整数，找出其中最小的k个数。
     * 【第一种解法】
     * @param input  输入数组
     * @param output 输出数组
     */
    public static void getLeastNumbers(int[] input, int[] output) {

        if (input == null || output == null || output.length <= 0 || input.length < output.length) {
            throw new IllegalArgumentException("Invalid args");
        }

        int start = 0;
        int end = input.length - 1;
        int index = partition(input, start, end);
        int target = output.length - 1;

        while (index != target) {
            if (index < target) {
                start = index + 1;
            } else {
                end = index - 1;
            }
            index = partition(input, start, end);
        }

        System.arraycopy(input, 0, output, 0, output.length);
    }

    /**
     * 分区算法
     *
     * @param input 输入数组
     * @param start 开始下标
     * @param end   结束下标
     * @return 分区位置
     */
    private static int partition(int[] input, int start, int end) {
        int tmp = input[start];

        while (start < end) {
            while (start < end && input[end] >= tmp) {
                end--;
            }
            input[start] = input[end];

            while (start < end && input[start] <= tmp) {
                start++;
            }
            input[end] = input[start];
        }

        input[start] = tmp;
        return start;
    }

    private static void test2() {
        int[] data = {4, 5, 1, 6, 2, 7, 3, 8};

        int[] output = new int[4];
        System.out.println(getLeastNumbers2(data, 4));
        System.out.println();

        int[] output2 = new int[8];
        System.out.println(getLeastNumbers2(data, 8));
        System.out.println();


        int[] output3 = new int[1];
        System.out.println(getLeastNumbers2(data, 1));
        System.out.println();


        int[] data2 = {4, 5, 1, 6, 2, 7, 2, 8};
        int[] output4 = new int[2];
        System.out.println(getLeastNumbers2(data2, 10));
        System.out.println();
    }


    public static void main(String[] args) {
        test2();
    }
}
