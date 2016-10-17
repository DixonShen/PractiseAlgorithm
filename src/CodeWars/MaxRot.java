package CodeWars;

/**
 * Created by dixonshen on 2016/10/17.
 */
public class MaxRot {

    public static long maxRot(long n) {
        String str = Long.toString(n);
        long max_n = n;
        char temp;
        char[] temps = str.toCharArray();

        for (int i = 0; i < temps.length; i++) {
            temp = temps[i];
            for (int j = i; j < temps.length - 1; j++) {
                temps[j] = temps[j + 1];
            }
            temps[temps.length - 1] = temp;
            str = new String(temps);
            // str = temps.toString();
            n = Long.parseLong(str);
            if (n > max_n)
                max_n = n;
        }
        return max_n;
    }

    public static void main(String[] args) {
        System.out.println(maxRot(38458215));
    }
}
