package Tree.Trie.RBTree;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by leeon on 2017/5/10.
 */
public class RBTree<T extends Comparable<T>> {

    private RBNode<T> root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    /**
     * RB-Tree node
     * @param <T>
     */
    static class RBNode<T extends Comparable<T>> {
        public boolean red;
        T key;
        public RBNode<T> left;
        public RBNode<T> right;
        public RBNode<T> parent;

        public RBNode (T key, boolean color, RBNode<T> left, RBNode<T> right, RBNode<T> parent) {
            this.key = key;
            this.red = color;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public T getKey(){
            return key;
        }

        @Override
        public String toString(){
            return "" + key + ( red ? "R" : "B");
        }
    }

    public RBTree(){
        root = null;
    }

    /**
     * 二叉查找树左旋
     * @param x 支点
     */
    private void leftRotate(RBNode<T> x) {
        RBNode<T> y = x.right;
        x.right = y.left;
        if (y.left != null)
            y.left.parent = x;
        y.parent = x.parent;
        if (x.parent == null)
            this.root = y;
        else {
            if (x == x.parent.left)
                x.parent.left = y;
            else
                x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    /**
     * 二叉查找树右旋
     * @param x 支点
     */
    private void rightRotate(RBNode<T> x) {
        RBNode<T> y = x.left;
        x.left = y.right;
        if (y.right != null)
            y.right.parent = x;
        y.parent = x.parent;
        if (x.parent == null)
            this.root = y;
        else {
            if (x == x.parent.left)
                x.parent.left = y;
            else
                x.parent.right = y;
        }
        y.right = x;
        x.parent = y;
    }

    /**
     * 二叉查找树插入操作
     * @param key
     */
    public void BSTreeInsert(T key) {
        RBNode<T> current = this.root;
        RBNode<T> p = current;
        RBNode<T> node = new RBNode<T>(key, RED, null, null, null);
        while(current != null) {
            if (key.compareTo(current.key) > 0) {
                p = current;
                current = current.right;
            }
            else if (key.compareTo(current.key) < 0) {
                p = current;
                current = current.left;
            }
            else {
                    System.out.println("结点已存在");
                    return;
            }
        }
        if (this.root != null) {
            if (key.compareTo(p.key) > 0) {
                p.right = node;
                node.parent = p;
            } else {
                p.left = node;
                node.parent = p;
            }
        } else {
            this.root = node;
        }
    }

    /**
     * 打印一棵二叉查找树
     * @param tree
     */
    public void printBST(RBTree tree) {
        List<T> res = levelTraversal(tree.root);
        if (res == null)
            System.out.println("Empty Tree");
        for (T val : res) {
            System.out.print(val + " ");
        }
    }

    public List<T> levelTraversal(RBNode node) {
        if (node == null)
            return null;
        List<T> res = new ArrayList<>();
        Queue<RBNode> nodes = new LinkedList<>();
        res.add((T) node.key);
        nodes.add(node);
        while (nodes.size() != 0) {
            node = nodes.poll();
            if (node.left != null) {
                res.add((T)node.left.key);
                nodes.add(node.left);
            }
            if (node.right != null) {
                res.add((T)node.right.key);
                nodes.add(node.right);
            }
        }
        return res;
    }

    /**
     * 将key插入红黑树
     * @param key
     */
    public void insert(T key) {
        RBNode<T> node = new RBNode<T>(key, RED, null, null, null);
        insert(node);
    }

    /**
     * 将值为key的结点插入红黑树中
     * @param node
     */
    public void insert(RBNode<T> node) {

    }

    public static void main(String[] args) {
        RBTree<Integer> rbTree = new RBTree<>();
        rbTree.BSTreeInsert(13);
        rbTree.BSTreeInsert(8);
        rbTree.BSTreeInsert(17);
        rbTree.BSTreeInsert(1);
        rbTree.BSTreeInsert(11);
        rbTree.BSTreeInsert(15);
        rbTree.BSTreeInsert(6);
        rbTree.BSTreeInsert(25);
        rbTree.BSTreeInsert(22);
        rbTree.BSTreeInsert(27);
        System.out.println(rbTree.root.getKey());
        rbTree.printBST(rbTree);
    }
}
