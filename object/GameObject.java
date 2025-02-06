package object;
import java.awt.Graphics;
import java.awt.Rectangle;

import core.Main;

public abstract class GameObject {
	
	protected int x, y;
	protected float velX = 0, velY = 0;
    protected long id;
	protected Bounds bounds;
	protected boolean active;
	
	public GameObject(int x, int y) {
        this(x,y,Main.randomLong(0L, 9223372036854775807L));
	}
    public GameObject(int x, int y, long id){
        setX(x);
        setY(y);
        setId(id);
    }

	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract void mouseClick(int mx, int my);

	public Bounds getBounds(){
		return bounds;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
        if(id<0L){
            this.id = 0L;
        }else{
		    this.id = id;
        }
	}

	

	public void setX(int x) {
        if(x<0){
            this.x=0;
        }else{
		    this.x = x;
        }
	}

	public void setY(int y) {
		if(y<0){
            this.y = 0;
        }else{
            this.y = y;
        }
	}


	public void setVelX(float velX) {
		this.velX = velX;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}


    public int getX() {
		return x;
	}

    public int getY() {
		return y;
	}


    public float getVelX() {
		return velX;
	}

    public float getVelY() {
		return velY;
	}

	public boolean isActive(){
		return active;
	}
	
	
}
