package input.key;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Handler;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
    private static ArrayList<KeyLink> keyChain = new ArrayList<KeyLink>();
	
	public KeyInput(Handler handler) {
		this.handler = handler;
		
	}

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        for(KeyLink link : keyChain){
            if(key == link.getKey()) link.onPress();
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        for(KeyLink link : keyChain){
            if(key == link.getKey()) link.onRelease();
        }
    }

    public static void register(int key, Runnable onPress, Runnable onRelease){
        keyChain.add(new KeyLink(key, onPress, onRelease));
    }

}
