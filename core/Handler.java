package core;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;

import object.GameObject;

public class Handler {
	
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	

	public void tick() {
		try{
			for(GameObject o : objects){
				if(/*o.isActive()&&*/o.tick) o.tick();
			}
		}catch(ConcurrentModificationException e){
			System.out.println("WARNING: tick lost due to game object removal");
		}
	}
	
	public void render(Graphics g) {
        for(GameObject o : objects){
            if(/*o.isActive()&&*/o.render) o.render(g);
        }
	}

	
	public void addObject(GameObject o) {
		objects.add(o);
	}
	public void addObjects(ArrayList<GameObject> os){
		for(GameObject o : os){
			objects.add(o);
		}
	}
	
	public void removeObject(GameObject o) {
		objects.remove(o);
	}
	public void removeObjects(ArrayList<GameObject> os){
		for(GameObject o : os){
			objects.remove(o);
		}
	}

	public void mouseClick(int mx, int my){
		for(GameObject o : objects){
			if(o.boundsMatter && o.mouseClickRegister && o.getBounds().isWithin(mx,my) && o.isActive()) o.mouseClick(mx, my);
		}
	}

}
