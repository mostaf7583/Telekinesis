public class Node {
    Grid state;
    
    public boolean goaltest(){
        int agentCol = state.agentCol;
        for(int i= agentCol+2;i<state.grid[1].length;i++){
            if(state.grid[1][i] !=0) return false;
        }
        return true;    
    }
    public String toString(){
        return "x: "+x+" y: "+y+" length: "+length+" orientation: "+orientation;
    }
    public void expand(){
        
    }

}
