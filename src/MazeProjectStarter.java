import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.util.ArrayList;
/**
 * @author aaravsamal
 *
 */
public class MazeProjectStarter extends JPanel implements KeyListener, ActionListener {
    private JFrame frame;
    private int size = 30, width = 1500, height = 1000;
    private char[][] maze;
    private Timer t;
    private Explorer explorer;
    private updownTing upDownTing;
    private leftrightTing leftRightTing;
    private FinishLine finish;
    
    private updownTing[] allUPTs = new updownTing[8];

    private leftrightTing[] allLRTs = new leftrightTing[5];


    public MazeProjectStarter() {
        setBoard("maze0.txt");
        explorer = new Explorer(new Location(1, 1), loadExplorerImages());
        finish = new FinishLine(new Location(25, 43), loadFinishLineTingsImage());

        
        allUPTs[0] = new updownTing(new Location(10,15), loadUpDownTingsImage());
        allUPTs[1] = new updownTing(new Location(6,7), loadUpDownTingsImage());
        allUPTs[2] = new updownTing(new Location(18,5), loadUpDownTingsImage());
        allUPTs[3] = new updownTing(new Location(20,35), loadUpDownTingsImage());
        allUPTs[4] = new updownTing(new Location(5,19), loadUpDownTingsImage());
        allUPTs[5] = new updownTing(new Location(16,37), loadUpDownTingsImage());
        allUPTs[6] = new updownTing(new Location(20,13), loadUpDownTingsImage());
        allUPTs[7] = new updownTing(new Location(15,25), loadUpDownTingsImage());


                
        allLRTs[0] = new leftrightTing(new Location(7,24), loadLeftRightTingsImage());
        allLRTs[1] = new leftrightTing(new Location(3,3), loadLeftRightTingsImage());
        allLRTs[2] = new leftrightTing(new Location(9,9), loadLeftRightTingsImage());
        allLRTs[3] = new leftrightTing(new Location(15,17), loadLeftRightTingsImage());
        allLRTs[4] = new leftrightTing(new Location(23,37), loadLeftRightTingsImage());
        
        
        
        frame = new JFrame("A-Mazing Program");
        frame.setSize(width, height);
        frame.add(this);
        frame.addKeyListener(this);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        t = new Timer(500, this);
        t.start();
    }

    private BufferedImage[] loadExplorerImages() {
        BufferedImage[] images = new BufferedImage[4];
        try {
            for (int i = 0; i < 4; i++) {
                images[i] = ImageIO.read(new File("///Users/aaravsamal/Desktop/explorer_" + (i+1) + ".png"));
                System.out.println("Image " + (i) + ": " + images[i] + "\n\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return images;
    }
    
    private BufferedImage loadFinishLineTingsImage() {
        try {
            return ImageIO.read(new File("///Users/aaravsamal/Desktop/finishLINE.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
        
    private BufferedImage loadLeftRightTingsImage() {
        try {
            return ImageIO.read(new File("///Users/aaravsamal/Desktop/otherOneLol.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private BufferedImage loadUpDownTingsImage() {
        try {
            return ImageIO.read(new File("///Users/aaravsamal/Desktop/BadGenesLol.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, frame.getWidth(), frame.getHeight());

        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[0].length; c++) {
                g2.setColor(Color.GRAY);
                if (maze[r][c] == '#') {
                    g2.fillRect(c * size + size, r * size + size, size, size);
                } else {
                    g2.drawRect(c * size + size, r * size + size, size, size);
                }
            }
        }

        g2.drawImage(explorer.getImg(), explorer.getLocation().getC() * size + size,
        		explorer.getLocation().getR() * size + size, size, size, null, this);
        
        g2.drawImage(finish.getImg(), finish.getLocation().getC() * size + size,
        		finish.getLocation().getR() * size + size, size, size, null, this);
        
        
        for(int x = 0; x<allUPTs.length; x++) {
        	g2.drawImage(allUPTs[x].getImg(), allUPTs[x].getLocation().getC() * size + size,
        			allUPTs[x].getLocation().getR() * size + size, size, size, null, this);
        	
        }
        
        
        for(int x = 0; x < allLRTs.length; x++) {
        	g2.drawImage(allLRTs[x].getImg(), allLRTs[x].getLocation().getC() * size + size,
        			allLRTs[x].getLocation().getR() * size + size, size, size, null, this);
        }
        
        
    }
    
    public void setBoard(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            ArrayList<String> lines = new ArrayList<>();

            String line;
            int maxCols = 0;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
                maxCols = Math.max(maxCols, line.length());
            }

            int numRows = lines.size();
            maze = new char[numRows][maxCols];

            for (int row = 0; row < numRows; row++) {
                String currentLine = lines.get(row);
                for (int col = 0; col < currentLine.length(); col++) {
                    maze[row][col] = currentLine.charAt(col);
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP) {
            explorer.moveForward(maze);
        } 
        else if (keyCode == KeyEvent.VK_LEFT) {
            explorer.turnLeft();
            System.out.println("\n\nCurrent image: " + explorer.getImg());
        } 
        else if (keyCode == KeyEvent.VK_RIGHT) {
            explorer.turnRight();
            System.out.println("\n\nCurrent image: " + explorer.getImg());
        }

        repaint();
    }

    
    public static void main(String[] args) {
        MazeProjectStarter app = new MazeProjectStarter();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
	    for (int x = 0; x < allUPTs.length; x++) {
	        allUPTs[x].move(maze);
	        if (explorer.getLocation().equals(allUPTs[x].getLocation())) {
	            explorer.setLocation(new Location(1, 1)); 
	        }
	    }

	    // Check if the explorer hits a leftrightTing
	    for (int x = 0; x < allLRTs.length; x++) {
	        allLRTs[x].move(maze);
	        if (explorer.getLocation().equals(allLRTs[x].getLocation())) {
	            explorer.setLocation(new Location(1, 1));
	        }
	    }

	    repaint();
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
