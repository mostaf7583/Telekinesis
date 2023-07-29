import java.util.*;


public class Search {
    public Solution breadthFirst(Node root) {
        ArrayList<Node> queue = new ArrayList< Node>();
        ArrayList<String> uniqueStates = new ArrayList<String>();
        ArrayList<Node> expanSequence = new ArrayList<Node>();   
        root.key = 1;
        long index = 1;
        queue.add(root);
        uniqueStates.add(root.strState);
        root.expand();
        root.cost = 0;
        while(!queue.isEmpty()){
            Node node = queue.get(0);
            expanSequence.add(node);
            queue.remove(0);
            if(node.goaltest()){   
                return new Solution(expanSequence,queue,node,true);
            }
            for(Node newNode: node.leaves){
                if (!uniqueStates.contains(newNode.strState)) {
                        newNode.expand();
                        queue.add(newNode);
                        newNode.key = ++index;
                        uniqueStates.add(newNode.strState);

                }else{
                    continue;
                }
            }
        }
        return new Solution(expanSequence,queue,root,false);
    }

    public Solution depthFirst(Node root) {
        ArrayList<Node> queue = new ArrayList< Node>();
        ArrayList<String> uniqueStates = new ArrayList<String>();
        ArrayList<Node> expanSequence = new ArrayList<Node>();   
        root.key = 1;
        long index = 1;
        queue.add(root);
        uniqueStates.add(root.strState);
        root.expand();
        root.cost = 0;
        while(!queue.isEmpty()){
            Node node = queue.get(0);
            expanSequence.add(node);
            queue.remove(0);
            if(node.goaltest()){   
                return new Solution(expanSequence,queue,node,true);
            }
            for(Node newNode: node.leaves){
                if (!uniqueStates.contains(newNode.strState)) {
                        newNode.expand();
                        queue.add(0,newNode);
                        newNode.key = ++index;
                        uniqueStates.add(newNode.strState);

                }else{
                    continue;
                }
            }
        }
        return new Solution(expanSequence,queue,root,false);
    }

   public Solution depthLimited(Node root, int depthLimit) {
        Queue<Node> queue = new LinkedList<>();
        HashSet<String> uniqueStates = new HashSet<>();
        ArrayList<Node> expandSequence = new ArrayList<>();
        root.key = 1;
        long index = 1;
        queue.add(root);
        uniqueStates.add(root.strState);
        root.expand();
        root.cost = 0;
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            expandSequence.add(node);
            if (node.goaltest()) {
                return new Solution(expandSequence, new ArrayList<>(queue), node, true);
            }
            if (node.getDepth() < depthLimit) {
                for (Node newNode : node.leaves) {
                    if (!uniqueStates.contains(newNode.strState)) {
                        newNode.expand();
                        queue.add(newNode);
                        newNode.key = ++index;
                        uniqueStates.add(newNode.strState);
                    }
                }
            }
        }
        return new Solution(expandSequence, new ArrayList<>(queue), root, false);
    }
    
    public Solution iterativeDeepening(Node root){
        Solution sol = new Solution();
        for(int i=1; i<10000;i++){
            sol = depthLimited(root, i);
            if (sol.solutionExist == true){
                return sol;
            }
        }
        return sol;
    }

}
