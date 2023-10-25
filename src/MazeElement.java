import java.awt.image.BufferedImage;
/**
 * @author aaravsamal
 *
 */
public class MazeElement {
    private Location location;
    private BufferedImage image;

    public MazeElement(Location location, BufferedImage image) {
        this.location = location;
        this.image = image;
    }

    public Location getLocation() {
        return location;
    }
    
    public void setLocation(Location location) {
    	this.location = location;
    }

    public BufferedImage getImg() {
        return image;
    }
}
