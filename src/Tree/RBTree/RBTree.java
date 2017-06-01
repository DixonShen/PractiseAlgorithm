package Tree.RBTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

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
        RBNode<T> child, parent;
        boolean color;  // 保存三种情况下被删除结点的颜色

        // 被删除节点的左右孩子都不为空的情况
        if ((node.left != null) && (node.right != null)) {
            // 被删结点的后继结点，用来取代被删结点的位置
            RBNode<T> replace = node;

            // 获取后继结点
            replace = replace.right;
            while (replace.left != null)
                replace = replace.left;

            // "node节点"不是根节点(只有根节点不存在父节点)
            if (node.parent != null){
                if (node == node.parent.left) {
                    node.parent.left = replace;
                } else {
                    node.parent.right = replace;
                }
            } else {
                // node结点是根节点，更新根结点
                this.root = node;
            }

            // child是取代结点的右孩子
            // 取代结点肯定不存在左孩子，因为它是一个后继结点
            child = replace.right;
            parent = replace.parent;
            // 保存取代结点的颜色
            color = replace.red;

            // 被删除结点是它的后继结点的父结点
            if (parent == node) {
                parent = replace;
            } else {
                // child不为空
                if (child != null)
                    child.parent = parent;
                parent.left = child;
                replace.right = node.right;
                node.right.parent = replace;
            }

            replace.parent = node.parent;
            replace.left = node.left;
            replace.red = node.red;
            node.left.parent = replace;

            if (color == BLACK)
                removeFixUp(child, parent);

            node = null;
            return;
        }

        if (node.left != null) {
            child = node.left;
        } else {
            child = node.right;
        }

        parent = node.parent;
        color = node.red;

        if (child != null)
            child.parent = parent;

        if (parent != null) {
            if (parent.left == node) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        } else {
            this.root = child;
        }

        if (color == BLACK)
            removeFixUp(child, parent);

        node = null;
    }

    /**
     * 红黑树删除修正
     * @param node 被删除结点的子节点
     * @param parent 被删除结点的父结点
     */
    public void removeFixUp(RBNode node, RBNode parent) {
        RBNode<T> other;

        while ((node == null || !isRed(node)) && (node != this.root)) {
            if (parent.left == node) {
                other = parent.right;

                // case 1: 当前为黑，兄弟结点为红
                if (isRed(other)){
                    setBlack(other);
                    setRed(parent);
                    leftRotate(parent);
                    other = parent.left;
                }

                // case 2: 当前为黑，兄弟结点为黑，且兄弟的两个儿子都为黑
                if ((other.left == null || !isRed(other.left)) &&
                        (other.right == null || !isRed(other.right))) {
                    setRed(other);
                    node = parent;
                    parent = node.parent;
                } else {
                    // case 3: 当前为黑，兄弟为黑，且兄弟左儿子为红，右儿子为黑
                    if (other.right == null || !isRed(other.right)) {
                        setBlack(other.left);
                        setRed(other.left);
                        rightRotate(other);
                        other = parent.right;
                    }

                    // case 4: 当前为黑，兄弟为黑，且兄弟右儿子为红，左儿子颜色任意
                    setColor(other, parent);
                    setBlack(parent);
                    setBlack(other.right);
                    leftRotate(parent);
                    node = this.root;
                    break;
                }
            } else {
                other = parent.left;

                // case1: 当前为黑，兄弟为红
                if (isRed(other)) {
                    setRed(parent);
                    setBlack(other);
                    rightRotate(parent);
                    other = parent.left;
                }

                // case2: 当前为黑，兄弟为黑，且兄弟两个儿子都为黑
                if ((other.left == null || !isRed(other.left)) &&
                (other.right == null || !isRed(other.right))) {
                    setRed(other);
                    node = parent;
                    parent = node.parent;
                } else {
                    //case3: 当前为黑，兄弟为黑，且兄弟左儿子为红，右儿子为黑
                    if (other.left == null || !isRed(other.left)){
                        setRed(other);
                        setBlack(other.right);
                        leftRotate(other);
                        other = parent.left;
                    }

                    //case4: 当前为黑，兄弟为黑，且兄弟右儿子为红，左儿子为任意颜色
                    setColor(other, parent);
                    setBlack(parent);
                    setBlack(other.left);
                    rightRotate(parent);
                    node = this.root;
                    break;
                }
            }
        }

        if (node != null) {
            setBlack(node);
        }
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

    public void setColor(RBNode node1, RBNode node2) {
        node1.red = node2.red;
    }

    public static void main(String[] args) {

    }
}
