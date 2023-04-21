package deque;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ArrayDeque<T> implements deque.Deque<T> {

    public static void main(String[] args) {
        Deque<Integer> dq = new ArrayDeque<>();
        dq.addLast(5);
        dq.addLast(-9);
        dq.addFirst(3);
        dq.addFirst(-33);
        dq.addLast(1);
        System.out.println(dq.toList());

        System.out.println("First number: " + dq.get(0));
        System.out.println("Second number: " + dq.get(1));
        System.out.println("Third number: " + dq.get(2));
        System.out.println("Fourth number: " + dq.get(3));
        System.out.println("Fifth number: " + dq.get(4));
    }

    private static double R_FACTOR = 1.5;
    private static double R_RATIO = 0.75;

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
        if(this.getCapacityRatio() >= R_RATIO) {
            upsize();
        }
        int lastInd = array.length - 1;
        array[lastInd - backSize] = x;
        backSize++;
    }

    @Override
    public void addLast(T x) {
        if(this.getCapacityRatio() >= R_RATIO) {
            upsize();
        }
        array[frontSize] = x;
        frontSize++;
    }

    private double getCapacityRatio() {
        return (double) this.size() / array.length;
    }

    private void upsize() {
        T[] arr = (T[]) new Object[(int) Math.round(array.length * R_FACTOR)];
        for(int i = 0; i < size(); i++) {
            arr[i] = this.get(i);
        }
        frontSize = size();
        backSize = 0;
        this.array = arr;
    }

    @Override
    public List<T> toList() {
        List<T> firstItems = getFirstItems();
        List<T> lastItems = getLastItems();
        List<T> list = Stream.concat(firstItems.stream(), lastItems.stream()).toList();
        return list;
    }

    private List<T> getFirstItems() {
        List<T> first = new ArrayList<>();
        if(backSize == 0) {
            return first;
        }
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
        array[array.length - backSize] = null;
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        if(index < 0) {
            throw new IllegalArgumentException();
        }
        if(index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        if(index < backSize) {
            // moving the "cursor" on the back array representing FIRST numbers
            int cursor = backSize != 0 ? array.length - backSize : array.length - 1;
            // moving the cursor onto the back array's actual desired number
            cursor += index;
            return array[cursor];
        }
        // cursor at the first number of the array representing LAST numbers
        int cursor = 0;
        // cursor on the front array's actual desired number
        cursor = index - backSize;
        return array[cursor];
    }
}
