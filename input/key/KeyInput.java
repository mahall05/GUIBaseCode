package input.key;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KeyInput extends KeyAdapter {
	
    //private static ArrayList<KeyLink> keyChain = new ArrayList<KeyLink>();
    public static Map<Integer, Boolean> keyChain = new HashMap<Integer, Boolean>();
	
	public KeyInput() {
        keyChain.put(KeyEvent.VK_W, false);
        keyChain.put(KeyEvent.VK_S, false);
        keyChain.put(KeyEvent.VK_D, false);
        keyChain.put(KeyEvent.VK_A, false);
	}

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        keyChain.put(key, true);
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        keyChain.put(key, false);
    }
}
