import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class SingleLinkedListTest {
    
    @DisplayName("Most basic test of SLList add, get and size methods")
    @Test
    public void listTest() {
        SLList<Integer> list = new SLList<>();
        list.addFirst(-99);
        assertEquals(-99, list.getFirst());
        
        list.addFirst(12);
        assertEquals(12, list.getFirst());
        
        list.addLast(55);
        list.addLast(66);
        assertEquals(12, list.getFirst());
        
        assertEquals(4, list.size());
    }
    
    @DisplayName("Adding an object as last when the list's empty")
    @Test
    public void listAddLastTest() {
        SLList<String> list = new SLList<>();
        list.addLast("TEST TEST");
        
        assertEquals("TEST TEST", list.getFirst());
        assertEquals(1, list.size());
    }
    
}
