package object.body;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import core.Main;
import object.Bounds;
import object.GameObject;
import object.map.MapHandler;

public class PopulationHandler extends GameObject{

    private final boolean INCLUDE_PLAYER = false;
    private Body playerBody;

    private final int POPULATION_SIZE = 100;
    private Generation gen;
    private Generation lastGen;

    private final Point spawnPoint = new Point(Main.WIDTH/2, Main.HEIGHT-100);

    private MapHandler map;


    public PopulationHandler(MapHandler map){
        super(0,0);
        boundsMatter=false;
        mouseClickRegister=false;
        this.map=map;

        Main.get().getHandler().addObject(this);

        if(INCLUDE_PLAYER){
            playerBody = new Body(100,100,new Player(), map);
            Main.get().getCamera().setParent(playerBody);
            Main.get().getHandler().addObject(playerBody);
        }
        
        gen = new Generation(spawnPoint, POPULATION_SIZE, map);

    }

    public Player getPlayerBrain(){
        return (Player) playerBody.getBrain();
    }

    @Override
    public void tick() {
        if(gen.allDead()){

            createNextGen();

        }else{
            gen.tick();
        }
    }

    private void createNextGen(){
        lastGen=gen;
        Brain[] lastBrains = lastGen.calcFitness();
        double fitnessSum = 0;
        for(Brain b : lastBrains){
            fitnessSum+=b.getFitness();
        }

        Brain[] newBrains = new Brain[POPULATION_SIZE];
        for(int i = 0; i < newBrains.length; i++){
            double randNum = Main.randomDouble(0, fitnessSum);
            double runningSum = 0;

            for(int j = 0; j < lastBrains.length; j++){
                runningSum += lastBrains[j].getFitness();
                if(randNum < runningSum){
                    newBrains[i] = new NPC(lastBrains[j]);
                    break;
                }
            }
        }

        gen = new Generation(spawnPoint, newBrains, map);
        
        gen.mutate();
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
