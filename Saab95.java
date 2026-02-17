import java.awt.*;

public final class Saab95 extends Car implements Movable, Towable{
    /** Initiate variables */
    private boolean turboOn;
    private Engine engine;

    /** Constructor for Saab95 */
    public Saab95(Color color, Double enginePower) {
        super(color, enginePower, 2, "Saab95", new Point(0,0), 0);
        engine.stopEngine();
    }
    /* Specific methods for SAAB95 */
        /** turbo get/set methods for saab */
    public boolean getTurboOn(){return turboOn;}

    public void setTurboOn(){turboOn = true;}

    public void setTurboOff(){turboOn = false;}

    /** Overrides the default function for speedFactor with SAAB95's own implementation */
    @Override
    protected double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return engine.getEnginePower() * 0.01 * turbo;
    }

}