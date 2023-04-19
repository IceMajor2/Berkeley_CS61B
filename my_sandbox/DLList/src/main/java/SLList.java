
public class SLList {

    private IntNode first;
    private int size;

    public SLList(int x) {
        this.first = new IntNode(x, null);
        this.size = 1;
    }

    public void addFirst(int item) {
        first = new IntNode(item, first);
        size++;
    }
    
    public void addLast(int item) {
        IntNode pointer = first;
        
    }

    public int getFirst() {
        return first.item;
    }
    
    public int size() {
        return this.size;
    }

    public static void main(String[] args) {
        SLList list = new SLList(15);
        list.addFirst(10);
        list.addFirst(5);
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
