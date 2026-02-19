import Components.Car;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

import Components.Volvo240;
import Components.Workshop;
import Components.Vehicle;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();
    ArrayList<Workshop> workshops = new ArrayList<>();
    ArrayList<DrawPanel> drawObjects = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Components.Volvo240(Color.BLUE, 120.0));
        cc.cars.add(new Components.Saab95(Color.BLUE, 120.0));
        cc.workshops.add(new Components.Workshop<Volvo240>(3, "VolvoWorkshop")); //Maybe should be VolvoBrand
        cc.workshops.add(new Components.Workshop<Volvo240>(3, "VolvoBrand"));

        for (Car car : cc.cars){
            cc.drawObjects.add(new DrawPanel(0,100,car.getModelName()));
        }

        for (Workshop workshop : cc.workshops){
            cc.drawObjects.add(new DrawPanel(0,100, workshop.getModelName()));
        }
        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (DrawPanel panel : drawObjects) {
                for (Car car : cars) {
                    if (Objects.equals(panel.getModelName(), car.getModelName())) {
                        car.move();
                        int x = (int) Math.round(car.getCoordinates().x);
                        int y = (int) Math.round(car.getCoordinates().y);
                        frame.panel.moveit(x, y);
                        // repaint() calls the paintComponent method of the panel
                        frame.panel.repaint();
                    }
                }
            }
        }
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
            if (car instanceof Components.Saab95) {
                ((Components.Saab95) car).setTurboOn();
            }
        }
    }

    void turboOff() {
        for (Car car : cars) {
            if (car instanceof Components.Saab95) {
                ((Components.Saab95) car).setTurboOff();
            }
        }
    }

    void turnLeft() {
        for (Car car : cars) {car.turnLeft();}}

    void turnRight() {
        for (Car car : cars) {car.turnRight();}}

    double getCurrentSpeed() {
        for (Car car : cars) {
            if (car instanceof Components.Saab95) {
                return ((Components.Saab95) car).getCurrentSpeed();
            }
        }
        return 0;
    }

    void addVehicleToWorkshop(Workshop workshop) {
        for (Car car : cars) {
            double distX = Math.abs(workshop.coordinates.getX() - car.getCoordinates().getX());
            double distY = Math.abs(workshop.coordinates.getY() - car.getCoordinates().getY());
            if (distX < 10 && distY < 10) {
                boolean answer = workshop.addCarToWorkshop(car);
                if (answer) {
                    car.forcePosition(new Point(300, 300));
                }
            }
        }
    }

    public double getGeoDistance(Vehicle v1, Vehicle v2){
        double distX = Math.abs(v1.coordinates.getX() - v2.coordinates.getX());
        double distY = Math.abs(v1.coordinates.getY() - v2.coordinates.getY());
        return Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2));
    }


    void addVehicleToWorkshop(Car car) {
        for(Workshop shop : workshops){
            if (shop instanceof Workshop){
                shop.addCarToWorkshop(car);
            }
        }
    }
}