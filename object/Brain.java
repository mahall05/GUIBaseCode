package object;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Brain {
    public Brain() {

    }

    public abstract int accelerate();
    public abstract void tick();
    public abstract void render(Graphics g);
}
