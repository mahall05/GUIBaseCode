package object;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import core.Main;

public class PopulationHandler extends GameObject{

    private final boolean INCLUDE_PLAYER = false;
    private Body playerBody;

    private final int POPULATION_SIZE = 100;
    private Generation gen;
    private Generation lastGen;

    private final Point spawnPoint = new Point(Main.WIDTH/2, Main.HEIGHT-100);


    public PopulationHandler(){
        super(0,0);
        boundsMatter=false;
        mouseClickRegister=false;

        Main.get().getHandler().addObject(this);

        if(INCLUDE_PLAYER){
            playerBody = new Body(100,100,new Player());
            Main.get().getCamera().setParent(playerBody);
            Main.get().getHandler().addObject(playerBody);
        }
        
        gen = new Generation(spawnPoint, POPULATION_SIZE);

    }

    public Player getPlayerBrain(){
        return (Player) playerBody.getBrain();
    }

    @Override
    public void tick() {
        if(gen.allDead()){
            lastGen=gen;
            gen=new Generation(spawnPoint, POPULATION_SIZE);
        }else{
            gen.tick();
        }
    }

    @Override
    public void render(Graphics g) {
        gen.render(g);
    }

    @Override
    public void mouseClick(int mx, int my) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClick'");
    }

    @Override
    public Bounds getBounds() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBounds'");
    }
}
