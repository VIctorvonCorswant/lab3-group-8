package Components;

import java.awt.*;

public abstract class Truck extends Car {

    protected boolean trailerSafe;

    protected Truck(Color color, Double enginePower, int nrDoors, String modelName, Point coordinates) {
        super(color, enginePower, nrDoors, modelName, coordinates, 0);
        engine.stopEngine();
    }

    @Override
    public void gas(double amount) {
        if (this.getCurrentSpeed() < engine.getEnginePower() && this.trailerSafe) {
            updateEngineSpeed(amount);
            this.move();
        } else {
            System.out.println("Throttle out of range or trailer is not safe to drive with");
        }
    }
}
