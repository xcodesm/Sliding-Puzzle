/**
 * @author : Samuel Cucicea
 * student id             : W1873364
 * 5SENG003W.2 Algorithms : Theory, Design and Implementation
 */

//PuzzleBoard class for ->
//A puzzle solver ,finds the shortest path (using BFS) in the maze by sliding in 4 cardinal directions , being able to  stop only after hiting an obstacle or a wall.

//loggers used for debuging purposes

package Coursework;

public class PuzzleBoard {
    private char[][] board;   //represents the board 2D array
    private Coordinate start;
    private Coordinate finish;


    //Nested class for ease read which represents the coordinate of the point row - column
    public static class Coordinate {
        int row;
        int col;

        public Coordinate(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public PuzzleBoard(int height, int width) {
        this.board = new char[height][width];
    }

    // Set the entire board at once
    public void setBoard(char[][] boardArray) {
        this.board = boardArray;
    }

    public boolean isWithinBounds(int row, int col) {
        
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    }

    public boolean isObstacle(int row, int col) {
        return board[row][col] == '0';
    }

    // Method to set the start and finish positions
    public void setStart(int row, int col) {
        this.start = new Coordinate(row, col);
    }

    public void setFinish(int row, int col) {
        this.finish = new Coordinate(row, col);
    }

    // Method to get the start and finish positions
    public Coordinate getStart() {
        return start;
    }

    public Coordinate getFinish() {
        return finish;
    }

    public int getHeight() {
        return board.length;
    }

    public int getWidth() {
        return board[0].length;
    }


    public void printBoard() {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}
