package Coding_Interviews.print_linkedlist_reversingly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * 题目：输入个链表的头结点，从尾到头反过来打印出每个结点的值。
 * Created by dixonshen on 2017/7/10.
 */
public class Test05 {

    /**
     * 结点
     */
    private static class ListNode{
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 递归方式进行
     * @param root
     */
    public static ArrayList<Integer> printListUsingRecursion(ListNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root != null) {
            res.addAll(printListUsingRecursion(root.next));
            res.add(root.val);
        }
        return res;
    }

    /**
     * 栈方式
     * @param root
     */
    public static void printListUsingStack(ListNode root) {
        Stack<ListNode> stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            root = root.next;
        }
        ListNode temp;
        while (!stack.isEmpty()){
            temp = stack.pop();
            System.out.println(temp.val + " ");
        }
    }

    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(4);
        root.next.next.next.next = new ListNode(5);

        System.out.println(Arrays.toString(printListUsingRecursion(root).toArray(new Integer[0])));
        System.out.println();
        printListUsingStack(root);
    }
}
