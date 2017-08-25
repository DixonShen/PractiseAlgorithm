package Coding_Interviews.more_than_half_number;

/**
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字
 * 例子说明：
 * 如输入一个长度为9 的数组｛ 1, 2, 3, 2, 2, 2, 5, 4, 2｝。由于数字2在数组中出现了5 次，超过数组长度的一半，因此输出2 。
 *
 * 解法一：基于Partition 函数的O（n）算法
 *
 * 解法二：根据数组组特点找出O(n)的算法
 *
 * Created by dixonshen on 2017/8/25.
 */
public class Test29 {

    /**
     * 解法二
     * @param array
     * @return
     */
    public static int moreThanHalfNum(int [] array) {
        if (array == null || array.length == 0)
            return 0;
        int count = 0;
        int currentNum = 0;
        for (int i : array) {
            if (count == 0) {
                currentNum = i;
                count = 1;
                continue;
            }
            if (currentNum != i) {
                count--;
                continue;
            }
            count++;
        }

        count = 0;
        for (int i : array) {
            if (currentNum == i)
                count++;
        }

        if (count <= array.length >> 1)
            return 0;
        else return currentNum;
    }

    public static void main(String[] args) {
        // 存在出现次数超过数组长度一半的数字
        int numbers[] = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println(moreThanHalfNum(numbers));

        // 出现次数超过数组长度一半的数字都出现在数组的前半部分
        int numbers2[] = {2, 2, 2, 2, 2, 1, 3, 4, 5};
        System.out.println(moreThanHalfNum(numbers2));

        // 出现次数超过数组长度一半的数字都出现在数组的后半部分
        int numbers3[] = {1, 3, 4, 5, 2, 2, 2, 2, 2};
        System.out.println(moreThanHalfNum(numbers3));

        // 只有一个数
        int numbers4[] = {1};
        System.out.println(moreThanHalfNum(numbers4));

        // 输入空指针
//        moreThanHalfNum(null);
        // 不存在出现次数超过数组长度一半的数字
        int numbers5[] = {1, 2, 3, 2, 4, 2, 5, 2, 3};
        moreThanHalfNum(numbers5);
    }
}
