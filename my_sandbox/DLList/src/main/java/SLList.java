
public class SLList {

    private IntNode sentinel;
    private int size;

    public SLList() {
        this.sentinel = new IntNode(124456, null);
        this.size = 0;
    }
    
    public SLList(int x) {
        this.sentinel = new IntNode(124456, null);
        this.sentinel.next = new IntNode(x, null);
        this.size = 1;
    }

    public void addFirst(int item) {
        sentinel.next = new IntNode(item, sentinel.next);
        size++;
    }
    
    public void addLast(int item) {
        IntNode pointer = sentinel;
        while(pointer.next != null) {
            pointer = pointer.next;
        }
        size++;
        pointer.next = new IntNode(item, null);
    }

    public int getFirst() {
        return sentinel.next.item;
    }
    
    public int size() {
        return this.size;
    }

    public static void main(String[] args) {
        SLList list = new SLList(15);
        list.addFirst(10);
        list.addFirst(5);
        list.addLast(25);
        System.out.println(list.getFirst());
    }

    private static class IntNode {

        public int item;
        public IntNode next;

        public IntNode(int item, IntNode node) {
            this.item = item;
            this.next = node;
        }
    }
}
