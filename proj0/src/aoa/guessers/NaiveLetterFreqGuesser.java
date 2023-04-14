package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.List;
import java.util.Map;

public class NaiveLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public NaiveLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Makes a guess which ignores the given pattern. */
    public char getGuess(String pattern, List<Character> guesses) {
        return getGuess(guesses);
    }
    public Map<Character, Integer> getFrequencyMap() {
        return GuesserHelperFunctions.getFrequencyMap(words);
    }

    /**
     * Returns the most common letter in WORDS that has not yet been guessed
     * (and therefore isn't present in GUESSES).
     */
    public char getGuess(List<Character> guesses) {
        var freqMap = GuesserHelperFunctions.getFrequencyMap(words);
        char max = GuesserHelperFunctions.mostAbundantChar(freqMap, guesses);
        return max;
    }

    public static void main(String[] args) {
        NaiveLetterFreqGuesser nlfg = new NaiveLetterFreqGuesser("data/example.txt");
        System.out.println("list of words: " + nlfg.words);
        System.out.println("frequency map: " + nlfg.getFrequencyMap());

        List<Character> guesses = List.of('e', 'l');
        System.out.println("guess: " + nlfg.getGuess(guesses));
    }
}
