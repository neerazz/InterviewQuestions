package screening;

import java.util.Arrays;

public class SentenceRearrangement {

    public static void main(String[] args) {

    }

    public static String arrangeSentence(String sentence) {
        // Split the sentence into an array of words
        String[] words = sentence.substring(0, sentence.length() - 1).split(" ");

        // Sort the words in ascending order based on their lengths
        Arrays.sort(words, (a, b) -> a.length() - b.length());

        // Reassemble the sentence by preserving the original order of words with the same length
        StringBuilder rearrangedSentence = new StringBuilder();
        int len = words.length;

//        Manually Append the first character.
        rearrangedSentence.append(Character.toUpperCase(words[0].charAt(0)));
        rearrangedSentence.append(words[0].substring(1).toUpperCase());

        for (int i = 1; i < len; i++) {
            rearrangedSentence.append(" ").append(words[i].toUpperCase());
        }

        // Remove the extra space at the end of the sentence and append a period
        return rearrangedSentence + ".";
    }
}
