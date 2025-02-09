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

    private final int POPULATION_SIZE = 1000;
    private Generation gen;
    private Generation lastGen;

    private final Point spawnPoint = new Point(Main.WIDTH/2, Main.HEIGHT-100);

    private MapHandler map;

    private int generation=1;
    private int winningSteps = -1;


    public PopulationHandler(MapHandler map){
        super(0,0);
        boundsMatter=false;
        mouseClickRegister=false;
        this.map=map;

        Main.get().getHandler().addObject(this);

        if(INCLUDE_PLAYER){
            playerBody = new Body(100,100,new Player(gen.BRAIN_SIZE), map);
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
            generation++;
            updateBestSteps();

        }else{
            gen.tick();
        }
    }

    private void createNextGen(){
        lastGen=gen;
        Brain[] lastBrains = lastGen.getBrains();
        double fitnessSum = 0;
        for(Brain b : lastBrains){
            fitnessSum+=b.getMaxFitness();
        }

        Brain[] newBrains = new Brain[POPULATION_SIZE];

        double maxFitness=Double.MIN_VALUE;
        int maxIndex = -1;

        for(int i = 0; i < lastBrains.length; i++){
            if(lastBrains[i].getMaxFitness() > maxFitness){
                maxFitness=lastBrains[i].getMaxFitness();
                maxIndex=i;
            }
        }

        newBrains[0]= lastBrains[maxIndex];
        //((NPC) newBrains[0]).setBest(true);

        for(int i = 1; i < newBrains.length; i++){
            double randNum = Main.randomDouble(0, fitnessSum);
            double runningSum = 0;

            for(int j = 0; j < lastBrains.length; j++){
                runningSum += lastBrains[j].getMaxFitness();
                if(randNum < runningSum){
                    newBrains[i] = lastBrains[j];
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

    public int getGeneration(){
        return generation;
    }

    public Generation getGen(){
        return gen;
    }

    public MapHandler getMap(){
        return map;
    }

    public int updateBestSteps(){
        int min = Integer.MAX_VALUE;

        for(Body b : lastGen.getBodies()){
            if(((NPC) b.getBrain()).isWinner() && ((NPC) b.getBrain()).getStep() < min){
                min = ((NPC) b.getBrain()).getStep();
            }
        }

        if(min!=Integer.MAX_VALUE) winningSteps=min;
        return winningSteps;
    }


}
