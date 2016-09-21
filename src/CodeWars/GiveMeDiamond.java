package CodeWars;

/**
 * You need to a string that when printed,
 * displays a diamond shape on the screen using asterisk ("*") characters.
 * Please see provided test cases for exact output format.
 * The shape that will be returned from print method resembles a diamond,
 * where the number provided as input represents the number
 * of *’s printed on the middle line.
 * The line above and below will be centered
 * and will have 2 less *’s than the middle line.
 * This reduction by 2 *’s for each line continues until a line
 * with a single * is printed at the top and bottom of the figure.
 * Return null if input is even number or negative (as it is not possible to print diamond with even number or negative number).
 * Created by DixonShen on 2016/9/14.
 */
public class GiveMeDiamond {

    public static String print(int n){
        if ( n%2==0 || n <=0 ) return null;
        StringBuffer s = new StringBuffer();
        int middleLine = n/2 + 1;
        for (int i=1; i<=n; i++){
            for (int j=1; j<=Math.abs(middleLine-i); j++){
                s.append(" ");
            }
            if (i <= middleLine) {
                for (int j = 1; j <= 2 * i - 1; j++)
                    s.append("*");
            }else {
                for (int j=1; j<=2*(2*middleLine-i)-1; j++)
                    s.append("*");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        System.out.println(print(11));
    }
}
