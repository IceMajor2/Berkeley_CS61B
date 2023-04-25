package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> implements Deque<T> {

    public static void main(String[] args) {
        
    }

    private Comparator<T> comp;

    public MaxArrayDeque() {
        this.comp = null;
    }

    public MaxArrayDeque(Comparator<T> c) {
        this.comp = c;
    }

    public T max() {
        return max(this.comp);
    }

    public T max(Comparator<T> c) {
        if(this.isEmpty()) {
            return null;
        }
        T max = this.get(0);
        for(int i = 1; i < this.size(); i++) {
            T i1 = this.get(i);
            int compare = c.compare(max, i1);
            max = compare < 0 ? i1 : max;
        }
        return max;
    }

    public void setComp(Comparator<T> comp) {
        this.comp = comp;
    }
}
