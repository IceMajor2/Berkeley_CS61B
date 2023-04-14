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

        return '?';
    }

    public List<String> getMatchingWords(String pattern) {
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

    public static void main(String[] args) {
        PatternAwareLetterFreqGuesser palfg = new PatternAwareLetterFreqGuesser("data/example.txt");
        System.out.println(palfg.getGuess("-e--", List.of('e')));
    }
}