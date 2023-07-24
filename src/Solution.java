import java.util.ArrayList;

public class Solution {
    ArrayList<Node> expanSequence;
    ArrayList<Node> queue;
    boolean solutionExist;
    Node goal;
    public Solution(ArrayList<Node> expanSequence, ArrayList<Node> queue,Node goal,boolean solutionExist){
        this.expanSequence = expanSequence;
        this.queue = queue;
        this.solutionExist = solutionExist;
        this.goal = goal;
    }
}
