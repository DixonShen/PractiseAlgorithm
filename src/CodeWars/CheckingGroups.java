package CodeWars;

import java.util.Stack;

/**
 * Created by DixonShen on 2016/7/28.
 */

public class CheckingGroups{

    public static boolean groupCheck(String s){
        if(s.length()%2 != 0) return false;

        Stack<Character> closeStack = new Stack<Character>();
        for(char c : s.toCharArray()){
            switch(c){
                case '[': closeStack.push(']'); break;
                case '(': closeStack.push(')'); break;
                case '{': closeStack.push('}'); break;
                case ']': //fall through
                case ')': //fall through
                case '}': if(closeStack.pop() != c) return false;
                default: break;
            }
        }
        return (closeStack.empty()) ? true : false;
    }

    public static boolean groupCheck1(String a){
        if (a == "")
            return true;
        char[] s = a.toCharArray();
        char[] mStack = new char[s.length];
        int i = 0;
        int p = 0;
        while (i < s.length){
            if (s[i] == '(' || s[i] == '{' || s[i] == '['){
                mStack[p] = s[i];
                p++;
            } else {
                if (s[i] == ')'){
                    if (p !=0 && mStack[p-1] == '(')
                        p--;
                    else {
                        return false;
                    }
                }
                if (s[i] == '}'){
                    if (p !=0 && mStack[p-1] == '{')
                        p--;
                    else {
                        return false;
                    }
                }
                if (s[i] == ']'){
                    if (p !=0 && mStack[p-1] == '[')
                        p--;
                    else {
                        return false;
                    }
                }
            }
            i++;
        }
        if (p == 0)
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
//        System.out.println(groupCheck("({})"));
//        System.out.println(groupCheck("[[]()]"));
//        System.out.println(groupCheck("[{()}]"));
//        System.out.println(groupCheck("{(})"));
//        System.out.println(groupCheck("([]"));
//        System.out.println(groupCheck("[])"));
        System.out.println(groupCheck1("{{xysad()(x)}[haha]}"));
    }

}
