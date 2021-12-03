package codility;

public class CropWords {

    public static String cropWords(String s, int k) {
        if (k >= s.length()) return s;
        StringBuffer sb = new StringBuffer(s.substring(0, k));

        if (s.charAt(k) != ' ') {
            while (s.charAt(--k) != ' ')
                sb.deleteCharAt(k);
        }

        return sb.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println(cropWords("Codility We test coders", 14));
        System.out.println(cropWords(" co de my", 5));
        System.out.println(cropWords(" co de my", 7));
    }
}
