package ngordnet.ngrams;

import edu.princeton.cs.algs4.In;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 * <p>
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    private static final int MIN_YEAR = 1400;
    private static final int MAX_YEAR = 2100;
    private static Map<String, TimeSeries> wordsStats;
    private static Map<Integer, TimeSeries> wordsHistory;

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        wordsStats = new HashMap<>();
        wordsHistory = new HashMap<>();
        readWordsFile(wordsFilename);
        readCountsFile(countsFilename);
    }

    private void readWordsFile(String wordsFilename) {
        In wordsFile = new In(wordsFilename);
        while (wordsFile.hasNextLine()) {
            String line = wordsFile.readLine();
            String[] pieces = line.split("\t");

            String word = pieces[0];
            int year = Integer.valueOf(pieces[1]);
            double appearances = Double.valueOf(pieces[2]);

            TimeSeries ts = wordsStats.getOrDefault(word, new TimeSeries());
            ts.put(year, appearances);
            wordsStats.put(word, ts);
        }
    }

    private void readCountsFile(String countsFilename) {
        In countsFile = new In(countsFilename);
        while(countsFile.hasNextLine()) {
            String line = countsFile.readLine();
            String[] pieces = line.split(",");

            int year = Integer.valueOf(pieces[0]);
            double appearances = Double.valueOf(pieces[1]);

            TimeSeries ts = wordsHistory.getOrDefault(year, new TimeSeries());
            ts.put(year, appearances);
            wordsHistory.put(year, ts);
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy".
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        return new TimeSeries(wordsStats.getOrDefault(word, new TimeSeries()), startYear, endYear);
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy,
     * not a link to this NGramMap's TimeSeries. In other words, changes made
     * to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy".
     */
    public TimeSeries countHistory(String word) {
        var years = wordsStats.getOrDefault(word, new TimeSeries()).years();
        if(years.isEmpty()) {
            return new TimeSeries();
        }
        int startYear = years.get(0);
        if(years.size() == 1) {
            return countHistory(word, startYear, startYear);
        }
        int endYear = years.get(years.size() - 1);
        return countHistory(word, startYear, endYear);
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        // TODO: Fill in this method.
        return null;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        return null;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to
     * all words recorded in that year. If the word is not in the data files, return an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        // TODO: Fill in this method.
        return null;
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS
     * between STARTYEAR and ENDYEAR, inclusive of both ends. If a word does not exist in
     * this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        // TODO: Fill in this method.
        return null;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        // TODO: Fill in this method.
        return null;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
