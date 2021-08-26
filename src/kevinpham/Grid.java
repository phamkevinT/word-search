package kevinpham;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Grid {

    private int gridSize;
    // Character grid as 2D Array
    private char[][] contents;
    // List of possible coordinates within grid
    private List<Coordinate> coordinates = new ArrayList<>();

    private enum Direction {
        HORIZONTAL,
        VERTICAL,
        DIAGONAL
    }

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
        // Randomize and shuffle the coordinates in the list
        Collections.shuffle(coordinates);

        for (String word : words) {
            // Loop through each coordinate and see which one could fit word
            for (Coordinate coordinate : coordinates) {
                int x = coordinate.x;
                int y = coordinate.y;
                Direction selectedDirection = getDirectionForFit(word, coordinate);

                if (selectedDirection != null) {

                    // Place word based on valid direction
                    switch (selectedDirection) {
                        case HORIZONTAL:
                            // Convert word to charArray and for each character insert into grid
                            for (char c : word.toCharArray()) {
                                contents[x][y++] = c;
                            }
                            break;
                        case VERTICAL:
                            for (char c : word.toCharArray()) {
                                contents[x++][y] = c;
                            }
                            break;
                        case DIAGONAL:
                            for (char c : word.toCharArray()) {
                                contents[x++][y++] = c;
                            }
                            break;
                    }
                    // After we fit a word, move onto the next word
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
     * Check if word fits within grid based on random direction
     *
     * @param word       the word
     * @param coordinate the coordinate
     * @return Valid word placement direction
     */
    private Direction getDirectionForFit(String word, Coordinate coordinate) {

        List<Direction> directions = Arrays.asList(Direction.values());
        Collections.shuffle(directions);
        for (Direction direction : directions) {
            if (doesFit(word, coordinate, direction)) {
                return direction;
            }
        }
        return null;
    }


    /**
     * Check if word fits in grid given word, coordinate, and direction
     *
     * @param word       the word
     * @param coordinate the coordinate
     * @param direction  random direction
     * @return true if fits, otherwise false
     */
    private boolean doesFit(String word, Coordinate coordinate, Direction direction) {
        int wordLength = word.length();

        switch (direction) {
            case HORIZONTAL:
                if (coordinate.y + wordLength > gridSize) return false;
                for (int i = 0; i < wordLength; i++) {
                    if (contents[coordinate.x][coordinate.y + i] != '_') return false;
                }
                break;
            case VERTICAL:
                if (coordinate.x + wordLength > gridSize) return false;
                for (int i = 0; i < wordLength; i++) {
                    if (contents[coordinate.x + i][coordinate.y] != '_') return false;
                }
                break;
            case DIAGONAL:
                if (coordinate.x + wordLength > gridSize || coordinate.y + wordLength > gridSize) return false;
                for (int i = 0; i < wordLength; i++) {
                    if (contents[coordinate.x + i][coordinate.y + i] != '_') return false;
                }
                break;
        }
        return true;
    }

}
