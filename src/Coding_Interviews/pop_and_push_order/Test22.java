package Coding_Interviews.pop_and_push_order;

import java.util.Stack;

/**
 * Created by dixonshen on 2017/8/17.
 */
public class Test22 {

    public static boolean IsPopOrder(int [] pushA,int [] popA) {
        boolean result = true;
        Stack<Integer> mStack = new Stack<>();
        int lastIndex = 0;
        for (int i=0; i<popA.length; i++) {
            if (i == 0) {
                mStack.push(pushA[lastIndex]);
                lastIndex++;
            }
            while (mStack.peek() != popA[i]) {
                if (lastIndex == pushA.length) {
                    result = false;
                    break;
                }
                mStack.push(pushA[lastIndex]);
                lastIndex++;
            }
            mStack.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        int[] push = {1, 2, 3, 4, 5};
        int[] pop = {4, 3, 5, 1, 2};
        System.out.println(IsPopOrder(push, pop));
    }
}
