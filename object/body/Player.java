package object.body;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

import input.key.KeyInput;

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
    public Point accelerate() {
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

        return alive ? new Point(Body.MAX_SPEED, angle) : new Point(0, 999);
    }

    private enum Direction{
        N,NE,E,SE,S,SW,W,NW,ZERO
    }

    public void render(Graphics g){

    }

    public void tick(){
        up=KeyInput.keyChain.get(KeyEvent.VK_W);
        down=KeyInput.keyChain.get(KeyEvent.VK_S);
        right=KeyInput.keyChain.get(KeyEvent.VK_D);
        left=KeyInput.keyChain.get(KeyEvent.VK_A);
    }
    @Override
    public boolean isAlive() {
        return alive;
    }
}
