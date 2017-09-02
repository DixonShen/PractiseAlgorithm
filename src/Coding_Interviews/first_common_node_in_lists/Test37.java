package Coding_Interviews.first_common_node_in_lists;

/**
 * 输入两个链表，找出它们的第一个公共结点。
 * Created by dixonshen on 2017/9/2.
 */
public class Test37 {

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) return null;
        int size1 = 0, size2 = 0;
        int count1 = 0, count2 = 0;
        ListNode p1 = pHead1, p2 = pHead2;
        while (p1 != null) {
            size1++;
            p1 = p1.next;
        }
        p1 = pHead1;
        while (p2 != null) {
            size2++;
            p2 = p2.next;
        }
        p2 = pHead2;
        if (size1 > size2) {
            while (size1 > size2+count1) {
                p1 = p1.next;
                count1++;
            }
        }
        if (size1 < size2) {
            while (size2 > size1+count2) {
                p2 = p2.next;
                count2++;
            }
        }
        while (p1 != p2) {
            if (p1 == null || p2 == null) return null;
            p1= p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    public static void main(String[] args) {
        ListNode root1 = new ListNode(1);
        root1.next = new ListNode(2);
        root1.next.next = new ListNode(3);

        ListNode root2 = new ListNode(4);
        root2.next = new ListNode(5);

        ListNode firstCommonNode = new ListNode(6);
        firstCommonNode.next = new ListNode(7);
        root1.next.next.next = firstCommonNode;

        root2.next.next = firstCommonNode;

        System.out.println(FindFirstCommonNode(root1, root2).val);
    }
}
