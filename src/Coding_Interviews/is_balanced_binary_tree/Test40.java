package Coding_Interviews.is_balanced_binary_tree;

/**
 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 * Created by dixonshen on 2017/9/4.
 */
public class Test40 {


    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static boolean IsBalanced_Solution(TreeNode root) {
        int[] depth = new int[1];
        return isBalanced(root, depth);
    }

    public static boolean isBalanced(TreeNode root, int[] depth) {
        if (root == null) {
            depth[0] = 0;
            return true;
        }
        int[] left = new int[1];
        int[] right = new int[1];
        if (isBalanced(root.left, left) && isBalanced(root.right, right)) {
            int diff = left[0] - right[0];
            if (diff >= -1 && diff <= 1) {
                depth[0] = 1 + ((left[0] > right[0]) ? left[0] : right[0]);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(7);
        System.out.println(IsBalanced_Solution(root));
    }
}
