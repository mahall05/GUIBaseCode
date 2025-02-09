package object.body;

import java.awt.Graphics;
import java.awt.Point;

import core.Main;
import object.PolarVect;

public class NPC extends Brain{
    private boolean best;

    public NPC(int brainSize){
        super(brainSize);
        isPlayer=false;
        best=false;
    }
    public NPC(Brain b, boolean best){
        super(b);

        this.best=best;
        isPlayer=false;
    }

    /**
     * Will only run when the dot is alive
     * If dot is a copy, then move in the way it is supposed to. Otherwise, randomize a new step
     */
    @Override
    protected PolarVect move() {
        return steps[step] == null ? randomVector() : steps[step];
    }

    @Override
    public void mutate(double rate) {
        for(int i = 0; i < steps.length; i++){
            if(steps[i]!=null){
                double rand = Main.randomDouble(0, 1.0);

                if(rand<rate){
                    steps[i] = randomVector();
                }
            }
        }
    }

    private PolarVect randomVector(){
        return new PolarVect(Main.randomInt(0, Body.MAX_SPEED), Main.randomInt(0, 360));
    }

    public boolean isBest(){
        return best;
    }
}
