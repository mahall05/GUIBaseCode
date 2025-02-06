package object;

import java.awt.Graphics;
import java.awt.Point;

public class Player extends Brain{
    private boolean up=false,down=false,left=false,right=false;

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
    public int accelerate() {
        if(right&left){
            right=false;
            left=false;
        }
        if(up&down){
            up=false;
            down=false;
        }

        int angle;

        if(up){
            if(right){
                angle=45;
            }
            else if(left){
                angle=135;
            }else{
                angle=90;
            }
        }else if(down){
            if(right){
                angle=315;
            }else if(left){
                angle=225;
            }else{
                angle=270;
            }
        }else if(right){
            angle=0;
        }else if(left){
            angle=180;
        }else{
            angle=999;
        }

        return angle;
    }

    private enum Direction{
        N,NE,E,SE,S,SW,W,NW,ZERO
    }

    
}
