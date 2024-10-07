/**
 * @author : Samuel Cucicea
 * student id             : W1873364
 * 5SENG003W.2 Algorithms : Theory, Design and Implementation
 * */
//Main class for ->
//A puzzle solver ,finds the shortest path (using BFS) in the maze by sliding in 4 cardinal directions , being able to  stop only after hiting an obstacle or a wall.
//loggers used for debuging purposes
package Coursework;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        String filePath = "examples/maze10_1.txt";                 //small but crowded mazes

        // benchmark_series for large inputs testing //For marking
        //Uncoment for benchmark testing(large inputs)
        //String filePath ="benchmark_series/puzzle_2560.txt";    
        try {
            PuzzleBoard board = MapParser.parseFile(filePath); //reading from file
            board.printBoard(); // please comment if you don't want to print the board , as for the benchmark will take a lot of space on the screen

            System.out.println();

            PathFinder solver = new PathFinder(board);        //creating and passing the board to Pathfinder
            long startTime = System.currentTimeMillis();
            solver.findPath();
            long endTime = System.currentTimeMillis();
            System.out.println("Time taken :" + (endTime - startTime));

            logger.log(Level.INFO, "Successfull read and path found ");
        } catch (FileNotFoundException e) {
            System.err.println("Failed to read the puzzle file: " + e.getMessage());

        } catch (Exception e) {
            System.err.println("\nError occurred: check first the log above if the parsing was successful might be because the incorrect path was given :" + e.getMessage());

        }
    }
}
