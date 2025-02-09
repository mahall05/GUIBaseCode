package object.body;

import java.awt.Graphics;

import object.PolarVect;

public abstract class Brain{
    protected Body body;
    
    protected double maxFitness;

    protected PolarVect[] steps;
    protected double[] fitnessTracker;
    protected int brainSize;

    /* Status Indicators */
    protected boolean winner;
    protected boolean alive;
    protected boolean endOfLife;
    protected boolean isPlayer;
    protected int step;

    public Brain(int brainSize) {
        alive=true;
        winner=false;
        endOfLife=false;
        step=0;
        this.brainSize=brainSize;

        steps = new PolarVect[brainSize];
        fitnessTracker = new double[brainSize];
    }
    public Brain(Brain b){
        this(b.getSteps().length);

        for(int i = 0; i < steps.length; i++){
            steps[i]=b.getSteps()[i];
        }
    }

    /**
     * Get the current vector of movement. The method of determining this vector will be different depending on the brain type,
     * but the output will be a movement vector
     * @return A PolarVect with a distance and an angle
     */
    protected abstract PolarVect move();

    /**
     * Any type of brain should have a tick() to determine, in one way or another, how the body should move
     */
    public PolarVect tick(){
        if(alive){
            PolarVect movement = move();
            steps[step] = movement;
            fitnessTracker[step] = calculateFitness();
            maxFitness= Math.max(fitnessTracker[step], maxFitness);

            step++;
            if(step>=brainSize){
                alive=false;
                endOfLife=true;
            }
            
            return movement;
        }
        return new PolarVect(0,0);
    }

    /**
     * This will probably never do anything, brains shouldn't need to be rendered
     */
    public void render(Graphics g){

    }
    
    /**
     * Calculate the current fitness of the brain based on its current location
     * @return The dot's fitness
     */
    protected double calculateFitness(){
        double fitness;
        if(winner){
            fitness = Math.max(Math.pow((body.getMap().getMaxDistance()), 3), steps.length * 5)*10 * (1.0 - (step/steps.length));
        }else{
            fitness = Math.pow((body.getMap().getMaxDistance()-body.distNearestGoal()), 3);
        }
        return fitness;
    }

    /**
     * 
     * @param rate The percentage rate of mutation (0.0-1.0). This is the percent chance that every given step will be mutated
     */
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
        return maxFitness;
    }
    public void setBody(Body b){
        this.body=b;
    }
    public boolean isPlayer(){
        return isPlayer;
    }
    public int getSize(){
        return brainSize;
    }
    public boolean isWinner(){
        return winner;
    }
    public int getStep(){
        return step;
    }
    public double getCurrentFitness(){
        return step>= fitnessTracker.length ? fitnessTracker[fitnessTracker.length-1] : fitnessTracker[step];
    }
    public PolarVect[] getSteps(){
        return steps;
    }
}
