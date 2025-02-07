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
        ArrayList<Point> points = new ArrayList<Point>();
        ArrayList<Point> otherPoints = new ArrayList<Point>();

        for(int i = 0; i < this.width; i++){
            for(int j = 0; j < this.height; j++){
                points.add(new Point(this.x+i, this.y+j));
            }
        }

        for(int i = 0; i < (int) otherBounds.getWidth(); i++){
            for(int j = 0; j < (int) otherBounds.getHeight(); j++){
                otherPoints.add(new Point((int) otherBounds.getX()+i, (int) otherBounds.getY()+j));
            }
        }

        for(int i = 0; i < points.size(); i++){
            for(int j = 0; j < otherPoints.size(); j++){
                if(points.get(i).getX() == otherPoints.get(j).getX() && points.get(i).getY() == otherPoints.get(j).getY()){
                    return true;
                }
            }
        }
        return false;
    }
}
