package object.map;

import java.awt.Graphics;
import java.awt.Point;

import core.Main;
import object.Bounds;
import object.GameObject;

public class MapHandler extends GameObject{
    private MapObject[] obstacles;
    private MapObject[] goals;

    private double maxDistance;

    public Point[] mapCorners;

    public MapHandler(){
        super(0,0);
        tick=false;
        mouseClickRegister=false;
        boundsMatter=false;

        Main.get().getHandler().addObject(this);

        goals = new MapObject[] {new MapObject(Main.WIDTH/2-25, 0, 50, 50, MapObject.Type.GOAL)};
        obstacles = new MapObject[] {
            new MapObject(Main.WIDTH/2-200, Main.HEIGHT/2+50, 400, 25, MapObject.Type.OBSTACLE),
            new MapObject(0, Main.HEIGHT/2-75, 350, 25, MapObject.Type.OBSTACLE),
            new MapObject(Main.WIDTH-350, Main.HEIGHT/2-75, 350, 25, MapObject.Type.OBSTACLE),
            new MapObject(Main.WIDTH/2-200, Main.HEIGHT/2-200, 400, 25, MapObject.Type.OBSTACLE)
        };

        mapCorners = new Point[] {
            new Point(0,0),
            new Point(Main.WIDTH, 0),
            new Point(0, Main.HEIGHT),
            new Point(Main.WIDTH, Main.HEIGHT)
        };

        maxDistance = maxFitness();
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
    public boolean checkOutOfBounds(Bounds b){
        Point[] corners = b.getCorners();
        for(Point p : corners){
            if(p.getX() < 0 || p.getX()>Main.WIDTH || p.getY()<0 || p.getY()>Main.HEIGHT){
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
        double maxDistance = Double.MIN_VALUE;
        for(int i = 0; i < goals.length; i++){
            maxDistance = Math.max(maxDistance,
                Math.max(
                    Math.max(
                        goals[i].getBounds().getCenter().distance(mapCorners[0]),
                        goals[i].getBounds().getCenter().distance(mapCorners[1])
                    ),
                    Math.max(
                        goals[i].getBounds().getCenter().distance(mapCorners[2]),
                        goals[i].getBounds().getCenter().distance(mapCorners[3])
                    )
                )
            );
        }

        return maxDistance;
    }

    public double distNearestGoal(Bounds b){
        double minDistance = Double.MAX_VALUE;
        for(int i = 0; i < goals.length; i++){
            minDistance = Math.min(minDistance, goals[i].getBounds().getCenter().distance(b.getCenter()));
            
        }

        return minDistance;
    }

    public double getMaxDistance(){
        return maxDistance;
    }
    
}
