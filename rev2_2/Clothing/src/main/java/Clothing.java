
public class Clothing {

    public String type; // one of "top", "bottom", "shoes", or "accessory"
    public String color; // describes the color of the item

    public Clothing(String type, String color) {
        this.type = type;
        this.color = color;
    }

    public void dyeColor(String newColor) {
        this.color = newColor;
    }
    
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        
        if(!(obj instanceof Clothing)) {
            return false;
        }
        
        Clothing compare = (Clothing) obj;
        
        if(this.type.equals(compare.type) && this.color.equals(compare.color)) {
            return true;
        }
        return false;
    }
    
    public String toString() {
        return this.color + " " + this.type;
    }
}
