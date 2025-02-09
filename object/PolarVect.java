package object;
import java.awt.Point;

public class PolarVect{
    private double distance;
    private double angle;

    public PolarVect(double distance, double angle){
        setDistance(distance);
        setAngle(angle);
    }

    private void setDistance(double distance){
        this.distance=distance;
    }
    private void setAngle(double angle){
        angle%=360;
        if(angle<0) angle += 360;
        this.angle=angle;
        return;
    }

    public double getDistance(){
        return distance;
    }
    public double getDegrees(){
        return angle;
    }
    public double getRadians(){
        return angle*Math.PI/180.0;
    }
}
