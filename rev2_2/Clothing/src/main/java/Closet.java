
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Closet {
    
    private Map<String, List<Clothing>> colorMap;
    private List<Clothing> wardrobe;
    
    public Closet() {
        this.colorMap = new HashMap<>();
        this.wardrobe = new ArrayList<>();
    }
    
    public void add(Clothing cloth) {
        wardrobe.add(cloth);
        String type = cloth.type;
        String color = cloth.color;
        List<Clothing> clothesOfColor = colorMap.getOrDefault(color, new ArrayList<>());
        clothesOfColor.add(cloth);
        colorMap.put(color, clothesOfColor);
    }
    
    public List<Clothing> getClothes(String color) {
        return colorMap.getOrDefault(color, new ArrayList<>());
    }
    
    public List<Clothing> uniqueClothes(List<Clothing> recent) {
        List<Clothing> unique = new ArrayList<>();
        for(Clothing cloth : recent) {
            if(!unique.contains(cloth)) {
                unique.add(cloth);
            }
        }
        return unique;
    }

    public Map<String, List<Clothing>> getColorMap() {
        return colorMap;
    }

    public List<Clothing> getWardrobe() {
        return wardrobe;
    }
}
