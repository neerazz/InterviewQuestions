package karat_assesment_10072022;/*
Suppose we have an unsorted log file of accesses to web resources. Each log entry consists of an access time, the ID of the user making the access, and the resource ID. 

The access time is represented as seconds since 00:00:00, and all times are assumed to be in the same day.

Example:
logs1 = [
    ["58523", "user_1", "resource_1"],
    ["62314", "user_2", "resource_2"],
    ["54001", "user_1", "resource_3"],
    ["200", "user_6", "resource_5"],    
    ["215", "user_6", "resource_4"],
    ["54060", "user_2", "resource_3"],
    ["53760", "user_3", "resource_3"],
    ["58522", "user_22", "resource_1"],
    ["53651", "user_5", "resource_3"],
    ["2", "user_6", "resource_1"],
    ["100", "user_6", "resource_6"],
    ["400", "user_7", "resource_2"],
    ["100", "user_8", "resource_6"],
    ["54359", "user_1", "resource_3"],
]

Example 2:
logs2 = [
    ["300", "user_1", "resource_3"],
    ["599", "user_1", "resource_3"],
    ["900", "user_1", "resource_3"],
    ["1199", "user_1", "resource_3"],
    ["1200", "user_1", "resource_3"],
    ["1201", "user_1", "resource_3"],
    ["1202", "user_1", "resource_3"]
]

Example 3:
logs3 = [
    ["300", "user_10", "resource_5"]
]

Question 1:
Write a function that takes the logs and returns all teh users with the start and end times.

Expected Output:
user_sessions(logs1) # =>
{
    user_3=[53760, 53760],
    user_2=[54060, 62314],
    user_1=[54001, 58523],
    user_7=[400, 400],
    user_6=[2, 215],
    user_5=[53651, 53651],
    user_22=[58522, 58522],
    user_8=[100, 100]
}

user_sessions(logs2) # =>
{user_1=[300, 1202]}

user_sessions(logs3) # =>
{user_10=[300, 300]}


Question 2:
Write a function that takes the logs and returns the resource with the highest number of accesses in any 5-minute window, together with how many accesses it saw.

Expected Output:
most_requested_resource(logs1) # => ('resource_3', 3) [resource_3 is accessed at 53760, 54001, and 54060]
most_requested_resource(logs2) # => ('resource_3', 4) [resource_3 is accessed at 1199, 1200, 1201, and 1202]
most_requested_resource(logs3) # => ('resource_5', 1) [resource_5 is accessed at 300]

Complexity analysis variables:

n: number of logs in the input
*/

import java.util.*;

public class LogAnalysis {

    public static void main(String[] argv) {
        String[][] logs1 = new String[][]{
                {"58523", "user_1", "resource_1"},
                {"62314", "user_2", "resource_2"},
                {"54001", "user_1", "resource_3"},
                {"200", "user_6", "resource_5"},
                {"215", "user_6", "resource_4"},
                {"54060", "user_2", "resource_3"},
                {"53760", "user_3", "resource_3"},
                {"58522", "user_22", "resource_1"},
                {"53651", "user_5", "resource_3"},
                {"2", "user_6", "resource_1"},
                {"100", "user_6", "resource_6"},
                {"400", "user_7", "resource_2"},
                {"100", "user_8", "resource_6"},
                {"54359", "user_1", "resource_3"},
        };

        String[][] logs2 = new String[][]{
                {"300", "user_1", "resource_3"},
                {"599", "user_1", "resource_3"},
                {"900", "user_1", "resource_3"},
                {"1199", "user_1", "resource_3"},
                {"1200", "user_1", "resource_3"},
                {"1201", "user_1", "resource_3"},
                {"1202", "user_1", "resource_3"}
        };

        String[][] logs3 = new String[][]{
                {"300", "user_10", "resource_5"}
        };

        System.out.println(user_sessions(logs1));
        System.out.println(user_sessions(logs2));
        System.out.println(user_sessions(logs3));

        var result = most_requested_resource(logs1);
        System.out.println(String.format("%s, %d", result.res, result.count));

        var result2 = most_requested_resource(logs2);
        System.out.println(String.format("%s, %d", result2.res, result2.count));

        var result3 = most_requested_resource(logs3);
        System.out.println(String.format("%s, %d", result3.res, result3.count));
    }

    public static Result most_requested_resource(String[][] logs) {
        Map<String, Grouping> map = new HashMap<>();
        for (String[] log : logs) {
            String res = log[2], time = log[0];
            map.putIfAbsent(res, new Grouping(res));
            Grouping group = map.get(res);
            group.addTimeForRes(Integer.parseInt(time));
        }
        String res = "";
        int maxCount = 0;
        for (Map.Entry<String, Grouping> entry : map.entrySet()) {
            String curRes = entry.getKey();
            List<Integer> val = new ArrayList<>(entry.getValue().pq);
            int occ = getOccurrence(val);

            if (occ > maxCount) {
                maxCount = occ;
                res = curRes;
            }
        }
        return new Result(res, maxCount);
    }

    private static int getOccurrence(List<Integer> times) {
        int p1 = 0, len = times.size();
        int max = 0, windowCount = 0;
        Map<Integer, Integer> window = new HashMap<>();
        for (int p2 = 0; p2 < len; p2++) {
            int cur = times.get(p2);
            while (cur - times.get(p1) > 5 * 60) {
                int preTime = times.get(p1++);
                int preCount = window.remove(preTime);
                windowCount -= preCount;
            }
            window.put(cur, window.getOrDefault(cur, 0) + 1);
            windowCount++;
            max = Math.max(max, windowCount);
        }
        return max;
    }

    static class Result {
        int count;
        String res;

        Result(String res, int count) {
            this.res = res;
            this.count = count;
        }
    }

    public static Map<String, List<Integer>> user_sessions(String[][] logs) {
        Map<String, Grouping> map = new HashMap<>();
        for (String[] log : logs) {
            String user = log[1], time = log[0];
            map.putIfAbsent(user, new Grouping(user));
            Grouping group = map.get(user);
            group.addTime(Integer.parseInt(time));
        }
        Map<String, List<Integer>> result = new HashMap<>();
        for (Map.Entry<String, Grouping> entry : map.entrySet()) {
            var value = entry.getValue().getMinMax();
            result.put(entry.getKey(), value);
        }
        return result;
    }


    static class Grouping {
        String user;
        int min, max;
        PriorityQueue<Integer> pq;

        Grouping(String user) {
            this.user = user;
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
            pq = new PriorityQueue<>();
        }

        /*
        min = Inf 1
        max = Inf 3

          at (1)
          at(3)
        */
        void addTime(int time) {
            min = Math.min(min, time);
            max = Math.max(max, time);
        }

        void addTimeForRes(int time) {
            pq.add(time);
        }

        List<Integer> getMinMax() {
            return List.of(min, max);
        }
    }
}
