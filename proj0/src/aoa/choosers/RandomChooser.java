package aoa.choosers;

import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;

public class RandomChooser implements Chooser {
    private final String chosenWord;
    private String pattern;

    public RandomChooser(int wordLength, String dictionaryFile) {
        if(wordLength < 1) {
            throw new IllegalArgumentException();
        }
        var words = FileUtils.readWordsOfLength(dictionaryFile, wordLength);
        if(words.isEmpty()) {
            throw new IllegalStateException();
        }
        this.pattern = "";
        for(int i = 0; i < wordLength; i++) {
            pattern += "-";
        }
        this.chosenWord = words.get(StdRandom.uniform(words.size()));
    }

    @Override
    public int makeGuess(char letter) {
        int occurs = 0;
        for(int i = 0; i < chosenWord.length(); i++) {
            char ch = chosenWord.charAt(i);
            if(ch == letter) {
                occurs++;
                StringBuilder sb = new StringBuilder(pattern);
                sb.replace(i, i + 1, Character.toString(letter));
                pattern = sb.toString();
            }
        }
        return occurs;
    }

    @Override
    public String getPattern() {
        return pattern;
    }

    @Override
    public String getWord() {
        return chosenWord;
    }
}
