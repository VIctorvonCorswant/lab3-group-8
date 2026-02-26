package Components;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public abstract class Car extends Vehicle {
    // Initiate variables and inheritance
    public Engine engine;

    public Car(Color color, Double enginePower, int nrDoors, String modelName, Point coordinates, double direction) {
        // All vehicles have color and a position
        super(coordinates, direction, nrDoors, modelName, color);
        this.engine = new Engine(enginePower);
    }

    public Image getImage()  {
        try {
            return ImageIO.read(new File("pics/modelName.jpg".replace("modelName", this.attribute.getModelName())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** Get the number of doors a car has */
    public int getNrDoors(){this.attribute.getNrDoors();} //Varför gnäller denna :sob:

    /** Get the model name of a car */
    public String getModelName(){
        return this.attribute.getModelName();
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
            this.movement.setCurrentSpeed(this.movement.getCurrentSpeed() + engine.calculateSpeed((amount)));
        }
    }
}