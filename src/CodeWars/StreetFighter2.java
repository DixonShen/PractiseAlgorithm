package CodeWars;

import java.util.Arrays;

/**
 * The initial position might be any non-empty slot in the grid (given as input).
   The characters grid (also given as input) might have any rectangular layout, not only 3 rows.
   The grid might contain empty spaces, both on the borders or right in the middle.
     INPUT
        Fighters grid;
        Initial position;
        List of moves.
     The third input parameter is still the list of moves
     (all valid ones: left, right, up or down).

     OUTPUT
       The output is the same as before: the list of characters that
       have been hovered by the selection cursor after each move, successful or not.
 * https://www.codewars.com/kata/street-fighter-2-character-selection-part-2/train/java
 * Created by ada on 2017/1/3.
 */
public class StreetFighter2 {

    public static String[][] fighters = new String[][]{
            new String[] {       "",    "Ryu",  "E.Honda",  "Blanka",   "Guile", ""      },
            new String[] { "Balrog",    "Ken",  "Chun Li", "Zangief", "Dhalsim", "Sagat" },
            new String[] {   "Vega", "T.Hawk", "Fei Long",  "Deejay",   "Cammy", "M.Bison"      },
    };

    public static String[][] fighters3 = new String[][]{
            new String[] {      "",    "Ryu",  "E.Honda",  "Cammy",  "Blanka",   "Guile",        "", "Chun Li" },
            new String[] {"Balrog",    "Ken",  "Chun Li",       "", "M.Bison", "Zangief", "Dhalsim", "Sagat"},
            new String[] {  "Vega",       "", "Fei Long", "Balrog",  "Deejay",   "Cammy",        "", "T.Hawk" },
    };

    public static String[] superStreetFighterize(String[][] fighters, int[] position, String[] moves) {

        String[] res = new String[moves.length];
        int row = position[0], col = position[1];
        int count = 0;
        for (String move : moves) {
            if (move == "up") {
                if (row == 0 || fighters[row-1][col] == "") {
                    res[count] = fighters[row][col];
                }
                else {
                    res[count] = fighters[--row][col];
                }
                count++;
                continue;
            }
            if (move == "down") {
                if (row == fighters.length-1 || fighters[row+1][col] == "") {
                    res[count] = fighters[row][col];
                }
                else {
                    res[count] = fighters[++row][col];
                }
                count++;
                continue;
            }
            if (move == "left") {
                col--;
                if (col == -1) col = fighters[row].length-1;
                while (fighters[row][col] == ""){
                    col--;
                    if (col == -1) {
                        col = fighters[row].length-1;
                    }
                }
                res[count] = fighters[row][col];
                count++;
                continue;
            }
            if (move == "right") {
                col++;
                if (col == fighters[row].length) col = 0;
                while (fighters[row][col] == ""){
                    col++;
                    if (col == fighters[row].length) {
                        col = 0;
                    }
                }
                res[count] = fighters[row][col];
                count++;
                continue;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String[] moves = new String[] {"right", "right", "right", "right", "right", "right", "down", "left", "left", "left", "left", "left", "left", "left", "left", "left", "left", "left", "left", "down", "right", "right", "right", "right", "right", "right", "right", "right", "right", "right", "right", "right"};
        int[] position = new int[] {0, 2};
        String[] solution = new String[] {"Cammy", "Blanka", "Guile", "Chun Li", "Ryu", "E.Honda", "Chun Li", "Ken", "Balrog", "Sagat", "Dhalsim", "Zangief", "M.Bison", "Chun Li", "Ken", "Balrog", "Sagat", "Dhalsim", "Zangief", "Cammy", "T.Hawk", "Vega", "Fei Long", "Balrog", "Deejay", "Cammy", "T.Hawk", "Vega", "Fei Long", "Balrog", "Deejay", "Cammy"};
        System.out.println(Arrays.toString(superStreetFighterize(fighters3, position, moves)));
        System.out.println(Arrays.toString(solution));
    }
}
