import deque.MaxArrayDeque;
import org.junit.jupiter.api.*;
import deque.Deque;
import deque.ArrayDeque;
import deque.LinkedListDeque;

import java.util.*;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class MaxArrayDequeTest {

    @Test
    public void addAndRemoveFirstAndLastBigDataTest() {
        Deque<Integer> bigDq = new MaxArrayDeque<>();
        LinkedList<Integer> expected = new LinkedList<>();
        Random rnd = new Random();

        for(int i = 0; i < 2000000; i++) {
            int rndNum = rnd.nextInt(2000000) - 1000000;
            int rndNum02 = rnd.nextInt(8905432) - 21938504;
            expected.addLast(rndNum);
            expected.addFirst(rndNum02);
            bigDq.addLast(rndNum);
            bigDq.addFirst(rndNum02);
        }

        List<Integer> bigDqList = bigDq.toList();
        assertThat(bigDqList).isEqualTo(expected);

        for(int i = 0; i < 100000; i++) {
            expected.removeLast();
            bigDq.removeLast();
            if(i % 2 == 0) {
                bigDq.removeFirst();
                expected.removeFirst();
            }
        }

        bigDqList = bigDq.toList();
        assertThat(bigDqList).isEqualTo(expected);
    }

    @Test
    public void comparatorMaxNumberTest() {
        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
        MaxArrayDeque<Integer> dq = new MaxArrayDeque<>(comp);
        dq.addFirst(0);
        dq.addLast(-9);
        dq.addFirst(5);
        dq.addLast(-23);
        dq.addFirst(2);
        dq.addFirst(3);

        int max = dq.max();

        assertThat(max).isEqualTo(5);
    }

    @Test
    public void comparatorLongestAndShortestStringTest() {
        Comparator<String> comp = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.valueOf(o1.length()).compareTo(o2.length());
            }
        };
        MaxArrayDeque<String> dq = new MaxArrayDeque<>(comp);

        dq.addFirst("Futbol");
        dq.addFirst("Hip-hop");
        dq.addLast("Pasja");
        dq.addFirst("Praca");
        dq.addLast("Gry");
        dq.addFirst("Swiat");

        String longest = dq.max();
        assertThat(longest).isEqualTo("Hip-hop");

        Comparator<String> newComp = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.valueOf(o2.length()).compareTo(o1.length());
            }
        };

        dq.setComp(newComp);

        String shortest = dq.max();
        assertThat(shortest).isEqualTo("Gry");
    }

    @Test
    public void comparatorAsArgumentTest() {

        MaxArrayDeque<String> dq = new MaxArrayDeque<>();

        dq.addFirst("FIFA");
        dq.addFirst("Max Payne");
        dq.addLast("Grand Theft Auto IV");
        dq.addLast("Mario");

        Comparator<String> compUpperCase = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int upper1 = 0;
                int upper2 = 0;
                for(char ch1 : o1.toCharArray()) {
                    if(Character.isUpperCase(ch1)) {
                        upper1++;
                    }
                }
                for(char ch2 : o2.toCharArray()) {
                    if(Character.isUpperCase(ch2)) {
                        upper2++;
                    }
                }
                return Integer.valueOf(upper1).compareTo(upper2);
            }
        };

        String mostUpperCaseLetters = dq.max(compUpperCase);
        assertThat(mostUpperCaseLetters).isEqualTo("Grand Theft Auto IV");
    }
}
