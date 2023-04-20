import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {

    public static void main(String[] args) {
        Deque<Integer> LLD = new LinkedListDeque<>();
        LLD.addLast(-46);
        LLD.addFirst(5);
        LLD.addFirst(-11);
        LLD.addLast(9);
        LLD.addFirst(6);
        LLD.removeFirst();
        LLD.removeFirst();
        System.out.println(LLD.toList());
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        this.sentinel = new Node(null, 2141235, null);
        this.size = 0;
    }

    @Override
    public void addFirst(T x) {
        size++;
        if(size == 1) {
            addFirstItem(x);
            return;
        }
        sentinel.next = new Node(sentinel, x, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
    }

    @Override
    public void addLast(T x) {
        size++;
        if(size == 1) {
            addFirstItem(x);
            return;
        }
        sentinel.prev = new Node(sentinel.prev, x, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
    }

    private void addFirstItem(T x) {
        sentinel.next = new Node(sentinel, x, sentinel);
        sentinel.prev = sentinel.next;
    }

    @Override
    public List<T> toList() {
        if(size == 0) {
            return new ArrayList<>();
        }
        List<T> asList = new ArrayList<>();
        Node<T> pointer = sentinel.next;
        while(pointer != sentinel) {
            asList.add(pointer.item);
            pointer = pointer.next;
        }
        return asList;
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
        if(size == 0) {
            throw new IndexOutOfBoundsException();
        }
        T removedItem = (T) sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return removedItem;
    }

    @Override
    public T removeLast() {
        if(size == 0) {
            throw new IndexOutOfBoundsException();
        }
        T removedItem = (T) sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return removedItem;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }

    private class Node<T> {

        private Node<T> prev;
        private T item;
        private Node<T> next;

        public Node(Node prev, T item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }
}
