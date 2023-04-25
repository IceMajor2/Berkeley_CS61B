package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    public static void main(String[] args) {
        Deque<Integer> d1 = new LinkedListDeque<>();
        Deque<Integer> d2 = new LinkedListDeque<>();
        d1.addLast(5);
        d2.addLast(5);
        d1.addLast(4);
        d2.addLast(4);
        d2.addFirst(2);
        d1.addLast(2);

        System.out.println(d1.equals(d2));
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        this.sentinel = new Node(null, 2141235, null);
        this.sentinel.next = sentinel;
        this.sentinel.prev = sentinel;
        this.size = 0;
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator<T>();
    }

    @Override
    public void addFirst(T x) {
        size++;
        sentinel.next = new Node(sentinel, x, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
    }

    @Override
    public void addLast(T x) {
        size++;
        sentinel.prev = new Node(sentinel.prev, x, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
    }

    @Override
    public List<T> toList() {
        if (size == 0) {
            return new ArrayList<>();
        }
        List<T> asList = new ArrayList<>();
        Node<T> pointer = sentinel.next;
        while (pointer != sentinel) {
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

    public boolean equals(Object other) {
        if(this == other) {
            return true;
        }
        
        if(other instanceof LinkedListDeque oLLD) {
            if(this.size != oLLD.size) {
                return false;
            }
            for(T x : this) {
                if(!oLLD.contains(x)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean contains(T x) {
        for(T object : this) {
            if(x.equals(object)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public T removeFirst() {
        checkForRemoveExceptions();
        T removedItem = (T) sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return removedItem;
    }

    @Override
    public T removeLast() {
        checkForRemoveExceptions();
        T removedItem = (T) sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return removedItem;
    }

    @Override
    public T get(int index) {
        checkForGetExceptions(index);
        Node pointer = sentinel.next;
        int i = 0;
        while (i != index) {
            pointer = pointer.next;
            i++;
        }
        return (T) pointer.item;
    }

    private void checkForRemoveExceptions() {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void checkForGetExceptions(int index) {
        if (index < 0) {
            throw new IllegalArgumentException();
        }
        if (index + 1 > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public T getRecursive(int index) {
        checkForGetExceptions(index);
        int i = 0;
        if (index == i) {
            return (T) sentinel.next.item;
        }
        i++;
        Node pointer = sentinel.next.next;
        return recursionHelp(pointer, index, i);
    }

    private T recursionHelp(Node pointer, int index, int currPos) {
        if (currPos == index) {
            return (T) pointer.item;
        }
        currPos++;
        pointer = pointer.next;
        return recursionHelp(pointer, index, currPos);
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

    private class LinkedListIterator<T> implements Iterator<T> {

        private int pos;

        public LinkedListIterator() {
            this.pos = 0;
        }

        @Override
        public boolean hasNext() {
            if(this.pos < size) {
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            T toReturn = (T) get(this.pos);
            pos++;
            return toReturn;
        }
    }
}
