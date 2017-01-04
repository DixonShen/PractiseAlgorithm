package CodeWars;

import java.util.Arrays;
import java.util.Comparator;

/**
 * The weight of a number will be from now on the sum of its digits.
 * Created by ada on 2017/1/4.
 */
public class Weight {

    public static String orderWeight(String strng) {
        System.out.println(strng);
        if (strng == "") return "";
        String res = "";
        String[] weights = strng.split(" ");
        int[] digitsSum = new int[weights.length];
        for (int i=0; i<weights.length; i++) {
            digitsSum[i] = getWeight(weights[i]);
            for (int j=0; j<i; j++) {
                if (digitsSum[j]>=digitsSum[i]) {
                    if (digitsSum[j]==digitsSum[i] && (weights[j].compareTo(weights[i]) < 0)) {
                        continue;
                    }
                    String temp = weights[j];
                    weights[j] = weights[i];
                    for (int k=i; k>j+1; k--) {
                        weights[k] = weights[k-1];
                    }
                    weights[j+1] = temp;
                    break;
                }
            }
//            System.out.print("--- current number(" + i +"): " + weights[i]);
            for (int p=0; p<=i; p++){
                digitsSum[p] = getWeight(weights[p]);
            }
        }
        res += weights[0];
        for (int i=1; i<weights.length; i++) {
            res += " " + weights[i];
        }
        System.out.println(res);
        return res;
    }

    public static int getWeight(String str) {
        char[] chs = str.toCharArray();
        int weight = 0;
        for (char ch : chs) {
            weight += Integer.parseInt(String.valueOf(ch));
        }
        return weight;
    }

    //better solution
    public static String orderWeight1(String strng) {
        String[] weights = strng.split(" ");
        Arrays.sort(weights, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                int aWeight = a.chars().map(c -> Character.getNumericValue(c)).sum();
                int bWeight = b.chars().map(c -> Character.getNumericValue(c)).sum();
                return aWeight-bWeight != 0 ? aWeight-bWeight : a.compareTo(b);
            }
        });
        return String.join(" ", weights);
    }

    public static void main(String[] args) {
        String str = "2000 10003 1234000 44444444 9999 11 11 22 123";
        System.out.println(orderWeight1(str));
        if ("2000".compareTo("11")>0)
            System.out.println(true);
        System.out.println(getWeight("22"));
    }
}
