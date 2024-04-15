package onsite;

import java.util.*;

public class GroupCustomerWithEmail {

    public static void main(String[] args) {
         Map<String, List<String>> contacts = new HashMap<>();
         List<String> c1list = Arrays.asList("bob@yahoo.com", "bob@gmail.com");
         contacts.put("C1",c1list);
        List<String> C2list = Arrays.asList("mary@facebook.com");
        contacts.put("C2", C2list);
        List<String> C3list = Arrays.asList("bob@yahoo.com", "bob_1@hotmail.com");
        contacts.put("C3", C3list);
        List<String> C4list = Arrays.asList("bob_1@hotmail.com");
        contacts.put("C4",C4list);
        List<String> C5list = Arrays.asList("mary@facebook.com");
        contacts.put("C5",C5list);
        List<String> C6list = Arrays.asList("mark@gmail.com");
        contacts.put("C6",C6list);
        System.out.println(groupEmails(contacts));

    }

    /**
     * Time Complexity: O(N * M * α(N)) where α(N) is the inverse Ackermann function,
     * which is practically constant. The main time is spent in the Union-Find operations, which are very efficient with path compression and union by rank.
     * Space Complexity: O(N * M) to store the parent map and the resulting groups.
     */
    public static List<List<String>> groupEmails(Map<String, List<String>> addressBook) {
        Map<String, String> parent = new HashMap<>();
        Map<String, List<String>> groups = new HashMap<>();

        // Initialize Union-Find with each email as its own parent
        for (List<String> emails : addressBook.values()) {
            for (String email : emails) {
                parent.put(email, email);
            }
        }

        // Union-Find to connect emails within each entry
        for (String name : addressBook.keySet()) {
            List<String> emails = addressBook.get(name);
            for (int i = 1; i < emails.size(); i++) {
                union(parent, emails.get(0), emails.get(i));
            }
        }

        // Group entries by finding the parent (representative) email
        for (String name : addressBook.keySet()) {
            for (String email : addressBook.get(name)) {
                String groupEmail = find(parent, email);
                groups.computeIfAbsent(groupEmail, k -> new ArrayList<>()).add(name);
            }
        }

        return new ArrayList<>(groups.values());
    }

    private static String find(Map<String, String> parent, String email) {
        if (parent.get(email).equals(email)) return email;
        return parent.put(email, find(parent, parent.get(email))); // Path compression
    }

    private static void union(Map<String, String> parent, String email1, String email2) {
        String parent1 = find(parent, email1);
        String parent2 = find(parent, email2);
        parent.put(parent1, parent2);
    }

}
