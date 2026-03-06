import Components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SimulationEngine implements Subject {

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private static final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    private static CarController cc;
    private static CarView frame;

    // static observer list to manage observers for the SimulationEngine
    private static ArrayList<Observer> observers = new ArrayList<Observer>();

    public void main(String[] args) {
        // Instance of this class

        cc = new CarController();
        SimulationEngine engine = new SimulationEngine();
        engine.registerObserver(cc);

        //SimulationEngine.registerObserver(cc);

        frame = new CarView("-... .- -- ... . ... lekstuga",cc);

        cc.frame = frame;

        // Start the timer
        this.timer.start();
    }


    /*
    Each step the TimerListener is just a tick source, each tick it tells
    CarController to update the cars and then tells the view to update its images.
    */
    private class TimerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            notifyObservers();

            for (Workshop workshop : cc.getWorkshops()) {
                checkAddCarToWorkshop(workshop);
                checkRemoveCarFromWorkshop(workshop);
            }
        }
    }

    private void checkAddCarToWorkshop(Workshop workshop) {
        for (Car car : cc.getCarList()){
            double distX = Math.abs(workshop.getCoordinates().getX() - car.getCoordinates().getX());
            double distY = Math.abs(workshop.getCoordinates().getY() - car.getCoordinates().getY());

            if (distX < workshop.getCatchRadius() && distY < workshop.getCatchRadius()) {
                boolean added = workshop.addCarToWorkshop(car);
                if (added) {
                    car.forcePosition(new Point(
                            (int)workshop.getCoordinates().getX(),
                            (int)workshop.getCoordinates().getY()
                    ));
                }
            }
        }
    }

    private void checkRemoveCarFromWorkshop(Workshop workshop) {
        for (Car car : cc.getCarList()){
            if(workshop.getCarsInWorkshop().contains(car)){
                double distX = Math.abs(workshop.getCoordinates().getX() - car.getCoordinates().getX());
                double distY = Math.abs(workshop.getCoordinates().getY() - car.getCoordinates().getY());

                if (distX > 2 || distY > 2) {
                    workshop.removeCarFromWorkshop(car);
                }
            }
        }
    }

//    // A static Subject proxy representing SimulationEngine for observers' update(Subject s)
//    private static final Subject ENGINE_SUBJECT = new Subject() {
//
//        @Override
//        public void registerObserver(Observer o) {
//            SimulationEngine.registerObserver(o);
//        }
//
//        @Override
//        public void removeObserver(Observer o) {
//            SimulationEngine.removeObserver(o);
//        }
//
//        @Override
//        public void notifyObservers() {
//            SimulationEngine.notifyObservers();
//        }
//    };
//
//    public static Subject getEngineSubject() {
//        return ENGINE_SUBJECT;
//    }
    public void registerObserver(Observer o) {
        observers.add(o);
    }
    public void removeObserver(Observer o) {
        observers.remove(o);
    }
    public void notifyObservers() {
        for (Observer o: observers) {
            o.update(this); // pass the proxy instead of the actual SimEngine (`this`)
        }
    }
}
