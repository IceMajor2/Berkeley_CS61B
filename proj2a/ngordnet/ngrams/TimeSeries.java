package ngordnet.ngrams;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    private static final int MIN_YEAR = 1400;
    private static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super((n1, n2) -> {
            return n1.compareTo(n2);
        });
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        this();

        for (int year : ts.years()) {
            if (!(year >= startYear && year <= endYear)) {
                continue;
            }
            double data = ts.get(year);
            super.put(year, data);
        }
    }

    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        List<Integer> years = super.keySet().stream().toList();
        return years;
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        List<Double> data = super.values().stream().toList();
        return data;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     * <p>
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        List<Integer> yearsThis = this.years();

        List<Integer> yearsOther = ts.years();
        List<Double> dataOther = ts.data();

        TimeSeries newTs = new TimeSeries();
        try {
            newTs = new TimeSeries(this, yearsThis.get(0), yearsThis.get(yearsThis.size() - 1));
        } catch(ArrayIndexOutOfBoundsException e) {}

        for (int i = 0; i < yearsOther.size(); i++) {
            int year = yearsOther.get(i);
            double data = dataOther.get(i);

            newTs.put(year, newTs.getOrDefault(year, 0d) + data);
        }

        return newTs;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     * <p>
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        List<Integer> yearsOther = ts.years();

        TimeSeries newTs = new TimeSeries();

        for(int year: this.years()) {
            // throws exception if other ts does not contain a year in this one
            if(!yearsOther.contains(year)) {
                throw new IllegalArgumentException();
            }
            newTs.put(year, super.get(year) / ts.get(year));
        }
        return newTs;
    }
}
