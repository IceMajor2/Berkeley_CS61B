package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> implements Deque<T> {

    public static void main(String[] args) {

    }

    private Comparator<T> comp;

    public MaxArrayDeque() {
        this.comp = null;
    }

    public MaxArrayDeque(Comparator<T> c) {
        this.comp = c;
    }

    public T max() {
        return null;
    }

    public T max(Comparator<T> c) {
        return null;
    }

    public void setComp(Comparator<T> comp) {
        this.comp = comp;
    }
}
