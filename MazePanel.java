/**
 * Ryan Caplette
 */

import javax.swing.*;
import java.awt.*;

class MazePanel extends JPanel {
    private Color bg, fg;
    private int width, height, px;
    private Maze maze;
    private int trackX,trackY;
    private long endTimeMillis;
    private final int pauseTime = 4;
    
    // MazePanel: instantiate a new GridPane w squares * h squares,
    // where squares are p pixels wide. b and f are background
    // and foreground (square) colors, respectively.
    public MazePanel(Maze m, int p, Color b, Color f) {
        super();
        maze = m;
        px = p;
        bg = b;
        fg = f;
        setPreferredSize(new Dimension(
            (m.getWidth()*2 + 1) * px,
            (m.getHeight()*2 + 1) * px
        ));
    }
    public MazePanel(Maze m, int p) {
        this(m, p, Color.white, Color.black);
    }
    public MazePanel(Maze m) {
        this(m, 10);
    }
    public void generateNew() {
        maze.rebuild();
        repaint();
    }
    public void setBackground(Color c) {
        bg = c;
    }
    public Color getBackground() {
        return bg;
    }
    public void setForeground(Color c) {
        fg = c;
    }
    public Color getForeground() {
        return fg;
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // reset drawing space
        g.setColor(fg);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        // mark a square for each cell
        for (int x = 0; x < maze.getWidth(); x++) {
            for (int y = 0; y < maze.getHeight(); y++) {
                Cell c = maze.getCell(x, y);
                int cx = 2*c.getX()*px + px;
                int cy = 2*c.getY()*px + px;
                // center
                
                if((x == maze.pickW) && (y == maze.pickH)){
                    g.setColor(Color.red);
                    g.fillRect(cx, cy, px, px);
                }
                else if(c.isVisited()){
                    g.setColor(bg);
                    g.fillRect(cx, cy, px, px);
                }
                g.setColor(fg);
                // northwest
                g.fillRect(cx-px, cy-px, px, px);
                // northeast
                g.fillRect(cx+px, cy-px, px, px);
                // southeast
                g.fillRect(cx+px, cy+px, px, px);
                // southwest
                g.fillRect(cx-px, cy+px, px, px);
                g.setColor((c.getNorth() == Wall.OPEN) ? bg : fg);
                g.fillRect(cx, cy-px, px, px);
                g.setColor((c.getEast() == Wall.OPEN) ? bg : fg);
                g.fillRect(cx+px, cy, px, px);
                g.setColor((c.getSouth() == Wall.OPEN) ? bg : fg);
                g.fillRect(cx, cy+px, px, px);
                g.setColor((c.getWest() == Wall.OPEN) ? bg : fg);
                g.fillRect(cx-px, cy, px, px);
            }
        }
        
        maze.step();
        endTimeMillis = System.currentTimeMillis() + pauseTime;
        while(System.currentTimeMillis() < endTimeMillis){
            
        }
        repaint();
    }
}