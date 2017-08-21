package Coding_Interviews.find_specific_path_of_binary_tree;

import java.util.ArrayList;

/**
 * Created by dixonshen on 2017/8/21.
 */
public class Test25 {

    private static ArrayList<Integer> currentPath = new ArrayList<>();

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    public static ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        findPath(result, root, target, 0);
        System.out.println(result);
        return result;
    }

    public static void findPath(ArrayList<ArrayList<Integer>> result, TreeNode root, int target, int currentSum) {
        currentSum += root.val;
        boolean isLeaf = root.left == null && root.right == null;
        currentPath.add(root.val);
        if (currentSum == target && isLeaf) {
            ArrayList<Integer> temp = new ArrayList<>();
            temp.addAll(currentPath);
            result.add(temp);
        }
        if (currentSum < target) {
            if (root.left != null)
                findPath(result, root.left, target, currentSum);
            if (root.right != null)
                findPath(result, root.right, target, currentSum);
        }
        currentPath.remove(currentPath.size()-1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(7);
        System.out.println(FindPath(root, 22));
    }
}
