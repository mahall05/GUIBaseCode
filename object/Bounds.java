package object;

import java.awt.Dimension;
import java.awt.Rectangle;

public class Bounds extends Rectangle{

    public Bounds(int x, int y, int width, int height){
        super(x,y,width,height);
    }

    public boolean isWithin(int x, int y){
        return (x > this.x && x-this.x < width) && (y > this.y && y-this.y < height);
    }
}
