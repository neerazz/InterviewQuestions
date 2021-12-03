package onlineTest;


/**
 * Created on:  Nov 15, 2021
 * Ref: https://leetcode.com/problems/gas-station/
 */

public class GasStation {

    public static void main(String[] args) {
        System.out.println(ArrayChallenge(new String[]{"4", "3:1", "2:2", "1:2", "0:1"}) + " = 1");
        System.out.println(ArrayChallenge(new String[]{"4", "1:1", "2:2", "1:2", "0:1"}) + " = impossible");
        System.out.println(ArrayChallenge(new String[]{"4", "0:1", "2:2", "1:2", "3:1"}) + " = 4");
    }

    public static String ArrayChallenge(String[] strArray) {
        int len = getInt(strArray[0]);
        int totalFuel = 0, startingPoint = 1, curFuel = 0;
        for (int i = 1; i <= len; i++) {
            String[] curVal = strArray[i].split(":");
            int fuelFilled = getInt(curVal[0]), fuelConsumed = getInt(curVal[1]);
            totalFuel += fuelFilled - fuelConsumed;
            curFuel += fuelFilled - fuelConsumed;
//            If current fuel goes empty, then it's not possible to start at the previous starting point and reach current point:
//              Reset the start pointer to the next index.
            if (curFuel < 0) {
                startingPoint = i+1;
                curFuel = 0;
            }
        }
//        If fuel left at the end, then circular route exists.
        return totalFuel >= 0 ? "" + startingPoint : "impossible";
    }

    static int getInt(String str) {
        return Integer.parseInt(str);
    }

}
