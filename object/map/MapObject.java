package object.map;

import java.awt.Color;
import java.awt.Graphics;

import object.Bounds;
import object.GameObject;

public class MapObject extends GameObject{
    private int width, height;

    public enum Type{
        GOAL,
        OBSTACLE
    }

    public Type type;

    public MapObject(int x, int y, int width, int height, Type t){
        super(x,y);
        this.width=width;
        this.height=height;
        this.type=t;
        tick=false;
        mouseClickRegister=false;
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tick'");
    }

    @Override
    public void render(Graphics g) {
        g.setColor(type==Type.GOAL ? Color.RED : Color.BLUE);

        g.fillRect(x, y, width, height);
    }

    @Override
    public void mouseClick(int mx, int my) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClick'");
    }

    @Override
    public Bounds getBounds() {
        return new Bounds(x,y,width,height);
    }

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    
}
