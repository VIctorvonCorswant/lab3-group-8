
package Components;
import java.awt.*;
import java.util.ArrayList;

public class Movement implements Subject {
    protected Point coordinates;
    protected double direction;
    protected double currentSpeed;
    protected ArrayList<Observer> observers;

    public Movement(Point coordinates, double direction) {
        this.coordinates = coordinates;
        this.direction = direction;
        this.currentSpeed = 0;
        this.observers = new ArrayList<>();
    }

    public void changePosition() {
        this.getCoordinates().x += (int) (Math.cos(this.getDirection()) * getCurrentSpeed());
        this.getCoordinates().y += (int) (Math.sin(this.getDirection()) * getCurrentSpeed());
        this.notifyObservers();
    }

    public double getCurrentSpeed() {return currentSpeed;}

    protected double setCurrentSpeed(double speed) {;return this.currentSpeed = speed;}

    public Point getCoordinates() {return this.coordinates;}

    protected void setCoordinates(Point coordinates) {}

    public  double getDirection() {return direction;}

    protected void setDirection(double direction) {this.direction = direction;}


    @Override
    public void registerObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer: observers) {
            observer.update();
        }
    }

    @Override
    public void removeObserver(Observer o) {
        this.observers.remove(o);
    }
}
