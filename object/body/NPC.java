package object.body;

import java.awt.Graphics;
import java.awt.Point;

import core.Main;

public class NPC extends Brain{
    private int step = 0;
    private Point[] steps;
    //private boolean alive;
    //private boolean winner;
    private boolean endOfLife;

    public NPC(int brainSize){
        steps = new Point[brainSize];
        alive=true;
        endOfLife=false;

        for(int i = 0; i < brainSize; i++){
            steps[i] = new Point(Main.randomInt(0, Body.MAX_SPEED), Main.randomInt(0, 360));
        }
    }
    public NPC(Brain b){
        steps = new Point[((NPC) b).getSteps().length];
        alive=true;

        for(int i = 0; i < steps.length; i++){
            steps[i] = ((NPC) b).getSteps()[i];
        }
    }

    @Override
    public Point accelerate() {
        return (alive) ? steps[step] : new Point(0,999);
    }

    public Point[] getSteps(){
        return steps;
    }

    @Override
    public void tick() {
        if(alive){
            step++;
            if(step>=steps.length){
                alive=false;
                endOfLife=true;
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
    public double calcFitness() {
        this.fitness = Math.pow(body.getMap().getMaxDistance()-body.distNearestGoal(), 5);
        fitness *= (!alive && !endOfLife) ? 0 : 1.0;
        return fitness;
    }
    @Override
    public void mutate(double rate) {
        for(int i = 0; i < steps.length; i++){
            double rand = Main.randomDouble(0, 1.0);

            if(rand<rate){
                steps[i] = new Point(Main.randomInt(0, Body.MAX_SPEED), Main.randomInt(0, 360));
            }

        }
    }
    
}
