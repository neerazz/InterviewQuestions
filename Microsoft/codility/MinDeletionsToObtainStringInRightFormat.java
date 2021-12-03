package codility;/*
 
 B A A A B A B - 2
 
 B B A B A A  - 3 

 A A B B B B - 0


Time = O(n) 
Space = O(1)


*/


public class MinDeletionsToObtainStringInRightFormat {

    private static int minDelToObtainStrInFormat(String S) {
        int minDeletions = 0;
        if (S == null || S.length() == 0) return minDeletions;
        int counter = 0;
        for (char c : S.toCharArray()) {
            if (c == 'B') counter++;
            else if (c == 'A') {
                minDeletions += counter;
                counter = 0;
            }
        }
        return minDeletions;
    }

    public static void main(String[] args) {
        System.out.println(minDelToObtainStrInFormat("BAAABAB"));
    }
}
