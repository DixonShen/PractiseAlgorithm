package Coding_Interviews.print_binary_tree_from_top_to_bottom;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 从上往下打印出二叉树的每个结点，同一层的结点按照从左向右的顺序打印。
 * Created by dixonshen on 2017/8/17.
 */
public class Test23 {

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }

    }

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root != null) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            TreeNode temp;
            while (!queue.isEmpty()) {
                temp = queue.remove();
                result.add(temp.val);
                System.out.print(temp.val + " ");
                if (temp.left != null)
                    queue.add(temp.left);
                if (temp.right != null)
                    queue.add(temp.right);
            }
        }
        return result;
    }



    public static void main(String[] args) {

    }
}
