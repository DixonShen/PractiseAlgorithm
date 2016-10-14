package CodeWars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by dixonshen on 2016/10/14.
 */
public class SomeEgyptianFractions {

    //better solution
    public static String decompose(String nrStr, String drStr){
        Long nr = Long.parseLong(nrStr);
        Long dr = Long.parseLong(drStr);
        ArrayList<String> result = new ArrayList<String>();
        if (nr > dr){
            Long i = (long) nr/dr;
            result.add(String.valueOf(i));
            nr -= i * dr;
        }
        while ( nr != 0) {
            Long i = (long) Math.ceil((double)dr/nr);
            result.add("1/" + String.valueOf(i));
            nr = nr * i - dr;
            dr = dr * i;
        }
        return result.toString();
    }

    public static String decompose1(String nrStr, String drStr){
        Long numer = Long.parseLong(nrStr);
        Long denom = Long.parseLong(drStr);
        if (numer == 0) return Arrays.toString(new String[] {});
        if (numer%denom == 0) return "[" + numer/denom + "]";
        ArrayList<String> result = new ArrayList<String>();
        Long[] temp = new Long[2];
        for (Long i=1L; i<Long.MAX_VALUE; i++){
            if (((double)numer/denom) == (double) 1/i){
                result.add("1/" + i);
                break;
            }
            if (((double)numer/denom) > (double)1/i){
                if (i == 1) result.add("1");
                else result.add("1/" + i);
                temp = substract(numer, denom, i);
                numer = temp[0];
                denom = temp[1];
                System.out.println(numer);
                System.out.println(denom);
                if (numer == 1.0){
                    result.add("1/" + denom);
                    break;
                }
            }
        }
        return result.toString();
    }

    public static Long[] substract(Long numer, Long denom, Long i){
        Long n = numer * i - denom;
        Long d = denom * i;
        return new Long[] {n, d};
    }

    public static void main(String[] args) {
//        System.out.println(Double.MAX_VALUE);
//        System.out.println(Integer.MAX_VALUE);
        long t = System.currentTimeMillis();
        System.out.println(decompose1("50", "4187"));
        System.out.println(System.currentTimeMillis()-t + "ms");
//        System.out.println((double)(50/4187-1/84-1/27055-1/132217907));
//        System.out.println(Integer.MAX_VALUE);
    }
}
