
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        
    }
    
    public static List<Clothing> uniqueClothes(List<Clothing> recent) {
        List<Clothing> unique = new ArrayList<>();
        for(Clothing cloth : recent) {
            if(!unique.contains(cloth)) {
                unique.add(cloth);
            }
        }
        return unique;
    }
}
