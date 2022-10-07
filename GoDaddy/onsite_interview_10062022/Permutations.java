package onsite_interview_10062022;

import java.util.*;

/*
https://leetcode.com/explore/learn/card/recursion-ii/507/beyond-recursion/2903/
Given a string, return all possible unique permutations
Example:
Input: abc
Output: [acb, bca, abc, cba, bac, cab]

Input: aab
Output: [aba, aab, baa]

 */

public class Permutations {

    public static void main(String[] args) {
        System.out.println("*************** StackImplementation 1 ********************");
        System.out.println(permute("abc"));
        System.out.println(permute("aab"));
        System.out.println(permute_optimal("aaaaaaaaaaaaaaaaaa"));
        System.out.println("*************** StackImplementation 2 ********************");
        System.out.println(permute_optimal("abc"));
        System.out.println(permute_optimal("aab"));
        System.out.println(permute_optimal("aaaaaaaaaaaaaaaaaa"));
    }

    public static Set<String> permute(String input) {
        int len = input.length();
        boolean[] taken = new boolean[len];
        Set<String> output = new HashSet<>();
        backtrack(input, taken, output, "");
        return output;
    }

    private static void backtrack(String input, boolean[] taken, Set<String> output, String current) {
        if (current.length() == input.length()) {
            output.add(current);
        } else {
            for (int i = 0; i < input.length(); i++) {
                if (!taken[i]) {
                    taken[i] = true;
                    backtrack(input, taken, output, current + input.charAt(i));
                    taken[i] = false;
                }
            }
        }
    }

    public static List<String> permute_optimal(String input) {
        int len = input.length();
        boolean[] taken = new boolean[len];
        List<String> output = new ArrayList<>();
        backtrack_optimal(input, taken, output, "");
        return output;
    }

    private static void backtrack_optimal(String input, boolean[] taken, List<String> output, String current) {
        if (current.length() == input.length()) {
            output.add(current);
        } else {
            Set<Character> visited = new HashSet<>();
            for (int i = 0; i < input.length(); i++) {
                if (!taken[i] && !visited.contains(input.charAt(i))) {
                    taken[i] = true;
                    char curChar = input.charAt(i);
                    visited.add(curChar);
                    backtrack_optimal(input, taken, output, current + curChar);
                    taken[i] = false;
                }
            }
        }
    }
}
