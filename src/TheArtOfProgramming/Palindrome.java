package TheArtOfProgramming;


/**
 * Created by leeon on 2017/3/9.
 */
public class Palindrome {

    // 从两头开始扫描
    public static boolean IsPalindrome1(String s) {
        s = s.trim();
        if (s.length() == 0)
            return false;
        char[] chs = s.toCharArray();
        System.out.println(chs);
        for (int i=0, j=chs.length-1; i<j; i++, j--) {
            if (chs[i] != chs[j])
                return false;
        }
        return true;
    }

    // 从中间开始扫描
    public static boolean IsPalindrome2(String s) {
        s = s.trim();
        if (s.length() == 0)
            return false;
        char[] chs = s.toCharArray();
        for (int i=(s.length()/2)-1, j=s.length()-1-i; i>=0; i--, j++) {
            System.out.println(chs[i] + "  " + chs[j]);
            if (chs[i] != chs[j])
                return false;
        }
        return true;
    }

    /**
     * 问题描述：
     * 判断一条单向链表是不是“回文”
     * 分析：对于单链表结构，可以用两个指针从两端或者中间遍历并判断对应字符是否相等。
     * 但这里的关键就是如何朝两个方向遍历。由于单链表是单向的，所以要向两个方向遍历的话，
     * 可以采取经典的快慢指针的方法，即先位到链表的中间位置，再将链表的后半逆置，
     * 最后用两个指针同时从链表头部和中间开始同时遍历并比较即可。
     *
     * （链表版）
     * @param start
     * @return
     */
    public static boolean ListPalindrome(ListNode start) {
        ListNode p = start;
        int length = 0;
        while (p != null) {
            length++;
            p = p.nextNode;
        }
        if (length == 0) return false;
        p = start;
        for (int i = 0; i < length/2; i++) {
            p = p.nextNode;
        }
        p = RotateString.ReverseList(p, null);
        for (int i = 0; i < length/2; i++) {
            if (start.val != p.val)
                return false;
            p = p.nextNode;
            start = start.nextNode;
        }
        return true;
    }

    public static int LongestPalindrome1(String s) {
        if (s.length() == 0) return 0;
        char[] chs = s.toCharArray();
        int max = 0, cmax = 0;
        int length = s.length();
        for (int i = 0; i<length-1; i++) {
            for (int j = 0; (i-j>=0) && (i + j < length); j++) {     //if the length of palindrome is odd
                if (chs[i-j] != chs[i+j])
                    break;
                cmax = j * 2 + 1;
            }
            if (cmax > max) {
                max = cmax;
            }
            for (int j = 0; (i-j>=0) && (i+j+1<length); j++) {
                if (chs[i-j] != chs[i+j+1])
                    break;
                cmax = j * 2 + 2;
            }
            if (cmax > max)
                max = cmax;
        }
        return max;
    }

    public static void main(String[] args) {

        String str = "abcefecda";
        System.out.println(LongestPalindrome1(str));
//        char[] chs = str.toCharArray();
//        ListNode head = new ListNode(chs[0], null);
//        ListNode temp = head;
//        for (int i=1; i<chs.length; i++) {
//            ListNode node = new ListNode(chs[i], null);
//            temp.nextNode = node;
//            temp = node;
//        }
//        System.out.println(ListPalindrome(head));
    }
}
