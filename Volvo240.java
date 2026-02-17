import java.awt.*;

public final class Volvo240 extends Car implements Movable, Towable{
    /** Initiate variables */
    private final static double trimFactor = 1.25;

    /** Constructor for Volvo240 */
    public Volvo240(Color color, Double enginePower){
        this.nrDoors = 4;
        this.color = color; //color.black
        //this.enginePower = enginePower; // 100
        this.engine = new Engine(100);
        this.modelName = "Volvo240";
        this.coordinates = new Point(0,0);
        this.direction = 0;
        engine.stopEngine();
    }

    /** Overrides the default function for speedFactor with SAAB95's own implementation */
    @Override
    protected double speedFactor() {return engine.getEnginePower() * 0.01 * trimFactor;}
}
