
public class SLList<T> {

    private Node<T> sentinel;
    private int size;

    public SLList() {
        this.sentinel = new Node(124456, null);
        this.size = 0;
    }
    
    public SLList(T item) {
        this.sentinel = new Node(124456, null);
        this.sentinel.next = new Node<>(item, null);
        this.size = 1;
    }

    public void addFirst(T item) {
        sentinel.next = new Node<>(item, sentinel.next);
        size++;
    }
    
    public void addLast(T item) {
        Node<T> pointer = sentinel;
        while(pointer.next != null) {
            pointer = pointer.next;
        }
        size++;
        pointer.next = new Node<>(item, null);
    }

    public T getFirst() {
        return sentinel.next.item;
    }
    
    public int size() {
        return this.size;
    }

    public static void main(String[] args) {
        
    }

    private static class Node<T> {

        public T item;
        public Node<T> next;

        public Node(T item, Node<T> node) {
            this.item = item;
            this.next = node;
        }
    }
}
