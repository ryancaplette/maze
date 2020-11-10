/**
 * Ryan Caplette
 */

enum Wall {
    OPEN, CLOSED
}

public class Cell {
    private int x, y;
    private boolean visited = false;
    private Wall n, s, e, w;
    
    public Cell(int getH, int getW){
        y = getH;
        x = getW;
        n = s = e = w = Wall.CLOSED;
    }
    public int getX() {
        return x;
    }
    public void setX(int val) {
        x = val;
    }
    public int getY() {
        return y;
    }
    public void setY(int val) {
        y = val;
    }
    public boolean isVisited() {
        return visited;
    }
    public void setVisited(boolean val) {
        visited = val;
    }
    public Wall getNorth() {
        return n;
    }
    public void setNorth(Wall val) {
        n = val;
    }
    public Wall getSouth() {
        return s;
    }
    public void setSouth(Wall val) {
        s = val;
    }
    public Wall getEast() {
        return e;
    }
    public void setEast(Wall val) {
        e = val;
    }
    public Wall getWest() {
        return w;
    }
    public void setWest(Wall val) {
        w = val;
    }
}