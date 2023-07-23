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
    //to String
    public String toString(){
        return "x: "+x+" y: "+y+" length: "+length+" orientation: "+orientation;
    }

}