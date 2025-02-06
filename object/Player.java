package object;

import java.awt.Graphics;
import java.awt.Point;

public class Player extends Brain{
    private boolean up=false,down=false,left=false,right=false;

    public void moveUp(boolean moving){
        System.out.println("Moving Right");
        up=moving;
    }
    public void moveDown(boolean moving){
        System.out.println("Moving Right");
        down=moving;
    }
    public void moveLeft(boolean moving){
        System.out.println("Moving Right");
        left=moving;
    }
    public void moveRight(boolean moving){
        System.out.println("Moving Right");
        right=moving;
    }

    @Override
    public Point getMovement() {
        return new Point(0+(up?5:0)-(down?5:0), 0+(right?5:0)-(left?5:0));
    }

    
}
