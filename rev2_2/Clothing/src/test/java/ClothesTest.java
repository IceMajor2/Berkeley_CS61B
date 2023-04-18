import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.List;

public class ClothesTest {
    
    @DisplayName("Testing for unique list return. No duplicates")
    @Test
    public void uniqueTypeTestNoDuplicates() {
        Clothing c1 = new Clothing("spodnie", "czarne");
        Clothing c2 = new Clothing("buty", "brazowe");
        Clothing c3 = new Clothing("buty", "biale");
        Clothing c4 = new Clothing("t-shirt", "brazowy");
        Clothing c5 = new Clothing("okulary", "czarne");
        
        List<Clothing> clothes = List.of(c1, c2, c3, c4, c5);
        List<Clothing> expected = List.of(c1, c2, c3, c4, c5);
        assertEquals(expected, Main.uniqueClothes(clothes),
                "List should stay the same. There were no duplicates");
    }
    
    @DisplayName("Testing for unique list return. With duplicates")
    @Test
    public void uniqueTypeTestWithDuplicates() {
        Clothing c1 = new Clothing("spodnie", "czarne");
        Clothing c2 = new Clothing("buty", "brazowe");
        Clothing c3 = new Clothing("buty", "biale");
        Clothing c4 = new Clothing("t-shirt", "brazowy");
        Clothing c5 = new Clothing("okulary", "czarne");
        List<Clothing> clothes = List.of(c1, c2, c1, c3, c5, c3, c4);
        List<Clothing> expected = List.of(c1, c2, c3, c5, c4);
        assertEquals(expected, Main.uniqueClothes(clothes), 
                "Duplicate clothes were not removed");
    }
}
