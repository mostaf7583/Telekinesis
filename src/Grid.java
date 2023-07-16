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
                if (row == agentRow && (col == agentCol || col == agentCol + 1)) {
                    System.out.print(" A ");  // Print agent marker
                } else {
                    switch (grid[row][col]) {
                        case 0:
                            System.out.print(" - ");  // Print blank cell marker
                            break;
                        case 1:
                            System.out.print(" X ");  // Print agent body marker
                            break;
                        case 2:
                            System.out.print(" F ");  // Print furniture marker
                            break;
                    }
                }
            }
            System.out.println();  // Move to the next line after each row
        }
    }
    // grid generation
     private static final int MIN_GRID_SIZE = 4;
    private static final int BLANK = 0;
    private static final int FURNITURE = 2;

    public static Grid generateGrid(int n, int m, int numFurniture) {
        if (n < MIN_GRID_SIZE || m < MIN_GRID_SIZE) {
            throw new IllegalArgumentException("Grid dimensions must be at least 4x4.");
        }

        if (numFurniture <= 0) {
            throw new IllegalArgumentException("Number of furniture must be positive.");
        }

        int[][] grid = new int[n][m];
        int agentRow = 1;  // The agent is in the second row from the top

        // Place furniture randomly
        Random random = new Random();
        int remainingFurniture = numFurniture;
        while (remainingFurniture > 0) {
            int row = random.nextInt(n);
            int col = random.nextInt(m);

            if (row == agentRow && col < 2) {
                // Skip agent's position
                continue;
            }

            if (grid[row][col] == BLANK) {
                grid[row][col] = FURNITURE;
                remainingFurniture--;
            }
        }

        return new Grid(grid, agentRow, 0);  // Adjust agentCol as needed
    }
    public static void main(String[] args) {
        Grid grid = Grid.generateGrid(5, 5, 5);
        grid.printGrid();
    }

    
}