import java.util.ArrayList;

public class Solution {
    ArrayList<Node> keys = new ArrayList<Node>();
    ArrayList<Node> expanSequence;
    ArrayList<Node> queue;
    boolean solutionExist;
    Node goal;
    public Solution(){

    }
    public Solution(ArrayList<Node> expanSequence, ArrayList<Node> queue,Node goal,boolean solutionExist){
        this.expanSequence = expanSequence;
        this.queue = queue;
        this.solutionExist = solutionExist;
        this.goal = goal;
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

    public void visualizeSolution(){
        if(this.solutionExist == false){
            System.out.println("No solution Exists");
            return;
        }
        System.out.println("Cost: "+ this.goal.cost);
        System.out.println("No. of the expanded nodes = "+ this.expanSequence.size());
        addParents(goal);
        System.out.println("Solution steps representation starting from the root: ");
        for(int i= keys.size()-1; i>=0;i--){
            keys.get(i).state.printGrid();
        }
        System.out.println("The goal: ");
        goal.state.printGrid();
    }
}
