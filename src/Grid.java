import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Grid {
     static int[][] grid;
     static int agentCol;
     static ArrayList<Furniture> furnitures = new ArrayList<Furniture>();
     public Grid(int[][] grid){
        this.grid = grid;
     }
     public void setAgentCol(int AgentCol){
        this.agentCol = AgentCol;
     }
        public int getAgentCol(){
            return agentCol;
        }
        public static ArrayList<Furniture> getFurnitures(){
            return furnitures;
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
                    }
                
            }
            System.out.println();  // Move to the next line after each row
        }
    }
    // grid generation


    public static Grid generateGrid() { // n rows, m columns
       

        int n = 5;
        int m = 6;
        /*
        //Generate random number of rows and cols
        int maxRowCol = Integer.MAX_VALUE;
        int n = (int)(Math.random()*(maxRowCol-4+1)+4);
        int m = (int)(Math.random()*(maxRowCol-4+1)+4);
        */

        //Generate random number of furniture
        int maxFurniture = (n*m-3)/2;
        int numFurniture = 2;

        //Generate random position of agent in the 2nd row
        int maxAgentCol = m-2;
        int agentCol = (int)(Math.random()*(maxAgentCol));

        int[][] grid = new int[n][m];
        int agentRow = 1;  // The agent is in the second row from the top


        // Place the agent in the first two cells of the agent row
        grid[agentRow][agentCol] = 1;
        grid[agentRow][agentCol+1] = 1;

        /* can delete ...
        // Place the goal (G) at the end of the second row
        grid[1][m - 1] = 3;
        */

        // Generate furniture and blanks randomly
        Random random = new Random();
        int furnitureCount = 0;
        int previousNum = 0;
        int NoChangeCount=0;

        while (furnitureCount < numFurniture) {

            int row = random.nextInt(n);
            int col = random.nextInt(m);

            if (previousNum == furnitureCount){
            	NoChangeCount++;
            }else{
            	NoChangeCount = 0;
            }

            if (NoChangeCount == 20){
            	numFurniture = furnitureCount;
            	break;
            }

            if (grid[row][col] == 0 ) {
                int furnitureLength = random.nextInt(2) + 2; // Generate furniture of length 2 or 3

                // Randomly select the orientation (horizontal or vertical)
                boolean isVertical = random.nextBoolean();

                if (isVertical && row + furnitureLength <= n) {
                    // Check if there is enough space to place vertical furniture
                    boolean canPlaceFurniture = true;
                    for (int i = 0; i < furnitureLength; i++) {
                        if (grid[row + i][col] != 0) {
                            canPlaceFurniture = false;
                            break;
                        }
                    }
                    if (canPlaceFurniture) {
                        for (int i = 0; i < furnitureLength; i++) {
                            grid[row + i][col] = 2; // Furniture marker
                        }
                        previousNum = furnitureCount;
                        furnitureCount++;
                        
                        furnitures.add(new Furniture(row,col,furnitureLength,'V'));
                    }
                } else if (!isVertical && col + furnitureLength <= m) {
                    // Check if there is enough space to place horizontal furniture
                    boolean canPlaceFurniture = true;
                    for (int i = 0; i < furnitureLength; i++) {
                        if (grid[row][col + i] != 0) {
                            canPlaceFurniture = false;
                            break;
                        }
                    }
                    if (canPlaceFurniture) {
                        for (int i = 0; i < furnitureLength; i++) {
                            grid[row][col + i] = 2; // Furniture marker
                        }
                        previousNum = furnitureCount;
                        furnitureCount++;
                        furnitures.add(new Furniture(row,col,furnitureLength,'H'));
                    }
                }
            }
        }
        Grid gridOut = new Grid(grid);
        gridOut.setAgentCol(agentCol);    
        return gridOut; // Adjust agentCol as needed
    }
 
    public static void  moveRight(Furniture f) {
        if (canMoveRight(f)) {
            int rightCellPos = f.y + f.length;
            grid[f.x][f.y] = 0; // Clear the current position of the furniture
            for (int i = 0; i < f.length; i++) {
                grid[f.x][f.y + i + 1] = 2; // Move the furniture to the right
            }
            f.y = f.y + 1; // Update the furniture's position
            furnitures.remove(f); // Remove the furniture from the list
            furnitures.add(f); // Add the furniture with the updated position back to the list
            grid[f.x][f.y] = 2; // Update the grid with the new position of the furniture
        }
    
      
    }



    public static boolean canMoveRight(Furniture f) {
        if (f.orientation == 'H') {
            int rightCellPos = f.y + f.length;
            if (rightCellPos < grid[0].length) {
                for (int i = 0; i < f.length; i++) {
                    if (grid[f.x][f.y + i + 1] != 0) {
                        return false; // Found another furniture, can't move right
                    }
                }
                return true; // Can move right
            }
        }
        return false; // Furniture is not oriented horizontally or already at the right edge
    }
public void setFurnitures(ArrayList<Furniture> furnitures2) {
        this.furnitures = furnitures2;
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
        Grid grid = Grid.generateGrid();
        grid.printGrid();
        Grid.moveRight(Grid.getFurnitures().get(0));
        System.out.println("=============================");
        System.out.println(grid.getFurnitures());
        System.out.println("=============================");
        grid.printGrid();
        //grid.saveGridToFile("grid.txt");
    }

    
}