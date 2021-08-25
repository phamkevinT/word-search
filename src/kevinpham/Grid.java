package kevinpham;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Grid {

    private int gridSize;

    // Character grid as 2D Array
    private char[][] contents = new char[gridSize][gridSize];

    public Grid(int gridSize) {

        this.gridSize = gridSize;

        // Initialize grid with underscores
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                contents[i][j] = '_';
            }
        }
    }


    /**
     * Populate grid with words to find
     * @param words the list of words to find
     */
    public void fillGrid(List<String> words) {
        for (String word : words) {
            // Get a random number between 0 and GRID_SIZE for x-coord and y-coord starting point for word
            int x = ThreadLocalRandom.current().nextInt(0, gridSize);
            int y = ThreadLocalRandom.current().nextInt(0, gridSize);

            // Assign coordinate in grid to contain first letter of word
            contents[x][y] = word.charAt(0);
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


}
