package CodeWars;


import java.util.Arrays;

/**
 * Given the initial display lines and the rotor moves for each line,
 * determine what the board will say after it has been fully updated.
 * For your convenience the characters of each rotor are in the pre-loaded string ALPHABET
 * https://www.codewars.com/kata/57feb00f08d102352400026e/train/java
 * Created by ada on 2016/12/29.
 */
public class Dinglemouse {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ?!@#&()|<>.:=-+*/0123456789";

    public static String[] flapDisplay(final String[] lines, final int[][] rotors) {

        String[] result = new String[lines.length];
        for (int i=0; i<lines.length; i++){
            result[i] = flap(lines[i], rotors[i]);
            System.out.println(result[i]);
        }

        return result;
    }

    public static String flap(String line, int[] rotors) {
        char[] res = line.toCharArray();
        int i = 0;
        int index;
        for (char c : res){
            for (int j=i; j<res.length; j++){
                index = positioning(res[j]);
                res[j] = ALPHABET.toCharArray()[(index + rotors[i])%ALPHABET.length()];
            }
            i++;
        }
        return new String(res);
    }

    public static int positioning(char ch) {
        for (int i=0; i<ALPHABET.length(); i++){
            if (ch == ALPHABET.toCharArray()[i]) return i;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(flapDisplay(new String[]{"CAT"},new int[][]{{1,13,27}})[0]);
    }
}
