import org.junit.jupiter.api.*;
import deque.Deque;
import deque.LinkedListDeque;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class LinkedListDequeTest {

    @Test
    public void iteratorTest() {
        Deque<String> dq = new LinkedListDeque<>();
        dq.addFirst("Ala");
        dq.addLast("ma");
        dq.addLast("psa!");
        dq.addFirst("kota");
        dq.addFirst("Nie");

        var iterator = dq.iterator();

        int index = 0;
        for (String str : dq) {
            String next = iterator.next();
            boolean hasNext = iterator.hasNext();
            assertThat(str).isEqualTo(next);
            index++;
            if(index == dq.size()) {
                assertThat(hasNext).isFalse();
            } else {
                assertThat(hasNext).isTrue();
            }
        }
    }

    @Test
    public void equalsTest() {
        Deque<String> dq1 = new LinkedListDeque<>();
        Deque<String> dq2 = new LinkedListDeque<>();

        dq1.addFirst("Ala");
        dq1.addLast("ma");
        dq1.addLast("psa!");
        dq1.addFirst("kota");
        dq1.addFirst("Nie");

        dq2.addLast("Ala");
        dq2.addLast("ma");
        dq2.addLast("kota");
        dq2.addLast("Nie");
        dq2.addLast("psa!");

        assertThat(dq1).isEqualTo(dq2);

        dq2.addLast("KONIEC.");

        assertThat(dq1).isNotEqualTo(dq2);

        List<String> list = new ArrayList<>(dq1.toList());

        assertThat(dq1).isNotEqualTo(list);
    }

    @Test
    public void toStringTest() {
        Deque<Double> dq = new LinkedListDeque<>();
        dq.addLast(5.05);
        dq.addFirst(5.43);
        dq.removeLast();
        dq.addFirst(9.0);
        dq.addFirst(4.323859234);

        String toString = dq.toString();

        assertThat(toString).isEqualTo("[4.323859234, 9.0, 5.43]");
    }
}
