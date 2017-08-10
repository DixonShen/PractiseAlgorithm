package Coding_Interviews.kth_node_to_tail_in_linkedlist;

/**
 * 链表中倒数第k个结点
 * Created by dixonshen on 2017/8/10.
 */
public class Test15 {

    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode FindKthToTail(ListNode head, int k) {
        if (k == 0 || head == null)
            return null;

        ListNode first = head;
        ListNode behind = null;
        for (int i=0; i<k-1; i++){
            if (first.next != null)
                first = first.next;
            else
                return null;
        }
        behind = head;
        while (first.next != null) {
            first = first.next;
            behind = behind.next;
        }
        return behind;
    }

    public static void main(String[] args) {

    }
}
