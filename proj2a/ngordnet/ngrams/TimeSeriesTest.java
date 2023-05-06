package ngordnet.ngrams;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/** Unit Tests for the TimeSeries class.
 *  @author Josh Hug
 */
public class TimeSeriesTest {
    @Test
    public void testFromSpec() {
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1991, 0.0);
        catPopulation.put(1992, 100.0);
        catPopulation.put(1994, 200.0);

        TimeSeries dogPopulation = new TimeSeries();
        dogPopulation.put(1994, 400.0);
        dogPopulation.put(1995, 500.0);

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);
        // expected: 1991: 0,
        //           1992: 100
        //           1994: 600
        //           1995: 500

        List<Integer> expectedYears = new ArrayList<>
                (Arrays.asList(1991, 1992, 1994, 1995));

        assertThat(totalPopulation.years()).isEqualTo(expectedYears);

        List<Double> expectedTotal = new ArrayList<>
                (Arrays.asList(0.0, 100.0, 600.0, 500.0));

        for (int i = 0; i < expectedTotal.size(); i += 1) {
            assertThat(totalPopulation.data().get(i)).isWithin(1E-10).of(expectedTotal.get(i));
        }
    }

    @Test
    public void copyConstructorTest() {
        TimeSeries ts = new TimeSeries();
        ts.put(1990, 5.5);
        ts.put(1991, 2.3);
        ts.put(1992, 3.3);

        ts.put(1800, 2.2);
        ts.put(1801, 1.1);
        ts.put(1802, 2.12);

        TimeSeries copy = new TimeSeries(ts, 1700, 1989);

        assertThat(copy.get(1800)).isEqualTo(2.2);
        assertThat(copy.get(1801)).isEqualTo(1.1);
        assertThat(copy.get(1802)).isEqualTo(2.12);
        assertThat(copy.containsKey(1990)).isFalse();
        assertThat(copy.containsKey(1991)).isFalse();
        assertThat(copy.containsKey(1992)).isFalse();
        assertThat(copy.size()).isEqualTo(3);
    }
} 