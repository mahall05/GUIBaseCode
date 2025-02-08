package object;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Bounds extends Rectangle{

    public Bounds(int x, int y, int width, int height){
        super(x,y,width,height);
    }

    public boolean isWithin(int x, int y){
        return (x > this.x && x-this.x < width) && (y > this.y && y-this.y < height);
    }

    public boolean checkCollision(Bounds otherBounds){
        // if other is within this

        if(((otherBounds.getX()>this.x && otherBounds.getX()<this.x+this.width) && (otherBounds.getY()>this.y && otherBounds.getY()<this.y+this.height))
            || ((otherBounds.getX()+otherBounds.getWidth()>this.x && otherBounds.getX()+otherBounds.getWidth()<this.x+this.width) && (otherBounds.getY()>this.y && otherBounds.getY()<this.y+this.height))
            || ((otherBounds.getX()>this.x && otherBounds.getX()<this.x+this.width) && (otherBounds.getY()+otherBounds.getHeight()>this.y && otherBounds.getY()+otherBounds.getHeight()<this.y+this.height))
            || ((otherBounds.getX()+otherBounds.getWidth()>this.x && otherBounds.getX()+otherBounds.getWidth()<this.x+this.width) && (otherBounds.getY()+otherBounds.getHeight()>this.y && otherBounds.getY()+otherBounds.getHeight()<this.y+this.height)))
        {
            return true;
        }else{
            return false;
        }
    }

    public Point getCenter(){
        return new Point((int)(this.getX()+this.getWidth()/2), (int)((this.getY()+this.getHeight())/2));
    }
    public Point[] getCorners(){
        return new Point[] {
            new Point(x,y),
            new Point(x+width, y),
            new Point(x,y+height),
            new Point(x+width, y+height)
        };
    }
}
