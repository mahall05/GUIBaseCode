package object;

import java.util.ArrayList;

import core.Main;

public class PopulationHandler {
    private ArrayList<Body> npcBodies = new ArrayList<Body>();
    private Body playerBody;


    public PopulationHandler(){
        playerBody = new Body(100,100,new Player());
        Main.get().getCamera().setParent(playerBody);

        Main.get().getHandler().addObject(playerBody);
    }

    public Player getPlayerBrain(){
        return (Player) playerBody.getBrain();
    }
}
