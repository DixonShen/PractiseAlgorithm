package CodeWars;

/**
 * Created by DixonShen on 2016/10/19.
 */
public class Evaluator {

    public static long evaluate(String s){
        int lastNumIndex = -1;
        long[] nums = new long[s.length()];
        String[] ss = s.split(" ");
        for (String str : ss){
            if (str.contentEquals("+")) {
                nums[lastNumIndex - 1] = nums[lastNumIndex] + nums[lastNumIndex - 1];
                lastNumIndex--;
                continue;
            }
            if (str.contentEquals("-")){
                nums[lastNumIndex-1] = nums[lastNumIndex-1] - nums[lastNumIndex];
                lastNumIndex--;
                continue;
            }
            if (str.contentEquals("*")){
                nums[lastNumIndex-1] = nums[lastNumIndex] * nums[lastNumIndex-1];
                lastNumIndex--;
                continue;
            }
            if (str.contentEquals("/")){
                nums[lastNumIndex-1] = nums[lastNumIndex-1] / nums[lastNumIndex];
                lastNumIndex--;
                continue;
            }
            nums[++lastNumIndex] = Long.parseLong(str);
        }
        return nums[lastNumIndex];
    }

    public static void main(String[] args) {
        System.out.println(evaluate("2 3 9 4 / + *"));
    }
}
