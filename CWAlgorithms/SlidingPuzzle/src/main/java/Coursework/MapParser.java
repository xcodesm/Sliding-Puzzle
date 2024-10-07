/**
 * @author : Samuel Cucicea
 * student id             : W1873364
 * 5SENG003W.2 Algorithms : Theory, Design and Implementation
 */
//MapParser class for ->
//A puzzle solver ,finds the shortest path (using BFS) in the maze by sliding in 4 cardinal directions , being able to  stop only after hiting an obstacle or a wall.
//loggers used for debuging purposes
package Coursework;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MapParser {

    private static Logger logger = Logger.getLogger(MapParser.class.getName());

    public static PuzzleBoard parseFile(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, filePath + "LOG: Error reading the file/ file not found" + e.getMessage());
            e.printStackTrace();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            logger.log(Level.SEVERE, filePath + " Error1 reading the file" + e.getMessage());
            e.printStackTrace();
        }

        int height = lines.size();
        int width = lines.get(0).length();
        PuzzleBoard board = new PuzzleBoard(height, width);
        try {
            char[][] boardArray = new char[height][width];
            for (int i = 0; i < height; i++) {
                String row = lines.get(i);
                for (int j = 0; j < row.length(); j++) {
                    char cell = row.charAt(j);
                    boardArray[i][j] = cell;
                    if (cell == 'S') {
                        board.setStart(i, j);
                    } else if (cell == 'F') {
                        board.setFinish(i, j);
                    }
                }
            }

            board.setBoard(boardArray);
            return board;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "File read succsessful but error occured when setting board");
            return null;
        }

    }
}
