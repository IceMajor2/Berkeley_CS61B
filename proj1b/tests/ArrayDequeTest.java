import deque.ArrayDeque;
import deque.Deque;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public void addFirstBigDataTest() {
        Deque<Integer> deque = new ArrayDeque<>();
        List<Integer> expected = new ArrayList<>();
        for(int i = 0; i < 128000; i++) {
            Random random = new Random();
            int randomNum = random.nextInt(500000) - 250000;
            deque.addFirst(randomNum);
            expected.add(0, randomNum);
        }

        assertThat(deque.toList()).isEqualTo(expected);
    }


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

        int index0 = dq.get(0);
        int index1 = dq.get(1);
        int index2 = dq.get(2);
        int index3 = dq.get(3);
        int index4 = dq.get(4);
        assertThat(index0).isEqualTo(-33);
        assertThat(index1).isEqualTo(3);
        assertThat(index2).isEqualTo(5);
        assertThat(index3).isEqualTo(-9);
        assertThat(index4).isEqualTo(1);

    }

    @Test
    public void testForEmptiness() {
        Deque<Object> dq = new ArrayDeque<>();
        boolean val = dq.isEmpty();
        assertThat(val).isTrue();

        dq.addLast(5);
        val = dq.isEmpty();
        assertThat(val).isFalse();

        dq.removeFirst();
        val = dq.isEmpty();
        assertThat(val).isTrue();
    }

    @Test
    public void testForEmptinessAndSizeBigData() {
        Deque<Integer> dq = this.getBigDataArrayDeque();
        int expectedSize = 128000;

        assertThat(dq.size()).isEqualTo(expectedSize);
        assertThat(dq.isEmpty()).isFalse();
    }

    @Test
    public void removeMethodsThrowRightExceptions() {
        Exception exc = null;
        Deque<Long> dq = new ArrayDeque<>();
        try {
            dq.removeFirst();
        } catch(NullPointerException e) {
            exc = e;
        }
        assertThat(exc instanceof NullPointerException).isTrue();
        exc = null;
        try {
            dq.removeLast();
        } catch(NullPointerException e) {
            exc = e;
        }
        assertThat(exc instanceof NullPointerException).isTrue();
    }

    @Test
    public void removeFirstBasicTest() {
        Deque<Integer> dq = new ArrayDeque<>();
        dq.addLast(5);
        dq.addLast(2);
        dq.addLast(6);
        dq.removeFirst();

        assertThat(dq.toList()).containsExactly(2, 6).inOrder();

        dq.removeFirst();

        assertThat(dq.toList()).containsExactly(6).inOrder();
    }

    @Test
    public void removeLastBasicTest() {
        Deque<Integer> dq = new ArrayDeque<>();
        dq.addFirst(5);
        dq.addFirst(2);
        dq.addFirst(6);
        dq.removeLast();

        assertThat(dq.toList()).containsExactly(6, 2).inOrder();

        dq.removeLast();

        assertThat(dq.toList()).containsExactly(6).inOrder();

        dq.removeLast();

        assertThat(dq.toList()).isEmpty();

        Exception exc = null;
        try {
            dq.removeLast();
        } catch(NullPointerException e) {
            exc = e;
        }
        assertThat(exc instanceof NullPointerException && dq.toList().isEmpty()).isTrue();
    }

    @Test
    public void removeFirstAndLastBigData() {
        Deque<Integer> dq = this.getBigDataArrayDeque();
        List<Integer> check = new ArrayList<>(dq.toList());

        var removed1 = check.remove(check.size() - 1);
        var removed2 = check.remove(0);
        var removed3 = check.remove(check.size() - 1);
        var removedDq1 = dq.removeLast();
        var removedDq2 = dq.removeFirst();
        var removedDq3 = dq.removeLast();

        assertThat(removed1 == removedDq1 && removed2 == removedDq2 &&
                removed3 == removedDq3).isTrue();

        for(int i = 0; i < 1000; i++) {
            dq.removeFirst();
            check.remove(0);
            dq.removeLast();
            check.remove(check.size() - 1);
            assertThat(dq.toList()).isEqualTo(check);
        }
    }

    private Deque<Integer> getBigDataArrayDeque() {
        Deque<Integer> dq = new ArrayDeque<>();
        for(int i = 0; i < 128000; i++) {
            Random random = new Random();
            int randomNum = random.nextInt(500000) - 250000;
            dq.addLast(randomNum);
        }
        return dq;
    }
}
