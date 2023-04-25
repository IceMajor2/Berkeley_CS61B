package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> implements Deque<T> {

    public static void main(String[] args) {
        Deque<Integer> dq = new MaxArrayDeque<>(null);
        dq.addFirst(5);
        dq.addLast(6);
        System.out.println(dq);
    }

    private Comparator<T> comp;

    public MaxArrayDeque(Comparator<T> c) {
        this.comp = c;
    }

    public T max() {
        return null;
    }

    public T max(Comparator<T> c) {
        return null;
    }
}
