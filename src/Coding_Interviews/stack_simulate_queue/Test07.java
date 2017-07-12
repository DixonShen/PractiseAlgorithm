package Coding_Interviews.stack_simulate_queue;

import java.util.Stack;

/**
 * 用两个栈实现一个队列
 * Created by dixonshen on 2017/7/12.
 */
public class Test07 {

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack1.isEmpty() && stack2.isEmpty()) return 0;
        if (!stack2.isEmpty())
            return stack2.pop();
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }

    public static void main(String[] args) {

    }
}
