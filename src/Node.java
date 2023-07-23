public class Node {
    Grid state;
    arrayList<Grid> leaves = new arrayList<Grid>();
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

}
