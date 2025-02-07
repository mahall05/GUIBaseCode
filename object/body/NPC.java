package object.body;

import java.awt.Graphics;
import java.awt.Point;

import core.Main;

public class NPC extends Brain{
    private int step = 0;
    private Point[] steps;
    private boolean alive;
    private boolean winner;

    public NPC(int brainSize){
        steps = new Point[brainSize];
        alive=true;

        for(int i = 0; i < brainSize; i++){
            steps[i] = new Point(Main.randomInt(0, Body.MAX_SPEED), Main.randomInt(0, 360));
        }
    }

    @Override
    public Point accelerate() {
        return (alive) ? steps[step] : new Point(0,999);
    }

    @Override
    public void tick() {
        if(alive){
            step++;
            if(step>=steps.length){ // TODO OR check bounds for death
                alive=false;
            }
        }
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public void kill(){
        alive=false;
    }

    @Override
    public void win(){
        winner=true;
    }
    
}
