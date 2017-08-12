package Coding_Interviews.merge_two_sorted_linkedlist;

/**
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的结点仍然是按照递增排序的
 * Created by dixonshen on 2017/8/12.
 */
public class Test17 {

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode Merge(ListNode list1,ListNode list2) {
        if (list1 == null)
            return list2;
        if (list2 == null)
            return list1;
        ListNode root;
        if (list1.val < list2.val) {
            root = list1;
            list1 = list1.next;
        } else {
            root = list2;
            list2 = list2.next;
        }
        ListNode last = root;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                last.next = list1;
                last = last.next;
                list1 = list1.next;
            } else {
                last.next = list2;
                last = last.next;
                list2 = list2.next;
            }
        }
        if (list1 == null){
            last.next = list2;
        } else {
            last.next = list1;
        }
        return root;
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        root.next = new ListNode(3);
        root.next.next = new ListNode(5);
        root.next.next.next = new ListNode(7);
        root.next.next.next.next = new ListNode(9);

        ListNode root2 = new ListNode(2);
        root2.next = new ListNode(4);
        root2.next.next = new ListNode(6);
        root2.next.next.next = new ListNode(8);
        root2.next.next.next.next = new ListNode(10);

        printList(Merge(null, null));
    }
}
