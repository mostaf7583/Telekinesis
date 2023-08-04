import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
import java.lang.*;
import java.io.*;
 
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
    long aStarCost;
    long greedyCostH1;
    long greedyCostH2;
    long h1;
    long h2;
    long heuristic;

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
                x.setHeuristic();
                leaves.add(x);

                Node y = new Node(state.moveDown(f));
                y.depth = this.depth+1;
                y.setParent(this);
                y.setHeuristic();
                leaves.add(y);
                
            }
            else if(f.orientation == 'H'){
                Node x = new Node(state.moveRight(f));
                x.depth = this.depth+1;
                x.setParent(this);
                x.setHeuristic();
                leaves.add(x);

                Node y = new Node(state.moveLeft(f));
                y.depth = this.depth+1;
                y.setParent(this);
                y.setHeuristic();
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

    public void setHeuristic(){
        long h1 = 0;
        long h2 = 0;
        int agentCol = state.agentCol;
        for(int i= agentCol+2;i<state.grid[1].length;i++){
            if(state.grid[1][i] ==2) h1++;
            else if(state.grid[1][i] ==3) h1+=1000;
        }
        for(int i=0; i<state.furnitures.size(); i++){
            Furniture f = state.furnitures.get(i);
            if(f.x > state.agentCol+1 && f.orientation == 'V'){
                if(f.y == 0) h2+=2;
                else if (f.y == 1) h2++;
            }

        }
        this.h1=h1;
        this.h2=h2;

        greedyCostH1 = h1 + cost;
        greedyCostH2 = h2 + cost;
    }
}

class Sortbycost implements Comparator<Node>
{
    public int compare(Node a, Node b)
    {
        return (int)(a.cost - b.cost);
    }
}

class SortbyaStar_cost_h1 implements Comparator<Node>
{
    public int compare(Node a, Node b)
    {
        return (int)(a.h1 - b.h1);
    }
}
class SortbyaStar_cost_h2 implements Comparator<Node>
{
    public int compare(Node a, Node b)
    {
        return (int)(a.h2 - b.h2);
    }
}

class Sortbygreedy_h1 implements Comparator<Node>
{
    public int compare(Node a, Node b)
    {
        return (int)(a.greedyCostH1 - b.greedyCostH1);
    }
}

class Sortbygreedy_h2 implements Comparator<Node>
{
    public int compare(Node a, Node b)
    {
        return (int)(a.greedyCostH2 - b.greedyCostH2);
    }
}
