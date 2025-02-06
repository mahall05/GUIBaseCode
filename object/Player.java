package object;

import java.awt.Graphics;
import java.awt.Point;

public class Player extends Brain{
    private boolean up=false,down=false,left=false,right=false;

    private static final int MOVE_SPEED = 3;

    public void moveUp(boolean moving){
        //System.out.println("Moving Right");
        up=moving;
    }
    public void moveDown(boolean moving){
        //System.out.println("Moving Right");
        down=moving;
    }
    public void moveLeft(boolean moving){
        //System.out.println("Moving Right");
        left=moving;
    }
    public void moveRight(boolean moving){
        //System.out.println("Moving Right");
        right=moving;
    }

    @Override
    public Point accelerate() {
        if(right&left){
            right=false;
            left=false;
        }
        if(up&down){
            up=false;
            down=false;
        }

        Direction d;

        if(up){
            if(right){
                d=Direction.NE;
            }
            else if(left){
                d=Direction.NW;
            }else{
                d=Direction.N;
            }
        }else if(down){
            if(right){
                d=Direction.SE;
            }else if(left){
                d=Direction.SW;
            }else{
                d=Direction.S;
            }
        }else if(right){
            d=Direction.E;
        }else if(left){
            d=Direction.W;
        }else{
            d=Direction.ZERO;
        }
        int x = (right&left) ? 0 : (right&!left) ? MOVE_SPEED : (left&!right) ? -MOVE_SPEED : 0;
        int y = (up&down) ? 0 : (down&!up) ? MOVE_SPEED : (up&!down) ? -MOVE_SPEED : 0;
        return new Point(x,y);
    }

    private enum Direction{
        N,NE,E,SE,S,SW,W,NW,ZERO
    }

    
}
