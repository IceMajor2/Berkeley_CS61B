import org.junit.jupiter.api.*;
import deque.Deque;
import deque.ArrayDeque;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class ArrayDequeTest {

    @Test
    public void iteratorTest() {
        Deque<Object> dq = new ArrayDeque<>();
        dq.addFirst("String");
        dq.addLast(3.14);
        dq.addFirst(-99);

        var iterator = dq.iterator();

        int index = 0;
        for(Object o : dq) {
            Object it = iterator.next();
            boolean hasNext = iterator.hasNext();
            index++;
            assertThat(it).isEqualTo(o);
            if(index == dq.size()) {
                assertThat(hasNext).isFalse();
            } else {
                assertThat(hasNext).isTrue();
            }
        }
    }

    @Test
    public void equalsTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ArrayDeque<Integer> ad2 = new ArrayDeque<>();

        ad1.addFirst(5);
        ad1.addLast(-99);
        ad1.addFirst(-213);
        ad1.addLast(0);

        ad2.addFirst(0);
        ad2.addLast(-99);
        ad2.addFirst(5);
        ad2.addLast(-213);

        assertThat(ad1).isEqualTo(ad2);

        ad1.addLast(0);

        assertThat(ad1).isNotEqualTo(ad2);

        List<Integer> list = ad1.toList();

        assertThat(list).isNotEqualTo(ad1);
    }

    @Test
    public void toStringTest() {
        Deque<Object> dq = new ArrayDeque<>();
        dq.addFirst("string");
        dq.addLast("string");
        dq.addFirst(5320568234L);

        assertThat(dq.toString()).isEqualTo("[5320568234, string, string]");
    }
}
