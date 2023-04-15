package aoa.choosers;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.TreeMap;

import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;
import org.checkerframework.checker.units.qual.A;

public class EvilChooser implements Chooser {
    private String pattern;
    private List<String> wordPool;

    public EvilChooser(int wordLength, String dictionaryFile) {
        if (wordLength < 1) {
            throw new IllegalArgumentException();
        }
        this.wordPool = FileUtils.readWordsOfLength(dictionaryFile, wordLength);
        if (wordPool.isEmpty()) {
            throw new IllegalStateException();
        }
        this.pattern = "";
        for (int i = 0; i < wordLength; i++) {
            pattern += "-";
        }
    }

    private List<Integer> getMatchingIndices(String word, char letter) {
        List<Integer> matchingIndices = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (ch == letter) {
                matchingIndices.add(i);
            }
        }
        return matchingIndices;
    }

    @Override
    public int makeGuess(char letter) {

        Map<String, List<String>> patternWords = new TreeMap<>();
        for (String word : wordPool) {
            List<Integer> matchingIndices = getMatchingIndices(word, letter);
            StringBuilder pattern = new StringBuilder(this.pattern);
            for (int index : matchingIndices) {
                pattern.setCharAt(index, letter);
            }
            List<String> wordsForPattern = patternWords.getOrDefault(pattern.toString(), new ArrayList<>());
            wordsForPattern.add(word);
            patternWords.put(pattern.toString(), wordsForPattern);
        }
        String bestPattern = patternWords.entrySet().stream()
                .max((e1, e2) -> Integer.valueOf(e1.getValue().size()).compareTo(e2.getValue().size())).get().getKey();
        int occurs = 0;
        for(int i = 0; i < this.pattern.length(); i++) {
            char bestCh = bestPattern.charAt(i);
            char patCh = this.pattern.charAt(i);
            if(bestCh != patCh) {
                occurs++;
            }
        }
        this.pattern = bestPattern;
        this.wordPool = patternWords.get(pattern);

        return occurs;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }

    @Override
    public String getWord() {
        return wordPool.get(StdRandom.uniform(wordPool.size()));
    }

    public static void main(String[] args) {
        EvilChooser ec = new EvilChooser(4, "data/example.txt");
        ec.makeGuess('e');
        System.out.println();
        ec.makeGuess('o');
        System.out.println(ec.pattern);
    }
}
