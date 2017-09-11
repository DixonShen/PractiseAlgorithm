package Coding_Interviews.continous_cards;

/**
 * 从扑克牌中随机抽5张牌，判断是不是一个顺子， 即这5张牌是不是连续的。2～10为数字本身， A为1。 J为11、Q为12、 为13。小王可以看成任意数字。
 * bitmap思想
 * Created by dixonshen on 2017/9/11.
 */
public class Test47 {

    public static int numberOfCards = 14;

    public static boolean isContinuous(int [] numbers) {
        if (numbers == null || numbers.length == 0) return false;
        int[] flags = new int[numberOfCards];
        for (int i=0; i<flags.length; i++)
            flags[i] = 0;
        int zeroCount = 0;
        int numCount = numbers.length;
        for (int a : numbers) {
            if (a == 0) {
                zeroCount++;
                flags[a] = 1;
            } else
                flags[a] = 1;
        }
        boolean isStart = false;
        for (int i=1; i<flags.length; i++) {
            if (numCount == 0 || numCount-zeroCount == 0)
                return true;
            if (flags[i] == 1) {
                isStart = true;
                numCount--;
            }
            else if (isStart){
                if (zeroCount == 0)
                    return false;
                else {
                    zeroCount--;
                    numCount--;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isContinuous(new int[] {0, 3, 2, 6, 4}));
    }
}
