import deque.ArrayDeque;
import deque.Deque;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDequeTest {

    @Test
    public void addFirstBasicTest() {
        Deque<String> dq = new ArrayDeque<>();
        dq.addFirst("bek");
        dq.addFirst("klok");
        dq.addFirst("bruk");

        assertThat(dq.toList()).containsExactly("bruk", "klok", "bek").inOrder();
    }

    @Test
    public void addLastBasicTest() {
        Deque<Float> dq = new ArrayDeque<>();
        dq.addLast(3.21f);
        dq.addLast(-9.09f);
        dq.addLast(0.00f);
        dq.addLast(1.00f);

        assertThat(dq.toList()).containsExactly(3.21f, -9.09f, 0.00f, 1.00f).inOrder();
    }

    @Test
    public void sizeIncreasesWithAdditionTest() {
        Deque<Object> dq = new ArrayDeque<>();
        dq.addLast("5523");
        dq.addLast(null);
        dq.addFirst(5.2f);
        dq.addLast("pekka");

        assertThat(dq.size()).isEqualTo(4);
    }

    @Test
    public void printEmptyList() {
        Deque<String> dq = new ArrayDeque<>();

        assertThat(dq.toList()).isEqualTo(new ArrayList());
    }

    @Test
    public void addFirstBigDataTest() {
        Deque<Integer> dq = new ArrayDeque<>();
        List<Integer> check = new ArrayList<>();

        for(int i = 0; i < 128000; i++) {
            Random random = new Random();
            int randomNum = random.nextInt(500000) - 250000;
            dq.addFirst(randomNum);
            check.add(randomNum);


        }
        List<Integer> reversed = new ArrayList<>();
        for(int i = check.size() - 1; i >= 0; i--) {
            reversed.add(check.get(i));
        }
        check = reversed;
        assertThat(dq.toList()).isEqualTo(check);
    }

    @Test
    public void addLastBigDataTest() {
        Deque<Integer> dq = new ArrayDeque<>();
        List<Integer> check = new ArrayList<>();

        for(int i = 0; i < 128000; i++) {
            Random random = new Random();
            int randomNum = random.nextInt(500000) - 250000;
            dq.addLast(randomNum);
            check.add(randomNum);
        }

        assertThat(dq.toList()).isEqualTo(check);
    }

    @Test
    public void getMethodThrowsRightExceptions() {
        Exception exc = null;
        Deque<Long> dq = new ArrayDeque<>();
        try {
            dq.get(1);
        } catch(IndexOutOfBoundsException e) {
            exc = e;
        }
        assertThat(exc instanceof IndexOutOfBoundsException).isTrue();
        dq.addLast(5l);
        try {
            dq.get(1);
        } catch(IndexOutOfBoundsException e) {
            exc = e;
        }
        assertThat(exc instanceof IndexOutOfBoundsException).isTrue();

        try {
            dq.get(-5);
        } catch(IllegalArgumentException e) {
            exc = e;
        }
        assertThat(exc instanceof IllegalArgumentException).isTrue();
    }

    @Test
    public void getBasicTest() {
        Deque<Integer> dq = new ArrayDeque<>();

        dq.addLast(5);
        dq.addLast(-9);
        dq.addFirst(3);
        dq.addFirst(-33);
        dq.addLast(1);
        // [-33, 3, 5, -9, 1]

        assertThat(dq.get(0)).isEqualTo(-33);
        assertThat(dq.get(1)).isEqualTo(3);
        assertThat(dq.get(2)).isEqualTo(5);
        assertThat(dq.get(3)).isEqualTo(-9);
        assertThat(dq.get(4)).isEqualTo(1);

    }
}
