package onsite.march2024;

import java.util.Stack;

public class UnixPath {

    public static String getCanonicalPath(String path) {
        if (path == null) {
            return "/";
        }

        // Split the path into components
        String[] components = path.split("/");

        // Use a stack to handle directory traversal
        Stack<String> stack = new Stack<>();

        // Iterate over path components
        for (String component : components) {
            if (component.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!component.isEmpty() && !component.equals(".")) {
                stack.push(component);
            }
        }

        // Build the canonical path
        StringBuilder result = new StringBuilder();
        if (!stack.isEmpty()) {
            result.append("/");
        }
        while (!stack.isEmpty()) {
            result.insert(0, "/" + stack.pop());
        }

        // Get the canonical path from the File object (handles symbolic links)
        return result.toString();
    }

}
