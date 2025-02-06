import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
    private ArrayList<KeyLink> keyChain = new ArrayList<KeyLink>();
	
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
	
    /*
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i ++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				if(key == KeyEvent.VK_W) handler.setUp(true);
				if(key == KeyEvent.VK_S) handler.setDown(true);
				if(key == KeyEvent.VK_A) handler.setLeft(true);
				if(key == KeyEvent.VK_D) handler.setRight(true);
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
		
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i ++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				if(key == KeyEvent.VK_W) handler.setUp(false);
				if(key == KeyEvent.VK_S) handler.setDown(false);
				if(key == KeyEvent.VK_A) handler.setLeft(false);
				if(key == KeyEvent.VK_D) handler.setRight(false);
			}
		}
	}
    */

    public class KeyLink{
        private int key;
        private Runnable onPress, onRelease;

        public KeyLink(int key, Runnable onPress, Runnable onRelease){
            this.key=key;
            this.onPress=onPress;
            this.onRelease=onRelease;
        }

        public int getKey(){
            return key;
        }
        public Runnable onPress(){
            return onPress;
        }
        public Runnable onRelease(){
            return onRelease;
        }
    }

}
