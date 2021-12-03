package codility;

import java.util.*;

/*

Time - O(n)
Space - O(n)

 */

public class LargestNumberXWhichOccursXTimes {


    public static int largestNumXOccoursXTimes(int[] nums) {
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else
                map.put(num, 1);
        }

        for (Integer i : map.keySet()) {
            if (i == map.get(i) && max < i)
                max = i;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(largestNumXOccoursXTimes(new int[]{3, 8, 2, 3, 3, 2}));
    }
} 
  
 
