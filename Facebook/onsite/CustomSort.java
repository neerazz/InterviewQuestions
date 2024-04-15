package onsite;

import java.util.HashMap;
import java.util.Map;

public class CustomSort {
    public static void main(String[] args) {
        String input = "abcabd";
        String order = "cab";

        System.out.println("Gemini " + customSort(input, order));

    }

    public static String customSort(String input, String order) {
        StringBuilder result = new StringBuilder();
        Map<Character, Integer> frequencyMap = new HashMap<>();

        // Count the frequency of each character in the input string
        for (char c : input.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Append characters in the 'order' to the result
        for (char c : order.toCharArray()) {
            if (frequencyMap.containsKey(c)) {
                for (int i = 0; i < frequencyMap.get(c); i++) {
                    result.append(c);
                }
                // Remove the character from the map once processed
                frequencyMap.remove(c);
            }
        }

        // Append remaining characters not in 'order'
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            result.append(String.valueOf(entry.getKey()).repeat(Math.max(0, entry.getValue())));
        }

        return result.toString();
    }


}
