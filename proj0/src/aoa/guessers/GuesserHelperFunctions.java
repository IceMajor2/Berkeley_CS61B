package aoa.guessers;

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
        for(String word : words) {
            for(char ch : word.toCharArray()) {
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

}
