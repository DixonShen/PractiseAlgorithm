package CodeWars;

/**
 * Created by DixonShen on 2016/8/2.
 */
public class Mumbling {

    public static String accum(String s){
        char[] ch = s.toCharArray();
        String result = "";
        for (int i = 0; i < ch.length; i++){
            String temp1,temp2 = "";
            temp1 = String.valueOf(ch[i]).toLowerCase();
            for (int j = 0; j < i; j++){
                temp2 = temp2 + temp1;
            }
            result = result + temp1.toUpperCase() + temp2;
            if (i == ch.length-1)
                break;
            result = result + "-";
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(accum("cwAt"));
    }
}
