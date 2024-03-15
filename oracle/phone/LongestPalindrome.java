package phone;

import java.util.*;
import java.io.*;

/**
 * Created on:  Mar 15, 2024
 * Ref:
 */

public class LongestPalindrome {

    public static void main(String[] args) {

    }

    // Helper function to expand around the center
    private static int[] expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return new int[]{left + 1, right - left - 1};
    }

    // Function to find the longest palindrome
    public static void findPalindrome(String input) {
        int maxLen = 0, startIdx = -1;
        if (input != null && input.length() > 0) {

            for (int i = 0; i < input.length(); i++) {
                // Odd length palindrome
                int[] odd = expandAroundCenter(input, i, i);

                // Even length palindrome
                int[] even = expandAroundCenter(input, i, i);

                if (odd[1] >= even[1] && odd[1] > maxLen) {
                    maxLen = odd[1];
                    startIdx = odd[0];
                }

                if (odd[1] < even[1] && even[1] > maxLen) {
                    maxLen = even[1];
                    startIdx = even[0];
                }
            }

        }
        System.out.println("Length: " + maxLen + " Index: " + startIdx);
    }

}
