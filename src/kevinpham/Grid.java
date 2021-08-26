package kevinpham;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grid {

    private int gridSize;
    // Character grid as 2D Array
    private char[][] contents;
    // List of possible coordinates within grid
    private List<Coordinate> coordinates = new ArrayList<>();


    private class Coordinate {
        int x;
        int y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    /**
     * Initializes grid with underscores based on grid size
     * Adds all possible coordinates to list of coordinates
     *
     * @param gridSize the grid size
     */
    public Grid(int gridSize) {
        this.gridSize = gridSize;
        contents = new char[gridSize][gridSize];

        // Initialize grid with underscores
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                // Add all possible coordinates into list of coordinates
                coordinates.add(new Coordinate(i, j));
                contents[i][j] = '_';
            }
        }
    }


    /**
     * Populate grid with words
     *
     * @param words the list of words to find
     */
    public void fillGrid(List<String> words) {

        for (String word : words) {
            // Randomize and shuffle the coordinates in the list
            Collections.shuffle(coordinates);

            // Loop through each coordinate and see which one could fit word
            for (Coordinate coordinate : coordinates) {
                int x = coordinate.x;
                int y = coordinate.y;

                // Checking if the word fits in grid
                if (doesFit(word, coordinate)) {
                    // Convert word to charArray and for each character insert into grid
                    for (char c : word.toCharArray()) {
                        contents[x][y++] = c;
                    }
                    break;
                }
            }
        }
    }


    /**
     * Method to print out the word search grid
     */
    public void displayGrid() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                // Print each row
                System.out.print(contents[i][j] + " ");
            }
            System.out.println("");
        }
    }


    /**
     * Check if word fits within grid considering coordinates given
     *
     * @param word       the word
     * @param coordinate the coordinates
     * @return true if word fits, otherwise false
     */
    private boolean doesFit(String word, Coordinate coordinate) {
        // Check if word size fits grid
        if (coordinate.y + word.length() < gridSize) {
            // Check if all proceeding elements are underscore to prevent word overlap
            for (int i = 0; i < word.length(); i++) {
                if (contents[coordinate.x][coordinate.y + i] != '_') {
                    return false;
                }
                return true;
            }
        }
        return false;
    }


}
