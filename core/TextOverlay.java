package core;

import java.awt.Color;
import java.awt.Graphics;

import object.Bounds;
import object.GameObject;
import object.body.PopulationHandler;

public class TextOverlay extends GameObject {
    private PopulationHandler population;

    public TextOverlay(PopulationHandler population){
        super(0,0);
        this.population=population;
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'tick'");
    }

    @Override
    public void render(Graphics g) {
        // Generation, best distance, if win, last best steps

        g.setColor(Color.white);
        g.drawString("Generation: " + population.getGeneration(), Main.WIDTH-100, 20);
        g.drawString("Closest: " + (int) population.getMap().distNearestGoal(population.getGen().getBestBoy().getBounds()), Main.WIDTH-100, 40);
        try{
            g.drawString("Best Steps: " + population.updateBestSteps(), Main.WIDTH-100, 60);
        }catch(NullPointerException e){
            g.drawString("Best Steps: -1", Main.WIDTH-100, 60);
        }
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
