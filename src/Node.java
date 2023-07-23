import java.util.ArrayList;

public class Node {
    Grid state;
    ArrayList<Grid> leaves;

    public Node(Grid state){
        this.state = state;
        leaves = new ArrayList<Grid>();
    }


    public boolean goaltest(){
        int agentCol = state.agentCol;
        for(int i= agentCol+2;i<state.grid[1].length;i++){
            if(state.grid[1][i] !=0) return false;
        }
        return true;    
    }
  
    public void expand(){
        for(int i=0;i<state.furnitures.size();i++){
            Furniture f = state.furnitures.get(i);
            if(f.orientation == 'V'){
                System.out.println("Down");
                Grid x = state.moveDown(f);
                x.printGrid();
                System.out.println("up");   
                Grid y = state.moveUp(f);
                y.printGrid();
                
                //leaves.add(state.moveUp(f));
                //leaves.add(state.moveDown(f));
            }
            else if(f.orientation == 'H'){
                System.out.println("Rigth");
                state.moveRight(f).printGrid();
                System.out.println("Left");
                state.moveLeft(f).printGrid();
                //leaves.add(state.moveRight(f));
                //leaves.add(state.moveLeft(f));
            }
        }
    }
    public void visualizeleaves(){
        for(Grid g:leaves){
            g.printGrid();
        }
    }

}
