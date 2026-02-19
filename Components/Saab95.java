package Components;

import java.awt.*;

public final class Saab95 extends Car implements Movable, Towable, Turbo {
    /** Initiate variables */
    private boolean turboOn;

    /** Constructor for Components.Saab95 */
    public Saab95(Color color, Double enginePower, Point coordinates) {
        super(color, enginePower, 2, "Saab95", coordinates, 0);
        this.engine.setTurboFactor(1.3);
        engine.stopEngine();
    }
    /* Specific methods for SAAB95 */
    public void setTurboOn(){this.engine.setTurboOn();}

    public void setTurboOff(){this.engine.setTurboOff();}
}