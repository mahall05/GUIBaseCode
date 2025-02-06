package input.mouse;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import core.Handler;
import core.Main;
import object.GameObject;

public class MouseInput extends MouseAdapter {
	
	private Main main;
	
	public MouseInput(Main main) {
		this.main = main;

	}

	public void mousePressed(MouseEvent e){
		int mx = (int) (e.getX() + main.getCamera().getX());
		int my = (int) (e.getY() + main.getCamera().getY());

		main.getHandler().mouseClick(mx,my);
	}

}
