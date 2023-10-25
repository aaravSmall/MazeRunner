import java.awt.image.BufferedImage;

public class Explorer extends MazeElement {
    private int direction;
    private int steps;
    private BufferedImage[] explorerImages;

    public Explorer(Location location, BufferedImage[] explorerImages) {
        super(location, explorerImages[0]);
        this.explorerImages = explorerImages;
        this.direction = 0; 
        this.steps = 0;
    }

    public BufferedImage getImg() {
        return explorerImages[direction];
    }

    public void moveForward(char[][] maze) {
        Location nextLocation = new Location(getLocation().getR(), getLocation().getC());

        if (direction == 0) {
            nextLocation.incR(-1);
        } 
        else if (direction == 1) {
            nextLocation.incC(1); 
        } 
        else if (direction == 2) {
            nextLocation.incR(1);
        } 
        else if (direction == 3) {
            nextLocation.incC(-1);
        }

        if (isValidMove(nextLocation, maze)) {
            getLocation().set(nextLocation.getR(), nextLocation.getC());
            steps++;
        }
    }

    public void turnLeft() {
        direction = (direction - 1 + 4) % 4; 
    }

    public void turnRight() {
        direction = (direction + 1) % 4;
    }

    public int getSteps() {
        return steps;
    }

    private boolean isValidMove(Location location, char[][] maze) {
        int r = location.getR();
        int c = location.getC();
        return r >= 0 && r < maze.length && c >= 0 && c < maze[0].length && maze[r][c] != '#';
    }
}
