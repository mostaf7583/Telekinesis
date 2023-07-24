public class Furniture{
    int x;
    int y;
    int length;
    char orientation;
    public Furniture(int x,int y,int length,char orientation){
        this.x = x;
        this.y = y;
        this.length = length;
        this.orientation = orientation;
    }
    public String toString(){
        return "x: "+x+" y: "+y+" length: "+length+" orientation: "+orientation;
    }
    public boolean compare(Furniture f){
        if(f.x == this.x && f.y == this.y && f.length==this.length && f.orientation==this.orientation){return true;}
        else return false;
    }

}