package object.map;

import java.awt.Graphics;

import core.Main;
import object.Bounds;
import object.GameObject;

public class MapHandler extends GameObject{
    private MapObject[] obstacles;
    private MapObject[] goals;

    public MapHandler(){
        super(0,0);
        tick=false;
        mouseClickRegister=false;
        boundsMatter=false;

        Main.get().getHandler().addObject(this);

        goals = new MapObject[] {new MapObject(Main.WIDTH/2-100, 0, 200, 200, MapObject.Type.GOAL)};
        obstacles = new MapObject[] {new MapObject(Main.WIDTH/2-200, Main.HEIGHT/2-50, 400, 100, MapObject.Type.OBSTACLE)};
    }

    public boolean checkGoalCollision(Bounds b){
        for(MapObject goal : goals){
            if(goal.getBounds().checkCollision(b)){
                return true;
            }
        }
        return false;
    }
    public boolean checkObstacleCollision(Bounds b){
        for(MapObject obstacle : obstacles){
            if(obstacle.getBounds().checkCollision(b)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tick'");
    }

    @Override
    public void render(Graphics g) {
        for(MapObject o : obstacles){
            o.render(g);
        }
        for(MapObject o : goals){
            o.render(g);
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
