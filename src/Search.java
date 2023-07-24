import java.util.ArrayList;
import java.util.HashMap;

public class Search {
    public Solution breadthFirst(Node root) {
        ArrayList<Node> queue = new ArrayList< Node>();
        ArrayList<String> uniqueStates = new ArrayList<String>();
        ArrayList<Node> expanSequence = new ArrayList<Node>();
        
        root.key = 'a';
        queue.add(root);
        uniqueStates.add(root.strState);
        root.expand();
        while(!queue.isEmpty()){
            Node node = queue.get(0);
            expanSequence.add(node);
            queue.remove(0);
            if(node.goaltest()){   
                return new Solution(expanSequence,queue,node,true);
            }
            for(Grid child: node.leaves){
                Node newNode = new Node(child);
                if (!uniqueStates.contains(newNode.strState)) {
                        newNode.expand();
                        queue.add(newNode);
                        uniqueStates.add(newNode.strState);
                }else{
                    continue;
                }
            }
        }
        return new Solution(expanSequence,queue,new Node(new Grid()),false);
    }
}
