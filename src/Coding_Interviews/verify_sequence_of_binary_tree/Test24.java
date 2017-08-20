package Coding_Interviews.verify_sequence_of_binary_tree;


/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则返回true。否则返回false。假设输入的数组的任意两个数字都互不相同。
 * 在后序遍历得到的序列中， 最后一个数字是树的根结点的值。
 * 数组中前面的数字可以分为两部分： 第一部分是左子树结点的值，它们都比根结点的值小： 第二部分是右子树结点的值，它们都比根结点的值大。
 * Created by dixonshen on 2017/8/20.
 */
public class Test24 {

    public static boolean VerifySequenceOfBST(int [] sequence) {
        if (sequence == null || sequence.length == 0)
            return false;
        return verifySequenceOfBST(sequence, 0, sequence.length-1);
    }

    public static boolean verifySequenceOfBST(int[] sequence, int start, int end) {
        if (start >= end)
            return true;
        int root = sequence[end];
        int i = start;
        for (; i<end; i++) {
            if (sequence[i] > root)
                break;
        }
        int j=i;
        for (; j<end; j++) {
            if (sequence[j] < root)
                return false;
        }
        return verifySequenceOfBST(sequence, start, i-1) && verifySequenceOfBST(sequence, i, end-1);
    }

    public static void main(String[] args) {
        //           10
        //         /   \
        //        6     14
        //       /\     /\
        //      4  8  12  16
        int[] data = {4, 8, 6, 12, 16, 14, 10};
        System.out.println("true: " + VerifySequenceOfBST(data));

        //           5
        //          / \
        //         4   7
        //            /
        //           6
        int[] data2 = {4, 6, 7, 5};
        System.out.println("true: " + VerifySequenceOfBST(data2));

        //               5
        //              /
        //             4
        //            /
        //           3
        //          /
        //         2
        //        /
        //       1
        int[] data3 = {1, 2, 3, 4, 5};
        System.out.println("true: " + VerifySequenceOfBST(data3));

        // 1
        //  \
        //   2
        //    \
        //     3
        //      \
        //       4
        //        \
        //         5
        int[] data4 = {5, 4, 3, 2, 1};
        System.out.println("true: " + VerifySequenceOfBST(data4));

        // 树中只有1个结点
        int[] data5 = {5};
        System.out.println("true: " + VerifySequenceOfBST(data5));

        int[] data6 = {7, 4, 6, 5};
        System.out.println("false: " + VerifySequenceOfBST(data6));

        int[] data7 = {4, 6, 12, 8, 16, 14, 10};
        System.out.println("false: " + VerifySequenceOfBST(data7));
    }
}
