package Coding_Interviews.mirror_of_binary_tree;

/**
 * 输入一个二叉树，该函数输出它的镜像
 * Created by dixonshen on 2017/8/15.
 */
public class Test19 {

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    public void Mirror(TreeNode root) {
        if (root == null)
            return;
        if (root.left == null && root.right == null)
            return;
        TreeNode tempNode = root.left;
        root.left = root.right;
        root.right = tempNode;

        if (root.left != null)
            Mirror(root.left);
        if (root.right != null)
            Mirror(root.right);
    }

    public static void main(String[] args) {

    }
}
