package object.body;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import core.Handler;
import object.Bounds;
import object.GameObject;
import object.map.MapHandler;

public class Generation extends GameObject{
    private MapHandler map;
    private ArrayList<Body> bodies = new ArrayList<Body>();
    private int size;

    private final int BRAIN_SIZE = 1000;
    private final double MUT_RATE = 0.1;

    private Body bestBoy;

    public Generation(Point spawnPoint, int size, MapHandler map){
        super(0,0);
        boundsMatter=false;
        mouseClickRegister=false;
        this.map=map;
        this.size=size;

        for(int i = 0; i < size; i++){
            bodies.add(new Body(spawnPoint, new NPC(BRAIN_SIZE), map));
        }
    }
    public Generation(Point spawnPoint, Brain[] brains, MapHandler map){
        super(0,0);
        boundsMatter=false;
        mouseClickRegister=false;
        this.map=map;
        this.size=brains.length;

        bodies.add(new Body(spawnPoint, new NPC(brains[0], true), map));

        for(int i = 0; i < size; i++){
            bodies.add(new Body(spawnPoint, new NPC(brains[i], false), map));
        }
    }

    public boolean allDead(){
        for(Body body : bodies){
            if(body.isAlive()){
                return false;
            }
        }
        return true;
    }

    /**
     * Calc fitness for all bodies
     * @return An array of the brains to make life easier
     */
    public Brain[] calcFitness(){
        Brain[] brains = new Brain[size];
        for(int i = 0; i < size; i++){
            Brain b = bodies.get(i).getBrain();
            b.calcFitness();
            brains[i] = b;
        }
        return brains;
    }

    public void mutate(){
        for(int i = 1; i < bodies.size(); i++){
            bodies.get(i).getBrain().mutate(MUT_RATE);
        }
    }

    @Override
    public void tick() {
        for(Body b : bodies){
            b.tick();
        }
    }

    @Override
    public void render(Graphics g) {

        double maxFitness = Double.MIN_VALUE;
        int maxIndex = -1;
        for(int i = 0; i < bodies.size(); i++){
            if(bodies.get(i).getBrain().calcFitness() > maxFitness){
                maxIndex=i;
                maxFitness=bodies.get(i).getBrain().calcFitness();
            }
        }
        bestBoy = bodies.get(maxIndex);

        for(int i = 0; i < bodies.size(); i++){
            bodies.get(i).render(g, i==maxIndex);
        }
    }

    @Override
    public void mouseClick(int mx, int my) {
    }

    @Override
    public Bounds getBounds() {
        return new Bounds(0,0,0,0);
    }

    public Body getBestBoy(){
        return bestBoy;
    }
}
