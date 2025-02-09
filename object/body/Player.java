package object.body;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import input.key.KeyInput;
import object.PolarVect;

public class Player extends Brain{
    private boolean up=false,down=false,left=false,right=false;

    public Player(int brainSize){
        super(brainSize);
        this.isPlayer=true;
    }

    /**
     * This will only be called when the dot is alive
     */
    @Override
    protected PolarVect move() {
        up=KeyInput.keyChain.get(KeyEvent.VK_W);
        down=KeyInput.keyChain.get(KeyEvent.VK_S);
        right=KeyInput.keyChain.get(KeyEvent.VK_D);
        left=KeyInput.keyChain.get(KeyEvent.VK_A);

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
                angle=45; // NE
            }
            else if(left){
                angle=135; // NW
            }else{
                angle=90; // N
            }
        }else if(down){
            if(right){
                angle=315; // SE
            }else if(left){
                angle=225; // SW
            }else{
                angle=270; // S
            }
        }else if(right){
            angle=0; // E
        }else if(left){
            angle=180; // W
        }else{
            angle=999; // NO MOVEMENT
        }

        return new PolarVect(Body.MAX_SPEED, angle);
    }


    @Override
    public void mutate(double rate) {
        // player brain can't mutate
    }
}
