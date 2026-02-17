import java.awt.*;

public abstract class Truck extends Car {

    protected boolean trailerSafe;

    protected Truck(Color color, Double enginePower, int nrDoors, String modelName) {
        this.currentSpeed = 0;
        this.color = color;
        // this.enginePower = enginePower;
        this.engine = new Engine(enginePower);
        this.coordinates = new Point(0, 0);
        this.direction = 0;
        this.nrDoors = nrDoors;
        this.modelName = modelName;
        engine.stopEngine();
    }

    @Override
    public void gas(double amount) {
        if (amount >= 0 && amount <= 1){
            if(this.getCurrentSpeed() < engine.getEnginePower() && this.trailerSafe) {
                incrementSpeed(amount, engine.isEngineOn());
                this.move();
            }
        }
        else {System.out.println("Throttle out of range or trailer is not safe to drive with");}
    }

    public void incrementSpeed(double amount) {this.incrementSpeed(amount, engine.isEngineOn());}

    public void decrementSpeed(double amount){this.decrementSpeed(amount, engine.isEngineOn());}

}
