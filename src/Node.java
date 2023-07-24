import java.util.ArrayList;

public class Node {
    long key;
    Grid state;
    ArrayList<Grid> leaves;
    String strState;
    long cost;

    public Node(Grid state){
        this.state = state;
        this.strState = state.convertGridToString();
        leaves = new ArrayList<Grid>();
        this.cost = state.cost;
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
                leaves.add(state.moveUp(f));
                leaves.add(state.moveDown(f));
            }
            else if(f.orientation == 'H'){
                leaves.add(state.moveRight(f));
                leaves.add(state.moveLeft(f));
            }
        }
    }
    public void printLeaves(){
        System.out.println("NODE:     ");
        this.state.printGrid();
        int count = 0;
        for(Grid g:leaves){
            System.out.println("Child no. "+ ++count+" :     ");
            g.printGrid();
        }
    }

}
