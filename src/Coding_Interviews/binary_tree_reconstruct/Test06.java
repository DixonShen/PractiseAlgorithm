package Coding_Interviews.binary_tree_reconstruct;

/**
 * Created by dixonshen on 2017/7/11.
 */
public class Test06 {

    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if (pre == null || in == null || pre.length != in.length || pre.length < 1)
            return null;
        TreeNode root = null;
        root = reconstruct(pre,0,pre.length-1,in,0,in.length-1);
        return root;
    }

    public TreeNode reconstruct(int[] pre, int start1, int end1, int[] in, int start2, int end2) {
        TreeNode root = new TreeNode(pre[start1]);
        if (start1 == end1)
            return root;
        int i = start2;
        for (;i<=end2; i++) {
            if (in[i] == pre[start1])
                break;
        }
        root.left = (i-start2)==0 ? null : reconstruct(pre, start1+1, start1+i-start2, in, start2, i-1);
        root.right = (end2-i)==0 ? null : reconstruct(pre, start1+i-start2+1, end1, in,i+1, end2);
        return root;
    }

    public static void printTree(TreeNode root) {
        if (root != null) {
            printTree(root.left);
            System.out.print(root.val + " ");
            printTree(root.right);
        }
    }

    public static void main(String[] args) {
        int[] preorder = {1, 2, 3, 4, 5};
        int[] inorder = {1, 2, 3, 4, 5};
        Test06 test = new Test06();
        TreeNode root = test.reConstructBinaryTree(preorder, inorder);
        printTree(root);
    }

}
