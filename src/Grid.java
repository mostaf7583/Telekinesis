import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Grid {
     static int[][] grid;
     int agentCol;
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
    public int[][] getGrid(){
        return grid;
    }
    public ArrayList<Furniture> getFurnitures(){
        return furnitures;
    }
    public void setFurnitures(ArrayList<Furniture> furnitures){
        this.furnitures = furnitures;
    }
    public void setGrid(int[][] grid){
        this.grid = grid;
    }
    
    // toString method
        
    public void printGrid() {
        int rows = grid.length;
        int cols = grid[0].length;
    
        CommandLineTable table = new CommandLineTable();
        table.setShowVerticalLines(true); // Show vertical lines between cells
    
        // Set table headers with column numbers
        String[] headers = new String[cols + 1];
        headers[0] = " "; // An empty cell for the row numbers column
        for (int col = 0; col < cols; col++) {
            headers[col + 1] = String.valueOf(col);
        }
        table.setHeaders(headers);
    
        // Add grid content with row numbers
        for (int row = 0; row < rows; row++) {
            String[] rowData = new String[cols + 1];
            rowData[0] = String.valueOf(row); // Row numbers
    
            for (int col = 0; col < cols; col++) {
                char symbol;
    
                if (grid[row][col] == 1) {
                    symbol = 'A'; // Agent body marker
                } else if (grid[row][col] == 2) {
                    symbol = 'V'; // Furniture marker
                } else if (grid[row][col] == 3){
                    symbol = 'H'; // Blank cell marker
                }else {
                    symbol = '-'; // Blank cell marker
                }
    
                rowData[col + 1] = String.valueOf(symbol);
            }
    
            table.addRow(rowData);
        }
    
        // Print the table
        System.out.println("===== Grid =====");
        table.print();
        System.out.println("================");
    }

    public static Grid generateGridTest() { // n rows, m columns
        int n = 6;
        int m = 7;
        int[][] grid = new int[n][m];
        int agentRow = 1;
        int agentCol = 0;
        grid[agentRow][agentCol] = 1;
        grid[agentRow][agentCol+1] = 1;

        grid[3][0] = 2;
        grid[4][0] = 2;
        furnitures.add(new Furniture(3,0,2,'V'));

        grid[2][2] = 3;
        grid[2][3] = 3;
        furnitures.add(new Furniture(2,2,2,'H'));

        grid[0][5] = 2;
        grid[1][5] = 2;
        grid[2][5] = 2;
        furnitures.add(new Furniture(0,5,3,'V'));

        grid[4][2] = 3;
        grid[4][3] = 3;
        grid[4][4] = 3;
        furnitures.add(new Furniture(4,2,3,'H'));

        return new Grid(grid);
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
        int numFurniture = (int)(Math.random()*(maxFurniture+1));

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
                            grid[row][col + i] = 3; // Furniture marker
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
   
    public static Grid moveRight(Furniture f){
        int rigthCellPos = f.y+f.length;
        int[][] g = grid.clone();
        if(f.orientation == 'H' && rigthCellPos<grid[0].length && grid[f.x][rigthCellPos] == 0){
            g[f.x][f.y] = 0;
            g[f.x][rigthCellPos] = 3;
            f.y = f.y+1;
        }
        Grid gr = new Grid(g);
        return gr;
    }
    public static Grid moveLeft(Furniture f){
        int leftCellPos = f.y-1;
        int[][] g = grid.clone();
        if(f.orientation == 'H' && leftCellPos>=0 && grid[f.x][leftCellPos] == 0){
            g[f.x][f.y+f.length-1] = 0;
            g[f.x][leftCellPos] = 3;
            f.y = f.y-1;
        }
        Grid gr = new Grid(g);
        return gr;
    }
    public static Grid moveUp(Furniture f){
        int upCellPos = f.x-1;
        int[][] g = grid.clone();
        if(f.orientation == 'V' && upCellPos >= 0 && grid[upCellPos][f.y] == 0){
            g[f.x+f.length-1][f.y] = 0;
            g[upCellPos][f.y] = 2;
            f.x = f.x-1;
        }
        Grid gr = new Grid(g);
        return gr;
    }
    public static Grid moveDown(Furniture f){
        int downCellPos = f.x+f.length;
        int[][] g = grid.clone();
        if(f.orientation == 'V' && downCellPos<grid.length && grid[downCellPos][f.y] == 0){
            g[f.x][f.y] = 0;
            g[downCellPos][f.y] = 2;
            f.x = f.x+1;
        }
        Grid gr = new Grid(g);
        return gr;
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
                            symbol = "V";
                            break;
                        case 3:
                            symbol = "H";
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
        Grid grid = Grid.generateGridTest();
        grid.printGrid();
        Furniture f = grid.furnitures.get(0);
        System.out.println("------------");
        grid.moveDown(f).moveDown(f).printGrid();
        //grid.saveGridToFile("grid.txt");
    }

    
}