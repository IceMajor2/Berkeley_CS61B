package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PatternAwareLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PatternAwareLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN. */
    public char getGuess(String pattern, List<Character> guesses) {
        var matchingWords = this.getMatchingWords(pattern);
        var freqMatchMap = this.getFrequencyMap(matchingWords);
        char max = freqMatchMap.entrySet().stream().filter(ch -> !guesses.contains(ch.getKey()))
                .max((e1, e2) -> Integer.valueOf(e1.getValue()).compareTo(e2.getValue())).get().getKey();
        return max;
    }

    public Map<Character, Integer> getFrequencyMap(List<String> words) {
        Map<Character, Integer> chFreq = new TreeMap<>();
        for(String word : words) {
            for(char ch : word.toCharArray()) {
                chFreq.put(ch, chFreq.getOrDefault(ch, 0) + 1);
            }
        }
        return chFreq;
    }

    public List<String> getMatchingWords(String pattern) {
        return GuesserHelperFunctions.getMatchingWords(words, pattern);
    }

    public static void main(String[] args) {
        PatternAwareLetterFreqGuesser palfg = new PatternAwareLetterFreqGuesser("data/example.txt");
        System.out.println(palfg.getGuess("-e--", List.of('e')));
    }
}