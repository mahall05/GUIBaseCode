import input.key.KeyInput;
import java.awt.event.KeyEvent;

public class MainContainer {

    /**
     * Use this method to register your keys. Use {@link KeyInput#register(int, Runnable, Runnable)}
     * It is recommended to use {@link KeyEvent} constants for key integers
     */
    public void registerKeys(){
        KeyInput.register(KeyEvent.VK_P, this::test, this::test);
    }

    public void registerMouseActions(){

    }

    private void test(){

    }
}
