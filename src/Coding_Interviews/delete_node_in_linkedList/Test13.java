package Coding_Interviews.delete_node_in_linkedList;

/**
 * Created by dixonshen on 2017/7/20.
 */
public class Test13 {

    private static class ListNode{
        int value;
        ListNode next = null;

//        ListNode(int val) {
//            this.value = val;
//        }
    }

    public static ListNode deleteNode(ListNode head, ListNode p) {
        if (head == null || p == null) {
            return head;
        }

        if ( p == head) {
            return head.next;
        }

        if (p.next == null) {
            ListNode tmp = head;
            while (tmp.next != p)
                tmp = tmp.next;
            tmp.next = null;
        } else {
            p.value = p.next.value;
            p.next = p.next.next;
        }

        return head;
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.value + "->");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        ListNode head = new ListNode();
        head.value = 1;

        head.next = new ListNode();
        head.next.value = 2;

        head.next.next = new ListNode();
        head.next.next.value = 3;

        head.next.next.next = new ListNode();
        head.next.next.next.value = 4;

        ListNode middle = head.next.next.next.next = new ListNode();
        head.next.next.next.next.value = 5;

        head.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.value = 6;

        head.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.value = 7;

        head.next.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.next.value = 8;

        ListNode last = head.next.next.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.next.next.value = 9;

        head = deleteNode(head, null); // 删除的结点为空
        printList(head);
        ListNode node = new ListNode();
        node.value = 12;

        head = deleteNode(head, head); // 删除头结点
        printList(head);
        head = deleteNode(head, last); // 删除尾结点
        printList(head);
        head = deleteNode(head, middle); // 删除中间结点
        printList(head);

//        head = deleteNode(head, node); // 删除的结点不在链表中
//        printList(head);
    }
}
