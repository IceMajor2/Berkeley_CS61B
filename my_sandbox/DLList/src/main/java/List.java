public class List<T> {

    T item;
    List<T> next;

    public List(T item) {
        this.item = item;
        this.next = null;
    }

    public int size() {
        if(this.next == null) {
            return 1;
        }
        return 1 + this.next.size();
    }
    
    public void addLast(T item) {
        List<T> pointer = this;
        while (true) {
            if (pointer.next == null) {
                pointer.next = new List(item);
                break;
            }
            pointer = pointer.next;
        }
    }
    
    public T get(int index) {
        if(index > size() - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        List<T> pointer = this;
        int current = 0;
        while(current != index) {
            pointer = pointer.next;
            current++;
        }
        return pointer.item;
    }
    
    public static void main(String[] args) {
        List<String> list = new List<>("5");
        list.addLast("7");
        list.addLast("lol");
        System.out.println(list.size());
        System.out.println(list.get(1));
    }
}
