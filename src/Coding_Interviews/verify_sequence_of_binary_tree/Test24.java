package Coding_Interviews.verify_sequence_of_binary_tree;


/**
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
