package object.map;

import java.awt.Graphics;
import java.awt.Point;

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

        goals = new MapObject[] {new MapObject(Main.WIDTH/2-25, 0, 50, 50, MapObject.Type.GOAL)};
        obstacles = new MapObject[] {new MapObject(Main.WIDTH/2-200, Main.HEIGHT/2-25, 400, 50, MapObject.Type.OBSTACLE)};
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

    public double maxFitness(){
        for(int i = 0; i < goals.length; i++){
            Point center = new Point((int)((goals[i].getX()+goals[i].getWidth())/2), (int)((goals[i].getY()+goals[i].getHeight())/2));

            // Calculate the maximum possible distance you could be from the goal so you can subtract from this to calculate an inverse fitness
            Math.max(
                Math.max(
                    Math.sqrt(Math.pow(center.getX() - 0, 2) + Math.pow(center.getY() - 0, 2)), 
                    Math.sqrt(Math.pow(center.getX() - Main.WIDTH, 2) + Math.pow(center.getY() - 0, 2))), 
                Math.max(
                    Math.sqrt(Math.pow(center.getX() - 0, 2) + Math.pow(center.getY() - Main.HEIGHT, 2)), 
                    Math.sqrt(Math.pow(center.getX() - Main.WIDTH, 2) + Math.pow(center.getY() - Main.HEIGHT, 2))
                    )
                    );
        }
    }
    
}
