package Coding_Interviews.stack_with_min_function;

import java.util.Stack;

/**
 * Created by dixonshen on 2017/8/17.
 */
public class Test21 {

    public Stack<Integer> dataStack = new Stack<>();
    public Stack<Integer> minStack = new Stack<>();
    public int currentMin = Integer.MAX_VALUE;
//    private int size = 0;

    public void push(int node) {
        dataStack.push(node);
        if (currentMin > node)
            currentMin = node;
        minStack.push(currentMin);
    }

    public void pop() {
        dataStack.pop();
        minStack.pop();
    }

    public int top() {
        int num = dataStack.pop();
        dataStack.push(num);
        return num;
    }

    public int min() {
        int num = minStack.pop();
        minStack.push(num);
        return num;
    }

    public static void main(String[] args) {

    }
}
