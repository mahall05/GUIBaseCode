package core;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

import object.GameObject;

public class Handler {
	
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	

	public void tick() {
        for(GameObject o : objects){
            o.tick();
        }
	}
	
	public void render(Graphics g) {
        for(GameObject o : objects){
            o.render(g);
        }
	}

	
	public void addObject(GameObject o) {
		objects.add(o);
	}
	
	public void removeObject(GameObject o) {
		objects.remove(o);
	}

	public void mouseClick(int mx, int my){
		for(GameObject o : objects){
			if(o.getBounds().isWithin(mx,my) && o.isActive()) o.mouseClick(mx, my);
		}
	}

}
