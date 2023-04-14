package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PAGALetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PAGALetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN and the GUESSES that have been made. */
    public char getGuess(String pattern, List<Character> guesses) {
        var matchingWords = this.excludeUnmatchingWords(words, pattern, guesses);
        var freqMap = GuesserHelperFunctions.getFrequencyMap(matchingWords);
        char max = GuesserHelperFunctions.mostAbundantChar(freqMap, guesses);
        return max;
    }

    public List<String> excludeUnmatchingWords(List<String> words, String pattern, List<Character> guesses) {
        List<String> excluded = new ArrayList<>();
        var matchingWords = GuesserHelperFunctions.getMatchingWords(words, pattern);
        var patternMap = GuesserHelperFunctions.stringToMap(pattern);
        one:
        for (String word : matchingWords) {
            var wordMap = GuesserHelperFunctions.stringToMap(word);
            for (char guess : guesses) {
                if ((!patternMap.containsKey(guess) || wordMap.get(guess) != patternMap.get(guess))
                        && wordMap.containsKey(guess)) {
                    continue one;
                }
            }
            excluded.add(word);
        }
        return excluded;
    }

    public static void main(String[] args) {
        PAGALetterFreqGuesser pagalfg = new PAGALetterFreqGuesser("data/example.txt");
        System.out.println(pagalfg.getGuess("----", List.of('e')));
    }
}
