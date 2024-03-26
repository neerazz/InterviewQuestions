package phone;

import java.util.PriorityQueue;

/**
 * Problem statement:
 * The goal is to find the k closest planets to the origin of the system.
 * The input is a list of planets with their coordinates, and the number k, and the output is the indices of the closest k planets.
 */
public class KClosestPlanets {

    public int[] findKClosestPlanets(int[][] planets, int k) {
        PriorityQueue<Coordinates> maxHeap = new PriorityQueue<>(k, (a, b) -> Double.compare(b.dist, a.dist));
        for (int i = 0; i < planets.length; i++) {
            double dist = Math.sqrt(planets[i][0] * planets[i][0] + planets[i][1] * planets[i][1]);
            Coordinates coord = new Coordinates(planets[i][0], planets[i][1], i, dist);
            maxHeap.add(coord);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        int[] result = new int[k];
        for (int i = 0; maxHeap.size() > 0; i++) {
            result[i] = maxHeap.poll().idx;
        }

        return result;
    }

    static class Coordinates {
        int x, y, idx;
        double dist;

        public Coordinates(int x, int y, int idx, double dist) {
            this.x = x;
            this.y = y;
            this.idx = idx;
            this.dist = dist;
        }
    }
}
