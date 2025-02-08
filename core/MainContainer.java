package core;
import input.key.KeyInput;
import object.body.PopulationHandler;
import object.map.MapHandler;

import java.awt.event.KeyEvent;

public class MainContainer {

    public PopulationHandler population;
    public MapHandler map;

    public MainContainer(){
        map = new MapHandler();
        population = new PopulationHandler(map);
        Main.get().setOverlay(new TextOverlay(population));


        // Leave at the end
        registerKeys();
        registerMouseActions();
    }

    /**
     * Use this method to register your keys. Use {@link KeyInput#register(int, Runnable, Runnable)}
     * It is recommended to use {@link KeyEvent} constants for key integers
     */
    public void registerKeys(){
        //KeyInput.register(KeyEvent.VK_W, ()-> population.getPlayerBrain().moveUp(true), ()-> population.getPlayerBrain().moveUp(false));
        //KeyInput.register(KeyEvent.VK_S, ()-> population.getPlayerBrain().moveDown(true), ()-> population.getPlayerBrain().moveDown(false));
        //KeyInput.register(KeyEvent.VK_A, ()-> population.getPlayerBrain().moveLeft(true), ()-> population.getPlayerBrain().moveLeft(false));
        //KeyInput.register(KeyEvent.VK_D, ()-> population.getPlayerBrain().moveRight(true), ()-> population.getPlayerBrain().moveRight(false));
    }

    public void registerMouseActions(){

    }
}
