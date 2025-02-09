package object.body;

import java.awt.Graphics;

import object.PolarVect;

public abstract class Brain{
    protected Body body;
    
    protected double maxFitness;
    protected double currentFitness;

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
    public PolarVect tick(double bestFit){
        currentFitness = calculateFitness(bestFit);
        
        maxFitness= Math.max(currentFitness, maxFitness);

        if(alive){
            PolarVect movement = move();
            steps[step] = movement;
            fitnessTracker[step] = currentFitness;
            
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
    protected double calculateFitness(double bestFit){
        double fitness;
        if(winner){
            fitness = 10000 + 100*(brainSize-step);
        }else{
            fitness = bestFit*Math.pow(Math.E, -0.02*body.distNearestGoal()) * ((!alive && !endOfLife) ? 0.1 : 1.0);
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
        return currentFitness;
    }
    public PolarVect[] getSteps(){
        return steps;
    }
}
