package input.key;

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
