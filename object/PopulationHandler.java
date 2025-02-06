package object;

import java.awt.Point;
import java.util.ArrayList;

import core.Main;

public class PopulationHandler {
    private ArrayList<Body> npcs = new ArrayList<Body>();
    private Body playerBody;

    private final int POPULATION_SIZE = 100;

    private final Point spawnPoint = new Point(Main.WIDTH/2, Main.HEIGHT-100);


    public PopulationHandler(){
        playerBody = new Body(100,100,new Player());
        
        for(int i = 0; i < POPULATION_SIZE; i++){
            npcs.add(new Body(spawnPoint, new NPC(1000)));
        }

        Main.get().getCamera().setParent(playerBody);
        Main.get().getHandler().addObject(playerBody);
        for(Body npc : npcs){
            Main.get().getHandler().addObject(npc);
        }
    }

    public Player getPlayerBrain(){
        return (Player) playerBody.getBrain();
    }
}
