package kevinpham;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class WordSearchApp {

    public static void main(String[] args) {

        final int GRID_SIZE = 10;

        // Character grid as 2D Array
        char[][] grid = new char[GRID_SIZE][GRID_SIZE];

        // Initialize grid with underscores
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = '_';
            }
        }

        // Words to find
        List<String> words = Arrays.asList("ONE", "TWO", "THREE");

        for (String word : words) {
            // Get a random number between 0 and GRID_SIZE for x-coord and y-coord starting point for word
            int x = ThreadLocalRandom.current().nextInt(0, GRID_SIZE);
            int y = ThreadLocalRandom.current().nextInt(0, GRID_SIZE);

            // Assign coordinate in grid to contain first letter of word
            grid[x][y] = word.charAt(0);
        }

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                // Print each row
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("");
        }

    }

}
