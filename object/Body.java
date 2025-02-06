package object;

import java.awt.Color;
import java.awt.Graphics;

public class Body extends GameObject{
    private Brain brain;

    public Body(int x, int y, Brain brain) {
        super(x, y);
        this.brain=brain;
    }
    
    @Override
    public void tick() {
        velX+=brain.accelerate().getX();
        velY+=brain.accelerate().getY();

        if(velY>10)velY=10;
        if(velX>10)velX=10;

        if(velY<-10)velY=-10;
        if(velX<-10)velX=-10;

        x+=velX;
        y+=velY;

        if(velX!=0) velX = (Math.abs(velX)-1) * velX/Math.abs(velX);
        if(velY!=0) velY = (Math.abs(velY)-1) * velY/Math.abs(velY);
        System.out.println("X: "+velX+",   Y: "+velY);
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
