package object.body;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import object.Bounds;
import object.GameObject;
import object.map.MapHandler;

public class Body extends GameObject{
    private Brain brain;
    private MapHandler map;

    public static final int MAX_SPEED = 5;
    public final int SIZE = 10;
    private final int FRICTION = 2;

    public Body(int x, int y, Brain brain, MapHandler map) {
        super(x, y);
        this.brain=brain;
        this.map=map;
        brain.setBody(this);
    }
    public Body(Point p, Brain brain, MapHandler map){
        this((int) p.getX(), (int) p.getY(), brain, map);
    }
    
    @Override
    public void tick() {
        Point polarCoord = brain.accelerate();
        //System.out.println("Angle: " + angle);

        if(polarCoord.getY()!=999){
            velX+= polarCoord.getX() * Math.cos(polarCoord.getY()*Math.PI/180);
            velY+= polarCoord.getX() * -Math.sin(polarCoord.getY()*Math.PI/180);
        }

        if(velY>10)velY=10;
        if(velX>10)velX=10;

        if(velY<-10)velY=-10;
        if(velX<-10)velX=-10;

        if(Math.abs(velY)<MAX_SPEED*0.01) velY=0;
        if(Math.abs(velX)<MAX_SPEED*0.01) velX=0;

        //System.out.println("X: "+velX+",   Y: "+velY);

        x+=velX;
        y+=velY;

        if(velX!=0){
            if(Math.abs(velX)<FRICTION){
                velX=0;
            }else{
                velX = (Math.abs(velX)-FRICTION) * velX/Math.abs(velX);
            }
        } 
        if(velY!=0){
            if(Math.abs(velY)<FRICTION){
                velY=0;
            }else{
                velY = (Math.abs(velY)-FRICTION) * velY/Math.abs(velY);
            }
        }
        
        brain.tick();

        boolean goalCollision = map.checkGoalCollision(this.getBounds());
        boolean obstacleCollision = map.checkObstacleCollision(this.getBounds()) || map.checkOutOfBounds(this.getBounds());

        if(goalCollision){
            brain.win();
            brain.kill();
        }
        else if(obstacleCollision){
            brain.kill();
        }
    }

    public void render(Graphics g, boolean closest) {
        g.setColor(brain.isAlive() ? brain.isPlayer() ? Color.CYAN : ((NPC) brain).isBest() ? Color.RED : closest ? Color.ORANGE : Color.GREEN : Color.BLACK);
        g.fillOval(x, y, SIZE * (!brain.isPlayer() && ((NPC) brain).isBest() ? 2 : 1), SIZE * (!brain.isPlayer() && ((NPC) brain).isBest() ? 2 : 1));
        brain.render(g);
    }

    @Override
    public void render(Graphics g){

    }

    @Override
    public void mouseClick(int mx, int my) {
        
    }

    @Override
    public Bounds getBounds() {
        return new Bounds(x,y,SIZE,SIZE);
    }

    public Brain getBrain(){
        return brain;
    }

    public boolean isAlive(){
        return brain.isAlive();
    }

    public double distNearestGoal(){
        return map.distNearestGoal(this.getBounds());
    }

    public MapHandler getMap(){
        return map;
    }
    
}
