package object.body;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Brain {
    protected boolean winner = false;
    protected boolean alive = true;
    public Brain() {

    }

    public abstract Point accelerate();
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract boolean isAlive();

    public void kill(){
        alive=false;
    }
    public void win(){
        winner=true;
    }
}
