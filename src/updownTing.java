import java.awt.image.BufferedImage;

public class updownTing extends MazeElement {
    private int direction;
    private BufferedImage image;

    public updownTing(Location location, BufferedImage image) {
        super(location, image);
        this.direction = 1; 
        this.image = image;
    }
    
    public BufferedImage getImg() {
        return image;
    }

    public void move(char[][] maze) {
        Location currentLocation = getLocation();
        int currentRow = currentLocation.getR();
        int currentCol = currentLocation.getC();

        Location nextLocation = new Location(currentRow + direction, currentCol);

        if (isValidMove(nextLocation, maze)) {
            currentLocation.set(nextLocation.getR(), nextLocation.getC());
        } else {
            direction *= -1;
        }
    }

    private boolean isValidMove(Location location, char[][] maze) {
        int r = location.getR();
        int c = location.getC();
        return r >= 0 && r < maze.length && c >= 0 && c < maze[0].length && maze[r][c] != '#';
    }

    public static void main(String[] args) {
    	
    }
}

