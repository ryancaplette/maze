/**
 * Ryan Caplette
 * Recursive Maze Generator - Built with depth first search algorithm
 */

import java.util.ArrayList;
import java.util.Random;

public class Maze {
    private Cell[][] maze;
    private int gridW, gridH;
    private Random rn = new Random();
    private ArrayList<Cell> stack = new ArrayList<Cell>();
    private int visitedCellCounter = 0;
    private Wall[] check = new Wall[]{Wall.OPEN,Wall.OPEN,Wall.OPEN,Wall.OPEN}; // NESW
    private int checkCount = 0;
    public int pickH, pickW;
    private boolean mazeFinished;
    
    public Maze(int gridWidth, int gridHeight) {
        maze = new Cell[gridHeight][gridWidth];
        gridH = gridHeight;
        gridW = gridWidth;
        for(int h = 0; h < gridH; h++)
            for(int w = 0; w < gridW; w++)
                maze[h][w] = new Cell(h,w);
                
        pickH = rn.nextInt(gridH);
        pickW = rn.nextInt(gridW);
        
        maze[pickH][pickW].setVisited(true);
        visitedCellCounter++;
        
        step();
    }
    public int getWidth() {
        return gridW;
    }
    public int getHeight() {
        return gridH;
    }
    public Cell getCell(int x, int y) {
        return maze[y][x];
    }
    public void rebuild() {
        for (int x = 0; x < gridW; x++) {
            for (int y = 0; y < gridH; y++) {
                maze[y][x].setVisited(false);
                maze[y][x].setNorth(Wall.CLOSED);
                maze[y][x].setEast(Wall.CLOSED);
                maze[y][x].setSouth(Wall.CLOSED);
                maze[y][x].setWest(Wall.CLOSED);
            }
        }
        pickH = rn.nextInt(gridH);
        pickW = rn.nextInt(gridW);
        stack = null;
        stack = new ArrayList<Cell>();
        visitedCellCounter = 0;
        maze[pickH][pickW].setVisited(true);
        visitedCellCounter++;
    }
    public void step(){
        if(visitedCellCounter < gridW*gridH){
            mazeFinished = false;
            if((pickH-1) >= 0){//NORTH
                if(!maze[pickH-1][pickW].isVisited()){
                    check[0] = Wall.CLOSED;
                    checkCount++;
                }
            }
            if((pickH+1) <= (gridH-1)){//SOUTH
                if(!maze[pickH+1][pickW].isVisited()){
                    check[2] = Wall.CLOSED;
                    checkCount++;
                }
            }
            if((pickW+1) <= (gridW-1)){//EAST
                if(!maze[pickH][pickW+1].isVisited()){
                    check[1] = Wall.CLOSED;
                    checkCount++;
                }
            }
            if((pickW-1) >= 0){//WEST
                if(!maze[pickH][pickW-1].isVisited()){
                    check[3] = Wall.CLOSED;
                    checkCount++;
                }
            }
            if(checkCount > 0){
                int number = rn.nextInt(4);
                while(check[number] == Wall.OPEN)
                    number = rn.nextInt(4);
                switch (number) {
                    case 0:
                        maze[pickH][pickW].setNorth(Wall.OPEN);
                        stack.add(maze[pickH][pickW]);
                        pickH--;
                        maze[pickH][pickW].setVisited(true);
                        maze[pickH][pickW].setSouth(Wall.OPEN);
                        break;
                    case 1:  maze[pickH][pickW].setEast(Wall.OPEN);
                        stack.add(maze[pickH][pickW]);
                        pickW++;
                        maze[pickH][pickW].setVisited(true);
                        maze[pickH][pickW].setWest(Wall.OPEN);
                        break;
                    case 2:
                        maze[pickH][pickW].setSouth(Wall.OPEN);
                        stack.add(maze[pickH][pickW]);
                        pickH++;
                        maze[pickH][pickW].setVisited(true);
                        maze[pickH][pickW].setNorth(Wall.OPEN);
                        break;
                    case 3:
                        maze[pickH][pickW].setWest(Wall.OPEN);
                        stack.add(maze[pickH][pickW]);
                        pickW--;
                        maze[pickH][pickW].setVisited(true);
                        maze[pickH][pickW].setEast(Wall.OPEN);
                        break;
                }
                for(int i = 0; i < 4; i++)
                    check[i] = Wall.OPEN;
                checkCount = 0;
                visitedCellCounter++;
            }
            else if(stack.size() > 0){
                Cell backTrack = stack.get(stack.size()-1);
                stack.remove(stack.size()-1);
                pickH = backTrack.getY();
                pickW = backTrack.getX();
            }
            else
                System.out.println("problem");
        }
    }
    public void printCells(){
        for(int h = 0; h < gridH; h++){
            for(int w = 0; w < gridW; w++){
                System.out.printf("X = %d Y = %d ", maze[h][w].getX(),
                    maze[h][w].getY());
                System.out.printf("North = %s ", maze[h][w].getNorth());
                System.out.printf("East = %s ", maze[h][w].getEast());
                System.out.printf("South = %s ", maze[h][w].getSouth());
                System.out.printf("West = %s%n", maze[h][w].getWest());
            }
        }
    }
}