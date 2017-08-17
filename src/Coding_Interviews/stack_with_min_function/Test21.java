package Coding_Interviews.stack_with_min_function;

import java.util.Stack;

/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小素的min 函数。在该栈中，调用min、push 及pop的时间复杂度都是0(1)
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
