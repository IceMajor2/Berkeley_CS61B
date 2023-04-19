public class IntList {

    int item;
    IntList next;

    public IntList(int item) {
        this.item = item;
        this.next = null;
    }

    public int size() {
        if(this.next == null) {
            return 1;
        }
        return 1 + this.next.size();
    }
    
    public void addLast(int item) {
        IntList pointer = this;
        while (true) {
            if (pointer.next == null) {
                pointer.next = new IntList(item);
                break;
            }
            pointer = pointer.next;
        }
    }
    
    public int get(int index) {
        if(index > size() - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        IntList pointer = this;
        int current = 0;
        while(current != index) {
            pointer = pointer.next;
            current++;
        }
        return pointer.item;
    }
    
    public static void main(String[] args) {
        IntList list = new IntList(5);
        list.addLast(6);
        list.addLast(9);
        System.out.println(list.size());
        System.out.println(list.get(2));
    }
}
