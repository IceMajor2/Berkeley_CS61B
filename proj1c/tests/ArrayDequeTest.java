import org.junit.jupiter.api.*;
import deque.Deque;
import deque.ArrayDeque;

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

    }

    @Test
    public void toStringTest() {

    }
}
