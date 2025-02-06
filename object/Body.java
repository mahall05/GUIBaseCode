package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Body extends GameObject{
    private Brain brain;

    public static final int MAX_SPEED = 5;
    public final int SIZE = 10;

    public Body(int x, int y, Brain brain) {
        super(x, y);
        this.brain=brain;
    }
    public Body(Point p, Brain brain){
        this((int) p.getX(), (int) p.getY(), brain);
    }
    
    @Override
    public void tick() {
        brain.tick();

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

        if(Math.abs(velY)<MAX_SPEED*0.1) velY=0;
        if(Math.abs(velX)<MAX_SPEED*0.1) velX=0;

        //System.out.println("X: "+velX+",   Y: "+velY);

        x+=velX;
        y+=velY;

        if(velX!=0) velX = (Math.abs(velX)-2) * velX/Math.abs(velX);
        if(velY!=0) velY = (Math.abs(velY)-2) * velY/Math.abs(velY);
        
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(x, y, SIZE, SIZE);
        brain.render(g);
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
    
}
