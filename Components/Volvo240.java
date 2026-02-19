package Components;

import java.awt.*;

public final class Volvo240 extends Car implements Movable, Towable {
    /** Initiate variables */
    private final static double trimFactor = 1.25;

    /** Constructor for Components.Volvo240 */
    public Volvo240(Color color, Double enginePower, Point coordinates) {
        super(color, enginePower,4,"Volvo240", coordinates, 0);
        this.engine.setTurboFactor(trimFactor);
        engine.stopEngine();
    }
}
