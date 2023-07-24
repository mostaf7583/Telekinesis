import java.util.ArrayList;

public class Solution {
    ArrayList<Node> expanSequence;
    ArrayList<Node> queue;
    boolean solutionExist;
    Node goal;
    long expandedNodes;
    public Solution(ArrayList<Node> expanSequence, ArrayList<Node> queue,Node goal,boolean solutionExist,long expandedNodes){
        this.expanSequence = expanSequence;
        this.queue = queue;
        this.solutionExist = solutionExist;
        this.goal = goal;
        this.expandedNodes = expandedNodes;
    }
}
