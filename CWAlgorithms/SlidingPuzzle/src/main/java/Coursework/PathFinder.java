/**
 * @author : Samuel Cucicea
 * student id             : W1873364
 * 5SENG003W.2 Algorithms : Theory, Design and Implementation
 */
//PathFinder class for ->
//A puzzle solver ,finds the shortest path (using BFS) in the maze by sliding in 4 cardinal directions , being able to  stop only after hiting an obstacle or a wall.
package Coursework;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PathFinder {

    private static Logger logger = Logger.getLogger(PathFinder.class.getName());

    static class Coordinate {

        int row, col;
        Coordinate parent;

        Coordinate(int row, int col, Coordinate parent) {
            this.row = row;
            this.col = col;
            this.parent = parent;

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Coordinate that = (Coordinate) o;
            return row == that.row && col == that.col;
        }

        //hash code overridden to ensure right hashing
        @Override
        public int hashCode() {
            return 31 * row + col;
        }
    }

    //represents the 4 cardinal directions to move
    private static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private PuzzleBoard board;
    //keeps track of visited cells
    private Set<Coordinate> visited = new HashSet<>();

    public PathFinder(PuzzleBoard board) {
        try {
            this.board = board;
            logger.log(Level.INFO, "Board initialized");
        } catch (Exception e) {
            System.out.println("Not being able to initialize the board in the path finder " + e.getMessage());
        }
    }

    public boolean findPath() {
        logger.log(Level.INFO, "Starting findPath execution.");

        //ArrayDegue for fast adding and removing cells
        ArrayDeque<Coordinate> queue = new ArrayDeque<>();

        //start is set to visited and the parent is null since there was no move yet
        Coordinate start = new Coordinate(board.getStart().row, board.getStart().col, null);
        visited.add(start);
        queue.offer(start);
        logger.log(Level.FINE, "Added start position to queue: {0}, {1}", new Object[]{start.row, start.col});

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();
            logger.log(Level.FINE, "Processing node at position: {0}, {1}", new Object[]{current.row, current.col});

            if (current.row == board.getFinish().row && current.col == board.getFinish().col) {
                logger.log(Level.INFO, "Finish found at: {0}, {1}", new Object[]{current.row, current.col});

                printPath(current);

                return true;

            }
            //traverses the directions array {-1,0} .. and calls slide method respectively which takes the current and the direction to move
            for (int[] direction : DIRECTIONS) {
                Coordinate next = slide(current, direction);
                if (next != null && visited.add(next)) {
                    queue.offer(next);
                    logger.log(Level.FINE, "Moved to new position: {0}, {1}", new Object[]{next.row, next.col});

                }
            }
        }
        logger.log(Level.WARNING, "No path found.");

        System.out.println("No path found.");
        return false;
    }

    //Slide method ,to move the block
// will move ,(traverse) until it is withing bonds, and is no obstacle
    private Coordinate slide(Coordinate current, int[] direction) {
        int newRow = current.row + direction[0];
        int newCol = current.col + direction[1];
        Coordinate lastValid = null;
        logger.log(Level.FINE, "Sliding from {0}, {1}", new Object[]{current.row, current.col});

//checks if is withing bonds and is not an obstacle
        while (board.isWithinBounds(newRow, newCol) && !board.isObstacle(newRow, newCol)) {
            lastValid = new Coordinate(newRow, newCol, current);
            logger.log(Level.FINE, "Valid step at {0}, {1}", new Object[]{newRow, newCol});

            if (newRow == board.getFinish().row && newCol == board.getFinish().col) {
                return lastValid;
            }
            newRow += direction[0];
            newCol += direction[1];
        }

        return lastValid;
    }

    //reconstructing the path and printing it
    private void printPath(Coordinate finish) {
        LinkedList<Coordinate> path = new LinkedList<>();
        for (Coordinate at = finish; at != null; at = at.parent) {
            path.addFirst(at); //reversing the path
        }
        logger.log(Level.INFO, "Path reconstructed successfully.");

        for (int i = 1; i < path.size(); i++) {
            // Current step's row and column
            int currentRow = path.get(i).row;
            int currentCol = path.get(i).col;

            int previousRow = path.get(i - 1).row;
            int previousCol = path.get(i - 1).col;

            String direction = "";
            if (currentRow == previousRow) {
                direction = currentCol > previousCol ? "right" : "left";  //shorthand version if else, if column is bigger than is righ otherwise left
            } else {
                direction = currentRow > previousRow ? "down" : "up";
            }

            System.out.println(i + ". Move " + direction + " to (" + currentRow + ", " + currentCol + ")");
        }
        System.out.println("Done!");
    }
}
