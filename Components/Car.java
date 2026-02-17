package Components;

import java.awt.*;

public abstract class Car extends Vehicle {
    // Initiate variables
    public int nrDoors;
    public String modelName;
    public Engine engine;

    public Car(Color color, Double enginePower, int nrDoors, String modelName, Point coordinates, double direction) {
        // All vehicles have color and a position
        super(color, coordinates, direction);
        this.engine = new Engine(enginePower);
        this.nrDoors = nrDoors;
        this.modelName = modelName;
    }

    /** Get the number of doors a car has */
    public int getNrDoors(){
        return nrDoors;
    }

    /** Get the model name of a car */
    public String getModelName(){
        return modelName;
    }

    /** Accelerate by pressing the gas pedal the car */
    public void gas(double amount){
        if(this.getCurrentSpeed() < engine.getEnginePower()){
            updateEngineSpeed(amount);
            this.move();
        }
        else {System.out.println("Can't push down pedal because it's already on the floor (Throttle is out of range.)");}
    }

    /** Decelerate by braking the car */
    public void brake(double amount){
        if(this.getCurrentSpeed() > 0){
            updateEngineSpeed(-amount);
            this.move();
        }
        else {System.out.println("It's either through the floor or  (Brake is out of range.)");}
    }

    /** Changes the speed of the vehicle */
    protected void updateEngineSpeed(double amount) {
        if (Math.abs(amount) >= 0 && Math.abs(amount) <= 1){
            currentSpeed += engine.calculateSpeed((amount));
        }
    }

    /** Does something different for each car type */
    protected abstract double speedFactor();
}