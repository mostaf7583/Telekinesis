import java.util.ArrayList;
import java.util.HashMap;

public class Node  { 
    long index = 0;
    long key;
    Grid state;
    ArrayList<Node> leaves;
    String strState;
    long cost;
    int depth;
    long parentKey;
    HashMap<Long, Node> allNodes = new HashMap<Long, Node>();
    Node parentNode;

    public void setParent(Node n){
        this.parentNode = n;
    }
    public Node getParent(){
        return this.parentNode;
    }

    public Node(Grid state){
        this.state = state;
        this.strState = state.convertGridToString();
        leaves = new ArrayList<Node>();
        this.cost = state.cost;
        this.depth = 0;
        parentKey = 0;
        this.key = ++index;
        allNodes.put(key, this );
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
                Node x = new Node(state.moveUp(f));
                x.depth = this.depth+1;
                x.setParent(this);
                leaves.add(x);

                Node y = new Node(state.moveDown(f));
                y.depth = this.depth+1;
                y.setParent(this);
                leaves.add(y);
                
            }
            else if(f.orientation == 'H'){
                Node x = new Node(state.moveRight(f));
                x.depth = this.depth+1;
                x.setParent(this);
                leaves.add(x);

                Node y = new Node(state.moveLeft(f));
                y.depth = this.depth+1;
                y.setParent(this);
                leaves.add(y);
            }
        }
    }
    public void printLeaves(){
        System.out.println("NODE:     ");
        this.state.printGrid();
        int count = 0;
        for(Node g:leaves){
            System.out.println("Child no. "+ ++count+" :     ");
            g.state.printGrid();
        }
    }

    public int getDepth() {
        return depth;
    }

    // Setter method to set the depth of the node
    public void setDepth(int depth) {
        this.depth = depth;
    }

  

    

}
