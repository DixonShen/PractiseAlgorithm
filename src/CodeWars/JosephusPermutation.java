package CodeWars;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ada on 2017/1/4.
 */
public class JosephusPermutation {

    public static <T> List<T> josephusPermutation(final List<T> items, final int k) {

        List<T> res = new ArrayList<T>();
        List<T> temp = new ArrayList<T>(items);
//        int[] orders = new int[items.size()];
//        boolean[] flags = new boolean[items.size()];
        int currentIndex = -1;
        while (res.size() != items.size()) {
            currentIndex = pick(temp, k, currentIndex);
            res.add(temp.get(currentIndex));
            temp.remove(currentIndex);
            currentIndex--;
        }
        return res;
    }

    public static <T> int pick(List<T> items, int k, int currentIndex) {
        if (items.size() == 1) return 0;
        for (int i=0; i<k; i++) {
            currentIndex++;
            if (currentIndex == items.size())
                currentIndex = 0;
        }
        return currentIndex;
    }

    public static void main(String[] args) {
        List<Object> list = new ArrayList<Object>();
        int[] test = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Object[] test1 = new Object[]{};
        for (Object a : test1)
            list.add(a);
        System.out.println(josephusPermutation(list, 4));
    }
}
