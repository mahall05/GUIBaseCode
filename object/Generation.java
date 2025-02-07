package object;

import java.awt.Point;
import java.util.ArrayList;

import core.Handler;

public class Generation {
    private Handler handler;
    private ArrayList<Body> bodies = new ArrayList<Body>();

    private final int BRAIN_SIZE = 500;

    public Generation(Point spawnPoint, int size, Handler handler){
        this.handler = handler;

        for(int i = 0; i < size; i++){
            bodies.add(new Body(spawnPoint, new NPC(BRAIN_SIZE)));
        }
        for(Body body : bodies){
            handler.addObject(body);
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

    public void cleanBodies(){
        for(Body body : bodies){
            handler.removeObject(body);
        }
    }
}
