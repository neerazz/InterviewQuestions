package phone;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created on:  Dec 02, 2021
 * Ref:
 */

public class MinimumTimeSpent {

    public static void main(String[] args) {
        System.out.println(minimumTimeSpent(
                Arrays.asList(1, 4), Arrays.asList(3, 2),
                Arrays.asList(5, 2), Arrays.asList(2, 2)
        ) + " = 6");
        System.out.println(minimumTimeSpent(
                Arrays.asList(1, 2, 3), Arrays.asList(1, 1, 1),
                Arrays.asList(1, 2, 3), Arrays.asList(10, 5, 1)
        ) + " = 4");
        System.out.println(minimumTimeSpent(
                Arrays.asList(1, 2, 3), Arrays.asList(3, 1, 1),
                Arrays.asList(1, 2, 3), Arrays.asList(1, 5, 1)
        ) + " = 5");
        System.out.println(minimumTimeSpent(
                Arrays.asList(1, 1), Arrays.asList(1, 1),
                Arrays.asList(1, 1, 1), Arrays.asList(1, 1, 1)
        ) + " = 3");
    }

    public static int minimumTimeSpent(List<Integer> comedyReleaseTime, List<Integer> comedyDuration, List<Integer> dramaReleaseTime, List<Integer> dramaDuration) {
        Comparator<Movie> order = (m1, m2) -> Integer.compare(m1.end, m2.end);
        PriorityQueue<Movie> comedyQueue = new PriorityQueue<>(order);
        PriorityQueue<Movie> dramaQueue = new PriorityQueue<>(order);
        buildQueue(comedyQueue, comedyReleaseTime, comedyDuration);
        buildQueue(dramaQueue, dramaReleaseTime, dramaDuration);
        Movie minComedy = comedyQueue.peek(), minDrama = dramaQueue.peek();
        if (hasOverLap(minComedy, minDrama)) {
            if(minComedy.end < minDrama.end){
//                Comedy move is ending first
                return minComedy.end + minDrama.duration;
            }else{
                return minDrama.end + minComedy.duration;
            }
        }
        return Math.max(minDrama.end, minComedy.end);
    }

    private static boolean hasOverLap(Movie m1, Movie m2) {
        return Math.max(m1.start, m2.start) < Math.min(m1.end, m2.end);
    }

    private static void buildQueue(PriorityQueue<Movie> queue, List<Integer> times, List<Integer> durations) {
        int len = times.size();
        for (int i = 0; i < len; i++) {
            queue.add(new Movie(times.get(i), durations.get(i)));
        }
    }

    static class Movie {
        int start, duration, end;

        public Movie(int start, int duration) {
            this.start = start;
            this.duration = duration;
            end = start + duration;
        }

    }

}
