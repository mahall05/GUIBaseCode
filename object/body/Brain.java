package object.body;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Brain {
    protected boolean winner = false;
    protected boolean alive = true;
    protected double[] fitnessTracker;
    protected Body body;
    protected boolean isPlayer=false;

    public Brain() {

    }
    public Brain(Brain b){

    }

    public abstract Point accelerate();
    public abstract void tick();
    public abstract void render(Graphics g);
    
    public abstract double calculateFitness();
    public abstract void mutate(double rate);

    public boolean isAlive(){
        return alive;
    }
    public void kill(){
        alive=false;
    }
    public void win(){
        winner=true;
    }
    public double[] getFitness(){
        return fitnessTracker;
    }
    public double getMaxFitness(){
        double maxFitness = Double.MIN_VALUE;
        for(double d : fitnessTracker){
            maxFitness = Math.max(d, maxFitness);
        }
        return maxFitness;
    }
    public void setBody(Body b){
        this.body=b;
    }
    public boolean isPlayer(){
        return isPlayer;
    }
    
}
