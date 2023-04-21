package deque;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ArrayDeque<T> implements deque.Deque<T> {

    public static void main(String[] args) {
        Deque<Integer> list = new ArrayDeque<>();
        list.addFirst(5);
        list.addFirst(7);
        list.addFirst(-2);
        System.out.println(list.toList());
    }

    private T[] array;
    private int backSize;
    private int frontSize;

    public ArrayDeque() {
        this.array = (T[]) new Object[8];
        this.backSize = 0;
        this.frontSize = 0;
    }

    @Override
    public void addFirst(T x) {
        int lastInd = array.length - 1;
        array[lastInd - backSize] = x;
        backSize++;
    }

    @Override
    public void addLast(T x) {
        array[frontSize] = x;
        frontSize++;
    }

    @Override
    public List<T> toList() {
        List<T> firstItems = getFirstItems();
        List<T> lastItems = getLastItems();
        List<T> list = Stream.concat(firstItems.stream(), lastItems.stream()).toList();
        return list;
    }

    private List<T> getFirstItems() {
        if(backSize == 0) {
            return new ArrayList<>();
        }
        List<T> first = new ArrayList<>();
        for(int i = array.length - backSize; i < array.length; i++) {
            first.add(array[i]);
        }
        return first;
    }

    private List<T> getLastItems() {
        List<T> last = new ArrayList<>();
        for(int i = 0; i < frontSize; i++) {
            last.add(array[i]);
        }
        return last;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return this.backSize + this.frontSize;
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
}
