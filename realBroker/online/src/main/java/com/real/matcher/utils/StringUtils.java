package com.real.matcher.utils;

import java.util.List;

public class StringUtils {


    public static boolean hasValue(String str) {
        return str != null && !str.trim().isEmpty();
    }

    public static boolean hasValue(List<String> strings) {
        return strings != null && !strings.isEmpty();
    }

    public static String normalize(String input) {
        // Normalize input for matching. Implement normalization rules as needed.
        return input.trim().toLowerCase().replaceAll("[^a-z0-9\\s]", "");
    }

}
