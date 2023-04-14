package aoa.guessers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GuesserHelperFunctions {

    /**
     * Returns a map from a given letter to its frequency across all words.
     * This task is similar to something you did in hw0b!
     */
    public static Map<Character, Integer> getFrequencyMap(List<String> words) {
        Map<Character, Integer> chFreq = new TreeMap<>();
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                chFreq.put(ch, chFreq.getOrDefault(ch, 0) + 1);
            }
        }
        return chFreq;
    }

    public static char mostAbundantChar(Map<Character, Integer> freqMap, List<Character> guesses) {
        char max = freqMap.entrySet().stream()
                .filter(ch -> !guesses.contains(ch.getKey()))
                .max((e1, e2) -> Integer.valueOf(e1.getValue()).compareTo(e2.getValue())).get().getKey();
        return max;
    }

    public static List<String> getMatchingWords(List<String> words, String pattern) {
        List<Integer> takenIndices = new ArrayList<>();
        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            if (ch != '-') {
                takenIndices.add(i);
            }
        }
        List<String> matchingWords = new ArrayList<>();
        one:
        for (String word : words) {
            if (pattern.length() != word.length()) {
                continue;
            }
            for (int index : takenIndices) {
                if (pattern.charAt(index) != word.charAt(index)) {
                    continue one;
                }
            }
            matchingWords.add(word);
        }
        return matchingWords;
    }

    public static Map<Character, Integer> stringToMap(String pattern) {
        Map<Character, Integer> patternMap = new TreeMap<>();
        for (char ch : pattern.toCharArray()) {
            if (ch == '-') {
                continue;
            }
            patternMap.put(ch, patternMap.getOrDefault(ch, 0) + 1);
        }
        return patternMap;
    }
}
