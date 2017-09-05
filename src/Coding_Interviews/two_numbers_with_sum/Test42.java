package Coding_Interviews.two_numbers_with_sum;

import java.util.ArrayList;

/**
 * Created by dixonshen on 2017/9/5.
 */
public class Test42 {

    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        if (array == null || array.length < 2) return new ArrayList<>();
        ArrayList<Integer> res = new ArrayList<>();
        int i = 0, j = array.length - 1;
        boolean isFound = false;
        int temp = 0;
        while (!isFound && (i != j)) {
            temp = array[i] + array[j];
            if (temp == sum) {
                isFound = true;
                break;
            } else if (temp > sum) {
                j--;
            } else
                i++;
        }
        if (isFound) {
                res.add(array[i]);
                res.add(array[j]);
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
