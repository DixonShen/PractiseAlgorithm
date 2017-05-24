package Tree.RBTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by DixonShen on 2017/5/10.
 */
public class RBTree<T extends Comparable<T>> {

    private RBNode<T> root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    /**
     * 内部类，结点类
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
        RBNode<T> current = this.root;
        RBNode<T> p = current;
        while (current != null) {
            if (node.key.compareTo(current.key) > 0) {
                p = current;
                current = p.right;
            } else if (node.key.compareTo(current.key) < 0) {
                p = current;
                current = p.left;
            } else {
                System.out.println("结点已存在");
                return;
            }
        }
        if (this.root != null) {
            if (node.key.compareTo(p.key) < 0) {
                node.parent = p;
                p.left = node;
            } else {
                node.parent = p;
                p.right = node;
            }
        } else {
            this.root = node;
        }
        insertFixUp(node);
    }

    /**
     * 红黑树插入修正
     * @param node
     */
    public void insertFixUp(RBNode node) {
        RBNode<T> parent, gparent;

        // 若父节点存在，并且父节点的颜色是红色
        while ((parent = node.parent) != null && isRed(parent)) {
            gparent = parent.parent;

            // 若父结点是祖父结点的左儿子
            if (parent == gparent.left) {
                RBNode<T> uncle = gparent.right;

                // 叔叔结点是红色
                if ((uncle != null) && isRed(uncle)) {
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);
                    node = gparent;
                    continue;
                }

                // 叔叔结点是黑色，且当前结点是右子结点
                if (node == parent.right) {
                    leftRotate(parent); // 以父结点为支点左旋
                    RBNode<T> tmp = parent; // 父结点与当前节点交换
                    parent = node;
                    node = tmp;
                }

                // 叔叔结点是黑色，且当前结点是左子结点
                setBlack(parent);
                setRed(gparent);
                rightRotate(gparent);
            } else {    // 若父结点是祖父结点的右孩子
                RBNode<T> uncle = parent.left;

                if (uncle != null && isRed(uncle)) {
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);
                    node = gparent;
                    continue;
                }

                if (node == parent.right) {
                    rightRotate(parent);
                    RBNode<T> tmp = parent;
                    parent = node;
                    node = tmp;
                }

                setBlack(parent);
                setRed(gparent);
                leftRotate(gparent);
            }
        }

        setBlack(this.root);
    }

    public void remove(T key) {
        RBNode<T> node;
        if ((node = this.search(key)) != null)
            remove(node);
    }

    public void remove(RBNode node) {

    }

    public RBNode search(T key) {
        RBNode<T> node = this.root;
        while (node != null) {
            if (key.compareTo(node.key) == 0)
                return node;
            else if (key.compareTo(node.key) > 0)
                node = node.right;
            else if (key.compareTo(node.key) < 0)
                node = node.left;
        }
        return null;
    }

    public boolean isRed(RBNode node) {
        return node.red == RED;
    }

    public void setBlack(RBNode node) {
        node.red = BLACK;
    }

    public void setRed(RBNode node) {
        node.red = RED;
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
