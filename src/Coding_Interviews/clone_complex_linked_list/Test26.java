package Coding_Interviews.clone_complex_linked_list;

/**
 * 请实现函数ComplexListNode clone(ComplexListNode head),复制一个复杂链表。
 * 在复杂链表中，每个结点除了有一个next 域指向下一个结点外，还有一个sibling 指向链表中的任意结点或者null。
 *
 * 在不用辅助空间的情况下实现O(n)的时间效率。
 * 第一步：仍然是根据原始链表的每个结点N 创建对应的N’。把N’链接在N的后面。
 * 第二步：设置复制出来的结点的sibling。
 * 第三步：把这个长链表拆分成两个链表。
 * Created by dixonshen on 2017/8/22.
 */
public class Test26 {

    public static class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    public static RandomListNode Clone(RandomListNode pHead) {
        cloneNodes(pHead);
        connectSiblings(pHead);
        return reconnectNodes(pHead);
    }

    public static void cloneNodes(RandomListNode head) {
        RandomListNode pNode = head;
        while (pNode != null) {
            RandomListNode clonedNode = new RandomListNode(pNode.label);
            clonedNode.next = pNode.next;
            pNode.next = clonedNode;
            pNode = clonedNode.next;
        }
    }

    public static void connectSiblings(RandomListNode head) {
        RandomListNode pNode = head;
        while (pNode != null) {
            RandomListNode pClonedNode = pNode.next;
            if (pNode.random != null) {
                pClonedNode.random = pNode.random;
            }
            pNode = pClonedNode.next;
        }
    }

    public static RandomListNode reconnectNodes(RandomListNode head) {
        RandomListNode pNode = head;
        RandomListNode pClonedNode = null, pClonedHead = null;
        if (pNode != null) {
            pClonedHead = pClonedNode = pNode.next;
            pNode.next = pClonedNode.next;
            pNode = pClonedNode.next;
        }
        while (pNode != null) {
            pClonedNode.next = pNode.next;
            pClonedNode = pClonedNode.next;
            pNode.next = pClonedNode.next;
            pNode = pNode.next;
        }
        return pClonedHead;
    }

    public static void printList(RandomListNode head) {
        if (head == null) {
            System.out.println("empty list");
            return;
        }
        while (head != null) {
            System.out.println(head.label);
            head = head.next;
        }
    }

    /**
     * 判断两个链表是否是同一个链表，不是值相同
     *
     * @param h1 链表头1
     * @param h2 链表头2
     * @return true：两个链表是同一个链表，false：不是
     */
    public static boolean isSame(RandomListNode h1, RandomListNode h2) {
        while (h1 != null && h2 != null) {
            if (h1 == h2) {
                h1 = h1.next;
                h2 = h2.next;
            } else {
                return false;
            }
        }

        return h1 == null && h2 == null;
    }

    public static void main(String[] args) {
        //          -----------------
        //         \|/              |
        //  1-------2-------3-------4-------5
        //  |       |      /|\             /|\
        //  --------+--------               |
        //          -------------------------
        RandomListNode head = new RandomListNode(1);
        head.next = new RandomListNode(2);
        head.next.next = new RandomListNode(3);
        head.next.next.next = new RandomListNode(4);
        head.next.next.next.next = new RandomListNode(5);

        head.random = head.next.next;
        head.next.next.next.random = head.next;
        head.next.random = head.next.next.next.next;
    }
}
