package CodeWars;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * In ["NORTH", "SOUTH", "EAST", "WEST"], the direction "NORTH" + "SOUTH"
 * is going north and coming back right away.
 * What a waste of time! Better to do nothing.
 * The path becomes ["EAST", "WEST"], now "EAST" and "WEST" annihilate each other,
 * therefore, the final result is [] (nil in Clojure).
 * In ["NORTH", "EAST", "WEST", "SOUTH", "WEST", "WEST"],
 * "NORTH" and "SOUTH" are not directly opposite but
 * they become directly opposite after the reduction of "EAST" and "WEST"
 * so the whole path is reducible to ["WEST", "WEST"].
 * Created by ada on 2017/1/3.
 */
public class DirReduction {

    public static String[] dirReduc(String[] arr) {
        if (arr == null) return null;
        boolean[] flag = new boolean[arr.length];
        for (int i=0; i<arr.length; i++)
            flag[i] = true;
        for (int i=1; i<arr.length; i++){
            for (int j=i-1; j>=0; j--){
                if (flag[j]) {
                    if (isMatch(arr[i], arr[j])) {
                        flag[i] = false;
                        flag[j] = false;
                    }
                    j = -1;
                }
            }
        }
        ArrayList<String> res = new ArrayList<String>();
        for (int i=0; i<arr.length; i++){
            if (flag[i]) {
                res.add(arr[i]);
            }
        }
        return res.toArray(new String[0]);
    }

    public static boolean isMatch(String s1, String s2) {
        if ((s1 == "NORTH" && s2 == "SOUTH") || (s1 == "SOUTH" && s2 == "NORTH") ||
                (s1 == "WEST" && s2 == "EAST") || (s1 == "EAST" && s2 == "WEST") )
            return true;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(dirReduc(new String[]{"NORTH", "WEST", "SOUTH", "EAST"})));
    }
}
