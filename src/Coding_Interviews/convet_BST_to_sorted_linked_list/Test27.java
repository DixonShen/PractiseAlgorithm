package Coding_Interviews.convet_BST_to_sorted_linked_list;

/**
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
 * Created by dixonshen on 2017/8/23.
 */
public class Test27 {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    public static TreeNode Convert(TreeNode pRootOfTree) {
        TreeNode[] pLastNode = new TreeNode[1];
        convertNode(pRootOfTree, pLastNode);
        TreeNode pHeadNode = pLastNode[0];
        while (pHeadNode != null && pHeadNode.left != null)
            pHeadNode = pHeadNode.left;
        return pHeadNode;
    }

    public static void convertNode(TreeNode pNode, TreeNode[] pLastNode) {
        if (pNode == null)
            return;
        if (pNode.left != null)
            convertNode(pNode.left, pLastNode);
        pNode.left = pLastNode[0];
        if (pLastNode[0] != null)
            pLastNode[0].right = pNode;
        pLastNode[0] = pNode;
        if (pNode.right != null)
            convertNode(pNode.right, pLastNode);
    }

    private static void printTree(TreeNode root) {
        if (root != null) {
            printTree(root.left);
            System.out.print(root.val + "->");
            printTree(root.right);
        }
    }

    private static void printList(TreeNode head) {
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.right;
        }

        System.out.println("null");
    }

    public static void main(String[] args) {
        //            10
        //         /      \
        //        6        14
        //       /\        /\
        //      4  8     12  16
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(6);
        root.right = new TreeNode(14);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(8);
        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(16);
        printList(Convert(root));
    }
}
