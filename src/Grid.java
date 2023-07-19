import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Grid {
     int[][] grid;
     int agentRow;
     int agentCol;
    

    public Grid(int[][] grid, int agentRow, int agentCol) {
        this.grid = grid;
        this.agentRow = agentRow;
        this.agentCol = agentCol;
    }

    // Implement methods for grid generation, move validation, and grid update
    public void printGrid() {
        int rows = grid.length;
        int cols = grid[0].length;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col <  grid[row].length; col++) {
            
                    switch (grid[row][col]) {
                        case 0:
                            System.out.print(" - ");  // Print blank cell marker
                            break;
                        case 1:
                            System.out.print(" A ");  // Print agent body marker
                            break;
                        case 2:
                            System.out.print(" F ");  // Print furniture marker
                            break;
                        case 3:
                            System.out.print(" G ");  // Print goal marker
                            break;
                    }
                
            }
            System.out.println();  // Move to the next line after each row
        }
    }
    // grid generation


    public static Grid generateGrid(int n, int m,int numFurniture) { // n rows, m columns
       

        int[][] grid = new int[n][m];
        int agentRow = 1;  // The agent is in the second row from the top
    
        // Place the agent in the first two cells of the agent row
        grid[agentRow][0] = 1;
        grid[agentRow][1] = 1;
    
        // Place the goal (G) at the end of the second row
        grid[1][m - 1] = 3;
    
        // Generate furniture and blanks randomly
        Random random = new Random();
        int furnitureCount = 0;
    
        while (furnitureCount < numFurniture) {
            int row = random.nextInt(n);
            int col = random.nextInt(m);
    
            if (row == agentRow && col < 2) {
                // Skip agent's position
                continue;
            }
    
            if (grid[row][col] == 0 && grid[row][col] != 3) {
                int furnitureLength = random.nextInt(2) + 2; // Generate furniture of length 2 or 3
    
                // Randomly select the orientation (horizontal or vertical)
                boolean isVertical = random.nextBoolean();
    
                if (isVertical && row + furnitureLength <= n) {
                    // Check if there is enough space to place vertical furniture
                    boolean canPlaceFurniture = true;
                    for (int i = 0; i < furnitureLength; i++) {
                        if (grid[row + i][col] != 0 || grid[row + i][col] == 3) {
                            canPlaceFurniture = false;
                            break;
                        }
                    }
                    if (canPlaceFurniture) {
                        for (int i = 0; i < furnitureLength; i++) {
                            grid[row + i][col] = 2; // Furniture marker
                        }
                        furnitureCount++;
                    }
                } else if (!isVertical && col + furnitureLength <= m) {
                    // Check if there is enough space to place horizontal furniture
                    boolean canPlaceFurniture = true;
                    for (int i = 0; i < furnitureLength; i++) {
                        if (grid[row][col + i] != 0 || grid[row][col + i] == 3) {
                            canPlaceFurniture = false;
                            break;
                        }
                    }
                    if (canPlaceFurniture) {
                        for (int i = 0; i < furnitureLength; i++) {
                            grid[row][col + i] = 2; // Furniture marker
                        }
                        furnitureCount++;
                    }
                }
            }
        }
    
        return new Grid(grid, agentRow, 0); // Adjust agentCol as needed
    }


   public void saveGridToFile(String filename) {
        try {
            FileWriter fileWriter = new FileWriter(filename, true);
            for (int[] row : grid) {
                for (int cell : row) {
                    String symbol;
                    switch (cell) {
                        case 0:
                            symbol = "-";
                            break;
                        case 1:
                            symbol = "A";
                            break;
                        case 2:
                            symbol = "F";
                            break;
                        case 3:
                            symbol = "G";
                            break;
                        default:
                            symbol = "?";
                            break;
                    }
                    fileWriter.write(symbol + " ");
                }
                fileWriter.write("\n");
            }
             fileWriter.write("---------------------\n");
            fileWriter.close();
            System.out.println("Grid saved to file: " + filename);
        } catch (IOException e) {
            System.err.println("Error while saving the grid to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Grid grid = Grid.generateGrid(5, 5,2);
        grid.printGrid();
        grid.saveGridToFile("grid.txt");
    }

    
}