package CodeWars;

/**
 * Created by DixonShen on 2016/7/28.
 */


public class TenMinWalk {

    public static boolean isValid(char[] walk){
        if (walk.length != 10) return false;
        int x = 0;
        int y = 0;
        for (char ch : walk){
            switch (ch){
                case 'n':
                    y++;
                    break;
                case 's':
                    y--;
                    break;
                case 'w':
                    x--;
                    break;
                case 'e':
                    x++;
                    break;
            }
        }
        if (x == 0 && y == 0)
            return true;
        return false;
    }

    public static void main(String[] args) {

    }
}
