import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/**
 * Performs some basic linked list tests.
 */
public class LinkedListDequeTest {

    @Test
    @DisplayName("LinkedListDeque has no fields besides nodes and primitives")
    void noNonTrivialFields() {
        Class<?> nodeClass = NodeChecker.getNodeClass(LinkedListDeque.class, true);
        List<Field> badFields = Reflection.getFields(LinkedListDeque.class)
                .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(nodeClass) || f.isSynthetic()))
                .toList();

        assertWithMessage("Found fields that are not nodes or primitives").that(badFields).isEmpty();
    }

    @Test
    /** In this test, we have three different assert statements that verify that addFirst works correctly. */
    public void addFirstTestBasic() {
        Deque<String> lld1 = new LinkedListDeque<>();

        lld1.addFirst("back"); // after this call we expect: ["back"]
        assertThat(lld1.toList()).containsExactly("back").inOrder();

        lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
        assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

        lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
    }

    @Test
    /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
     *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
    public void addLastTestBasic() {
        Deque<String> lld1 = new LinkedListDeque<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    /** This test performs interspersed addFirst and addLast calls. */
    public void addFirstAndAddLastTest() {
        Deque<Integer> lld1 = new LinkedListDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

        assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
    }

    // Below, you'll write your own tests for LinkedListDeque.

    @Test
    public void isEmptyTest() {
        Deque<String> L = new LinkedListDeque<>();
        assertThat(L.isEmpty()).isTrue();
    }

    @Test
    public void isEmptyTestWithItems() {
        Deque<Object> L = new LinkedListDeque<>();
        L.addFirst("5");
        L.addLast(3);
        L.addLast(null);

        assertThat(L.isEmpty()).isFalse();
    }

    @Test
    public void sizeTestOnNonEmptyDeque() {
        Deque<Object> L = new LinkedListDeque<>();
        L.addFirst("66");
        L.addLast(31);
        L.addFirst(null);

        assertThat(L.size()).isEqualTo(3);
    }

    @Test
    public void sizeTestOnEmptyDeque() {
        Deque<Integer> L = new LinkedListDeque<>();

        assertThat(L.size() == 0).isTrue(); // just tried another way of asserting the size's value ;')
    }

    @Test
    public void removeFirstBasicTest() {
        Deque<Integer> L = new LinkedListDeque<>();
        L.addFirst(5);
        L.addFirst(7);
        L.addFirst(1);
        L.addFirst(-66);

        L.removeFirst();

        assertThat(L.toList()).containsExactly(1, 7, 5).inOrder();
    }

    @Test
    public void removeFirstTestThrownExceptionWhenEmpty() {
        Deque<Long> L = new LinkedListDeque<>();
        Exception expected = null;
        try {
            L.removeFirst();
        } catch(IndexOutOfBoundsException e) {
            expected = e;
        }
        assertThat(expected).isNotNull();
    }

    @Test
    public void removeFirstWhenListContainsOne() {
        Deque<String> L = new LinkedListDeque<>();
        L.addLast("joo");
        L.removeFirst();

        assertThat(L.toList()).isEmpty();
    }

    @Test
    public void removeFirstReturnItemTest() {
        Deque<Float> L = new LinkedListDeque<>();
        L.addLast(3.1f);
        L.addFirst(6.023f);

        float returned = L.removeFirst();
        assertThat(returned).isEqualTo(6.023f);
    }

    @Test
    public void removeLastBasicTest() {
        Deque<String> L = new LinkedListDeque<>();
        L.addLast("ka");
        L.addFirst("be");
        L.removeLast();

        assertThat(L.toList()).containsExactly("be");
    }

    @Test
    public void removeLastWhenListContainsOne() {
        Deque<Integer> L = new LinkedListDeque<>();
        L.addFirst(5);
        L.removeLast();

        assertThat(L.toList()).isEmpty();
    }

    @Test
    public void removeLastTestThrownExceptionWhenEmpty() {
        Deque<Double> L = new LinkedListDeque<>();
        Exception expected = null;
        try {
            L.removeLast();
        } catch(IndexOutOfBoundsException e) {
            expected = e;
        }
        assertThat(expected).isNotNull();
    }

    @Test
    public void removeLastReturnItemTest() {
        Deque<String> L = new LinkedListDeque<>();
        L.addFirst("rek");
        L.addFirst("tal");
        L.addLast("jejunum");

        String removed = L.removeLast();
        assertThat(removed).isEqualTo("jejunum");
    }

    @Test
    public void removeLastTestWithCoupleOfElements() {
        Deque<Object> L = new LinkedListDeque<>();
        L.addFirst(6.21);
        L.addLast("besa");
        L.addLast(123);
        L.addFirst(Math.PI);
        L.addLast(10);
        L.addFirst("Hello world!");

        L.removeLast();
        L.removeLast();

        assertThat(L.toList()).containsExactly("Hello world!", Math.PI, 6.21, "besa").inOrder();
    }

    @Test
    public void testSizeIsNotConstant() {
        Deque<Integer> L = new LinkedListDeque<>();
        L.addFirst(2);
        L.addFirst(21);
        L.addFirst(9);

        assertThat(L.size() == 3).isTrue();

        L.addFirst(-1245);

        assertThat(L.size() == 4).isTrue();

        L.removeLast();

        assertThat(L.size()).isEqualTo(3);

        L.removeFirst();

        assertThat(L.size()).isEqualTo(2);
    }

    @Test
    public void getBasicTest() {
        Deque<Boolean> L = new LinkedListDeque<>();
        L.addFirst(true);
        L.addLast(false);

        boolean value = L.get(1);

        assertThat(value).isFalse();

        value = L.get(0);

        assertThat(value).isTrue();
    }

    @Test
    public void getTestWhenListEmpty() {
        Deque<String> L = new LinkedListDeque<>();
        Exception expected = null;
        try {
            String val = L.get(0);
        } catch(IndexOutOfBoundsException e) {
            expected = e;
        }
        assertThat(expected).isNotNull();
    }

    @Test
    public void getTestDoesNotChangeSize() {
        Deque<Object> L = new LinkedListDeque<>();
        L.addLast(12);
        L.addLast("sdka");
        L.addFirst(5.2f);

        L.get(1);

        assertThat(L.size()).isEqualTo(3);

        L.get(2);

        assertThat(L.size()).isEqualTo(3);
    }

    @Test
    public void getTestWhenIndexIsNegative() {
        Deque<Integer> L = new LinkedListDeque<>();
        L.addFirst(2);
        Exception expected = null;

        try {
            L.get(-1);
        } catch(IllegalArgumentException e) {
            expected = e;
        }
        assertThat(expected instanceof IllegalArgumentException).isTrue();
    }

    @Test
    public void getRecursiveBasicTest() {
        Deque<Boolean> L = new LinkedListDeque<>();
        L.addFirst(true);
        L.addLast(false);

        boolean value = L.getRecursive(1);

        assertThat(value).isFalse();

        value = L.getRecursive(0);

        assertThat(value).isTrue();
    }

    @Test
    public void getRecursiveTestWhenListEmpty() {
        Deque<String> L = new LinkedListDeque<>();
        Exception expected = null;
        try {
            String val = L.getRecursive(0);
        } catch(IndexOutOfBoundsException e) {
            expected = e;
        }
        assertThat(expected).isNotNull();
    }

    @Test
    public void getRecursiveTestDoesNotChangeSize() {
        Deque<Object> L = new LinkedListDeque<>();
        L.addLast(12);
        L.addLast("sdka");
        L.addFirst(5.2f);

        L.getRecursive(1);

        assertThat(L.size()).isEqualTo(3);

        L.getRecursive(2);

        assertThat(L.size()).isEqualTo(3);
    }

    @Test
    public void getRecursiveTestWhenIndexIsNegative() {
        Deque<Integer> L = new LinkedListDeque<>();
        L.addFirst(2);
        Exception expected = null;

        try {
            L.getRecursive(-1);
        } catch(IllegalArgumentException e) {
            expected = e;
        }
        assertThat(expected instanceof IllegalArgumentException).isTrue();
    }
}