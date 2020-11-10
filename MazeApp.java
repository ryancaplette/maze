
/**
 * Ryan Caplette
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

class MazeApp {
    /* main: Maze demo. left-click changes colors;
      * middle- or right-click resets the grid with
      * new marked squares.
      */
    private static Color last = null;
    private static Color getRandomColor() {
        Color[] color = {
            Color.black, Color.blue, Color.cyan, Color.darkGray, Color.gray,
            Color.green, Color.lightGray, Color.magenta, Color.orange,
            Color.pink, Color.white, Color.yellow
        };
        Random r = new Random();
        if (last == null)
            last = color[r.nextInt(color.length)];
        Color c;
        while ((c = color[r.nextInt(color.length)]) == last)
            ;
        last = c;
        return c;
    }
    public static void main(String[] args) {
        final int w = 200, h = 130, p = 10;
        final Maze m = new Maze(50, 30);
        final MazePanel mp = new MazePanel(m, p, getRandomColor(), getRandomColor());
        mp.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                int btn = e.getButton();
                switch (btn) {
                case MouseEvent.BUTTON1:
                    mp.setBackground(getRandomColor());
                    mp.setForeground(getRandomColor());
                    break;
                case MouseEvent.BUTTON2:
                case MouseEvent.BUTTON3:
                    mp.generateNew();
                    break;
                default:
                    break;
                }
            }
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
        });
        JFrame fr = new JFrame("Maze");
        fr.add(mp);
        fr.pack();
        fr.setResizable(false);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
    }
}