import java.util.*;

/**
 * Created on:  Jul 12, 2021
 * Ref:
 * Design a hit counter which counts the number of hits received in
 * the last 60 seconds. Your hit counter should define a function
 * that records a hit at a particular timestamp, and a fucntion that
 * returns the number of hits in the 60 seconds prior to a particular
 * timestamp.
 * <p>
 * You may assume the following:
 * * Timestamps are integers representing seconds, where 0 represents
 * the time at which the program started executing.
 * * Timestamps will be monotonically increasing.
 * * The hit counter will only be used from a single thread.
 */

public class HitCounterImpl {

    public static void main(String[] args) {

    }
}

class HitCounter {
    Map<Integer, Node> map = new HashMap<>();
    PriorityQueue<Node> list = new PriorityQueue<>();

    void hit(int time) {
        clean(time); // W
        if (map.containsKey(time)) { // R
            Node node = map.get(time);  // R
            node.count++; // W
        } else {
            Node node = new Node(time, 1);
            list.add(node);       // W
            map.put(time, node); // W
        }
    }

    void clean(int time) {

    }

    int count(int time) {
        return count(time - 60, time);
    }

    int count(int start, int end) {
        int count = 0;
        for (Node node : list) {
            if (start <= node.time && node.time <= end) {
                count += node.count;
            }
        }
        return count;
    }

    static class Node {
        int time, count;

        Node(int time, int count) {
            this.time = time;
            this.count = count;
        }
    }
}