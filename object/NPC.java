package object;

import java.awt.Graphics;
import java.awt.Point;

import core.Main;

public class NPC extends Brain{
    private int step = 0;
    private Point[] steps;

    public NPC(int brainSize){
        steps = new Point[brainSize];

        for(int i = 0; i < brainSize; i++){
            steps[i] = new Point(Main.randomInt(0, Body.MAX_SPEED), Main.randomInt(0, 360));
        }
    }

    @Override
    public Point accelerate() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'getMovement'");
        return steps[step];
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'tick'");
        step++;
    }

    @Override
    public void render(Graphics g) {
    }
    
}
