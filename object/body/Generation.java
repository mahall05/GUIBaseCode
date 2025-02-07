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

    private final int BRAIN_SIZE = 500;

    public Generation(Point spawnPoint, int size, MapHandler map){
        super(0,0);
        boundsMatter=false;
        mouseClickRegister=false;
        this.map=map;

        for(int i = 0; i < size; i++){
            bodies.add(new Body(spawnPoint, new NPC(BRAIN_SIZE), map));
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

    @Override
    public void tick() {
        for(Body b : bodies){
            b.tick();
        }
    }

    @Override
    public void render(Graphics g) {
        for(Body b : bodies){
            b.render(g);
        }
    }

    @Override
    public void mouseClick(int mx, int my) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClick'");
    }

    @Override
    public Bounds getBounds() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBounds'");
    }
}
