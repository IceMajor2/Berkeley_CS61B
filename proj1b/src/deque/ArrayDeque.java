package deque;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ArrayDeque<T> implements deque.Deque<T> {

    public static void main(String[] args) {
        Deque<Integer> dq = new ArrayDeque<>();
        dq.addLast(5);
        dq.addFirst(3);
        dq.addLast(77);
        dq.addFirst(-9);
        dq.addLast(23123);
    }

    private static double R_FACTOR = 1.5;
    private static double R_RATIO = 0.75;

    private T[] array;
    private int size;
    private int firstIndex;
    private int lastIndex;

    public ArrayDeque() {
        this.array = (T[]) new Object[8];
        this.size = 0;
        this.firstIndex = 0;
        this.lastIndex = 0;
    }

    @Override
    public void addFirst(T x) {
        if(size == 0) {
            array[0] = x;
            size++;
            return;
        }
        size++;
        firstIndex = newFirstIndex(firstIndex);
        array[firstIndex] = x;
    }

    @Override
    public void addLast(T x) {
        size++;
        this.lastIndex = newLastIndex(lastIndex);
        array[lastIndex] = x;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        for(int i = firstIndex, iterations = 0; iterations < size; i++, iterations++) {
            list.add(array[i]);
            if(i == array.length - 1) {
                i = -1;
            }
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    private int newFirstIndex(int prevFirst) {
        int newIndex = prevFirst - 1;
        if(newIndex < 0) {
            newIndex = array.length - 1;
        }
        return newIndex;
    }

    private int newLastIndex(int prevLast) {
        int newIndex = prevLast;
        while(array[newIndex] != null) {
            newIndex++;
        }
        return newIndex;
    }
}
