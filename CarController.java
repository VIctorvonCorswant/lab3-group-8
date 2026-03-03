import Components.*;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* Its responsibilities is to make the buttons clickable and relay command to appropriate functionss
 */

public class CarController {
    //member fields
    // The frame that represents this instance View of the MVC pattern
    CarView frame;

    // A list of cars, modify if needed
    ArrayList<Car> cars;
    ArrayList workshops;

    public CarController() {
    // Add more cars in the constructor

        // Start a new view and send a reference of self
        frame = new CarView("CarSim 1.0", this);

        this.cars = new ArrayList<>();
        this.cars.add(new Components.Volvo240(Color.BLUE, 120.0, new Point (0, 0)));
        
        this.workshops = new ArrayList<Workshop>();
        this.workshops.add(workshops.add(new Components.Workshop<Volvo240>(Volvo240.class,3, "VolvoBrand", new Point(300, 200))));
    }


/*
    cars.add(new Components.Volvo240(Color.BLUE, 120.0, new Point (0, 0)));
    cars.add(new Components.Volvo240(Color.BLUE, 120.0, new Point (200, 0)));
    cars.add(new Components.Saab95(Color.BLUE, 120.0, new Point (0, 100)));

    cars.add(new Components.Scania(Color.BLUE, 240.0, new Point (0, 200)));
    workshops.add(new Components.Workshop<Volvo240>(Volvo240.class,3, "VolvoBrand", new Point(300, 200))); //VolvoBrand
    workshops.add(new Components.Workshop<>(Car.class,3, "VolvoWorkshop", new Point(200, 350))); //Our Volvo Workshop
*/


    public ArrayList<Car> getCarList() {
        return cars;
    }
    public ArrayList<Workshop> getWorkshops() {
        return workshops;
    }

    public void moveObject(int x, int y){
        frame.moveObject(x, y);
    }

    public Point getPanelDim(){
        return new Point(frame.getWidth(),frame.getHeight());
    }

    void startEngines() {
        for (Car car : cars) {car.engine.startEngine();}}

    void stopEngines() {
        for (Car car : cars) {car.engine.stopEngine();}}

    // Calls the gas method for each car once
    void gas(double amount) {
        double gas = (amount) / 100;
        for (Car car : cars) {
            car.gas(gas);
        }
    }

    void brake(double amount) {
        double brake = (amount) / 100;
        for (Car car : cars) {
            car.brake(brake);
        }
    }

    void turboOn() {
        for (Car car : cars) {
            if (car instanceof Turbo) {
                ((Turbo) car).setTurboOn();
            }
        }
    }

    void turboOff() {
        for (Car car : cars) {
            if (car instanceof Turbo) {
                ((Turbo) car).setTurboOff();
            }
        }
    }

    void turnLeft() {
        for (Car car : cars) {car.turnLeft();}}

    void turnRight() {
        for (Car car : cars) {car.turnRight();}}

    void lowerTrailer() {
        for (Car car : cars)
        {
            if(car instanceof Components.Scania){
                ((Scania) car).lowerBed(35.0);
            }
        }
    }
    void raiseTrailer() {
        for (Car car : cars)
        {
            if(car instanceof Components.Scania){
                ((Scania) car).raiseBed(35.0);
            }
        }
    }

    double getCurrentSpeed() {
        for (Car car : cars) {
            if (car instanceof Components.Saab95) {
                return ((Components.Saab95) car).getCurrentSpeed();
            }
        }
        return 0;
    }

}