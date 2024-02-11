package onsite.jan2024;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created on:  Jan 27, 2024
 * Ref:
 */

public class FruitsGame {

    public static void main(String[] args) {
        System.out.println(getRemainingFruitsCount(new int[]{}));
    }

    private static int getRemainingFruitsCount(int[] fruits) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int fruit : fruits) {
            map.put(fruit, map.getOrDefault(fruit, 0) + 1);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((f1, f2) -> f2[1] - f1[1]);
        map.forEach((key, value) -> pq.add(new int[]{key, value}));
        while (pq.size() >= 2) {
            int[] first = pq.poll();
            int[] second = pq.poll();
            int remain = first[1] - second[1];
            if (remain > 0) {
                first[1] = remain;
                pq.add(first);
            } else if (remain < 0) {
                second[1] = -1 * remain;
                pq.add(second);
            }
        }
        int remaining = 0;
        while (!pq.isEmpty()) {
            remaining += pq.poll()[1];
        }
        return remaining;
    }

}
