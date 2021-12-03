package phone;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created on:  Dec 02, 2021
 * Ref:
 */

public class FindMaximumSustainableClusterSize {

    public static void main(String[] args) {
        System.out.println(findMaximumSustainableClusterSize(Arrays.asList(3,6,1,3,4), Arrays.asList(2,1,3,4,5), 25));
    }

    static long closestPower = 0;
    static int bestK = 0;

    private static int findMaximumSustainableClusterSize(List<Integer> processingPower, List<Integer> bootingPower, long powerMax) {
        // Time: O(N * N *log N)
        for (int k = 1; k <= processingPower.size(); k++) {
            calculatePower(processingPower, bootingPower, k, powerMax);
        }
        return bestK;
        // Optimal
//        int start = 1, end = processingPower.size();
    }

    private static void calculatePower(List<Integer> processingPower, List<Integer> bootingPower, int k, long powerMax) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((v1, v2) -> Integer.compare(v2, v1));
        long sum = 0;
        for (int i = 0; i < k - 1; i++) {
            pq.add(bootingPower.get(i));
            sum += processingPower.get(i);
        }
        int p1 = 0;
        for (int i = k - 1; i < processingPower.size(); i++) {
            pq.add(bootingPower.get(i));
            sum += processingPower.get(i);
            long curPower = pq.peek() + sum * k;
            if (curPower <= powerMax && curPower > closestPower) {
                closestPower = curPower;
                bestK = k;
            }
            pq.remove(bootingPower.get(p1));
            sum -= processingPower.get(p1);
            p1++;
        }
    }

}
