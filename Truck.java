import java.awt.*;

public abstract class Truck extends Car {

    protected boolean trailerSafe;

    protected Truck(Color color, Double enginePower, int nrDoors, String modelName) {
        this.currentSpeed = 0;
        this.color = color;
        this.enginePower = enginePower;
        this.coordinates = new Point(0, 0);
        this.direction = 0;
        this.nrDoors = nrDoors;
        this.modelName = modelName;
        this.engineOn = false;
    }

    @Override
    public void gas(double amount) {
        if (amount >= 0 && amount <= 1){
            if(this.getCurrentSpeed() < this.getEnginePower() && this.trailerSafe) {
                incrementSpeed(amount, this.engineOn);
                this.move();
            }
        }
        else {System.out.println("Throttle out of range or trailer is not safe to drive with");}
    }

    public void incrementSpeed(double amount) {this.incrementSpeed(amount, this.engineOn);}

    public void decrementSpeed(double amount){this.decrementSpeed(amount, this.engineOn);}

}
