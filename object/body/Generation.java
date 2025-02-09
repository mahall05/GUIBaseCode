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

    public final int BRAIN_SIZE = 1000;
    private final double MUT_RATE = 0.1;
    private int step = 0;

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
        bestBoy=bodies.get(0);
    }
    public Generation(Point spawnPoint, Brain[] brains, MapHandler map){
        super(0,0);
        boundsMatter=false;
        mouseClickRegister=false;
        this.map=map;
        this.size=brains.length;

        if(brains[0]==null){
            bodies.add(new Body(spawnPoint, new NPC(BRAIN_SIZE), map));
         }else{
            bodies.add(new Body(spawnPoint, new NPC(brains[0], true), map));
         }

        for(int i = 1; i < size; i++){
            if(brains[i]==null){
                bodies.add(new Body(spawnPoint, new NPC(BRAIN_SIZE), map));
             }else{
                bodies.add(new Body(spawnPoint, new NPC(brains[i], false), map));
             }
        }
        bestBoy=bodies.get(0);
    }

    public boolean allDead(){
        for(Body body : bodies){
            if(body.isAlive()){
                return false;
            }
        }
        return true;
    }

    public void mutate(){
        for(int i = 1; i < bodies.size(); i++){
            bodies.get(i).getBrain().mutate(MUT_RATE);
        }
    }

    @Override
    public void tick() {
        for(Body b : bodies){
            b.tick(map.maxDistance()-bestBoy.distNearestGoal());
        }
        step++;
    }

    @Override
    public void render(Graphics g) {

        int maxIndex=-1;
        if(step<BRAIN_SIZE){
            double maxFitness = Double.MIN_VALUE;
            for(int i = 0; i < bodies.size(); i++){
                if(bodies.get(i).getBrain().getCurrentFitness() > maxFitness){
                    maxIndex=i;
                    maxFitness= bodies.get(i).getBrain().getCurrentFitness();
                }
            }
            if(maxIndex > -1) bestBoy = bodies.get(maxIndex);
        }

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

    public ArrayList<Body> getBodies(){
        return bodies;
    }
    public Brain[] getBrains(){
        Brain[] brains = new Brain[size];
        for(int i = 0; i < brains.length; i++){
            brains[i] = bodies.get(i).getBrain();
        }
        return brains;
    }
}
