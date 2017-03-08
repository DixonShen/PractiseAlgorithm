package TheArtOfProgramming;

/**
 * 字符串旋转
 * Created by leeon on 2017/3/7.
 */
public class RotateString {

    /**
     * 问题描述：
     * 给定一个字符串，要求把字符串前面的若干个字符移动到字符串的尾部，
     * 如把字符串“abcdef”前面的2个字符'a'和'b'移动到字符串的尾部，使得原字符串变成字符串“cdefab”。
     * 请写一个函数完成此功能，要求对长度为n的字符串操作的
        时间复杂度为 O(n)，
        空间复杂度为 O(1)。
     *
     * 三步反转法
     * @param chs
     * @param n
     * @return
     */
    public static char[] LeftRotate(char[] chs, int n) {
        n %= chs.length;
        char[] reverseLeft = ReverseString(chs, 0, n-1);
        char[] reverseRight = ReverseString(reverseLeft, n, chs.length-1);
        char[] res = ReverseString(reverseRight, 0, chs.length-1);
        return res;
    }

    /**
     * 翻转字符串
     * @param chs
     * @param from
     * @param to
     * @return
     */
    public static char[] ReverseString(char[] chs, int from, int to) {

        while (from < to) {
            char temp = chs[to];
            chs[to--] = chs[from];
            chs[from++] = temp;
        }

        return chs;
    }

    /**
     * 问题描述：
     * 链表翻转。给出一个链表和一个数k，比如，链表为1→2→3→4→5→6，k=2，则翻转后2→1→6→5→4→3，若k=3，翻转后
     *  3→2→1→6→5→4，若k=4，翻转后4→3→2→1→6→5，用程序实现。
     *
     * 思路：
     *  使用反转链表的思路，将链表的前部分反转，然后将链表的后部分反转，最后将前部分链表的尾节点指向后部分链表的头节点。
     *
     * @param chList
     * @param k
     * @return
     */
    public static ListNode LinkedListRotate(ListNode chList, int k) {
        ListNode leftEnd = chList;
        for (int i = 0; i < k; i++) {
            leftEnd = leftEnd.nextNode;
        }
        ListNode leftStart = ReverseList(chList, leftEnd);
        ListNode rightStart = ReverseList(leftEnd, null);
        chList.nextNode = rightStart;
        return leftStart;
    }

    public static ListNode ReverseList(ListNode start, ListNode end){
        ListNode pre = null;
        ListNode current = start;
        ListNode next;
        while (current != end) {
            next = current.nextNode;
            current.nextNode = pre;
            pre = current;
            current = next;
        }
        return pre;
    }

    //print the linked list
    public static void printLinkedList(ListNode head) {
        ListNode temp = head;
        String res = "";
        while (temp != null) {
            res += temp.val;
            temp = temp.nextNode;
        }
        System.out.println(res);
    }

    /**
     *  问题描述：
     *  编写程序，在原字符串中把字符串尾部的m个字符移动到字符串的头部，要求：长度为n的字符串操作时间复杂度为O(n)，空间复杂度
     *  为O(1)。 例如，原字符串为”Ilovebaofeng”，m=7，输出结果为：”baofengIlove”。
     *
     *  思路：
     *   与左旋转字符串的思路是一样的；
     * @param str
     * @param m
     * @return
     */
    public static char[] RightRotate(char[] str, int m) {
        m %= str.length;
        char[] reverseLeft = ReverseString(str, 0, str.length-m-1);
        char[] reverseRight = ReverseString(reverseLeft, str.length-m, str.length-1);
        char[] res = ReverseString(reverseRight, 0, str.length-1);
        return res;
    }

    /**
     * 问题描述：
     *  单词翻转。输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变，句子中单词以空格符隔开。为简单起见，标点符
     *  号和普通字母一样处理。例如，输入“I am a student.”，则输出“student. a am I”。
     *
     * 思路：
     *  先将每个单词反转，然后将整个句子反转；
     *
     * @param sentence
     * @return
     */
    public static String RotateWords(String sentence) {
        String[] words = sentence.split(" ");
        for (int i = 0; i<= (int)(words.length/2-1); i++) {
            String temp = words[i];
            words[i] = words[words.length-1-i];
            words[words.length-1-i] = temp;
        }
        String res = "";
        for (String word : words)
            res += word + " ";
        return res.trim();
    }

    public static void main(String[] args) {
        String str = "Ilovebaofeng";
        char[] chs = str.toCharArray();

        String sentence = "I am a student.";
        System.out.println(RotateWords(sentence));

//        ListNode head = new ListNode(chs[0], null);
//        ListNode temp = head;
//        for (int i=1; i<chs.length; i++) {
//            ListNode node = new ListNode(chs[i], null);
//            temp.nextNode = node;
//            temp = node;
//        }
//        head = LinkedListRotate(head, 4);
//        printLinkedList(head);
    }
}

class ListNode {
    char val;
    ListNode nextNode;

    public ListNode(char val1, ListNode nextNode1) {
        val = val1;
        nextNode = nextNode1;
    }
}
