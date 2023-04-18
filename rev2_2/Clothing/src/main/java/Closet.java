
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

    public void updateColor(Clothing cloth, String prevColor) {
        if(!this.wardrobe.contains(cloth)) {
            return;
        }
        String type = cloth.type;
        String currColor = cloth.color;
        this.colorMap.get(prevColor).remove(new Clothing(type, currColor));
        
        var listOfColors = this.colorMap.getOrDefault(currColor, new ArrayList<>());
        listOfColors.add(cloth);
        this.colorMap.put(currColor, listOfColors);
    }
    
    public void add(Clothing cloth) {
        if(!wardrobe.contains(cloth)) {
            wardrobe.add(cloth);
        }
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
        for (Clothing cloth : recent) {
            if (!unique.contains(cloth)) {
                unique.add(cloth);
            }
        }
        return unique;
    }

    public List<Clothing> getItemsByDay(Map<String, String> daysToColors, String currentDay) {
        String color = daysToColors.get(currentDay);
        return getClothes(color);
    }
    
    public void dyeClothes(List<String> previousColors, String toColor) {
        for(String color : colorMap.keySet()) {
            if(previousColors.contains(color)) {
                List<Clothing> clothesToDye = colorMap.get(color);
                List<Clothing> toBeUpdated = new ArrayList<>();
                for(Clothing cloth : clothesToDye) {
                    cloth.dyeColor(toColor);
                    toBeUpdated.add(cloth);
                }
                for(Clothing cloth : toBeUpdated) {
                    this.updateColor(cloth, color);
                }
            }
        }
    }

    public Map<String, List<Clothing>> getColorMap() {
        return colorMap;
    }

    public List<Clothing> getWardrobe() {
        return wardrobe;
    }
}
