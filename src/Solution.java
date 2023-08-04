import java.util.ArrayList;

public class Solution {
    ArrayList<Node> keys = new ArrayList<Node>();
    ArrayList<Node> expanSequence;
    ArrayList<Node> queue;
    boolean solutionExist;
    Node goal;
    ArrayList<Long> path;
    public Solution(){

    }
    public Solution(ArrayList<Node> expanSequence, ArrayList<Node> queue,Node goal,boolean solutionExist){
        this.expanSequence = expanSequence;
        this.queue = queue;
        this.solutionExist = solutionExist;
        this.goal = goal;
        if(solutionExist){
            addParents (goal);
            getPath();
        } 
    }

    void addParents (Node n){
        if(n.getParent() == null){
            return;
        }
        else{
            keys.add(n.getParent());
            addParents(n.getParent());
        }     
    }

    void getPath(){
        this.path = new ArrayList<Long>();
        for(int i= keys.size()-1; i>=0;i--){
            path.add(keys.get(i).key);
        }
    }

    public void visualizeSolution(){
        if(this.solutionExist == false){
            System.out.println("No solution Exists");
            return;
        }
        System.out.println("Cost: "+ this.goal.cost);
        System.out.println("No. of the expanded nodes = "+ this.expanSequence.size());
        System.out.print("The path to the goal: ");
        for(long i: path) System.out.print(i+" ");
        System.out.println();
        System.out.println("Solution steps representation starting from the root: ");
        for(int i= keys.size()-1; i>=0;i--){
            keys.get(i).state.printGrid();
        }
        System.out.println("The goal: ");
        goal.state.printGrid();
    }
}
