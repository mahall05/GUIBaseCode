package object.body;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Brain {
    public Brain() {

    }

    public abstract Point accelerate();
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract boolean isAlive();

    public abstract void kill();
    public abstract void win();
}
