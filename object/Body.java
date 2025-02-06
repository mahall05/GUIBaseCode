package object;

import java.awt.Color;
import java.awt.Graphics;

public class Body extends GameObject{
    private Brain brain;

    private static final int MOVE_SPEED = 3;

    public Body(int x, int y, Brain brain) {
        super(x, y);
        this.brain=brain;
    }
    
    @Override
    public void tick() {

        int angle = brain.accelerate();

        if(angle!=999){
            velX+= MOVE_SPEED * Math.cos(angle*Math.PI/180);
            velY+= MOVE_SPEED * -Math.sin(angle*Math.PI/180);
        }

        if(velY>10)velY=10;
        if(velX>10)velX=10;

        if(velY<-10)velY=-10;
        if(velX<-10)velX=-10;

        x+=velX;
        y+=velY;

        if(velX!=0) velX = (Math.abs(velX)-1) * velX/Math.abs(velX);
        if(velY!=0) velY = (Math.abs(velY)-1) * velY/Math.abs(velY);
        System.out.println("X: "+x+",   Y: "+y);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(x, y, 20, 20);
    }

    @Override
    public void mouseClick(int mx, int my) {
        
    }

    @Override
    public Bounds getBounds() {
        return new Bounds(x,y,20,20);
    }

    public Brain getBrain(){
        return brain;
    }
    
}
